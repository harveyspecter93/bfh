package gruppenarbeit1_warenkorb;

import java.util.ArrayList;
import java.util.Scanner;

public class BasketManager {
	/*
	* Gruppenarbeit 01: Warenkorb
	* Klasse 1o
	* Ziegler, Andrin; Frei, Yannick; Dräyer, Michael
	*
	*/
	private ArrayList<ShoppingItem> availableItems;
	private ArrayList<ShoppingItem> itemsInBasket = new ArrayList<ShoppingItem>();

	public BasketManager(ArrayList<ShoppingItem> availableItems) {
		this.availableItems = availableItems;
	}

	public void showCurrentBasket() {
		System.out.println("----------------------------------------------------------------");
		
		// check if the basket is currently empty
		if (itemsInBasket.isEmpty()) {	
			System.out.println("Your Basket is empty.");
		}else {
			System.out.println("Your Basket:");
			for (ShoppingItem item : itemsInBasket) {
			    System.out.println(item.getAmountInBasket() + " " + item.getName() + " " + String.format("%.2f", item.getPrice()) + " CHF " + String.format("%.2f", item.getPrice() * item.getAmountInBasket()) + " CHF");
			}
			
		}
		showPossibleBuys();
	}

	public void showPossibleBuys() {
		// Anweisungen
		System.out.println("---------------------");
		System.out.println("\nYou can buy following items:\n");
		// check if the basket is currently empty
		for (int i = 1; i < availableItems.size() + 1; i++) {
			// calculate tax of item
			double tax = calculateTaxOfItem(availableItems.get(i - 1));
			System.out.println(i + ") " + availableItems.get(i - 1).getName() + "   "
					+ String.format("%.2f", availableItems.get(i - 1).getPrice()) + " CHF " + "("+ String.format("%.2f", tax) +" CHF)" );
		}
		enableBuying();
	}

	private double calculateTaxOfItem(ShoppingItem shoppingItem) {
		return shoppingItem.getPrice() / 100 * shoppingItem.getTaxRate();
	}

	public void enableBuying() {
		System.out.println("What item would you like to add to your basket? (1,2,3 or q to quit)");
		// check for Quit
		int productToBuy = 0;
		while(productToBuy <= 0) {
			productToBuy = readNumber();
		}
		System.out.println("Thank you! Got " + productToBuy);
	
		ShoppingItem itemToBuy = availableItems.get(productToBuy - 1); 
		
		System.out.println("How many " + itemToBuy.getName() + " you'd like to add to your basket? (Integer or a to abort)");
		// check for Quit
		int amountToBuy = readAmount();
		if(amountToBuy != 0) {
		addItemToBasket(itemToBuy, amountToBuy);
		}else {
			System.out.println("You changed your mind and don't want to add " + itemToBuy.getName());
		}
		showCurrentBasket();
	}

	public void addItemToBasket(ShoppingItem shoppingItem, int amount) {
		// check if basket is empty
		if (itemsInBasket.isEmpty()) {
			itemsInBasket.add(shoppingItem);
			shoppingItem.setAmountInBasket(amount);
			System.out.println("Item " + shoppingItem.getName() + " was added to the basket. Thanks!");
		} else {
			
			ShoppingItem itemInBasket = getItemFromBasket(shoppingItem);
			if( itemInBasket instanceof ShoppingItem){
				//add amount of item in basket
				itemInBasket.setAmountInBasket(itemInBasket.getAmountInBasket() + amount);
				System.out.println("Successfully updated amount of " + shoppingItem.getName() + "!");
			}else {
				//add item to the basket with given amount
				itemsInBasket.add(shoppingItem);
				shoppingItem.setAmountInBasket(amount);
				System.out.println("Item " + shoppingItem.getName() + " was added to the basket. Thanks!");
			}
		}
	}

	public int readNumber() {
		Scanner input = new Scanner(System.in);
		int number = 0;

		// check for valid integer
		while (number <= 0) {
			while (!input.hasNextInt()) {
				// check for quit
				
				if("q".equals(input.nextLine())) {
					System.out.println("Thank you for shopping in our store. We value your patronage.\r\n" + 
							"Please visit us again soon! Andrin, Yannick & Michael");
					System.exit(1);
				}
				System.out.println("That's not a valid input!");
				number = 0;
			}
			
			number = input.nextInt();
			if(number > availableItems.size()) {
				System.out.println("This is not a valid item! Please try again..");
				number = 0;
			}
		}
		return number;
	}
	
	public int readAmount() {
		Scanner input = new Scanner(System.in);
		int amount = 0;

		// check for valid integer
		while (amount <= 0) {
			while (!input.hasNextInt()) {
				// check for quit
				
				if("a".equals(input.nextLine())) {
					System.out.println("Aborting..");
				}
				
				return 0;
			}
			
			amount = input.nextInt();
		}
		
		
		System.out.println("Thank you! Got " + amount);
		return amount;
	}
	
	public ShoppingItem getItemFromBasket(ShoppingItem item) {
		
		for (ShoppingItem shoppingItemInBasket : itemsInBasket) {
			if (shoppingItemInBasket.getName() == item.getName()) {
				// if the names are equal, item is already in basket
				return shoppingItemInBasket;
			} 
		}
		
		return null;
	}

}
