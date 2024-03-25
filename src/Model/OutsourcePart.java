package Model;

public class OutsourcePart extends Part {

	private String companyName;
	
	public OutsourcePart(int _id, int _stock, int _minRequire, int _maxCapacity, String _name, double _price) {
		super(_id, _stock, _minRequire, _maxCapacity, _name, _price);	
	}

	public void setCompanyName(String _companyName) {
		this.companyName = _companyName;
	}
	
	public String getCompanyName() {
		return this.companyName;
	}
	
}
