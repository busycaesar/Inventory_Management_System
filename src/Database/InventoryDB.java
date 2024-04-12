package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Part;
import Model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InventoryDB {

	private static final String URL = "jdbc:sqlite:src/Database/inventory.db";
	
	private static Connection connect() {
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return connection;
		
	}
	
	// This initialized the db. Drop all the existing tables, create all new tables.
	public static void init() {
		
		try(Connection connection = InventoryDB.connect()){
			
			String dropAllTables = "DROP TABLE IF EXISTS Parts;"
								 + "DROP TABLE IF EXISTS InHousePart;"
								 + "DROP TABLE IF EXISTS OutsourcePart;"
								 + "DROP TABLE IF EXISTS Products;"
								 + "DROP TABLE IF EXISTS AssociatedParts;";
			
			String createPartsTable = "CREATE TABLE IF NOT EXISTS Parts ("
					   				+ "id 			INTEGER PRIMARY KEY AUTOINCREMENT,"
					   				+ "name 		STRING  NOT NULL,"
					   				+ "price 	    DOUBLE  NOT NULL,"
					   				+ "minRequire   INTEGER NOT NULL,"
					   				+ "maxCapacity  INTEGER NOT NULL,"
					   				+ "stock 	    INTEGER NOT NULL,"
					   				+ "isAssociated INTEGER NOT NULL)";
			
			String createInHousePartTable = "CREATE TABLE IF NOT EXISTS InHousePart ("
	   									  + "id 	   INTEGER PRIMARY KEY AUTOINCREMENT,"
	   									  + "partId    INTEGER NOT NULL,"
	   									  + "machineId INTEGER NOT NULL,"
	   									  + "FOREIGN KEY (partId) REFERENCES Parts(id))";
			
			String createOutsourcedPartTable = "CREATE TABLE IF NOT EXISTS OutsourcePart ("
						  					 + "id 		    INTEGER PRIMARY KEY AUTOINCREMENT,"
						  					 + "partId 	    INTEGER NOT NULL,"
						  					 + "companyName STRING  NOT NULL,"
						  					 + "FOREIGN KEY (partId) REFERENCES Parts(id))";
			
			String createProductsTable = "CREATE TABLE IF NOT EXISTS Products ("
	   								   + "id 		   INTEGER PRIMARY KEY AUTOINCREMENT,"
	   								   + "name 		   STRING  NOT NULL,"
	   								   + "price 	   DOUBLE  NOT NULL,"
	   								   + "minRequire   INTEGER NOT NULL,"
	   								   + "maxCapacity  INTEGER NOT NULL,"
	   								   + "stock 	   INTEGER NOT NULL)";	
			
			String createAssociatedPartsTable = "CREATE TABLE IF NOT EXISTS AssociatedParts ("
					   						  + "id 	   INTEGER PRIMARY KEY AUTOINCREMENT,"
					   						  + "partId    INTEGER NOT NULL,"
					   						  + "productId INTEGER NOT NULL,"
					   						  + "FOREIGN KEY (partId) REFERENCES Parts(id),"
											  + "FOREIGN KEY (productId) REFERENCES Products(id))";
			
			if(connection != null) {
				
				connection.createStatement().executeUpdate(dropAllTables);
				connection.createStatement().executeUpdate(createPartsTable);
				connection.createStatement().executeUpdate(createInHousePartTable);
				connection.createStatement().executeUpdate(createOutsourcedPartTable);
				connection.createStatement().executeUpdate(createProductsTable);
				connection.createStatement().executeUpdate(createAssociatedPartsTable);
				
			} else System.out.println("Connection object is null. Cannot create tables.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	// This function add a new part to the DB.
	public static int addPart(String name, double price, int minRequire, int maxCapacity, 
							  int stock, boolean associated) {
		
		String addPartQuery = "INSERT INTO Parts (name, price, minRequire, maxCapacity, stock, isAssociated)"
									   + "VALUES (?, ?, ?, ?, ?, ?)";
		int    _associated  = associated? 1 : 0, 
			   partId 		= -1;
		
        try (Connection connection = InventoryDB.connect();
             PreparedStatement statement 
                		= connection.prepareStatement(addPartQuery, Statement.RETURN_GENERATED_KEYS)) {

           	statement.setString(1, name);
           	statement.setDouble(2,  price);
           	statement.setInt(3, minRequire);
           	statement.setInt(4, maxCapacity);
           	statement.setInt(5, stock);
           	statement.setInt(6, _associated);
           	
           	statement.execute();
           	
           	try(ResultSet result = statement.getGeneratedKeys()){
           		
               	if (result.next()) partId = result.getInt(1);
           		
           	}
           	
        } catch (SQLException e) {
               System.out.println(e.getMessage());
        }
		
		return partId;
	
	}

	// Add the existing part as inhouse part with machineId.
	public static void addInHousePart(int partId, int machineId) {

		String addInHousePartQuery = "INSERT INTO InHousePart (partId, machineId)"
													+ "VALUES (?, ?)";

		try (Connection connection = InventoryDB.connect();
			 PreparedStatement statement 
			 	= connection.prepareStatement(addInHousePartQuery)) {

			statement.setInt(1, partId);
			statement.setInt(2, machineId);

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	// Add the existing part as outsource part with companyName.
	public static void addOutsourcePart(int partId, String companyName) {

		String addOutsourceQuery = "INSERT INTO OutsourcePart (partId, companyName)"
													+ "VALUES (?, ?)";

		try (Connection connection = InventoryDB.connect();
			 PreparedStatement statement 
			 	= connection.prepareStatement(addOutsourceQuery)) {

				statement.setInt(1, partId);
				statement.setString(2, companyName);

				statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	// This adds a new product in the db.
	public static int addProduct(String name, double price, int minRequire, int maxCapacity, int stock) {

		String addProductQuery = "INSERT INTO Products (name, price, minRequire, maxCapacity, stock)"
											 + "VALUES (?, ?, ?, ?, ?)";
		int    productId 	   = -1;

		try (Connection connection = InventoryDB.connect();
			 PreparedStatement statement 
				= connection.prepareStatement(addProductQuery, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, name);
			statement.setDouble(2,  price);
			statement.setInt(3, minRequire);
			statement.setInt(4, maxCapacity);
			statement.setInt(5, stock);

			statement.execute();

			try(ResultSet result = statement.getGeneratedKeys()){

				if (result.next()) productId = result.getInt(1);

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return productId;
		
	}

	// Add an associate part of a particular product.
	public static void addAssociatedPart(int id, int productId) {

		String addOutsourceQuery = "INSERT INTO AssociatedParts (partId, productId)"
													  + "VALUES (?, ?)";

		try (Connection connection = InventoryDB.connect();
			 PreparedStatement statement 
				= connection.prepareStatement(addOutsourceQuery)) {

			statement.setInt(1, id);
			statement.setInt(2, productId);

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	// Get all the parts from the DB.
	public static ArrayList<Part> getAllParts(){
		
		ArrayList<Part> allParts   = new ArrayList<>();
		String getAllPartsQuery = "SELECT * "
								+ "FROM Parts";
		
		try(Connection connection = InventoryDB.connect();
			Statement statement   = connection.createStatement();
			ResultSet result 	  = statement.executeQuery(getAllPartsQuery)){
			
			allParts = DBUtilFunctions.convertIntoParts(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allParts;
		
	}
	
	// Get all the products from the DB.
	public static ArrayList<Product> getAllProducts(){
		
		ArrayList<Product> allProducts   = new ArrayList<>();
		String getAllProductsQuery = "SELECT * "
								   + "FROM Products";
		
		try(Connection connection = InventoryDB.connect();
			Statement  statement  = connection.createStatement();
			ResultSet  result 	  = statement.executeQuery(getAllProductsQuery)){
			
			allProducts = DBUtilFunctions.convertIntoProducts(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allProducts;
		
	}
	
	// Get the machine id of an inhouse part.
	public static int getMachineId(int partId) {
		
		String getMachineIdQuery = "SELECT machineId "
				  				 + "FROM InHousePart "
				  				 + "WHERE partId = ?";
		int machineId = -1;

		try(Connection connection 		= InventoryDB.connect();
			PreparedStatement statement = connection.prepareStatement(getMachineIdQuery)){

			statement.setInt(1, partId);

			statement.execute();

			try(ResultSet result = statement.executeQuery()){

				if (result.next()) {
					machineId = result.getInt(1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return machineId;
		
	}
	
	// Get the company name of the particular pard.
	public static String getCompanyName(int partId) {
		
		String getCompanyNameQuery = "SELECT companyName "
				  				   + "FROM OutsourcePart "
				  				   + "WHERE partId = ?";
		String companyName = "";

		try(Connection connection 		= InventoryDB.connect();
			PreparedStatement statement = connection.prepareStatement(getCompanyNameQuery)){

			statement.setInt(1, partId);

			statement.execute();

			try(ResultSet result = statement.executeQuery()){

				if (result.next()) {
					companyName = result.getString(1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return companyName;
		
	}
	
	// Get the part using id.
	public static Part getPartUsingId(int partId) {
		
		String getPartUsingIdQuery = "SELECT * "
								   + "FROM Parts "
								   + "WHERE id = ?";

		try(Connection 		  connection = InventoryDB.connect();
			PreparedStatement statement  = connection.prepareStatement(getPartUsingIdQuery)){

			statement.setInt(1, partId);

			statement.execute();

			try(ResultSet result = statement.executeQuery()){

				return DBUtilFunctions.convertIntoParts(result).get(0);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
		
	}
	
	// Get all the associated parts of a particular product.
	public static ArrayList<Part> getAssociatedParts(int productId){
		
		String 			getAssociatedPartsQuery = "SELECT partId "
									   			+ "FROM AssociatedParts "
									   			+ "WHERE productId = ?";
		ArrayList<Part> associatedParts 		= new ArrayList<>();

		try(Connection 		  connection = InventoryDB.connect();
			PreparedStatement statement  = connection.prepareStatement(getAssociatedPartsQuery)){

			statement.setInt(1, productId);

			statement.execute();

			try(ResultSet result = statement.executeQuery()){

				while(result.next()) {

					int  partId = result.getInt(1);
					
					Part part   = InventoryDB.getPartUsingId(partId);
					associatedParts.add(part);
					
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return associatedParts;
		
	}
	
}
