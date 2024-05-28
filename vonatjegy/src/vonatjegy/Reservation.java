package vonatjegy;

/**
 * A foglalásokat reprezentálja.
 */
public class Reservation {
	private TrainCar kocsi;
	private String indulasiHely;
	private String erkezesiHely;

	/**
	 * Konstruktor
	 * 
	 * @param kocsi        TrainCar típus
	 * @param indulasiHely String típus
	 * @param erkezesiHely String típus
	 */
	public Reservation(TrainCar kocsi, String indulasiHely, String erkezesiHely) {
		this.kocsi = kocsi;
		this.indulasiHely = indulasiHely;
		this.erkezesiHely = erkezesiHely;
	}

	/**
	 * 
	 * @return Visszaadja a kocsit, TrainCar típus.
	 */
	public TrainCar getKocsi() {
		return kocsi;
	}

	/**
	 * 
	 * @return visszaadja a indulási helyet, String típus.
	 */
	public String getIndulasiHely() {
		return indulasiHely;
	}

	/**
	 * 
	 * @return visszaadja az érkezési helyet, String típus.
	 */
	public String getErkezesiHely() {
		return erkezesiHely;
	}

}
