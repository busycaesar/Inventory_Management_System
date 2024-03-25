package Model;

public abstract class Part {

	private int     id, 
		   		    // The number of parts in stock.
		   		    stock, 
		   		    // The minimum number of parts required.
		   		    minRequire, 
		   		    // The maximum number of parts that can be stored.
		   		    maxCapacity;
	private String  name;
	private double 	price;
	private boolean isAssociated;
	
	// Create a new part object.
	public Part(int _id, int _stock, int _minRequire, int _maxCapacity, String _name, double _price) {
		this.setId(_id);
		this.setStock(_stock);
		this.setMinRequire(_minRequire);
		this.setMaxCapacity(_maxCapacity);
		this.setName(_name);
		this.setPrice(_price);
	}
	
	private void setName(String _name) {
		this.name = _name;
	}

	public void setId(int _id) { 
		this.id = _id; 
	}
	
	// Return the part id.
	public int getId() { 
		return this.id; 
	}
	
	// Get the part name.
	public String getName() { 
		return this.name; 
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getMinRequire() {
		return this.minRequire;
	}

	public void setMinRequire(int minRequire) {
		this.minRequire = minRequire;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAssociated() {
		return this.isAssociated;
	}

	public void setAssociated(boolean isAssociated) {
		this.isAssociated = isAssociated;
	}
	
}
