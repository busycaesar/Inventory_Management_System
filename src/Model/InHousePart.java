package Model;

public class InHousePart extends Part {

	private int machineId;
	
	// Create a new part object.
	public InHousePart(int _id, int _stock, int _minRequire, int _maxCapacity, String _name, double _price, int _machineId) {
		super(_id, _stock, _minRequire, _maxCapacity, _name, _price);
		this.machineId = _machineId;
	}
	
	public void setMachineId(int _machineId) {
		this.machineId = _machineId;
	}
	
	public int getMachineId() {
		return this.machineId;
	}

}
