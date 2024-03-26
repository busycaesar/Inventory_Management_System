package Controller;

import java.util.ArrayList;

import Model.*;

public class InventoryController {

	private Inventory inventory;
	
	public InventoryController() {
		ArrayList<Product> allProducts = new ArrayList<>();
		ArrayList<Part>    allParts = new ArrayList<>();
		inventory = new Inventory(allProducts, allParts);
	}
	
	public void saveInHousePart(String name, int unitsAvailable, double unitCost, int maxAllowed, int minRequire, int machineId) {
		
		// Create a part object.
		InHousePart part = new InHousePart(this.inventory.getPartId(), unitsAvailable, minRequire, maxAllowed, name, unitCost, machineId);
		
		// Store the object into the inventory.
		this.inventory.addPart(part);
		
	}
	
	public void saveOutsourcePart(String name, int unitsAvailable, double unitCost, int maxAllowed, int minRequire, String companyName) {
		
		// Create a part object.
		OutsourcePart part = new OutsourcePart(this.inventory.getPartId(), unitsAvailable, minRequire, maxAllowed, name, unitCost, companyName);
		
		// Store the object into the inventory.
		this.inventory.addPart(part);
		
	}
	
	public void saveProduct(int unitsAvailable, int minRequire, int maxAllowed, String name, double price, ArrayList<PartController> associatedParts) {
		
		ArrayList<Part> _associatedParts = new ArrayList<>();
		
		for(PartController partController: associatedParts) {
			_associatedParts.add(partController.getPart());
		}
		
		Product product = new Product(this.inventory.getProductId(), unitsAvailable, minRequire, maxAllowed, name, price, _associatedParts);
		
		this.inventory.addProduct(product);
		
	}
	
	public ArrayList<PartController> getAllParts() {
		ArrayList<PartController> parts = new ArrayList<>();
		ArrayList<Part> allParts = this.inventory.getAllParts();
		
		for(int i = 0; i < allParts.size(); i++) {
			PartController part = new PartController(allParts.get(i));
			parts.add(part);
		}
		
		return parts;
	}
	
}
