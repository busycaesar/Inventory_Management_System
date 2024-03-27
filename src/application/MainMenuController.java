package application;

import java.util.ArrayList;

import Controller.*;
import UtilityFunction.AlertBox;
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
    private BorderPane root;
    private SwitchScreen switchScreen;
    @FXML
    private TableView<PartController> partsTable;
    @FXML
    private TableView<ProductController> productsTable;
    @FXML
    private Text warning;
    @FXML
    private TextField searchPart, searchProduct;
    
    @FXML
    public void initialize() {
        this.switchScreen = new SwitchScreen();
        this.initPartsTableColumns();
        this.initProductsTableColumns();
        this.loadPartsTable(InventoryController.getAllParts());
        this.loadProductsTable(InventoryController.getAllProducts());
        this.setDefaults();
    }
    
    private void setDefaults() {
    	this.warning.setText("");
    	this.warning.setFill(Color.BLACK);
    	this.setEventListeners();
    }
    
    private void setEventListeners() {
    	this.searchPart.textProperty().addListener((observable, oldValue, newValue) -> {
    		
    		ArrayList<PartController> foundParts = null;
    		
    		try {
    			
    	        int id = Integer.parseInt(newValue);
    	        foundParts = InventoryController.searchPartById(id);
    	        
    	    } catch (NumberFormatException e) {
    	    	
    	    	if(newValue.isBlank()) this.loadPartsTable(InventoryController.getAllParts());
    	    	else foundParts = InventoryController.searchPartByName(newValue);
    	    	
    	    }
    		
    		if(foundParts != null) this.loadPartsTable(foundParts);
    		
    	});
    	
    	this.searchProduct.textProperty().addListener((observable, oldValue, newValue) -> {

    		ArrayList<ProductController> foundProducts = null;
    		
    		try {
    			
    	        int id = Integer.parseInt(newValue);
    	        foundProducts = InventoryController.searchProductById(id);
    	        
    	    } catch (NumberFormatException e) {
    	    	
    	    	if(newValue.isBlank()) this.loadProductsTable(InventoryController.getAllProducts());
    	    	else foundProducts = InventoryController.searchProductByName(newValue);
    	    	
    	    }
    		
    		if(foundProducts != null) this.loadProductsTable(foundProducts);
    		
    	});
    }
    
	@FXML
	private void handleAddPartButtonClick() { 
		System.out.println("Add new part form");
		this.switchScreen.screen(root, "AddUpdatePartForm.fxml"); 
	}
	
	@FXML
	private void handleUpdatePartButtonClick() {
		System.out.println("Update new part form");
		this.switchScreen.screen(root, "AddUpdatePartForm.fxml"); 
	}
	
	@FXML
	private void handleDeletePartButtonClick() {
		if(!AlertBox.confirmation("Are you sure you want to delete this part?")) return;
		
		PartController selectedPart = this.partsTable.getSelectionModel().getSelectedItem();
		
		if(InventoryController.deletePart(selectedPart)) {
			this.warning.setText("Part deleted successfully!");
			this.warning.setFill(Color.GREEN);
			this.loadPartsTable(InventoryController.getAllParts());
		}
		else {
			this.warning.setText("Part cannot be deleted. Since it is associated with a product.");
			this.warning.setFill(Color.RED);
		}
		
	}
	
	@FXML
	private void handleAddProductButtonClick() { 
		System.out.println("Add new product form");
		this.switchScreen.screen(root, "AddUpdateProductForm.fxml"); 
	}
	
	@FXML
	private void handleUpdateProductButtonClick() {
		System.out.println("Update new product form");
		this.switchScreen.screen(root, "AddUpdateProductForm.fxml"); 
	}
	
	@FXML
	private void handleDeleteProductButtonClick() {
		if(AlertBox.confirmation("Are you sure you want to delete this product?")) {
			System.out.println("Delete a product");
		}
	}
	
	private void initPartsTableColumns() {
		
		this.partsTable.setEditable(true);

		TableColumn<PartController, Integer> idColumn = new TableColumn<>("Part Id");
		idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

		TableColumn<PartController, String> nameColumn = new TableColumn<>("Part Name");
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		TableColumn<PartController, Integer> inventoryColumn = new TableColumn<>("Units Available");
		inventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUnitsAvailable()).asObject());

		TableColumn<PartController, Double> priceColumn = new TableColumn<>("Unit Cost");
		priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

		this.partsTable.getColumns().addAll(idColumn, nameColumn, inventoryColumn, priceColumn);
	
	}
	
	private void initProductsTableColumns() {
		
		this.productsTable.setEditable(true);

		TableColumn<ProductController, Integer> idColumn = new TableColumn<>("Product Id");
		idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

		TableColumn<ProductController, String> nameColumn = new TableColumn<>("Product Name");
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		TableColumn<ProductController, Integer> inventoryColumn = new TableColumn<>("Units Available");
		inventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUnitsAvailable()).asObject());

		TableColumn<ProductController, Double> priceColumn = new TableColumn<>("Unit Cost");
		priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

		this.productsTable.getColumns().addAll(idColumn, nameColumn, inventoryColumn, priceColumn);
	
	}
	
	private void loadPartsTable(ArrayList<PartController> allParts) {

		ObservableList<PartController> _allParts = FXCollections.observableArrayList(allParts);
		this.partsTable.setItems(_allParts);

	}
	
	private void loadProductsTable(ArrayList<ProductController> allProducts) {
		
		ObservableList<ProductController> _allProducts = FXCollections.observableArrayList(allProducts);
		this.productsTable.setItems(_allProducts);
		
	}
	
}
