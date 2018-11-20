package gruppenarbeit2_klassenverwaltung;

public class Anschrift {
   private String land;    
   private String plz;
   private String ort;
   private String strasse;
   private String nr;       //22c ist auch erlaubt
   
   public Anschrift(String land, String plz, String ort, String strasse, String nr) {
      this.land = land;
      this.plz = plz;
      this.ort = ort;
      this.strasse = strasse;
      this.nr = nr;
   }

   //erg�nze um einen Konstruktor, so dass bei nicht vorhandener Angabe von "land" der Wert "CH" gesetzt wird
   //in diesem Fall pr�fen, ob "plz" 4-stellig ist, sonst RuntimeException ausl�sen (vgl. class Person)
   //...
   
   public String toString(){
      String s = strasse + " " + nr + ", ";
      if( land!=null && !land.equals("CH") )
         s = s + land + "-";
      s = s + plz + " " + ort;
      return s;
   }
}
