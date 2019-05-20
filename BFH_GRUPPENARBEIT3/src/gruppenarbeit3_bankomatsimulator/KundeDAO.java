package gruppenarbeit3_bankomatsimulator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
* Gruppenarbeit 3: 
* Dräyer Michael; Frei Yannick; Ziegler Andrin; 
* Klasse 2o
*/

public class KundeDAO {
   List<Kunde> all = new LinkedList<Kunde>();
   
   public KundeDAO(){//diese Kunden sollen existieren
      all.add(new Kunde(1000000, "Meier", "Gabi", "9876-2309-2765-9", "1112"));
      all.add(new Kunde(2000000, "Maurer", "Rolf", "7562-0994-4984-1", "9876"));      
      all.add(new Kunde(1000001, "Wirth", "Nicola", "9862-0994-4965-9", "7865"));
      all.add(new Kunde(1000002, "Casutt", "Ralf", "9222-2221-2323-1", "7860"));
      all.add(new Kunde(1000003, "Breset", "Maurice", "1212-8743-2435-1", "99999"));
      all.add(new Kunde(4000000, "Schindler", "Ueli", "1022-2221-2323-1","11221"));
      all.add(new Kunde(1999999, "Leuthard", "Dora", "0976-5210-5552-1", "98976"));
   }
   
   //alle auf einmal
   public List<Kunde> getAll(){
      LinkedList<Kunde> rc = new LinkedList<Kunde>(all);
      Collections.sort(rc);
      return rc;
   }
   
   //ein ganz bestimmter Kunde, falls dieser existert (null, falls nicht)
   public Kunde getKunde( int kundenNr ){
      Kunde rc = null;
      for( Kunde k : all )
         if(k.getKundenNr()==kundenNr)
            return k;
      return rc;
   }
   
   //schnelltest
   public static void main(String[] args) {
      KundeDAO data = new KundeDAO();
      System.out.println( data.getAll() );
      System.out.println( data.getKunde(1000002));
   }
}
