package vonatjegy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Program futtatása
 * 
 * @author Laczy Gábor
 * @version 1.0
 */
public class Main {
	private static ArrayList<Train> vonatok = new ArrayList<Train>(); // Ebben tároljuk az összes vonatot.
	public static Scanner sc = new Scanner(System.in);

	/**
	 * A metódus lényege, hogy létrehoz egy standard vonatot a tesztekhez, stb.
	 * 
	 * @return egy vonatot ad vissza.
	 */
	public static Train vonatCreate() {
		ArrayList<Departure> indulasi = new ArrayList<Departure>();
		ArrayList<Arrival> erkezesi = new ArrayList<Arrival>();
		ArrayList<TrainCar> kocsik = new ArrayList<TrainCar>();
		ArrayList<Reservation> foglalasok = new ArrayList<Reservation>();
		Time ido = new Time(8, 21);
		Departure indulas = new Departure(ido, "Budapest");
		indulasi.add(indulas);
		Time ido2 = new Time(11, 11);
		Arrival erkezes = new Arrival(ido2, "Nyíregyháza", false);
		Time ido3 = new Time(11, 20);
		Departure indulas2 = new Departure(ido3, "Nyíregyháza");
		indulasi.add(indulas2);
		Time ido4 = new Time(13, 12);
		Arrival erkezes2 = new Arrival(ido4, "Záhony", true);
		erkezesi.add(erkezes);
		erkezesi.add(erkezes2);
		TrainCar kocsi = new TrainCar(24, 105, 2);
		kocsik.add(kocsi);
		kocsik.add(new TrainCar());
		Reservation foglalas = new Reservation(kocsi, "Budapest", "Nyíregyháza");
		foglalasok.add(foglalas);
		Train vonat = new Train("3232", indulasi, erkezesi, kocsik, foglalasok);
		return vonat;
	}

	/**
	 * Ebben fut le az egész program ezt hívjuk meg a main-ben. Ennek segítségébel
	 * átláthatóbb a program.
	 */
	public static void run() {
		// Létrehozunk egy standard vonatot:
		Train vonat = vonatCreate();
		vonatok.add(vonat);
		// Idáig tart a vonat létrehozása.
		System.out.println(Commands.commandslist());
		String[] cmd = null; // Beolvasáshoz tömb létrehozása - későbbi legetséges paraméterek miatt tömb.
		while (cmd == null || !cmd[0].equals("exit")) // Addig fut míg nem kap exit-et.
		{
			System.out.println("Írj be egy utasítást!");
			String temp = sc.nextLine();
			cmd = temp.split(" "); // Későbbi lehetséges fejlesztés.
			if (cmd[0].equals("save")) {
				Commands.save(Main.vonatok, "VonatAdatok.txt");
			} else if (cmd[0].equals("load")) {
				Commands.load(Main.vonatok, "VonatAdatok.txt");
			} else if (cmd[0].equals("add")) {
				Commands.add(Main.vonatok);
			} else if (cmd[0].equals("list")) {
				Commands.list(Main.vonatok);
			} else if (cmd[0].equals("reservation")) {
				Commands.reservation(Main.vonatok);
			} else if (cmd[0].equals("timetable")) {
				Commands.timetable(Main.vonatok);
			} else if (cmd[0].equals("commandslist")) {
				System.out.println(Commands.commandslist());
			}
		}
		Main.sc.close(); // Scanner bezárása
	}

	public static void main(String[] args) {
		run();
	}

}
