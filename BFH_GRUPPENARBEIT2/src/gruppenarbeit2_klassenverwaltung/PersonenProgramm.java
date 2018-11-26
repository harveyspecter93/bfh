package gruppenarbeit2_klassenverwaltung;

import java.util.Date;

public class PersonenProgramm {
   public static void main(String[] args) {
      Person[] pA = new Person[10];
      
      pA[0] = new Dozent( "Meier", "Markus", null, null, 'm', 100, new String[]{ "Mathematik", "Programmieren"} );
      //pA[0].setAnschrift( a );
      
      Date d1 = new Date(1998, 11, 31);
      Date d2 = new Date(1993, 02, 20);
      Date d3 = new Date(1994, 05, 30);
      Date d4 = new Date(1996, 02, 9);
      Date d5 = new Date(1998, 11, 31);
      Date d6 = new Date(1998, 11, 31);
      pA[1] = new Student( "Berber", "Ina", "Visp", d1, 'w', 1000, "Wirtschaftsinformatik" );
      pA[2] = new Student( "Drayer", "Michael", "Thun", d2, 'm', 1001, "Wirtschaftsinformatik" );
      pA[3] = new Student( "Yannick", "Frei", "Thun", d3, 'm', 1000, "Wirtschaftsinformatik" );
      pA[4] = new Student( "Ziegler", "Andrin", "Bern", d4, 'm', 1003, "Wirtschaftsinformatik" );
      pA[5] = new Student( "Enis", "Peter", "Murten", d5, 'm', 1004, "Wirtschaftsinformatik" );
      pA[6] = new Student( "Schmid", "Anton", "Lyss", d6, 'm', 1005, "Wirtschaftsinformatik" );
      
      
      for( Person p : pA )
         if( p!=null )
            System.out.println( p );
      
      for( Person p : pA )
         if( p!=null )
            System.out.println( p );
      
      Person p = Person.getOldest(pA);
      System.out.println("Älteste Person: " + p);
      
      p = Person.getYoungest(pA);
      System.out.println("Jüngste Person: " + p);
   }

}
