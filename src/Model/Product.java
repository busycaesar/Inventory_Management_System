package Model;

import java.util.ArrayList;

public class Product {
	
	private int    			id, 
		   		   			// The number of product in stock.
		   		   			stock, 
		   		   			// The minimum number of products required.
		   		   			minRequire, 
		   		   			// The maximum number of products that can be stored.
		   		   			maxCapacity;
	private String 			name;
	private double 			price;
	// All the parts associated with the product.
	private ArrayList<Part> associatedParts;
	
	// Create a new product object.
	public Product(int _id, int _stock, int _minRequire, int _maxCapacity, String _name, double _price, ArrayList<Part> _associatedParts) {
		this.setId(_id);
		this.setStock(_stock);
		this.setMinRequire(_minRequire);
		this.setMaxCapacity(_maxCapacity);
		this.setName(_name);
		this.setPrice(_price);
		this.setAssociatedParts(new ArrayList<>());
		this.setAssociatedParts(_associatedParts);
	}

	private void setId(int _id) {
		this.id = _id;
	}

	private void setName(String _name) {
		this.name = _name;
	}

	// Get the product id.
	public int getId() { 
		return this.id; 
	}
	
	// Get the product name.
	public String getName() {
		return this.name; 
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getMinRequire() {
		return this.minRequire;
	}

	public void setMinRequire(int minRequire) {
		this.minRequire = minRequire;
	}

	public int getMaxCapacity() {
		return this.maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Part> getAssociatedParts() {
		return this.associatedParts;
	}

	public void setAssociatedParts(ArrayList<Part> associatedParts) {
		this.associatedParts = associatedParts;
	}
	
}
