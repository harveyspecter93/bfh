/*
* Gruppenarbeit 2: 
* Dräyer Michael; Frei Yannick; Ziegler Andrin; 
* Klasse 1o
*/
package gruppenarbeit2_klassenverwaltung;

import java.time.LocalDate;

public abstract class Person {
	public static String TYP_STUDENT = "Student";
	public static String TYP_DOZENT = "Dozent ";
	int maxLength = 15;

	private String name;
	private String vorname;
	private String heimatOrt; // optional
	private LocalDate gebDat; // optional
	private char gender; // optional
	private Anschrift anschrift; // optional
	
	private String personTyp; // wird von der konkreten Klasse gesetzt
	
	//Konstruktor Person
	public Person(String name, String vorname, String heimatOrt, LocalDate d1, char gender, String personTyp) {
		this.checkname(name);
		this.checkvorname(vorname);
		this.heimatOrt = heimatOrt;
		this.gebDat = d1;
		this.gender = gender;
		this.personTyp = personTyp;
	}
	
	//Prüft ob name ausgefüllt ist
	private void checkname(String name) {
        if (name == null) {
        	throw new RuntimeException("Name muss angegeben werden!");
        } else if (name.length() == 0) {
        	throw new RuntimeException("Name muss angegeben werden!");
        }
		else {
        	this.name = name;
        }
	}
	
	//Prüft ob vorname ausgefüllt ist	
	private void checkvorname(String vorname) {
        if (vorname == null) {
        	throw new RuntimeException("Vorname muss angegeben werden!");
        } else if (vorname.length() == 0) {
        	throw new RuntimeException("Vorname muss angegeben werden!");
        }
		else {
        	this.vorname = vorname;
        }
	}
	
	//padding rechts, für formatierte Ausgabe
	private static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}
	
	private static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}

	public String toString() {
		
		String formatedName = String.format(padRight(name, maxLength) + padRight(vorname, maxLength));
		return formatedName;
	}
	
	public String getInfo() {
		String basis = String.format(padRight(name, maxLength) + padRight(vorname, maxLength));
		//Ausgabe Heimatort
		if(heimatOrt != null && heimatOrt.length() > 0 ) {
			basis = basis + String.format(padRight(heimatOrt, maxLength));
		} else {
			basis = basis + String.format(padRight("k.A.", maxLength));
        }
		//Ausgabe Geburtsdatum
		if(gebDat != null) {
			basis = basis + gebDat;
		} else {
			basis = basis + String.format(padRight("k.A.", 10));
        }
		//Ausgabe Gender
		if(gender != ' ') {
			basis = basis + String.format(padRight("", 5)) + gender + String.format(padRight("", 14));
		} else {
			basis = basis + String.format(padRight("", 5)) + String.format(padRight("k.A.", maxLength));
        }
		//Ausgabe Anschrift
		
		
		if(anschrift != null) {
			basis = basis + anschrift;
		} else {
			basis = basis + String.format(padRight("k.A.", 40));
        }
		//Ausgabe Typ
		
		
		if(personTyp != null) {
			basis = basis + personTyp;
		} else {
			basis = basis + String.format(padLeft("k.A.", 1000));
        }
		
		
		return basis;
	}
	
	



	/*
	 * gibt ï¿½lteste Person aus pA zurï¿½ck (falls es keine Person gibt oder keine mit
	 * Geburtsdatum -> null; falls es mehrere gibt -> irgendeine)
	 * 
	 */
	
	//Ausgabe der ältesten Person
	public static Person getOldest(Person[] pA) {
		Person tempPerson = null;
		
		for (Person person : pA) {			
			if(person.getGebDate() == null) {
				continue;
			}
			if (tempPerson == null) {
				tempPerson = person;
			}
			if (person.getGebDate().isBefore(tempPerson.getGebDate())) {
				tempPerson = person;
			}
		}
		System.out.println("Die älteste Person ist " + tempPerson.getName() + " " + tempPerson.getVorname() + " und darf am " + tempPerson.getGebDate() + " ihren Geburtstag feiern! \n");
		return tempPerson ;	
	}

	//Ausgabe jüngste Person
	public static Person getYoungest(Person[] pA) {
		Person tempPerson = null;
		
		for (Person person : pA) {			
			if(person.getGebDate() == null) {
				continue;
			}
			if (tempPerson == null) {
				tempPerson = person;
			}
			if (person.getGebDate().isAfter(tempPerson.getGebDate())) {
				tempPerson = person;
			}
		}
		System.out.println("Die jüngste Person ist " + tempPerson.getName() + " " + tempPerson.getVorname() + " und darf am " + tempPerson.getGebDate() + " ihren Geburtstag feiern! \n");
		return tempPerson ;
	}
	

	//Getter obligatorische Felder
	public String getName() {
		return name;
	}

	public String getVorname() {
		return vorname;
	}

	//Getter optionale Felder
	public String getHeimatOrt() {
		return heimatOrt;
	}
	
	public LocalDate getGebDate() {
		return gebDat;
	}
	
	public char getGender() {
		return gender;
	}
	
	public String getPersonTyp() {
		return personTyp;
	}
	
	//Setter optionale Felder
	public void setHeimatOrt(String heimatOrt) {
		this.heimatOrt = heimatOrt;
	}
	
	public void setGebDate(LocalDate gebDat) {
		this.gebDat = gebDat;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public void setPersonTyp(String personTyp) {
		this.personTyp = personTyp;
	}
	
	public void setAnschrift(Anschrift anschrift) {
		this.anschrift = anschrift;
	}


}
