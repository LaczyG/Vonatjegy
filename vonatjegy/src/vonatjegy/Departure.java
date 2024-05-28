package vonatjegy;

/**
 * Stop osztály leszármazottja, indulást reprezentálja.
 */
public class Departure extends Stop {

	/**
	 * Default konstruktor alap adatokkal.
	 */
	public Departure() {
		super(new Time(11, 23), "Budapest"); // Paraméter nélküli konstruktor
	}

	/**
	 * Konstruktor.
	 * 
	 * @param ido  Time típussal megadva
	 * @param hely String típussal mmegadva.
	 */
	public Departure(Time ido, String hely) {
		super(ido, hely);
	}

	@Override
	/**
	 * Beállítja az időt, amit megad Time típusban.
	 */
	public void setIdo(Time ido) {
		this.ido = ido;
	}

	@Override
	/**
	 * Beállítja a helyet, amit megad String típusban.
	 */
	public void setHely(String hely) {
		this.hely = hely;
	}
}
