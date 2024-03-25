package Model;

import java.util.ArrayList;

public class Product {
	
	int    id, 
		   // The number of product in stock.
		   stock, 
		   // The minimum number of products required.
		   minRequire, 
		   // The maximum number of products that can be stored.
		   maxCapacity;
	String name;
	double price;
	// All the parts associated with the product.
	ArrayList<Part> associatedParts = new ArrayList<>();
	
	// Load the product object from the database using id.
	public Product(int _id) {
	}
	
	// Create a new product object.
	public Product(int _stock, int _minRequire, int _maxCapacity, String _name, double _price, ArrayList<Part> _associatedParts) {
		this.stock = _stock;
		this.minRequire = _minRequire;
		this.maxCapacity = _maxCapacity;
		this.name = _name;
		this.price = _price;
		this.associatedParts = _associatedParts;
		this.id = this.generateProductId();
	}
	
	// Generate a new unique id.
	private int generateProductId() {
		return 0;
	}
	
}
