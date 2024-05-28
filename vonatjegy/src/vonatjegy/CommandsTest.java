package vonatjegy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CommandsTest {

	private ArrayList<Train> vonatok;
	private ArrayList<Departure> indulasi;
	private ArrayList<Arrival> erkezesi;
	private ArrayList<TrainCar> kocsik;

	@Before
	public void setUp() {
		vonatok = new ArrayList<>();
		indulasi = new ArrayList<>();
		erkezesi = new ArrayList<>();
		kocsik = new ArrayList<>();
		Train vonat = new Train("5234");
		vonat.indulasiAdd(new Departure());
		vonat.erkezesiAdd(new Arrival());
		vonat.kocsikAdd(new TrainCar());
		vonatok.add(vonat);
	}

	@Test
	public void testSave() {

		String eleres = "TestVonatAdatok.txt";
		Commands.save(vonatok, eleres);

		File file = new File(eleres);
		assertTrue(file.exists());
	}

	@Test
	public void testLoad() {
		Commands.save(vonatok, "TestVonatAdatok.txt");
		File f = new File("VonatAdatok.txt");

		Commands.load(vonatok, "TestVonatAdatok.txt");
		assertEquals("5234", vonatok.get(0).getVonatszam());
	}

	@Test
	public void testAdd() {
		// 6565-öt kell beírni a konzolba. Ezt vizsgáljuk, utána a többi bármi lehet.

		Commands.add(vonatok);
		assertEquals("6565", vonatok.get(1).getVonatszam());
	}

	@Test
	public void testMegallo() {
		// 12:23 Bp
		Departure megallo = Commands.megallo();
		assertEquals("Bp", megallo.getHely());
		assertEquals(12, megallo.getIdo().getHours());
		assertEquals(23, megallo.getIdo().getMins());

	}

	@Test
	public void testKocsi() {
		// 23 95 2
		TrainCar kocsi = Commands.kocsi();
		assertEquals(23, kocsi.getKocsiszam());
		assertEquals(95, kocsi.getHely());
		assertEquals(2, kocsi.getOsztaly());

	}

	@Test
	public void testVanFoglalas() {
		TrainCar kocsi = new TrainCar();
		assertFalse(Commands.vanFoglalas(vonatok.get(0), kocsi));
	}

	@Test
	public void testCommandsList() {
		assertEquals(
				"Parancsok: \nexit - kilép az alkalmazásból\nadd - hozzá tudunk adni egy új vonatot\nlist - kitudjuk listázni a vonatokat\ntimetable - Menetrend kiírása\nreservation - hely foglalása\ncommandslist - parancsok kiíratása",
				Commands.commandslist());
		;
	}
}
