package Controller;

import Model.Part;

public class PartController {

	private Part part;
	
	public PartController(Part _part) {
		this.part = _part;
	}
	
	public Part getPart() {
		return this.part;
	}
	
	public int getId() {
		return this.part.getId();
	}
	
	public String getName() {
		return this.part.getName();
	}
	
	public int getUnitsAvailable() {
		return this.part.getStock();
	}
	
	public double getPrice() {
		return this.part.getPrice();
	}
	
}
