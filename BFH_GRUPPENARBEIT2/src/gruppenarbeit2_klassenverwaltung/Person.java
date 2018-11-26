package gruppenarbeit2_klassenverwaltung;

import java.util.Date;

public abstract class Person {
	public static String TYP_STUDENT = "Student";
	public static String TYP_DOZENT = "Dozent";

	private String name;
	private String vorname;
	private String heimatOrt; // optional
	private Date gebDat; // optional
	private char gender; // optional
	private Anschrift anschrift; // optional

	private String personTyp; // wird von der konkreten Klasse gesetzt

	public Person(String name, String vorname, String heimatOrt, Date gebDat, char gender, String personTyp) {
		if (name == null || name.length() == 0) {
			throw new RuntimeException("Name darf nicht leer oder Leerstring sein.");
		}
		this.name = name;
		this.vorname = vorname; // name �berpr�fen, obligatorisch
		this.heimatOrt = heimatOrt;
		this.gebDat = gebDat;
		this.gender = gender;
		this.personTyp = personTyp;
	}

	public String toString() {
		return name + ", " + vorname;
	}

	public String getPersonTyp() {
		return personTyp;
	}

	/*
	 * gibt �lteste Person aus pA zur�ck (falls es keine Person gibt oder keine mit
	 * Geburtsdatum -> null; falls es mehrere gibt -> irgendeine)
	 * 
	 */
	public static Person getOldest(Person[] pA) {
		Person tempPerson = null;
		for (Person person : pA) {
			if(person.gebDat == null) {
				continue;
			}
			if (tempPerson == null) {
				tempPerson = person;
			}
			if (person.gebDat.before(tempPerson.gebDat)) {
				tempPerson = person;
			}
		}
		return tempPerson;
	}

	public static Person getYoungest(Person[] pA) {
		return null;
	}

	public abstract String getInfo();

}
