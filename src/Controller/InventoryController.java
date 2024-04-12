package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Database.DBController;
import Model.*;

public class InventoryController {

	private static Inventory inventory = new Inventory();
	
	public static boolean loadDataFromDB() {
		
		InventoryController.inventory = DBController.getData();
		
		return true;
		
	}
	
	public static boolean storeInventoryIntoDB() {
		
		return DBController.storeData(InventoryController.inventory);
		
	}
	
	public static boolean storeInventoryObject() {

		try(ObjectOutputStream out 
				= new ObjectOutputStream(new FileOutputStream("src/Database/inventory.dat"))) {
			
			out.writeObject(InventoryController.inventory);
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean getInventoryObject() {

		try(ObjectInputStream in 
				= new ObjectInputStream(new FileInputStream("src/Database/inventory.dat"))){
			
			InventoryController.inventory = (Inventory) in.readObject();
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public static void saveInHousePart(String name, int unitsAvailable, double unitCost, 
									   int maxAllowed, int minRequire, int machineId) {
		
		// Create a part object.
		InHousePart part = new InHousePart(InventoryController.inventory.getPartId(), unitsAvailable, 
										   minRequire, maxAllowed, name, unitCost, machineId);
		
		// Store the object into the inventory.
		InventoryController.inventory.addPart(part);
		
	}
	
	public static void updateInHousePart(int id, String name, int unitsAvailable, double unitCost, 
										 int maxAllowed, int minRequire, int machineId) {
		
		// Create a part object.
		InHousePart part = new InHousePart(id, unitsAvailable, minRequire, maxAllowed, name, unitCost, 
										   machineId);
		InventoryController.inventory.updatePart(part);
		
	}
	
	public static void saveOutsourcePart(String name, int unitsAvailable, double unitCost, 
										 int maxAllowed, int minRequire, String companyName) {
		
		// Create a part object.
		OutsourcePart part = new OutsourcePart(InventoryController.inventory.getPartId(), unitsAvailable, 
											   minRequire, maxAllowed, name, unitCost, companyName);
		
		// Store the object into the inventory.
		InventoryController.inventory.addPart(part);
		
	}
	
	public static void updateOutsourcePart(int id, String name, int unitsAvailable, double unitCost, 
										   int maxAllowed, int minRequire, String companyName) {
		
		// Create a part object.
		OutsourcePart part = new OutsourcePart(id, unitsAvailable, minRequire, maxAllowed, name, 
											   unitCost, companyName);
		InventoryController.inventory.updatePart(part);
	
	}
	
	public static void saveProduct(int unitsAvailable, int minRequire, int maxAllowed, String name, 
								   double price, ArrayList<PartController> associatedParts) {
		
		ArrayList<Part> _associatedParts = new ArrayList<>();
		
		for(int i = 0; i < associatedParts.size(); i++) {
			PartController part = associatedParts.get(i);
			part.setAssociated(true);
			_associatedParts.add(part.getPart());
		}
		
		Product product = new Product(InventoryController.inventory.getProductId(), unitsAvailable, 
									  minRequire, maxAllowed, name, price, _associatedParts);
		
		InventoryController.inventory.addProduct(product);
		
	}
	
	public static void updateProduct(int id, int unitsAvailable, int minRequire, int maxAllowed, 
									 String name, double price, ArrayList<PartController> associatedParts) {
		
		ArrayList<Part> _associatedParts = new ArrayList<>();
		
		Product currentProduct = InventoryController.inventory.searchProductById(id);
		
		for(int i = 0; i < currentProduct.getAssociatedParts().size(); i++) {
			currentProduct.getAssociatedParts().get(i).setAssociated(false);
		}
		
		for(int i = 0; i < associatedParts.size(); i++) {
			PartController part = associatedParts.get(i);
			part.setAssociated(true);
			_associatedParts.add(part.getPart());
		}
		
		Product product = new Product(id, unitsAvailable, minRequire, maxAllowed, name, 
									  price, _associatedParts);
		
		InventoryController.inventory.updateProduct(product);
		
	}
	
	public static ArrayList<PartController> getAllParts() {
		
		ArrayList<PartController> parts    = new ArrayList<>();
		ArrayList<Part> 		  allParts = InventoryController.inventory.getAllParts();
		
		for(int i = 0; i < allParts.size(); i++) {
			PartController part = new PartController(allParts.get(i));
			parts.add(part);
		}
		
		return parts;
		
	}
	
	public static ArrayList<ProductController> getAllProducts() {
		
		ArrayList<ProductController> products 	 = new ArrayList<>();
		ArrayList<Product> 			 allProducts = InventoryController.inventory.getAllProducts();
		
		for(int i = 0; i < allProducts.size(); i++) {
			ProductController product = new ProductController(allProducts.get(i));
			products.add(product);
		}
		
		return products;
		
	}
	
	public static ArrayList<PartController> getNonAssociatedParts(ProductController product){
		
		ArrayList<PartController> nonAssociatedParts = new ArrayList<>();
		ArrayList<PartController> allParts			 = InventoryController.getAllParts();
		
		for(int i = 0; i < allParts.size(); i++) {
			PartController part = allParts.get(i);
			if(!InventoryController.isAssociated(part.getId(), product.getAssociatedParts())) {
				nonAssociatedParts.add(part);
			}
		}
		
		return nonAssociatedParts;
		
	}
	
	private static boolean isAssociated(int id, ArrayList<PartController> parts) {
		
		for(PartController part : parts) {
			if(part.getId() == id) return true;
		}
		
		return false;
		
	}
	
	public static boolean deleteProduct(ProductController product) {
		
		if(product.getProduct().getAssociatedParts().size() > 0) return false;
		InventoryController.inventory.deleteProduct(product.getProduct());
		return true;
		
	}
	
	public static boolean deletePart(PartController part) {
		
		if(part.getPart().isAssociated()) return false;
		InventoryController.inventory.deletePart(part.getPart());
		return true;
		
	}
	
	public static ArrayList<PartController> searchPartById(int partId) {
		
		Part 					  foundPart  = InventoryController.inventory.searchPartById(partId);
		ArrayList<PartController> foundParts = new ArrayList<>();

		if(foundPart != null) {
			PartController part = new PartController(foundPart);
			foundParts.add(part);
		}

		return foundParts;			
		
	}
	
	public static ArrayList<PartController> searchPartByName(String partName) {
		
		ArrayList<Part> 		  foundParts  = InventoryController.inventory.searchPartByName(partName);
		ArrayList<PartController> _foundParts = new ArrayList<>();
		
		if(foundParts.size() > 0) {
			for(int i = 0; i < foundParts.size(); i++) {
				PartController part = new PartController(foundParts.get(i));
				_foundParts.add(part);
			}
		}

		return _foundParts;
		
	}
	
	public static ArrayList<ProductController> searchProductById(int productId) {
		
		Product 					 foundProduct  = InventoryController.inventory.searchProductById(productId);
		ArrayList<ProductController> foundProducts = new ArrayList<>();

		if(foundProduct != null) {
			ProductController product = new ProductController(foundProduct);
			foundProducts.add(product);
		}

		return foundProducts;		
		
	}
	
	public static ArrayList<ProductController> searchProductByName(String productName) {
		
		ArrayList<Product> 			 foundProducts	= InventoryController.inventory.searchProductByName(productName);
		ArrayList<ProductController> _foundProducts = new ArrayList<>();
		
		if(foundProducts.size() > 0) {
			for(int i = 0; i < foundProducts.size(); i++) {
				ProductController product = new ProductController(foundProducts.get(i));
				_foundProducts.add(product);
				
			}
		}

		return _foundProducts;
		
	}
	
}
