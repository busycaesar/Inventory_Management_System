package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {

	private ArrayList<Product> allProducts;
	private ArrayList<Part>    allParts;
	
	public Inventory(ArrayList<Product> _allProducts, ArrayList<Part> _allParts) {
		this.allProducts = new ArrayList<>();
		this.allProducts = _allProducts;
		this.allParts 	 = new ArrayList<>();
		this.allParts 	 = _allParts;
	}
	
	// Add a new part to the inventory.
	public void addPart(Part part) {
		this.allParts.add(part);
	}
	
	// Add a new product to the inventory.
	public void addProduct(Product product) {
		this.allProducts.add(product);
	}
	
	// Get a product using its id.
	public Product searchProductById(int id) {
		for(Product product: this.allProducts) {
			if(product.getId() == id) return product;
		}
		return null;
	}
	
	// Get a product using its id.
	public Part searchPartById(int id) {
		for(Part part: this.allParts) {
			if(part.getId() == id) return part;
		}
		return null;
	}
	
	// Get a product using its id.
	public Product searchProductByName(String name) {
		for(Product product: this.allProducts) {
			if(product.getName() == name) return product;
		}
		return null;
	}
	
	// Get a part using its name.
	public Part searchPartByName(String name) {
		for(Part part: this.allParts) {
			if(part.getName() == name) return part;
		}
		return null;
	}
	
	// Update an existing product.
	public void updateProduct(Product _product) {
		for(Product product: this.allProducts) {
			if(product.getId() == _product.getId()) product = _product;
		}
	}
	
	// Update an existing part.
	public void updatePart(Part _part) {
		for(Part part: this.allParts) {
			if(part.getId() == _part.getId()) part = _part;
		}
	}
	
	// Update an existing product.
	// Reference: Iterator, https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
	public void deleteProduct(Product _product) {
		Iterator<Product> iterator = this.allProducts.iterator();
		while(iterator.hasNext()) {
			Product product = iterator.next();
			if(product.getId() == _product.getId()) {
				iterator.remove();
				break;
			}
		}
	}
	 
	// Update an existing part.
	public void deletePart(Part _part) {
		Iterator<Part> iterator = this.allParts.iterator();
		while(iterator.hasNext()) {
			Part part = iterator.next();
			if(part.getId() == _part.getId()) {
				iterator.remove();
				break;
			}
		}
	}
	
	// Get all products.
	public ArrayList<Product> getAllProducts(){
		return this.allProducts;
	}
	
	// Get all parts.
	public ArrayList<Part> getAllParts(){
		return this.allParts;
	}

}
