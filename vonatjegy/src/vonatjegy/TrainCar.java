package vonatjegy;

/**
 * Vonatkocsik reprezentálásához használjuk.
 */
public class TrainCar {
	private int kocsiszam;
	private int hely;
	private int osztaly;

	/**
	 * Konstruktor alap adatokkal.
	 */
	public TrainCar() {
		this.kocsiszam = 23;
		this.hely = 95;
		this.osztaly = 1;
	}

	/**
	 * Konstruktor megadott adatokkal
	 * 
	 * @param kocsiszam Integer típusú
	 * @param hely      Integer típus
	 * @param osztaly   Integer típus
	 */
	public TrainCar(int kocsiszam, int hely, int osztaly) {
		this.kocsiszam = kocsiszam;
		this.hely = hely;
		this.osztaly = osztaly;
	}

	/**
	 * 
	 * @return visszaadja a kocsiszámát.
	 */
	public int getKocsiszam() {
		return kocsiszam;
	}

	/**
	 * 
	 * @return visszaadja a helyszámát.
	 */
	public int getHely() {
		return hely;
	}

	/**
	 * 
	 * @return visszaadja az osztályszámát.
	 */
	public int getOsztaly() {
		return osztaly;
	}

}
