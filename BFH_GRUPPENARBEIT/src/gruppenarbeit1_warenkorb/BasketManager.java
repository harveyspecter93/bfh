package gruppenarbeit1_warenkorb;

import java.util.ArrayList;
import java.util.Scanner;

public class BasketManager {
	/*
	* Gruppenarbeit 01: Warenkorb
	* Klasse 1o
	* Ziegler, Andrin; Frei, Yannick; Dr�yer, Michael
	*
	*/
	private ArrayList<ShoppingItem> availableItems;
	private ArrayList<ShoppingItem> itemsInBasket = new ArrayList<ShoppingItem>();

	public BasketManager(ArrayList<ShoppingItem> availableItems) {
		this.availableItems = availableItems;
	}

	public void showCurrentBasket() {
		double totalPrice = 0;
		double totalTax = 0;
		int itemAmountPadding = 0, itemPricePadding = 0, amountInBasket = 0;
		String itemPrice = "", itemTotalPrice = "";
		
		System.out.println("----------------------------------------------------------------");
		
		// check if the basket is currently empty
		if (itemsInBasket.isEmpty()) {	
			System.out.println("Your Basket is empty.");
		}else {
			System.out.println("Your Basket:");
			for (ShoppingItem item : itemsInBasket) {
			    
				amountInBasket = item.getAmountInBasket();
				itemAmountPadding = 4 - (int)(Math.log10(amountInBasket)+1);
				itemPricePadding = 45 - item.getName().length();
				itemPrice = String.format("%.2f", item.getPrice()) + " CHF"; 
				itemTotalPrice = String.format("%.2f", item.getPrice() * amountInBasket ) + " CHF" ;
				
				String formatedOutput = String.format(padLeft(" ", itemAmountPadding) + amountInBasket 
														+ " " + item.getName()
 														+ padLeft(itemPrice, itemPricePadding)
														+ padLeft(itemTotalPrice, 15));
				System.out.println(formatedOutput);
			    
			    totalPrice += item.getPrice() * item.getAmountInBasket();
			    totalTax += calculateTaxOfItem(item) * item.getAmountInBasket();
			}
			System.out.println("\nTotal price is " + String.format("%.2f", totalPrice) + " CHF with a total amount of " + 
					String.format("%.2f", totalTax) + " CHF taxes (" + String.format("%.2f",calculateTotalTax(totalPrice, totalTax)) + "%). \n");
			
		}
		showPossibleBuys();
	}

	public void showPossibleBuys() {
		// Anweisungen
		String number, itemName, itemPrice, itemTax;
		int itemPadding = 0;
		System.out.println("---------------------");
		System.out.println("You can buy following items:\n");
		// check if the basket is currently empty
		for (int i = 1; i < availableItems.size() + 1; i++) {

			number = i + ") ";
			itemName = availableItems.get(i - 1).getName();
			itemPrice = String.format("%.2f", availableItems.get(i - 1).getPrice()) + " CHF";
			itemTax = "(" + String.format("%.2f", calculateTaxOfItem(availableItems.get(i - 1))) + " CHF)";

			itemPadding = 40 - itemName.length();
            
            String formatedOutput = String.format(number + itemName + padLeft(itemPrice, itemPadding) + padLeft(itemTax, 15));
            System.out.println(formatedOutput);

		}

		System.out.println("---------------------\n");
		
		enableBuying();
	}
	
	public static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}
	
	private double calculateTaxOfItem(ShoppingItem shoppingItem) {
		return shoppingItem.getPrice() / 100 * shoppingItem.getTaxRate();
	}

	private double calculateTotalTax(double totalPrice, double totalTax) {
		return 100 / totalPrice * totalTax;
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
