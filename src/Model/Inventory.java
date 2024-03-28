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
		for(int i = 0; i < this.allProducts.size(); i++) {
			Product product = this.allProducts.get(i);
			if(product.getId() == id)
				return product;
		}
		return null;
	}
	
	// Get a product using its id.
	public Part searchPartById(int id) {
		for(int i = 0; i < this.allParts.size(); i++) {
			Part part = this.allParts.get(i);
			if(part.getId() == id)
				return part;
		}
		return null;
	}
	
	// Get a product using its id.
	public ArrayList<Product> searchProductByName(String name) {
		
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		
		for(int i = 0; i < this.allProducts.size(); i++) {
			Product product = this.allProducts.get(i);
			if(product.getName().contains(name)) foundProducts.add(product);
		}
		
		return foundProducts;
	}
	
	// Get a part using its name.
	public ArrayList<Part> searchPartByName(String name) {
		
		ArrayList<Part> foundParts = new ArrayList<>();
		
		for(int i = 0; i < this.allParts.size(); i++) {
			Part part = this.allParts.get(i);
			if(part.getName().contains(name)) foundParts.add(part);
		}
		
		return foundParts;
	}
	
	// Update an existing product.
	public void updateProduct(Product _product) {
		
		for(int i = 0; i < this.allProducts.size(); i++) {
			if(this.allProducts.get(i).getId() == _product.getId()) {
				this.allProducts.set(i, _product);
				break;
			}
		}

	}
	
	// Update an existing part.
	public void updatePart(Part _part) {
		
		for(int i = 0; i < this.allParts.size(); i++) {
			if(this.allParts.get(i).getId() == _part.getId()) {
				this.allParts.set(i, _part);
				break;
			}
		}
		
	}
	
	// Delete an existing product.
	public void deleteProduct(Product _product) {
		
		for(int i = 0; i < this.allProducts.size(); i++) {
			if(this.allProducts.get(i).getId() == _product.getId()) {
				this.allProducts.remove(i);
				break;
			}
		}
	}
	 
	// Delete an existing part if it is not associated with any Product.
	public void deletePart(Part _part) {

		for(int i = 0; i < this.allParts.size(); i++) {
			if(this.allParts.get(i).getId() == _part.getId()) {
				this.allParts.remove(i);
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
