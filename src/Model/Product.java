package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Product implements Serializable {
	
	private static final long 			 serialVersionUID = 1L;

	private 			 int    		 id, 
		   		   						 // The number of product in stock.
		   		   						 stock, 
		   		   						 // The minimum number of products required.
		   		   						 minRequire, 
		   		   				 		 // The maximum number of products that can be stored.
		   		   						 maxCapacity;
	private 			 String 		 name;
	private 			 double 		 price;
	// All the parts associated with the product.
	private 			 ArrayList<Part> associatedParts;
	
	public Product() {
	}
	
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

	// Getters and Setters.
	
	public int getId() { 
		return this.id; 
	}

	private void setId(int _id) {
		this.id = _id;
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
	
	private void setName(String _name) {
		this.name = _name;
	}
	
	public String getName() {
		return this.name; 
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
	
	// Query functions.
	
	// Add a new associated part into the list.
	public void addAssociatedParts(Part newAssociatedPart) {
		this.associatedParts.add(newAssociatedPart);
	}
	
	// Delete an associated part from the list.
	public void deleteAssociatedParts(Part associatedPart) {
		Iterator<Part> iterator = this.associatedParts.iterator();
		while(iterator.hasNext()) {
			Part part = iterator.next();
			if(part.getId() == associatedPart.getId()) {
				iterator.remove();
				break;
			}
		}
	}
	
}
