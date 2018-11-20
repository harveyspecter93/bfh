package gruppenarbeit2_klassenverwaltung;

import java.util.Date;

public class Dozent extends Person{
   private int dozId;          //obligatorisch
   private String[] faecher;   //optional
   
   public Dozent(String name, String vorname, String heimatOrt, Date gebDat, char gender, int dozId){
      super ()
   }
   
   public Dozent(String name, String vorname, String heimatOrt, Date gebDat, char gender, int dozId, String[] faecher){
      super( name, vorname, heimatOrt, gebDat, gender, Person.TYP_DOZENT );
      this.dozId = dozId;
      this.faecher = faecher;
   }

   public String toString(){
       return super.toString() + " [" + dozId + "]" + " (" + getPersonTyp() + ")";
   }
   
   public String getInfo(){
      return null;
   }
}

