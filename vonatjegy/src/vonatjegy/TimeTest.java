package vonatjegy;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TimeTest {

	private Time ido, ido2;

	@Before

	public void setUp() {
		ido = new Time(0, 23);
		ido2 = new Time(21, 6);
	}

	@Test
	public void testToString() {
		assertEquals("00:23", ido.toString());
		assertEquals("21:06", ido2.toString());

	}

	@Test
	public void testAdd() {
		assertEquals(21, ido.add(ido2).getHours());
		assertEquals(29, ido.add(ido2).getMins());
	}

	@Test
	public void testSub() {
		assertEquals(3, ido.sub(ido2).getHours());
		assertEquals(17, ido.sub(ido2).getMins());
	}

	@Test
	public void testCompareTo() {
		assertEquals(-1, ido.compareTo(ido2));
		assertEquals(1, ido2.compareTo(ido));

	}

}
