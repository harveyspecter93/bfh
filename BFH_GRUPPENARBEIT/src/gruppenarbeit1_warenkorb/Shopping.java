package gruppenarbeit1_warenkorb;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Shopping {
	/*
	* Gruppenarbeit 01: Warenkorb
	* Klasse 1o
	* Ziegler, Andrin; Frei, Yannick; Dräyer, Michael
	*/
	public static void main(String[] args) {
		ResourceBundle bundle = chooseLanguage();
		// stock the shop!
		ArrayList<ShoppingItem> availableItems = stockTheshop(bundle);
		BasketManager basketManager = new BasketManager(availableItems, bundle);
	}

	private static ArrayList<ShoppingItem> stockTheshop(ResourceBundle bundle) {
		// Welcome to the shop!
		System.out.println("----------------------------------------------------------------");
		System.out.println(bundle.getString("GREETING"));

		// create list of availabe Items and fill in the item objects
		ArrayList<ShoppingItem> stock = new ArrayList<ShoppingItem>();
		stock.add(new ShoppingItem("Walliser Roggenbrot", 4.10, 2.5));
		stock.add(new ShoppingItem("Engadiner Nusstorte", 12.90, 7.7));
		stock.add(new ShoppingItem("Langatun Whiskey OLD BEAR", 225, 7.7));
		return stock;
	}

	private static ResourceBundle chooseLanguage() {
		Scanner input = new Scanner(System.in);
		String lang = "";
		ResourceBundle currentBundle;
		Locale englishLocale = new Locale("en", "EN");
		ResourceBundle bundleEnglish = ResourceBundle.getBundle( "resource/strings_en", englishLocale );
		Locale germanLocale = new Locale("de", "DE");
		ResourceBundle bundleGerman = ResourceBundle.getBundle( "resource/strings_de", germanLocale );
	
		// choose language
		System.out.println("----------------------------------------------------------------");
		System.out.println("Wählen Sie Ihre Sprache! / Choose your language!");
		System.out.println("de für deutsch, en für englisch / en for english de for german!");		
		
		
		//default is german
		currentBundle = bundleGerman;
		
		while(input.hasNextLine()) {
			lang = input.nextLine();
			if ("en".equals(lang)) {
				currentBundle = bundleEnglish;	
				System.out.println(currentBundle.getString("LANGUAGE_CHOSEN"));
				break;
			}else if("de".equals(lang)){
				currentBundle = bundleGerman;				
				System.out.println(currentBundle.getString("LANGUAGE_CHOSEN"));
				break;
			}
			System.out.println("No language chosen! try again. Noch keine Sprache ausgewählt! Versuchen Sie es erneut.");
		}
		return currentBundle;
	}

}
