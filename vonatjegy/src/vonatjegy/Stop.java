package vonatjegy;

/**
 * Absztrakt osztály a Departure és az Arrival-hez.
 */
abstract class Stop {
	protected Time ido;
	protected String hely;

	/**
	 * Konstruktor
	 * 
	 * @param ido  Time típussal megadva.
	 * @param hely ez egy String.
	 */
	public Stop(Time ido, String hely) {
		this.ido = ido;
		this.hely = hely;
	}

	/**
	 * 
	 * @return visszadja a Time típusú időt.
	 */
	public Time getIdo() {
		return ido;
	}

	/**
	 * 
	 * @return visszaadja a String típusú helyet.
	 */
	public String getHely() {
		return hely;
	}

	/**
	 * Beállítja az időt.
	 * 
	 * @param ido Time típusú idő.
	 */
	public abstract void setIdo(Time ido);

	/**
	 * Beállítja a helyet.
	 * 
	 * @param hely String típusú hely
	 */
	public abstract void setHely(String hely);

	@Override
	/**
	 * Kiírja az időt és utána spacel elválasztva a helyet.
	 */
	public String toString() {
		return ido.toString() + " " + hely;
	}
}
