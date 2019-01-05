/*
* Gruppenarbeit 2: 
* Dräyer Michael; Frei Yannick; Ziegler Andrin; 
* Klasse 1o
*/

package gruppenarbeit2_klassenverwaltung;

import java.time.LocalDate;

public class Student extends Person {
   private int matNr;             //obligatorisch
   private String studiengang;    //optional
   
   
   int maxLength = 15;
   //Konstruktor mit obligatorischen Werten
   public Student(String name, String vorname, String heimatOrt, LocalDate gebDat, char gender, int matNr) {
	   super(name, vorname, heimatOrt, gebDat, gender, Person.TYP_STUDENT);
	   this.checkmatNr(matNr);	   
   }
   
   //Konstruktor bei Angabe aller Werte
   public Student(String name, String vorname, String heimatOrt, LocalDate d1, char gender, int matNr, String studiengang) {
      super(name, vorname, heimatOrt, d1, gender, Person.TYP_STUDENT);
      this.checkmatNr(matNr);
      this.studiengang = studiengang;
   }
   
   //Prüft ob matNr ausgefüllt ist
   private void checkmatNr(int matNr) {
       if (matNr != 0) {
    	   this.matNr = matNr;
       } 
		else {
			throw new RuntimeException("Student muss zwingend eine ID haben!");
       }
	}

   //padding rechts, für formatierte Ausgabe
   private static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}   
   
   //Obligatorische Informationen
	public String toString() {
		String studentresult  = super.toString() + '\t' + matNr;
		return studentresult;
	}
   
	//Alle Informationen
	public String getInfo() {
		String basis = super.getInfo() + '\t' +  matNr +'\t';
		//Ausgabe Heimatort
		if(studiengang != null && studiengang.length() > 0 ) {
			basis = basis + "[" + studiengang + "]";
		} else {
			basis = basis + String.format(padRight("k.A.", maxLength));
        }
		return basis;
	}
	
	//Getter optionales Feld
	public String getStudiengang() {
		return studiengang;
	}
	   
	//Setter optionales Feld
	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

}
