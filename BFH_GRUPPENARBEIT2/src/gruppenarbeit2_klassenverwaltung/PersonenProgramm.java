/*
* Gruppenarbeit 2: 
* Dr�yer Michael; Frei Yannick; Ziegler Andrin; 
* Klasse 1o
*/

package gruppenarbeit2_klassenverwaltung;

import java.time.LocalDate;




public class PersonenProgramm {
   public static void main(String[] args) {
	  int maxLength = 15;
      Person[] pA = new Person[8]; 
      
      pA[0] = new Dozent( "Meier", "Markus", null, null, 'm', 100, new String[]{ "Mathematik", "Programmieren"} );
      pA[0].setAnschrift(new Anschrift("CH", "3613", "Steffisburg", "Thunstrasse", "18" ));

      LocalDate d1 = LocalDate.of(1970,01,20);
      pA[1] = new Student( "Berber", "Ina", "Visp", d1, 'w', 1000, "Chemie" );
      //Deutschland? 5- Stellige Postleitzahl
      pA[1].setAnschrift(new Anschrift("DE", "77977", "Rust", "Europaparkallee", "203c" ));
          
      LocalDate d2 = LocalDate.of(1993,02,20);
      pA[2] = new Student( "Dr�yer", "Michael", "Thun", d2, 'm', 1001, "Wirtschaftsinformatik" );
      pA[2].setAnschrift(new Anschrift("3604", "Thun", "Ulmenweg", "65" ));
      
      LocalDate d3 = LocalDate.of(1994,05,30);
      pA[3] = new Student( "Frei", "Yannick", "Thun", d3, 'm', 1002, "Wirtschaftsinformatik" );
      pA[3].setAnschrift(new Anschrift("3600", "Thun", "Bahnhofstrasse", "8" ));
      
      LocalDate d4 = LocalDate.of(1996,02,9);
      pA[4] = new Student( "Ziegler", "Andrin", "", d4, 'm', 1003, "Wirtschaftsinformatik" );
      pA[4].setAnschrift(new Anschrift("3097", "Liebefeld", "Könizstrasse", "180" ));
      
      LocalDate d5 = LocalDate.of(2002,06,24);
      pA[5] = new Student( "Enis", "Peter", "Murten", d5, 'm', 1004, "" );
      pA[5].setAnschrift(new Anschrift("3122", "Kehrsatz", "Ulmenweg", "123" ));
      
      LocalDate d6 = LocalDate.of(1931,8,12);
      pA[6] = new Student( "Schmid", "Anton", "Lyss", d6, ' ', 1005, "Architektur" );
      
      pA[7] = new Dozent( "H�berli", "Gustav", "Frutigen", null, 'm', 101, new String[]{ "FRWI", "Programmieren"} );
      pA[7].setAnschrift(new Anschrift("3122", "Kirchdorf", "Dorf", "71" ));
      

	
      String title1 = String.format(padRight("Nachname", maxLength) + padRight("Vorname", maxLength) + "\tID ");
      String title2 = String.format(padRight("Nachname", maxLength) + padRight("Vorname", maxLength) + padRight("Heimatort", maxLength) + padRight("Geburtsdatum", maxLength) + padRight("Geschlecht", maxLength) + padRight("Anschrift", 40) + padRight("Typ", 13) + padRight("ID", 8) + padRight("Fach", maxLength));
	
      //Ausgabe obligatorische Werte
      System.out.println("-------------------------------------");
      System.out.println("OBLIGATORISCHE WERTE");
      System.out.println("-------------------------------------");
      System.out.println(title1);
      System.out.println("-------------------------------------");
      for( Person p : pA ) {
         if( p !=null ) {
            System.out.println( p );
         }
      }
      
      //Ausgabe freiwillige Werte
      System.out.println("-------------------------------------");
      System.out.println("\n\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
      System.out.println("FREIWILLIGE WERTE");
      System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
      System.out.println(title2);
      System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
     
      for( Person p : pA ) {
          if( p !=null ) {
             System.out.println( p.getInfo() );
          }
       }
      
      //Ausgabe �lteste und j�ngste Person
      System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
      System.out.println("\n\n--------------------------------------------------------------");
      System.out.println("�lteste und j�ngste Person in der Liste");
      System.out.println("--------------------------------------------------------------");
      Person.getOldest(pA);
      
      Person.getYoungest(pA);
      
   }
   
	//padding rechts, f�r formatierte Ausgabe
	private static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}
   
   

}
