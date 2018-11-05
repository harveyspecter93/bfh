package gruppenarbeit1_warenkorb;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BasketManager {
	/*
	 * Gruppenarbeit 01: Warenkorb Klasse 1o Ziegler, Andrin; Frei, Yannick; Dräyer,
	 * Michael
	 */

	private ArrayList<ShoppingItem> availableItems;
	private ArrayList<ShoppingItem> itemsInBasket = new ArrayList<ShoppingItem>();
	private MessageHandler messages;

	/**
	 * Konstruktor, wird aufgerufen bei der Insanziierung des BasketManagers Setzt
	 * die Verfügbaren Artikel (availableItems-Parameter) als Klassenattribut
	 * Erzeugt eine neue Instanz der MessageHandler-Klasse (mithilfe des
	 * bundle-Parameters) und speichert diese Instanz ebenfalls als Klassenattribut
	 *
	 * @param availableItems <java.util.ArrayList<ShoppingItem>> Verfügbare Artikel
	 *                       im Shop
	 * @param bundle         <java.util.ResourceBundle> ResourceBundle zum Zugriff
	 *                       auf die Texte
	 */
	public BasketManager(ArrayList<ShoppingItem> availableItems, ResourceBundle bundle) {
		this.availableItems = availableItems;
		this.messages = new MessageHandler(bundle);
	}

	/**
	 * Gibt den aktuellen Warenkorb in der Konsole aus
	 */
	void showCurrentBasket() {

		double totalPrice = 0;
		double totalTax = 0;
		int itemAmountPadding = 0, itemPricePadding = 0, amountInBasket = 0;
		String itemPrice = "", itemTotalPrice = "";

		System.out.println("----------------------------------------------------------------");

		// check if the basket is currently empty
		if (itemsInBasket.isEmpty()) {
			System.out.println(messages.getString("EMPTY_BASKET"));
		} else {
			System.out.println(messages.getString("YOUR_BASKET"));

			for (ShoppingItem item : itemsInBasket) {

				amountInBasket = item.getAmountInBasket();
				itemAmountPadding = getMaxItemAmountLength() - (int) (Math.log10(amountInBasket) + 1);
				itemPricePadding = 40 - item.getName().length();
				itemPrice = String.format("%.2f", item.getPrice()) + " CHF";
				itemTotalPrice = String.format("%.2f", item.getPrice() * amountInBasket) + " CHF";

				String formatedOutput = String.format(padLeft(" ", itemAmountPadding) + amountInBasket + " "
						+ item.getName() + padLeft(itemPrice, itemPricePadding) + padLeft(itemTotalPrice, 15));

				System.out.println(formatedOutput);

				totalPrice += item.getPrice() * item.getAmountInBasket();
				totalTax += calculateTaxOfItem(item) * item.getAmountInBasket();
			}

			System.out.println(messages.getString("BASKET_AMOUNT", String.format("%.2f", totalPrice),
					String.format("%.2f", totalTax), String.format("%.2f", calculateTotalTax(totalPrice, totalTax))));

		}
	}

	/**
	 * Gibt die zu kaufenden Artikel mit Preis und MwSt-Angabe in der Konsole aus
	 */
	void showPossibleBuys() {
		String number, itemName, itemPrice, itemTax;
		int itemPadding = 0;

		System.out.println("---------------------");
		System.out.println(messages.getString("ITEMS_AVAILABLE"));

		for (int i = 1; i < availableItems.size() + 1; i++) {

			number = i + ") ";
			itemName = availableItems.get(i - 1).getName();
			itemPrice = String.format("%.2f", availableItems.get(i - 1).getPrice()) + " CHF";
			itemTax = "(" + String.format("%.2f", calculateTaxOfItem(availableItems.get(i - 1))) + " CHF)";

			itemPadding = 40 - itemName.length();

			String formatedOutput = String
					.format(number + itemName + padLeft(itemPrice, itemPadding) + padLeft(itemTax, 15));
			System.out.println(formatedOutput);

		}

		System.out.println("---------------------\n");

	}

	/**
	 * Fügt dem String s ein Left-Padding von n hinzu
	 * 
	 * @param s <java.lang.String> String, dem ein Left-Padding hinzugefügt werden
	 *          soll
	 * @param n <int> Grösse des Left-Padding
	 * @return
	 */
	private static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}

	/**
	 * Berechnet die MwSt eines gegebenen Items (shoppingItem)
	 * 
	 * @param shoppingItem <gruppenarbeit1_warenkorb.ShoppingItem Item, von welchem
	 *                     die MwSt berechnet werden soll
	 * @return
	 */
	private double calculateTaxOfItem(ShoppingItem shoppingItem) {
		return shoppingItem.getPrice() / 100 * shoppingItem.getTaxRate();
	}

	/**
	 * Berechnet den Prozentsatz der enthaltenen Mehrwertsteuer im Gesamtpreis
	 * 
	 * @param totalPrice <double> Der Gesamtpreis des Warenkorbs
	 * @param totalTax   <double> Die Total-Mehrwertsteuer, die im Gesamtpreis
	 *                   enthalten sind
	 * @return
	 */
	private double calculateTotalTax(double totalPrice, double totalTax) {
		return 100 * (totalTax / (totalPrice - totalTax));
	}

	/**
	 * Gibt dem Benutzer die Möglichkeit einen Artikel zu kaufen
	 */
	boolean enableBuying() {
		System.out.println(messages.getString("ITEM_CHOICE"));

		int productToBuy = readNumber();

		if (productToBuy == 0) {
			return false;
		}

		ShoppingItem itemToBuy = availableItems.get(productToBuy - 1);

		System.out.println(messages.getString("AMOUNT_ADD", itemToBuy.getName()));

		// check for Quit
		int amountToBuy = readAmount();
		if (amountToBuy != 0) {
			addItemToBasket(itemToBuy, amountToBuy);
		} else {
			System.out.println(messages.getString("CHANGED_MIND", itemToBuy.getName()));
		}

		return true;

	}

	/**
	 * Fügt dem Warenkorb die Menge (amount) des Artikels (shoppingItem) hinzu.
	 * 
	 * @param shoppingItem <gruppenarbeit1_warenkorb.ShoppingItem> Artikel, der dem
	 *                     Warenkorb hinzugefügt werden soll
	 * @param amount       <int> Die Anzahl, die vom Artikel zum Warenkorb
	 *                     hinzugefügt werden soll.
	 */
	private void addItemToBasket(ShoppingItem shoppingItem, int amount) {

		// check if basket is empty
		if (itemsInBasket.isEmpty()) {
			itemsInBasket.add(shoppingItem);
			shoppingItem.setAmountInBasket(amount);
			System.out.println(messages.getString("ITEM_ADDED", shoppingItem.getName()));
		} else {

			if (itemsInBasket.contains(shoppingItem)) {
				// item already in basket => add amount of item in basket
				ShoppingItem itemInBasket = getItemFromBasket(shoppingItem);
				itemInBasket.setAmountInBasket(itemInBasket.getAmountInBasket() + amount);
				System.out.println(messages.getString("SUCCESS_UPDATE", shoppingItem.getName()));
			} else {
				// item not already in basket => add item to the basket with given amount
				itemsInBasket.add(shoppingItem);
				shoppingItem.setAmountInBasket(amount);
				System.out.println(messages.getString("ITEM_ADDED", shoppingItem.getName()));
			}
		}

	}

	/**
	 * Liest anhand eines java.util.Scanner die Nummer des Artikels, welcher dem
	 * Warenkorb hinzugefügt werden soll
	 * 
	 * @return
	 */
	private int readNumber() {
		Scanner input = new Scanner(System.in);
		int number = 0;

		// check for valid integer
		while (number <= 0) {
			while (!input.hasNextInt()) {
				// check for quit

				if ("q".equals(input.nextLine())) {
					System.out.println(messages.getString("BYE"));
					return 0;
				}
				System.out.println(messages.getString("INVALID_INPUT"));
				input.nextLine(); //needs to consume input
				number = 0;
			}

			number = input.nextInt();
			if (number > availableItems.size() || number <= 0) {
				System.out.println(messages.getString("INVALID_ITEM"));
				number = 0;
			}
		}

		return number;
	}

	/**
	 * Liest anhand eines java.util.Scanner die gwünschte Menge des Artikels
	 * 
	 * @return
	 */
	private int readAmount() {
		Scanner scanAmount = new Scanner(System.in);
		int amount = 0;

		// check for valid integer
		while (amount <= 0) {
			while (!scanAmount.hasNextInt()) {
				// check for quit

				if ("a".equals(scanAmount.nextLine())) {
					System.out.println(messages.getString("ABORT"));
				}

				return 0;
			}

			amount = scanAmount.nextInt();
		}

		return amount;
	}

	/**
	 * Gibt den Artikel zurück, sofern dieser bereits im Warenkorb ist
	 * 
	 * @param item <gruppenarbeit1_warenkorb.ShoppingItem> Artikel, der geprüft
	 *             werden soll
	 * @return
	 */
	private ShoppingItem getItemFromBasket(ShoppingItem item) {

		ShoppingItem basketItem = null;

		for (ShoppingItem itemInBasket : itemsInBasket) {
			if (itemInBasket.getName() == item.getName()) {
				// we checked already, that there is an item where names are the same
				basketItem = itemInBasket;
			}
		}

		// basketItem will not be null, cause we checked the names with the overridden
		// contains method
		return basketItem;
	}

	/**
	 * Gibt die Länge(Anzahl Zeichen) der höchsten Menge im Warenkorb zurück. Diese
	 * wird zur Bestimmung des Left-Padding in der showCurrentBasket-Methode
	 * gebraucht.
	 * 
	 * @return
	 */
	private int getMaxItemAmountLength() {

		// set default
		int maxLength = 5;

		for (ShoppingItem item : itemsInBasket) {

			if (item.getAmountLength() >= maxLength) {
				maxLength = item.getAmountLength() + 1; // at least a left padding of 1
			}
		}

		return maxLength;
	}

}
