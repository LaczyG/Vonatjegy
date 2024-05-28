package vonatjegy;

/**
 * Idő tárolásához alkalmazzuk.
 */
public class Time {
	private int min;

	/**
	 * Megtudjuk adni az időt vesszővel elválasztva.
	 * 
	 * @param hh óra megadása
	 * @param mm perc megadása
	 */
	public Time(int hh, int mm) {
		this.min = 60 * hh + mm;
	}

	/**
	 * Csak a perc megadásával.
	 * 
	 * @param mins percek megadása.
	 */
	public Time(int mins) {
		this.min = mins;
	}

	/**
	 * Csak a percek állítása
	 * 
	 * @param mins percek megadása.
	 */
	public void setMinsOnly(int mins) {
		this.min = mins;
	}

	/**
	 * 
	 * @return visszaadja a perceket.
	 */
	public int getMins() {
		return this.min % 60;
	}

	/**
	 * 
	 * @return visszaadja az órákat.
	 */
	public int getHours() {
		return this.min / 60;
	}

	/**
	 * 
	 * @return visszaadja a perceket, de az összeset.
	 */
	public int getMinsOnly() {
		return this.min;
	}

	/**
	 * Formázza az időt, hogy ilyen formában legyen: xx:xx
	 * 
	 * @return visszaadja az időt megfelelő formátumban.
	 */
	public String toString() {
		if (this.getMins() < 10) {
			if (this.getHours() < 10) {
				return "0" + this.getHours() + ":" + "0" + this.getMins();
			}
			return this.getHours() + ":" + "0" + this.getMins();
		} else if (this.getHours() < 10) {
			return "0" + this.getHours() + ":" + this.getMins();
		}
		return this.getHours() + ":" + this.getMins();
	}

	/**
	 * Kettő idő összeadására szolgál.
	 * 
	 * @param other egy másik idő megadása.
	 * @return visszaadja a 2 idő összegét.
	 */
	public Time add(Time other) {
		Time vissza = new Time(this.min + other.min);
		return vissza;
	}

	/**
	 * Kettő idő kivonása egymásból.
	 * 
	 * @param other egy másik idő megadása.
	 * @return visszaadja a 2 idő különbségét.
	 */

	public Time sub(Time other) {
		int szamol = this.min - other.min;
		while (szamol < 0) {
			szamol += 24 * 60;
		}
		Time vissza = new Time(szamol);
		return vissza;
	}

	/**
	 * Hozzáad perceket egy időhöz.
	 * 
	 * @param perc megadása.
	 */
	public void addMins(int perc) {
		int szam = this.getMinsOnly() + perc;
		while (szam > 24 * 60) {
			szam -= 24 * 60;
		}
		this.setMinsOnly(szam);
	}

	/**
	 * Hozzáad órákat egy időhöz.
	 * 
	 * @param orakat kell beírni.
	 */
	public void addHours(int ora) {
		int szam = this.getMinsOnly() + ora * 60;
		while (szam > 24 * 60) {
			szam -= 24 * 60;
		}
		this.setMinsOnly(szam);
	}

	/**
	 * Összehasonlítja a két időt, hogy kisebb -e az első vagy nagyobb, vagy
	 * ugyanannyi.
	 * 
	 * @param t idő megadása
	 * @return 0, ha egyenlő 1, ha az első nagyobb -1, ha az első kisebb.
	 */
	public int compareTo(Time t) {
		if (this.getMinsOnly() < t.getMinsOnly()) {
			return -1;
		} else if (this.getMinsOnly() > t.getMinsOnly()) {
			return 1;
		}
		return 0;
	}
}
