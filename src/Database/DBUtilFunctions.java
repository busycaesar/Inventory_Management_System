package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.InHousePart;
import Model.OutsourcePart;
import Model.Part;
import Model.Product;

public class DBUtilFunctions {

	// Convert the result into an ArrayList of part.
	public static ArrayList<Part> convertIntoParts(ResultSet result){
		
		ArrayList<Part> allParts = new ArrayList<>();
		
		try {
			
			while(result.next()) {
				
				int 	id 			 = result.getInt("id"),
						stock 		 = result.getInt("stock"),
						minRequire   = result.getInt("minRequire"), 
						maxCapacity  = result.getInt("maxCapacity");
				String 	name 		 = result.getString("name");
				double 	price 		 = result.getDouble("price");
				boolean isAssociated = result.getInt("isAssociated") == 1;
				int 	machineId 	 = InventoryDB.getMachineId(id);
				Part 	part		 = null;
				
				if(machineId > 0) {
					part = new InHousePart(id, stock, minRequire, maxCapacity, name, price, machineId);
				} 
				else {
					String companyName = InventoryDB.getCompanyName(id);
					part = new OutsourcePart(id, stock, minRequire, maxCapacity, name, price, companyName);
					
				}
				
				part.setAssociated(isAssociated);
				allParts.add(part);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allParts;
			
	}
	
	// Convert the result into an ArrayList of products.
	public static ArrayList<Product> convertIntoProducts(ResultSet result){
		
		ArrayList<Product> allProducts = new ArrayList<>();
		
		try {
			
			while(result.next()) {
				
				int 			id 				= result.getInt("id"),
								stock 			= result.getInt("stock"),
								minRequire 		= result.getInt("minRequire"), 
								maxCapacity 	= result.getInt("maxCapacity");
				String 			name 			= result.getString("name");
				double 			price 			= result.getDouble("price");
				ArrayList<Part> associatedParts = InventoryDB.getAssociatedParts(id);
				Product 		product 		= new Product(id, stock, minRequire, maxCapacity, 
															  name, price, associatedParts);
				
				allProducts.add(product);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allProducts;
			
	}
	
}
