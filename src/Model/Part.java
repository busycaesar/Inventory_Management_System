package Model;

import java.util.ArrayList;

public class Part {

	int    id, 
		   // The number of parts in stock.
		   stock, 
		   // The minimum number of parts required.
		   minRequire, 
		   // The maximum number of parts that can be stored.
		   maxCapacity;
	String name;
	double price;
	
	// Load the product object from the database using id.
	public Part(int _id) {
	}
	
	// Create a new product object.
	public Part(int _stock, int _minRequire, int _maxCapacity, String _name, double _price, ArrayList<Part> _associatedParts) {
		this.stock = _stock;
		this.minRequire = _minRequire;
		this.maxCapacity = _maxCapacity;
		this.name = _name;
		this.price = _price;
		this.id = this.generatePartId();
	}
	
	// Generate a new unique id.
	private int generatePartId() {
		return 0;
	}
	
}
