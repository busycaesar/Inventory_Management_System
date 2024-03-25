package Model;

import java.util.ArrayList;

public class Product {
	
	int id, 
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
	
}
