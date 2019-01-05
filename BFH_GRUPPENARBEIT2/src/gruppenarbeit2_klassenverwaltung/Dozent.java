/*
* Gruppenarbeit 2: 
* Dräyer Michael; Frei Yannick; Ziegler Andrin; 
* Klasse 1o
*/

package gruppenarbeit2_klassenverwaltung;

import java.time.LocalDate;
import java.util.Arrays;

public class Dozent extends Person{
   private int dozId;          //obligatorisch
   private String[] faecher;   //optional
   
   int maxLength = 15;

   
   //Konstruktor mit obligatorischen Werten
   public Dozent(String name, String vorname, String heimatOrt, LocalDate gebDat, char gender, int dozId){
      super (name, vorname, heimatOrt, gebDat, gender, Person.TYP_DOZENT);
      this.checkdozId(dozId);
   }
  
   //Konstruktor bei Angabe aller Werte
   public Dozent(String name, String vorname, String heimatOrt, LocalDate gebDat, char gender, int dozId, String[] faecher){
      super( name, vorname, heimatOrt, gebDat, gender, Person.TYP_DOZENT );
      this.checkdozId(dozId);
      this.faecher = faecher;
   }
   
   //Prüft ob dozId ausgefüllt ist
   private void checkdozId(int dozId) {
       if (dozId != 0) {
    	   this.dozId = dozId;
       } 
		else {
			throw new RuntimeException("Dozent muss zwingend eine ID haben!");
       }
	}

   //padding rechts, für formatierte Ausgabe
   private static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}
   
   //Obligatorische Informationen
   public String toString(){
	   String resultDozent = super.toString() + '\t'  + dozId;
       return resultDozent;
   }
   
   //Alle Informationen 
   public String getInfo(){
		String basis = super.getInfo() + '\t' + dozId +'\t';
		//Ausgabe Heimatort
		if(faecher != null && faecher.length > 0 ) {
			basis = basis + Arrays.toString(faecher);
		} else {
			basis = basis + String.format(padRight("k.A.", maxLength));
       }
		return basis;
   }
   
   //Getter optionales Feld
   public String[] getFaecher() {
	   return faecher;
   }
   
   //Setter optionales Feld
   public void setFaecher(String[] faecher) {
	   this.faecher = faecher;
   }
   
}

