package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {

	private ArrayList<Product> allProducts;
	private ArrayList<Part>    allParts;
	
	public Inventory(ArrayList<Product> _allProducts, ArrayList<Part> _allParts) {
		this.allProducts = _allProducts;
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
	public ArrayList<Product> searchProductByName(String name) {
		
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		
		for(Product product: this.allProducts) {
			if(product.getName() == name) foundProducts.add(product);
		}
		
		return foundProducts;
	}
	
	// Get a part using its name.
	public ArrayList<Part> searchPartByName(String name) {
		
		ArrayList<Part> foundParts = new ArrayList<>();
		
		for(Part part: this.allParts) {
			if(part.getName() == name) foundParts.add(part);
		}
		
		return foundParts;
	}
	
	// Update an existing product.
	public void updateProduct(Product _product) {
		
		for(int i = 0; i < this.allProducts.size(); i++) {
			if(this.allProducts.get(i).getId() == _product.getId())
				this.allProducts.set(i, _product);
		}

	}
	
	// Update an existing part.
	public void updatePart(Part _part) {
		
		for(int i = 0; i < this.allParts.size(); i++) {
			if(this.allParts.get(i).getId() == _part.getId())
				this.allParts.set(i, _part);
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
	 
	// Delete an existing part if it is not associated with any Product.
	// Returns true if the part is delete successfully; otherwise false.
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
	
	public int getProductId() {
		return this.allProducts.size() + 1;
	}
	
	public int getPartId() {
		return this.allParts.size() + 1;
	}

}
