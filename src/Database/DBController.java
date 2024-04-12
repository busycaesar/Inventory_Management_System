package Database;

import java.util.ArrayList;

import Model.InHousePart;
import Model.Inventory;
import Model.OutsourcePart;
import Model.Part;
import Model.Product;

public class DBController {
	
	// Store all the inventory data.
	public static boolean storeData(Inventory inventory) {
		
		System.out.println("Clear Previous Data");
		
		InventoryDB.init();
		
		System.out.println("Store Inventory");
		
		ArrayList<Part>    allParts    = inventory.getAllParts();
		ArrayList<Product> allProducts = inventory.getAllProducts();
		
		System.out.println("Store Parts: " + allParts.size());
		
		// Store All Parts.
		for(Part part: allParts) {
			
			int partId = InventoryDB.addPart(part.getName(), part.getPrice(), part.getMinRequire(), 
							  			   part.getMaxCapacity(), part.getStock(), part.isAssociated());
			
			// Store InHouseParts MachineId
			if(part instanceof InHousePart) InventoryDB.addInHousePart(partId, 
																	((InHousePart) part).getMachineId());
			// Store OutsourcedParts CompanyName
			else InventoryDB.addOutsourcePart(partId, ((OutsourcePart) part).getCompanyName());
			
		}
		
		System.out.println("Store Products: " + allProducts.size());
		
		// Store All Products.
		for(Product product: allProducts) {
			
			int productId = InventoryDB.addProduct(product.getName(), product.getPrice(), 
												   product.getMinRequire(), product.getMaxCapacity(), 
												   product.getStock()); 
			
			// Store All Associated Parts of a product.
			ArrayList<Part> associatedParts = product.getAssociatedParts();
			
			for(Part part: associatedParts) InventoryDB.addAssociatedPart(part.getId(), productId);
			
		}
		
		return true;
		
	}
	
	// Get the inventory from DB.
	public static Inventory getData() {
		
		// Get all parts.
		ArrayList<Part> allParts = InventoryDB.getAllParts();
		
		System.out.println("Got all parts: " + allParts.size());
		
		// Get all products.
		ArrayList<Product> allProducts = InventoryDB.getAllProducts();
		
		System.out.println("Got all products: " + allProducts.size());
		
		// Create new inventory instance and return it.
		return new Inventory(allParts, allProducts);
		
	}
	
}
