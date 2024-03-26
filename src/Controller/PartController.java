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
	
	public double getPrice() {
		return this.part.getPrice()
	}
	
}
