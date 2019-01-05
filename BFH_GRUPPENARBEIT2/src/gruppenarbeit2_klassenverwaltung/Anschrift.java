/*
* Gruppenarbeit 2: 
* Dr�yer Michael; Frei Yannick; Ziegler Andrin; 
* Klasse 1o
*/

package gruppenarbeit2_klassenverwaltung;

public class Anschrift {
	private String land;
	private String plz;
	private String ort;
	private String strasse;
	private String nr; // 22c ist auch erlaubt

	//Konstruktor bei Angabe aller Werte
	public Anschrift(String land, String plz, String ort, String strasse, String nr) {
		this.land = land;
		checkAndSetPlz(plz);
		this.ort = ort;
		this.strasse = strasse;
		this.nr = nr;
		
	}
	
	//erg�nze um einen Konstruktor, so dass bei nicht vorhandener Angabe von "land" der Wert "CH" gesetzt wird
		// in diesem Fall pr�fen, ob "plz" 4-stellig ist, sonst RuntimeException
		// ausl�sen (vgl. class Person)
		// ...
	
	//Konstruktor welchem Land den Defaultwert CH zuweist.
	public Anschrift(String plz, String ort, String strasse, String nr) {
		this.land = "CH";
		checkAndSetPlz(plz);
		this.ort = ort;
		this.strasse = strasse;
		this.nr = nr;

	}

	//Postleitzahl wird auf 4 Stellen �berpr�ft und darf nicht leer sein.
	private void checkAndSetPlz(String plz) {
        if (plz == null) {
        	throw new RuntimeException("PLZ muss angeben werden");
        } else if (plz.length() != 4 && land.equals("CH")) {//Da ausl�ndische Postleitzahlen nicht vierstellig sind
        	throw new RuntimeException("PLZ ist nicht 4-Stellig!");
        }
		else {
        	this.plz = plz;
        }

	}
	
	//padding rechts, f�r formatierte Ausgabe
	private static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	public String toString() {
		String s = strasse + " " + nr + ", ";
		if (land != null && !land.equals("CH"))
			s = s + land + "-";
		s = s + plz + " " + ort;
		s = String.format(padRight(s, 40));
		return s;
	}
}
