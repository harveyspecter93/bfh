package gruppenarbeit3_bankomatsimulator;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Bankomat {
	private static final Logger LOGGER = Logger.getLogger(Bankomat.class.getName());
	static boolean inBenutzung = true;

	private enum Aktion {
		EINZAHLEN, AUSZAHLEN, KONTOABFRAGE, ABSCHLIESSEN
	}

	// constants
	private static final int ANZAHL_VERSUCHE_PIN_BANKOMAT = 3;
	private static final String KARTEN_ID = "7562-0994-4984-1";

	public static void main(String[] args) {
		// Formatting logger
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");

		// start dialog
		LOGGER.info("Bankomat startet...");
		LOGGER.info("Bitte schieben Sie die Bankkarte ein.");
		LOGGER.info("Karte lesen....");

		// get customer data
		KundeDAO kundenDaten = new KundeDAO();
		KontoDAO kundenKonten = new KontoDAO();
		LOGGER.info("Karte ist: " + KARTEN_ID);

		// check if customer for card exists
		Kunde kunde = fetchKundeForKarte(kundenDaten, KARTEN_ID);
		if (kunde == null) {
			LOGGER.severe("Kunde Konte nicht gefunden werden, unbekannte Karte.");
			System.exit(-1);
		}

		// pin login cycle
		login(kunde);

		Konto konto = fetchKontoForKunde(kunde.getKundenNr(), kundenKonten.all);
		if (konto == null) {
			LOGGER.severe("Konto des Kunden: " + kunde + " konnte nicht gefunden werden, unbekanntes Konto.");
			System.exit(-1);
		}

		while (inBenutzung) {
			LOGGER.info("Wählen Sie eine der folgenden Transaktionen: ");
			for (int i = 1; i < Aktion.values().length + 1; i++) {
				Aktion aktion = Aktion.values()[i - 1];
				LOGGER.info(aktion.name() + " (" + i + ")");
			}

			Aktion aktion = determineAktion();

			switch (aktion) {
			case EINZAHLEN:
				startEinzahlung(konto);
				break;
			case AUSZAHLEN:
				startAuszahlung(konto);
				break;
			case KONTOABFRAGE:
				startKontoAbfrage(konto);
				break;
			case ABSCHLIESSEN:
				inBenutzung = false;
				LOGGER.info("Besten Dank, auf wiedersehen!");
				break;
			default:
				LOGGER.severe("Einhabe konnte nicht zugeordnet werden, wählen Sie 1, 2 oder 3.");
				break;
			}

		}
	}

	private static void startEinzahlung(Konto konto) {
		LOGGER.info("Wie viel möchten Sie einzahlen?");
		Double menge = 0D;
		String mengeStr = readInput();
		try {
			menge = Double.parseDouble(mengeStr);
		} catch (Exception e) {
			LOGGER.severe("Menge konnte nicht gelesen werden. Geben Sie eine Zahl ein.");
		}
		konto.einzahlen(menge);
	}

	private static void startAuszahlung(Konto konto) {
		LOGGER.info("Wie viel möchten Sie abheben?");
		Double menge = 0D;
		String mengeStr = readInput();
		try {
			menge = Double.parseDouble(mengeStr);
		} catch (Exception e) {
			LOGGER.severe("Menge konnte nicht gelesen werden. Geben Sie eine Zahl ein.");
		}
		konto.auszahlen(menge);
	}

	private static void startKontoAbfrage(Konto konto) {
		LOGGER.info("Gerne zeigen Wir Ihnen Ihre Konto-Informationen: ");
		konto.kontoAbfragen();
	}

	private static Konto fetchKontoForKunde(int kundenNummer, List<Konto> kontenListe) {
		for (Konto konto : kontenListe) {
			if (konto.getKundenNummer() == kundenNummer) {
				return konto;
			}
		}
		return null;
	}

	private static Aktion determineAktion() {
		String input = readInput();
		if (input.contains("1")) {
			return Aktion.EINZAHLEN;
		} else if (input.contains("2")) {
			return Aktion.AUSZAHLEN;
		} else if (input.contains("3")) {
			return Aktion.KONTOABFRAGE;
		}

		return null;
	}

	private static void login(Kunde kunde) {
		int zaehler = 0;
		String eingabePin = "";

		String korrekterPin = kunde.getPin();
		LOGGER.info("Geben Sie ihren PIN ein.");

		do {
			String pinStr = readInput();
			eingabePin = pinStr;
			zaehler++;
			if (zaehler == ANZAHL_VERSUCHE_PIN_BANKOMAT) {
				LOGGER.severe(ANZAHL_VERSUCHE_PIN_BANKOMAT + ". Versuch erfolglos. Bankkarte wurde eingezogen!");
				System.exit(0);
			}

			if (!eingabePin.equals(korrekterPin)) {
				LOGGER.info("PIN falsch, noch: " + (ANZAHL_VERSUCHE_PIN_BANKOMAT - zaehler) + " Versuche.");
			}

		} while (!eingabePin.equals(korrekterPin));
		LOGGER.info("Pin korrekt. Guten Tag " + kunde.getVorname());
	}

	private static Kunde fetchKundeForKarte(KundeDAO kundenDaten, String kartenId) {
		List<Kunde> kunden = kundenDaten.getAll();
		for (Kunde kunde : kunden) {
			if (kunde.getBankkarte().equals(kartenId)) {
				return kunde;
			}

		}
		return null;
	}

	private static String readInput() {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder("");

		while (input.hasNextLine()) {
			String inputStr = input.nextLine();
			if (inputStr.equals("OK")) {
				break;
			}
			sb.append(inputStr);
			if (inputStr.equals("DELETE")) {
				sb.delete(0, sb.length());
			}
			LOGGER.info(
					"Aktuelle eingabe: " + sb.toString() + " bestätigen Sie mit \"OK\", löschen Sie mit \"DELETE\" ");
		}
		return sb.toString();
	}

}
