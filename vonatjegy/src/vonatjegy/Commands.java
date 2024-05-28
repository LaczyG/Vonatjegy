package vonatjegy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A parancsokhoz tartozó metódusokat tartalmazza.
 */
public class Commands {
	/**
	 * Ez a metódus fájlba menti a vonatokat, soronként vannak a vonatok.
	 * 
	 * @param vonatok  egy ArrayList Train típusú elemekkel.
	 * @param fileName a fájl neve, ahova menteni kell.
	 */
	public static void save(ArrayList<Train> vonatok, String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Train vonat : vonatok) {
				String line = vonat.getVonatszam() + ";";
				for (TrainCar kocsi : vonat.getKocsik()) {
					line += kocsi.getKocsiszam() + " " + kocsi.getHely() + " " + kocsi.getOsztaly() + " ";
				}
				line += ";";
				for (Departure indulas : vonat.getIndulasi()) {
					line += indulas.getIdo() + " " + indulas.getHely() + " ";
				}
				line += ";";
				for (Arrival erkezes : vonat.getErkezesi()) {
					line += erkezes.getIdo() + " " + erkezes.getHely() + " " + erkezes.getKeses() + " ";
				}
				line += ";";
				if (vonat.getFoglalasok().size() != 0) {
					for (Reservation foglalas : vonat.getFoglalasok()) {
						TrainCar kocsi = foglalas.getKocsi();
						line += kocsi.getKocsiszam() + " " + kocsi.getHely() + " " + kocsi.getOsztaly() + " "
								+ foglalas.getIndulasiHely() + " " + foglalas.getErkezesiHely() + " ";
					}
				}
				line += ";";
				bw.write(line);
				bw.newLine();
			}
			System.out.println("Sikeres volt a fájlba írás!");
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.err.println("Hiba történt a fájl írása közben: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Kiűríti a vonatokat. Beolvassa a vonatok adatait. Soronként olvassa és szét
	 * darabolja. Ezekután beteszi a vonatok-ba az adott vonatot.
	 * 
	 * @param vonatok  egy ArrayList Train típusokkal.
	 * @param fileName a fájl neve, ahonnan bekell olvasni az adatokat.
	 */
	public static void load(ArrayList<Train> vonatok, String fileName) {
		try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr)) {
			vonatok = new ArrayList<Train>();
			String[] elemek = null;
			Train vonat = null;
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				elemek = line.split(";");
				String kocsiszam = elemek[0];
				vonat = new Train(kocsiszam);
				vonatok.add(vonat);
				String[] darabol = elemek[1].split(" ");
				for (int i = 0; i < darabol.length; i += 3) {
					TrainCar kocsi = new TrainCar(Integer.parseInt(darabol[i]), Integer.parseInt(darabol[i + 1]),
							Integer.parseInt(darabol[i + 2]));
					vonat.kocsikAdd(kocsi);
				}
				darabol = elemek[2].split(" ");
				for (int i = 0; i < darabol.length; i += 2) {
					Time ido = new Time(Integer.parseInt(darabol[i].split(":")[0]),
							Integer.parseInt(darabol[i].split(":")[1]));
					Departure indulas = new Departure(ido, darabol[i + 1]);
					vonat.indulasiAdd(indulas);
				}
				darabol = elemek[3].split(" ");
				for (int i = 0; i < darabol.length; i += 3) {
					String[] idoDarabol = darabol[i].split(":");
					Time ido = new Time(Integer.parseInt(darabol[i].split(":")[0]),
							Integer.parseInt(darabol[i].split(":")[1]));
					Arrival erkezes = new Arrival(ido, darabol[i + 1], Boolean.parseBoolean(darabol[i + 2]));
					vonat.erkezesiAdd(erkezes);
				}
				if (elemek.length == 5) {
					darabol = elemek[4].split(" ");
					for (int i = 0; i < darabol.length; i += 5) {
						TrainCar kocsi = new TrainCar(Integer.parseInt(darabol[i]), Integer.parseInt(darabol[i + 1]),
								Integer.parseInt(darabol[i + 2]));
						Reservation foglalas = new Reservation(kocsi, darabol[i + 3], darabol[i + 4]);
						vonat.foglalasokAdd(foglalas);
					}
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			System.err.println("Hiba történt a fájl olvasása közben: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Ezzel a parancsal tudunk hozzáadni vonatokat az ArrayListhez és később ezt
	 * letudjuk menteni.
	 * 
	 * @param vonatok egy ArrayList Train típusokkal.
	 */
	public static void add(ArrayList<Train> vonatok) {
		System.out.println("Kérem írjon be egy vonatszámot! Megfelelő formátum: {xxxx}");
		String vonatszam = Main.sc.nextLine();
		Train vonat = new Train(vonatszam);
		vonatok.add(vonat);
		System.out.println("Vonat hozzáadva: " + vonat);
		System.out.println("Adjon hozzá kocsikat:");
		String folytatja = "igen";
		while (folytatja.equals("igen")) {
			TrainCar kocsi = kocsi();
			vonat.kocsikAdd(kocsi);
			System.out.println("Szeretne további kocsikat hozzáadni? (igen/nem)");
			folytatja = Main.sc.nextLine();
		}
		System.out.println("Adjon hozzá megállókat:");
		folytatja = "igen";
		while (folytatja.equals("igen")) {
			Departure indulas = megallo();
			vonat.indulasiAdd(indulas);
			System.out.println("Kérem adja meg az érkezési állomást is!");
			Departure allomas = megallo();
			System.out.println("Késésben van a vonat? {true/false}");

			Boolean keses = Boolean.parseBoolean(Main.sc.nextLine());

			Arrival erkezes = new Arrival(allomas.getIdo(), allomas.getHely(), keses);
			vonat.erkezesiAdd(erkezes);
			System.out.println("Folytatja? (igen/nem)");
			folytatja = Main.sc.nextLine();
		}
	}

	/**
	 * Kéri be az adatokat, közben vizsgálja a formátumot, hogy megfelelő -e. A
	 * végén pedig létrehoz egy Departuret.
	 * 
	 * @return Visszaadja a felhasználó által beírt paraméterek alapján a megállót.
	 *         (Departure)
	 */
	public static Departure megallo() {
		int i = 0;
		Departure megallo = null;
		while (true) {
			System.out.println("Kérem írja be a megállót ebben a formátumban: {óra}:{perc} {hely}");
			String megalloInput = Main.sc.nextLine();
			String[] megalloParts = megalloInput.split(" ");
			if (megalloParts.length != 2) {
				System.out.println("Hibás formátum. Kérem próbálja újra.");
				continue;
			}
			String[] idoParts = megalloParts[0].split(":");
			if (idoParts.length != 2) {
				System.out.println("Hibás idő formátum. Kérem próbálja újra.");
				continue;
			}
			String hely = megalloParts[1];
			Time ido = new Time(Integer.parseInt(idoParts[0]), Integer.parseInt(idoParts[1]));
			megallo = new Departure(ido, megalloParts[1]);
			System.out.println("Megálló hozzáadva: " + megallo);
			break;
		}
		return megallo;
	}

	/**
	 * A felhasználótól folyton kérdezi a program, hogy mit írjon be. Ezután a
	 * felhasználótól bekért adatokat átalakítja, és létrehoz egy új TrainCart.
	 * 
	 * @return Visszaad egy TrainCar típust, amiben a kocsi adatai vannak.
	 */
	public static TrainCar kocsi() {
		System.out.println("Kérem adjon meg egy kocsiszámot!");
		int kocsiszam = Integer.parseInt(Main.sc.nextLine());
		System.out.println("Adja meg kérem a helyszámot!");
		int helyekszama = Integer.parseInt(Main.sc.nextLine());
		System.out.println("Kérem adja meg a kocsinak az osztályát ilyen formában {x}");
		int osztaly = Integer.parseInt(Main.sc.nextLine());
		TrainCar kocsi = new TrainCar(kocsiszam, helyekszama, osztaly);
		System.out.println(kocsi.getHely());
		return kocsi;
	}

	/**
	 * Kilistázza a vonatokat, hogy mi a vonatszáma, utána az első, majd az utolsó
	 * megállót. Ez azért érdemes, mert így látjuk, hogy melyik vonatra szeretnénk
	 * felszállni.
	 * 
	 * @param vonatok egy ArrayList Train típusokkal.
	 */
	public static void list(ArrayList<Train> vonatok) {
		for (Train x : vonatok) {
			System.out.println(x.toString() + "- Első megálló: " + x.getIndulasi().get(0) + ", Végállomás: "
					+ x.getErkezesi().get(x.getErkezesi().size() - 1));
		}
	}

	/**
	 * Lényegében ez a metódus megvizsgálja, hogy van -e már foglalás azon a helyen.
	 * 
	 * @param vonat A vonat amelyiken a foglalásokat nézzük.
	 * @param kocsi Megadjuk, hogy melyik kocsit szeretnénk vizsgálni.
	 * @return Visszaad egy booleant ami megadja, hogy van -e foglalás arra az adott
	 *         helyre.
	 */
	public static boolean vanFoglalas(Train vonat, TrainCar kocsi) {
		ArrayList<Reservation> foglalasok = vonat.getFoglalasok();
		for (Reservation x : foglalasok) {
			if (x.getKocsi().equals(kocsi)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ez egy foglalási metódus. Itt megy végig az egész foglalási metódus.
	 * 
	 * @param vonatok egy ArrayList Train típusokkal.
	 */
	public static void reservation(ArrayList<Train> vonatok) {
		System.out.println("Kérem írja be melyik vonatra szeretne foglalni! {vonatszámot kell beírni!}");
		String vonatszam = Main.sc.nextLine();
		Train vonat = null;
		TrainCar kocsi = null;
		String foglalni = null;
		for (Train x : vonatok) {
			if (x.getVonatszam().equals(vonatszam)) {
				vonat = x;
			}
		}
		String helyIndulas = null;
		String helyErkezes = null;
		if (vonat != null) {
			timetableshow(vonat);
			System.out.println("Erre a vonatra szeretne foglalni? (igen/nem)");
			foglalni = Main.sc.nextLine();
			boolean hely1 = false, hely2 = false;
			while (foglalni.equals("igen")) {
				boolean van = false;
				System.out.println(vonat.kocsikString());
				System.out.println(vonat.foglalasokString());
				kocsi = kocsi();
				TrainCar vonatkocsi = kocsi;
				for (int i = 0; i < vonat.getKocsik().size(); i++) {
					if ((vonat.getKocsik().get(i).getKocsiszam() == (kocsi.getKocsiszam()))) {
						van = true;
						vonatkocsi = vonat.getKocsik().get(i);
					}
				}
				if (van == false) {
					System.out.println("Nincs ilyen kocsi!");
					break;
				} else if (vonatkocsi.getOsztaly() != kocsi.getOsztaly()) {
					System.out.println("Nem megfelelő az osztály!");
					break;
				}
				if (vanFoglalas(vonat, kocsi)) {
					System.out.println("Erre a kocsira van már foglalás!");
					break;
				}
				System.out.println("Adja meg a helyet ahonnan szeretne indulni!");
				helyIndulas = Main.sc.nextLine();
				System.out.println("Adja meg a helyet ahova szeretne érkezni!");
				helyErkezes = Main.sc.nextLine();
				for (int i = 0; i < vonat.getIndulasi().size(); i++) {
					if (vonat.getIndulasi().get(i).getHely().equals(helyIndulas)) {
						System.out.println("Ettől a megállótól szeretne utazni: " + helyIndulas);
						hely1 = true;
					}
					System.out.println(vonat.getErkezesi().get(i).getHely());
					if (vonat.getErkezesi().get(i).getHely().equals(helyErkezes)) {
						System.out.println("Eddig a megállóig szeretne utazni: " + helyErkezes);
						hely2 = true;
					}
				}
				if (!hely1 || !hely2) {
					System.out.println("Nem jó adatok a helyeknél!");
					foglalni = "nem";
					break;
				} else {
					Reservation foglalas = new Reservation(kocsi, helyIndulas, helyErkezes);
					vonat.foglalasokAdd(foglalas);
					System.out.println("Szeretne további helyeket foglalni?");
					foglalni = Main.sc.nextLine();
				}
			}

		} else {
			System.out.println("Nem találtunk ilyen vonatot!");
			System.out.println("Szeretné újra próbálni? (igen/nem)");
			String dontes = Main.sc.nextLine();
			if (dontes.equals("igen")) {
				reservation(vonatok);
			}
		}
	}

	/**
	 * A timetable parancs azaz a menetrend kiíratás végzi és meghívja a
	 * timetableshow()-t.
	 * 
	 * @param vonatok egy ArrayList Train típusokkal.
	 */
	public static void timetable(ArrayList<Train> vonatok) {
		System.out.println("Kérem írja be a vonatszámát!");
		String vonatszam = Main.sc.nextLine();
		System.out.println("A keresett vonatszáma: " + vonatszam);
		for (Train x : vonatok) {
			if (x.getVonatszam().equals(vonatszam)) {
				timetableshow(x);
			}
		}
	}

	/**
	 * Ez egy segítő metódus a timetable()-hez. Továbbá máskor is használjuk a
	 * menetrend kiíráshoz.
	 * 
	 * @param x Az adott vonat amelynek a menetrendjét szeretnénk kiírni.
	 */
	public static void timetableshow(Train x) {
		System.out.println(x.getVonatszam() + " Vonat menetrendje:");
		for (int i = 0; i < x.getIndulasi().size(); i++) {
			System.out.println(i + 1 + ". állomás: " + "Indulás: " + x.getIndulasi().get(i));
			System.out.println(i + 2 + ". állomás: " + "Érkezés: " + x.getErkezesi().get(i));

		}
	}

	/**
	 * Parancsok használatát íratja ki.
	 */
	public static String commandslist() {
		return ("Parancsok: \nexit - kilép az alkalmazásból\nadd - hozzá tudunk adni egy új vonatot\nlist - kitudjuk listázni a vonatokat\ntimetable - Menetrend kiírása\nreservation - hely foglalása\ncommandslist - parancsok kiíratása\nsave - fájlba mentés\nload - fájlból olvasás");

	}
}
