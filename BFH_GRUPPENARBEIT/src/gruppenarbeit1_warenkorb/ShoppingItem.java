package gruppenarbeit1_warenkorb;

public class ShoppingItem {
	/*
	* Gruppenarbeit 01: Warenkorb
	* Klasse 1o
	* Ziegler, Andrin; Frei, Yannick; Dräyer, Michael
	*/
	// the fields
	private String name;
	private double price;
	private int amountInBasket;
	private double taxRate;

	// fill the fields in the constructor
	public ShoppingItem(String name, double price, double taxRate) {
		this.name = name;
		this.price = price;
		this.taxRate = taxRate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmountInBasket() {
		return amountInBasket;
	}

	public void setAmountInBasket(int amountInBasket) {
		this.amountInBasket = amountInBasket;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	
	public int getAmountLength() {
		return (int) Math.log10(this.getAmountInBasket()) + 1;
	}
	
	/**
	     * equal if their names are same.
	     */
	    @Override
	    public boolean equals(Object obj) {
	        return this.name.equals(((ShoppingItem) obj).name);
	    }

}
