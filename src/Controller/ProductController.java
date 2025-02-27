package Controller;

import java.util.ArrayList;

import Model.Part;
import Model.Product;

public class ProductController {

	Product product;
	
	public ProductController(Product _product) {
		this.product = _product;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public int getId() {
		return this.product.getId();
	}
	
	public String getName() {
		return this.product.getName();
	}
	
	public int getUnitsAvailable() {
		return this.product.getStock();
	}
	
	public double getPrice() {
		return this.product.getPrice();
	}
	
	public ArrayList<PartController> getAssociatedParts(){
		
		ArrayList<PartController> associatedParts 		 = new ArrayList<>();
		ArrayList<Part> 		  currentAssociatedParts = this.getProduct().getAssociatedParts();
		
		for(int i = 0; i < currentAssociatedParts.size(); i++) {
			PartController part = new PartController(currentAssociatedParts.get(i));
			associatedParts.add(part);			
		}
		
		return associatedParts;
		
	}
	
	public int getMaxAllowed() {
		return this.product.getMaxCapacity();
	}
	
	public int getMinRequire() {
		return this.product.getMinRequire();
	}
	
}
