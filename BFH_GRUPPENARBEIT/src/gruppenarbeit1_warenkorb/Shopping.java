package gruppenarbeit1_warenkorb;

import java.util.ArrayList;

public class Shopping {
	/*
	* Gruppenarbeit 01: Warenkorb
	* Klasse 1o
	* Ziegler, Andrin; Frei, Yannick; Dräyer, Michael
	*
	*/
	public static void main(String[] args) {
		// stock the shop!
		ArrayList<ShoppingItem> availableItems = stockTheshop();

		BasketManager basketManager = new BasketManager(availableItems);
		basketManager.showCurrentBasket();
		basketManager.enableBuying();
	}

	private static ArrayList<ShoppingItem> stockTheshop() {
		// Welcome to the shop!
		System.out.println("----------------------------------------------------------------");
		System.out.println("Welcome to the Shop! - we just restocked for you!");

		// create list of availabe Items and fill in the item objects
		ArrayList<ShoppingItem> stock = new ArrayList<ShoppingItem>();
		stock.add(new ShoppingItem("Walliser Roggenbrot", 4.10, 2.5));
		stock.add(new ShoppingItem("Engadiner Nusstorte", 12.90, 7.7));
		stock.add(new ShoppingItem("Langatun Whiskey OLD BEAR", 225, 7.7));
		return stock;
	}

}
