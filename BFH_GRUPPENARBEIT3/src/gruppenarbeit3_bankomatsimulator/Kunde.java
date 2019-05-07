package gruppenarbeit3_bankomatsimulator;

public class Kunde implements Comparable<Kunde> {
   private int kundenNr;
   private String name;
   private String vorname;
   private String bankkarte;
   private String pin;
   
   
   public Kunde(int kundenNr, String name, String vorname, String bankkarte, String pin) {
      if(kundenNr<1000000 || kundenNr>9999999)
         throw new IllegalArgumentException("Kundennummer ist nicht erlaubt: " + kundenNr);
      this.kundenNr = kundenNr;
      this.name = name;
      this.vorname = vorname;
      this.bankkarte = bankkarte;
      this.pin = pin;
   }
   public int getKundenNr(){
      return kundenNr;
   }
   public String toString(){
      return name + ", " + vorname + " (" + kundenNr + ")";
   }
   public boolean equals(Object o){
      if( o==this ) return true;
      if( o==null || o.getClass()!=getClass() ) return false;
      Kunde that = (Kunde)o;
      return kundenNr==that.kundenNr;
   }
   public int hashCode(){
      return kundenNr;
   }
   public int compareTo(Kunde o) {
      return kundenNr - o.kundenNr;
   }
public String getBankkarte() {
	return bankkarte;
}
public void setBankkarte(String bankkarte) {
	this.bankkarte = bankkarte;
}
public String getPin() {
	return pin;
}
public void setPin(String pin) {
	this.pin = pin;
}
public String getVorname() {
	return vorname;
}
public void setVorname(String vorname) {
	this.vorname = vorname;
}
}
