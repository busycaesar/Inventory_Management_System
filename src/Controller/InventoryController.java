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
	
	//int _id, int _stock, int _minRequire, int _maxCapacity, String _name, double _price, String _companyName
	public void saveOutsourcePart(String name, int unitsAvailable, double unitCost, int maxAllowed, int minRequire, String companyName) {
		
		// Create a part object.
		OutsourcePart part = new OutsourcePart(this.inventory.getPartId(), unitsAvailable, minRequire, maxAllowed, name, unitCost, companyName);
		
		// Store the object into the inventory.
		this.inventory.addPart(part);
		
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
