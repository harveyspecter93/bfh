package gruppenarbeit3_bankomatsimulator;

import java.util.logging.Logger;

/*
* Gruppenarbeit 3: 
* Dräyer Michael; Frei Yannick; Ziegler Andrin; 
* Klasse 2o
*/

/**
 * @author Dräyer Michael; Frei Yannick; Ziegler Andrin;
 *
 */
public class Konto {
	private static final Logger LOGGER = Logger.getLogger(Konto.class.getName());

	private double kontostand;
	private int kundenNummer;

	public Konto(int kundenNr, double kontostand) {
		this.kundenNummer = kundenNr;
		this.kontostand = kontostand;
	}

	/**
	 * @param menge
	 */
	public void einzahlen(double menge) {
		this.kontostand = kontostand + menge;
		LOGGER.info("Neuer Kontostand: " + this.kontostand);
		kontoAbfragen();
	}

	/**
	 * @param menge
	 */
	public void auszahlen(double menge) {
		if (menge > kontostand) {
			LOGGER.info("Sie können nicht mehr abheben, als auf Ihrem Konto ist.");
			return;
		}

		this.kontostand = kontostand - menge;
		LOGGER.info("Neuer Kontostand: " + this.kontostand);
		kontoAbfragen();
	}

	/**
	 * 
	 */
	public void kontoAbfragen() {
		LOGGER.info("Ihre Kontoinformationen: ");

		// Logger only takes Strings not other objects.
		LOGGER.info(this.toString());
		LOGGER.info("-----------------------");
		LOGGER.info(" ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Konto Nummer: " + this.kundenNummer + "[ Kontostand: " + this.kontostand + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		Konto object = (Konto) o;
		if (!(o instanceof Konto)) {
			return false;
		}

		if (!(object.kundenNummer == this.kundenNummer)) {
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return kundenNummer;
	}

	/**
	 * @return
	 */
	public double getKontostand() {
		return kontostand;
	}

	/**
	 * @param kontostand
	 */
	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	/**
	 * @return
	 */
	public int getKundenNummer() {
		return kundenNummer;
	}

	/**
	 * @param kundenNummer
	 */
	public void setKundenNummer(int kundenNummer) {
		this.kundenNummer = kundenNummer;
	}

}
