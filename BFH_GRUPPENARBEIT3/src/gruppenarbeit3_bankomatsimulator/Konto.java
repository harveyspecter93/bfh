package gruppenarbeit3_bankomatsimulator;

import java.util.logging.Logger;

public class Konto {
	private static final Logger LOGGER = Logger.getLogger(Konto.class.getName());

	private double kontostand;
	private int kundenNummer;

	public Konto(int kundenNr, double kontostand) {
		this.kundenNummer = kundenNr;
		this.kontostand = kontostand;
	}

	public void einzahlen(double menge) {
		this.kontostand = kontostand + menge;
		LOGGER.info("Neuer Kontostand: " + this.kontostand);
		kontoAbfragen();
	}

	public void auszahlen(double menge) {
		if (menge > kontostand) {
			LOGGER.info("Sie können nicht mehr abheben, als auf Ihrem Konto ist.");
			return;
		}

		this.kontostand = kontostand - menge;
		LOGGER.info("Neuer Kontostand: " + this.kontostand);
		kontoAbfragen();
	}

	public void kontoAbfragen() {
		LOGGER.info("Ihre Kontoinformationen: ");

		// Logger only takes Strings not other objects.
		LOGGER.info(this.toString());
		LOGGER.info("-----------------------");
		LOGGER.info(" ");
	}

	@Override
	public String toString() {
		return "Konto Nummer: " + this.kundenNummer + "[ Kontostand: " + this.kontostand + "]";
	}

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

	@Override
	public int hashCode() {
		return kundenNummer;
	}

	public double getKontostand() {
		return kontostand;
	}

	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	public int getKundenNummer() {
		return kundenNummer;
	}

	public void setKundenNummer(int kundenNummer) {
		this.kundenNummer = kundenNummer;
	}

}
