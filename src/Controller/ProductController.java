package Controller;

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
	
}
