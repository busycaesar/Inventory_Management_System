package application;

import Controller.InventoryController;
import Controller.PartController;
import Controller.ProductController;
import UtilityFunction.AlertBox;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    public void initialize() {
        this.switchScreen = new SwitchScreen();
        this.loadPartsTable();
        this.loadProductsTable();
        this.setDefaults();
    }
    
    public void setDefaults() {
    	this.warning.setText("");
    	this.warning.setFill(Color.BLACK);
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
			this.loadPartsTable();
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
	
	private void loadPartsTable() {
		
		ObservableList<PartController> allParts = FXCollections.observableArrayList(InventoryController.getAllParts());
			
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
	    
		this.partsTable.setItems(allParts);

	}
	
	private void loadProductsTable() {
		
		ObservableList<ProductController> allProducts = FXCollections.observableArrayList(InventoryController.getAllProducts());
			
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
		
		this.productsTable.setItems(allProducts);
		
	}
	
}
