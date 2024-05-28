package vonatjegy;

/**
 * Stop leszármazottja, az érkezést reprezentálja.
 */
public class Arrival extends Stop {

	private boolean kesik;

	/**
	 * Ez egy konstruktor alap adatokkal.
	 */
	public Arrival() {
		super(new Time(23, 54), "Siófok");
		this.kesik = true;
	}

	/**
	 * Konstruktor
	 * 
	 * @param ido   Time típus
	 * @param hely  String típus
	 * @param kesik boolean típus
	 */
	public Arrival(Time ido, String hely, boolean kesik) {
		super(ido, hely);
		this.kesik = kesik;
	}

	/**
	 * 
	 * @return visszaadja a késés értékét, boolean.
	 */
	public boolean getKeses() {
		return kesik;
	}

	@Override
	/**
	 * Beállítja az időt Time típusú.
	 */
	public void setIdo(Time ido) {
		this.ido = ido;
	}

	@Override
	/**
	 * Beállítja a helyet, String típusú.
	 */
	public void setHely(String hely) {
		this.hely = hely;
	}

	/**
	 * Visszaadja Stringben a Stop metódus toString és a késés értékét.
	 */
	public String toString() {
		return super.toString() + " Késés: " + this.kesik;
	}
}
