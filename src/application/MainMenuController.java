package application;

import java.util.ArrayList;

import Controller.*;
import UtilityFunction.AlertBox;
import UtilityFunction.Table;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainMenuController {

    @FXML
    private BorderPane 				  	 root;
    @FXML
    private TableView<PartController> 	 partsTable;
    @FXML
    private TableView<ProductController> productsTable;
    @FXML
    private Text 						 warning;
    @FXML
    private TextField 					 searchPart,
    									 searchProduct;
    
    @FXML
    public void initialize() {
    	
    	// Init columns for both parts and product table.
    	Table.initPartsColumns(this.partsTable);
    	Table.initProductsColumns(this.productsTable);
        
        // Load both the table with all the data.
        Table.load(this.partsTable, InventoryController.getAllParts());
        Table.load(this.productsTable, InventoryController.getAllProducts());
        
        this.setDefaults();
        this.setEventListeners();
    }
    
    // Set all properties to default.
    private void setDefaults() {
    	this.warning.setText("");
    	this.warning.setFill(Color.BLACK);
    }
    
    // Set event listeners for searching part/product with id/name.
    private void setEventListeners() {
    	
    	this.searchPart.textProperty().addListener((observable, oldValue, newValue) -> {
    		
    		ArrayList<PartController> foundParts = null;
    		
    		try {
    			
    	        int id = Integer.parseInt(newValue);
    	        foundParts = InventoryController.searchPartById(id);
    	        
    	    } catch (NumberFormatException e) {
    	    	
    	    	if(newValue.isBlank()) Table.load(this.partsTable, InventoryController.getAllParts());
    	    	else foundParts = InventoryController.searchPartByName(newValue);
    	    	
    	    }
    		
    		if(foundParts != null) Table.load(this.partsTable, foundParts);
    		
    	});
    	
    	this.searchProduct.textProperty().addListener((observable, oldValue, newValue) -> {

    		ArrayList<ProductController> foundProducts = null;
    		
    		try {
    			
    	        int id = Integer.parseInt(newValue);
    	        foundProducts = InventoryController.searchProductById(id);
    	        
    	    } catch (NumberFormatException e) {
    	    	
    	    	if(newValue.isBlank()) Table.load(this.productsTable, InventoryController.getAllProducts());
    	    	else foundProducts = InventoryController.searchProductByName(newValue);
    	    	
    	    }
    		
    		if(foundProducts != null) Table.load(this.productsTable, foundProducts);
    		
    	});
    	
    }
    
	@FXML
	private void handleAddPartButtonClick() { 
		System.out.println("Add new part form");
		_FXMLUtil.setScreen(root, "AddUpdatePartForm.fxml"); 
	}
	
	// Update the selected part.
	@FXML
	private void handleUpdatePartButtonClick() {
		
		PartController selectedPart = (PartController)Table.getSelected(this.partsTable);
		
		// Make sure there is a selected part.
		if(selectedPart == null) {
			this.warning.setFill(Color.RED);
			this.warning.setText("Please select a part to update.");
			return;
		}
		
		System.out.println("Update new part form");
		
		// Load the selected part in the form view.
		AddUpdatePartFormController.setPart(selectedPart);
		_FXMLUtil.setScreen(root, "AddUpdatePartForm.fxml"); 			

	}
	
	// Delete selected part.
	@FXML
	private void handleDeletePartButtonClick() {
		
		PartController selectedPart = (PartController)Table.getSelected(this.partsTable);
		
		// Make sure there is a part selected.
		if(selectedPart == null) {
			this.warning.setFill(Color.RED);
			this.warning.setText("Please select a part to delete.");
			return;
		}

		// Confirm the intension with the user.
		if(!AlertBox.confirmation("Are you sure you want to delete this part?")) return;
		
		// Make sure the part is not associated with any product.
		if(!InventoryController.deletePart(selectedPart)) {
			this.warning.setFill(Color.RED);
			// Inform the user.
			this.warning.setText("Part cannot be deleted. Since it is associated with a product.");
			return;
		}
		
		this.warning.setFill(Color.GREEN);
		// Inform the user.
		this.warning.setText("Part deleted successfully!");

		// Reload the table.
		Table.load(this.partsTable, InventoryController.getAllParts());
		
		this.setDefaults();
		
	}
	
	@FXML
	private void handleAddProductButtonClick() { 
		System.out.println("Add new product form");
		_FXMLUtil.setScreen(root, "AddUpdateProductForm.fxml"); 
	}
	
	// Update the product.
	@FXML
	private void handleUpdateProductButtonClick() {

		// Get the selected product.
		ProductController selectedProduct = (ProductController)Table.getSelected(this.productsTable);
		
		// Make sure a product is selected.
		if(selectedProduct == null) {
			this.warning.setFill(Color.RED);
			this.warning.setText("Please select a product to update.");
			return;
		}
		
		System.out.println("Update new product form");
		
		// Fill the update product with the selected product.
		AddUpdateProductFormController.setProduct(selectedProduct);
		_FXMLUtil.setScreen(root, "AddUpdateProductForm.fxml"); 
		
	}
	
	// Delete a product.
	@FXML
	private void handleDeleteProductButtonClick() {

		// Get selected product.
		ProductController selectedProduct = (ProductController)Table.getSelected(this.productsTable);
		
		// Make sure there is a product selected.
		if(selectedProduct == null) {
			this.warning.setFill(Color.RED);
			this.warning.setText("Please select a product to delete.");
			return;
		}

		// Confirm the intension with the user.
		if(!AlertBox.confirmation("Are you sure you want to delete this part?")) return;
		
		// Make sure there is no part associated with the product.
		if(!InventoryController.deleteProduct(selectedProduct)) {
			this.warning.setFill(Color.RED);
			this.warning.setText("Product cannot be deleted, since it has parts associated with it.");
			return;
		}
		
		this.warning.setText("Product deleted successfully!");
		this.warning.setFill(Color.GREEN);
		
		// Reload the table.
		Table.load(this.productsTable, InventoryController.getAllProducts());
		
	}
	
	@FXML
	private void handleExitButtonClick() {
		Platform.exit();
		System.exit(0);
	}
	
}
