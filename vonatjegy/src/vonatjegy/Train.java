package vonatjegy;

import java.util.ArrayList;

/**
 * Vonatok reprezentálásához.
 */
public class Train {
	private String vonatszam;
	private ArrayList<TrainCar> kocsik;
	private ArrayList<Departure> indulasi;
	private ArrayList<Arrival> erkezesi;
	private ArrayList<Reservation> foglalasok;

	/**
	 * Ez a konstruktor szimplán vonatszámmal hívható meg.
	 * 
	 * @vonatszam: A vonatszáma, ez egy String mivel tartalmazhat betűt is.
	 */
	public Train(String vonatszam) {
		this.vonatszam = vonatszam;
		this.indulasi = new ArrayList<>();
		this.erkezesi = new ArrayList<>();
		this.kocsik = new ArrayList<>();
		this.foglalasok = new ArrayList<>();
	}

	/**
	 * Ez a konstruktor, ha még nincs foglalás akkor érdemes használni.
	 * 
	 * @vonatszam: A vonatszáma, ez egy String mivel tartalmazhat betűt is.
	 * @kocsik: Egy Arraylist, amelyben tároljuk a kocsik adatait, de ez TrainCar
	 *          típusú.
	 * @indulasi: Egy Arraylist, amelyben tároljuk az indulási adatokat, de ez
	 *            Departure típusú.
	 * @erkezesi: Egy Arraylist, amelyben tároljuk az érkezési adatokat, de ez
	 *            Arrival típusú.
	 */
	public Train(String vonatszam, ArrayList<Departure> indulasi, ArrayList<Arrival> erkezesi,
			ArrayList<TrainCar> kocsik) {
		this.vonatszam = vonatszam;
		this.indulasi = indulasi;
		this.erkezesi = erkezesi;
		this.kocsik = kocsik;
	}

	/**
	 * Ha minden adattal rendelkezünk akkor használatos ez a konstruktor.
	 * 
	 * @vonatszam: A vonatszáma, ez egy String mivel tartalmazhat betűt is.
	 * @kocsik: Egy Arraylist, amelyben tároljuk a kocsik adatait, de ez TrainCar
	 *          típusú.
	 * @indulasi: Egy Arraylist, amelyben tároljuk az indulási adatokat, de ez
	 *            Departure típusú.
	 * @erkezesi: Egy Arraylist, amelyben tároljuk az érkezési adatokat, de ez
	 *            Arrival típusú.
	 * @foglalasok: Egy Arraylist, amelyben tároljuk a foglalási adatokat, de ez
	 *              Reservation típusú.
	 */
	public Train(String vonatszam, ArrayList<Departure> indulasi, ArrayList<Arrival> erkezesi,
			ArrayList<TrainCar> kocsik, ArrayList<Reservation> foglalas) {
		this.vonatszam = vonatszam;
		this.indulasi = indulasi;
		this.erkezesi = erkezesi;
		this.kocsik = kocsik;
		this.foglalasok = foglalas;
	}

	/**
	 * Kiírja a vonatszámát, előtte ezt jelzi.
	 */
	public String toString() {
		return "Vonatszám: " + vonatszam;
	}

	/**
	 * A meglévő indulási ArrayListhez hozzáad egy további indulást.
	 * 
	 * @param indulas Departure típusú paraméter, egy vonat indulást kell megadni.
	 */
	public void indulasiAdd(Departure indulas) {
		indulasi.add(indulas);
	}

	/**
	 * A meglévő erkezesi ArrayListhez hozzáad egy további indulást.
	 * 
	 * @param erkezes Arrival típusú paraméter, egy vonat érkezését kell megadni.
	 */
	public void erkezesiAdd(Arrival erkezes) {
		erkezesi.add(erkezes);
	}

	/**
	 * A meglévő kocsikhoz ArrayListhez hozzáad egy TrainCar típusú kocsit.
	 * 
	 * @param kocsi TrainCar típusú paraméter, ez egy kocsit tartalmaz.
	 */
	public void kocsikAdd(TrainCar kocsi) {
		kocsik.add(kocsi);
	}

	/**
	 * A meglévő foglalasok ArrayListhez hozzáad egy Reservation típusú foglalást.
	 * 
	 * @param foglalas Reservation típusú paraméter, ez egy foglalást tartalmaz.
	 */
	public void foglalasokAdd(Reservation foglalas) {
		foglalasok.add(foglalas);
	}

	/**
	 * 
	 * @return visszaadja a vonatszámot.
	 */
	public String getVonatszam() {
		return vonatszam;
	}

	/**
	 * 
	 * @return visszaadja az indulási ArrayListet, Departure típus.
	 */
	public ArrayList<Departure> getIndulasi() {
		return indulasi;
	}

	/**
	 * 
	 * @return visszadja az erkezesi ArrayListet, Arrival típus.
	 */
	public ArrayList<Arrival> getErkezesi() {
		return erkezesi;
	}

	/**
	 * 
	 * @return visszaadja a kocsik ArrayListet, TrainCar típus.
	 */
	public ArrayList<TrainCar> getKocsik() {
		return kocsik;
	}

	/**
	 *
	 * @return visszaadja a foglalások ArrayListet, Reservation típus.
	 */
	public ArrayList<Reservation> getFoglalasok() {
		return foglalasok;
	}

	/**
	 * A kocsikat teszi Stringbe megfelelő formátumban.
	 * 
	 * @return visszaad egy Stringet ami az adott vonat kocsikat listázza ki.
	 */
	public String kocsikString() {
		String kiir = "Kocsik:\n";
		for (TrainCar x : kocsik) {
			kiir += ("Kocsiszám: " + x.getKocsiszam() + " Helyek száma: " + x.getHely() + " Osztály száma: "
					+ x.getOsztaly() + "\n");
		}
		return kiir;
	}

	/**
	 * A foglalásokat teszi Stringbe megfelelő formátumban.
	 * 
	 * @return visszaad egy Stringet ami az adott vonat foglalásait listázza ki.
	 */
	public String foglalasokString() {
		String kiir = "Foglalások:\n";
		for (Reservation foglalas : foglalasok) {
			TrainCar x = foglalas.getKocsi();
			kiir += "Kocsiszám: " + x.getKocsiszam() + " Helyjegy: " + x.getHely() + " Indulási hely: "
					+ foglalas.getIndulasiHely() + " Érkezési hely: " + foglalas.getErkezesiHely() + "\n";
		}
		return kiir;
	}
}
