package Model;

import java.util.ArrayList;

public class Part {

	private int    id, 
		   		   // The number of parts in stock.
		   		   stock, 
		   		   // The minimum number of parts required.
		   		   minRequire, 
		   		   // The maximum number of parts that can be stored.
		   		   maxCapacity;
	private String name;
	private double price;
	
	// Load the product object from the database using id.
	public Part(int _id) {
		this.loadPart(_id);
	}
	
	// Create a new product object.
	public Part(int _stock, int _minRequire, int _maxCapacity, String _name, double _price, ArrayList<Part> _associatedParts) {
		this.stock 		 = _stock;
		this.minRequire  = _minRequire;
		this.maxCapacity = _maxCapacity;
		this.name 		 = _name;
		this.price 		 = _price;
		this.id 		 = this.generatePartId();
	}
	
	private void loadPart(int id) {
		// Load the part using id.
	}
	
	// Generate a new unique id.
	private int generatePartId() {
		return 0;
	}
	
	// Return the part id.
	public int getId() { return this.id; }
	
	// Get the part name.
	public String getName() { return this.name; }
	
}
