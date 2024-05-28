package vonatjegy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TrainTest {
	Train vonat;

	@Before

	public void setUp() {
		vonat = new Train("3432");
	}

	@Test
	public void testConstructor() {
		assertEquals("3432", vonat.getVonatszam());
		assertTrue(vonat.getIndulasi().isEmpty());
		assertTrue(vonat.getErkezesi().isEmpty());
		assertTrue(vonat.getKocsik().isEmpty());
		assertTrue(vonat.getFoglalasok().isEmpty());
	}

	@Test
	public void testIndulasiAdd() {
		Departure indulas = new Departure(new Time(12, 23), "Mátészalka");
		vonat.indulasiAdd(indulas);
		assertTrue(vonat.getIndulasi().contains(indulas));
		assertEquals(1, vonat.getIndulasi().size());
	}

	@Test
	public void testErkezesiAdd() {
		Arrival erkezes = new Arrival(new Time(22, 23), "Budapest", false);
		vonat.erkezesiAdd(erkezes);
		assertEquals("Budapest", vonat.getErkezesi().get(0).getHely());
		assertFalse(vonat.getErkezesi().get(0).getKeses());
	}

	@Test
	public void testKocsikAdd() {
		TrainCar kocsi = new TrainCar(32, 98, 2);
		vonat.kocsikAdd(kocsi);
		assertEquals(32, vonat.getKocsik().get(0).getKocsiszam());
		assertEquals(98, vonat.getKocsik().get(0).getHely());
		assertEquals(2, vonat.getKocsik().get(0).getOsztaly());
	}

	@Test
	public void testFoglalalasokAdd() {
		Reservation foglalas = new Reservation(new TrainCar(32, 54, 2), "Mátészalka", "Budapest");
		vonat.foglalasokAdd(foglalas);
		assertEquals(32, vonat.getFoglalasok().get(0).getKocsi().getKocsiszam());
		assertEquals(54, vonat.getFoglalasok().get(0).getKocsi().getHely());
		assertEquals(2, vonat.getFoglalasok().get(0).getKocsi().getOsztaly());
		assertEquals("Mátészalka", vonat.getFoglalasok().get(0).getIndulasiHely());
		assertEquals("Budapest", vonat.getFoglalasok().get(0).getErkezesiHely());
	}

	@Test
	public void testKocsikString() {
		TrainCar kocsi = new TrainCar(32, 98, 2);
		vonat.kocsikAdd(kocsi);
		assertEquals(vonat.kocsikString(),
				"Kocsik:\n" + "Kocsiszám: " + vonat.getKocsik().get(0).getKocsiszam() + " Helyek száma: "
						+ vonat.getKocsik().get(0).getHely() + " Osztály száma: "
						+ vonat.getKocsik().get(0).getOsztaly() + "\n");
	}

	@Test
	public void testFoglalasokString() {
		Reservation foglalas = new Reservation(new TrainCar(32, 54, 2), "Mátészalka", "Budapest");
		vonat.foglalasokAdd(foglalas);
		assertEquals(vonat.foglalasokString(),
				"Foglalások:\n" + "Kocsiszám: " + vonat.getFoglalasok().get(0).getKocsi().getKocsiszam() + " Helyjegy: "
						+ vonat.getFoglalasok().get(0).getKocsi().getHely() + " Indulási hely: "
						+ vonat.getFoglalasok().get(0).getIndulasiHely() + " Érkezési hely: "
						+ vonat.getFoglalasok().get(0).getErkezesiHely() + "\n");
	}

}