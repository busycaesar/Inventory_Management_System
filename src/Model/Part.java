package Model;

import java.util.ArrayList;

public abstract class Part {

	private int    id, 
		   		   // The number of parts in stock.
		   		   stock, 
		   		   // The minimum number of parts required.
		   		   minRequire, 
		   		   // The maximum number of parts that can be stored.
		   		   maxCapacity;
	private String name;
	private double price;
	
	// Create a new part object.
	public Part(int _id, int _stock, int _minRequire, int _maxCapacity, String _name, double _price) {
		this.id 		 = _id;
		this.stock 		 = _stock;
		this.minRequire  = _minRequire;
		this.maxCapacity = _maxCapacity;
		this.name 		 = _name;
		this.price 		 = _price;
	}
	
	// Return the part id.
	public int getId() { return this.id; }
	
	// Get the part name.
	public String getName() { return this.name; }
	
}
