package Controller;

import Model.InHousePart;
import Model.OutsourcePart;
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
	
	public boolean isInHouse() {
		return this.part instanceof InHousePart;
	}
	
	public int getMaxAllowed() {
		return this.part.getMaxCapacity();
	}
	
	public int getMinRequire() {
		return this.part.getMinRequire();
	}
	
	public int getMachineId() {
		
		if(!this.isInHouse()) return -1;
		
		InHousePart part = (InHousePart) this.getPart();
		return part.getMachineId();
		
	}
	
	public String getCompanyName() {
		
		if(this.isInHouse()) return null;
		
		OutsourcePart part = (OutsourcePart) this.getPart();
		return part.getCompanyName();

	}
	
}
