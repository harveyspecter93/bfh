package gruppenarbeit2_klassenverwaltung;

import java.util.Date;

public class PersonenProgramm {
   public static void main(String[] args) {
      Person[] pA = new Person[10];
      
      pA[0] = new Dozent( "Meier", "Markus", null, null, 'm', 100, new String[]{ "Mathematik", "Programmieren"} );
      //pA[0].setAnschrift( a );
      
      Date d = new Date(1998, 11, 31);
      pA[1] = new Student( "Berber", "Ina", "Visp", d, 'w', 1000, "Wirtschaftsinformatik" );
      
      
      for( Person p : pA )
         if( p!=null )
            System.out.println( p );
      
      for( Person p : pA )
         if( p!=null )
            System.out.println( p );
      
      Person p = Person.getOldest(pA);
      System.out.println("�lteste Person: " + p);
      
      p = Person.getYoungest(pA);
      System.out.println("J�ngste Person: " + p);
   }

}
