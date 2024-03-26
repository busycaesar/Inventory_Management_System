package Controller;

import java.util.ArrayList;

import Model.*;

public class InventoryController {

	private static ArrayList<Product> allProducts = new ArrayList<>();
	private static ArrayList<Part>    allParts = new ArrayList<>();
	private static Inventory inventory = new Inventory(allProducts, allParts);
	
	public static void saveInHousePart(String name, int unitsAvailable, double unitCost, int maxAllowed, int minRequire, int machineId) {
		
		// Create a part object.
		InHousePart part = new InHousePart(InventoryController.inventory.getPartId(), unitsAvailable, minRequire, maxAllowed, name, unitCost, machineId);
		
		// Store the object into the inventory.
		InventoryController.inventory.addPart(part);
		
	}
	
	public static void saveOutsourcePart(String name, int unitsAvailable, double unitCost, int maxAllowed, int minRequire, String companyName) {
		
		// Create a part object.
		OutsourcePart part = new OutsourcePart(InventoryController.inventory.getPartId(), unitsAvailable, minRequire, maxAllowed, name, unitCost, companyName);
		
		// Store the object into the inventory.
		InventoryController.inventory.addPart(part);
		
	}
	
	public static void saveProduct(int unitsAvailable, int minRequire, int maxAllowed, String name, double price, ArrayList<PartController> associatedParts) {
		
		ArrayList<Part> _associatedParts = new ArrayList<>();
		
		for(PartController partController: associatedParts) {
			_associatedParts.add(partController.getPart());
		}
		
		Product product = new Product(InventoryController.inventory.getProductId(), unitsAvailable, minRequire, maxAllowed, name, price, _associatedParts);
		
		InventoryController.inventory.addProduct(product);
		
	}
	
	public static ArrayList<PartController> getAllParts() {
		ArrayList<PartController> parts = new ArrayList<>();
		ArrayList<Part> allParts = InventoryController.inventory.getAllParts();
		
		for(int i = 0; i < allParts.size(); i++) {
			PartController part = new PartController(allParts.get(i));
			parts.add(part);
		}
		
		return parts;
	}
	
}
