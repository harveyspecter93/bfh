package gruppenarbeit3_bankomatsimulator;

/*
* Gruppenarbeit 3: 
* Dräyer Michael; Frei Yannick; Ziegler Andrin; 
* Klasse 2o
*/

/**
 * @author Dräyer Michael; Frei Yannick; Ziegler Andrin; 
 *
 */
public class Kunde implements Comparable<Kunde> {
	private int kundenNr;
	private String name;
	private String vorname;
	private String bankkarte;
	private String pin;

	/**
	 * @param kundenNr
	 * @param name
	 * @param vorname
	 * @param bankkarte
	 * @param pin
	 */
	public Kunde(int kundenNr, String name, String vorname, String bankkarte, String pin) {
		if (kundenNr < 1000000 || kundenNr > 9999999)
			throw new IllegalArgumentException("Kundennummer ist nicht erlaubt: " + kundenNr);
		this.kundenNr = kundenNr;
		this.name = name;
		this.vorname = vorname;
		this.bankkarte = bankkarte;
		this.pin = pin;
	}

	/**
	 * @return
	 */
	public int getKundenNr() {
		return kundenNr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name + ", " + vorname + " (" + kundenNr + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o == null || o.getClass() != getClass())
			return false;
		Kunde that = (Kunde) o;
		return kundenNr == that.kundenNr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return kundenNr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Kunde o) {
		return kundenNr - o.kundenNr;
	}

	/**
	 * @return
	 */
	public String getBankkarte() {
		return bankkarte;
	}

	/**
	 * @param bankkarte
	 */
	public void setBankkarte(String bankkarte) {
		this.bankkarte = bankkarte;
	}

	/**
	 * @return
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param pin
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * @return
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
}
