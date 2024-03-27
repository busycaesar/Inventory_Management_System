package application;

import java.util.ArrayList;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddUpdateProductFormController {

	@FXML
    private BorderPane root;
    @FXML
    private TextField name, unitsAvailable, unitCost, maxAllowed, minRequire;
    @FXML
    private Text requireFieldsWarning, warning, title;
    @FXML
    private TableView<PartController> nonAssociatedPartsTable, associatedPartsTable;
    @FXML
    private static ProductController product;
    private ArrayList<PartController> nonAssociatedParts, associatedParts;
    
    @FXML
    public void initialize() {
        this.setEventListener();
        this.initPartsTables();
        
        if(AddUpdateProductFormController.product != null) {   
        	this.title.setText("Update Product");        	
        	this.loadProduct(AddUpdateProductFormController.product);
        	return;
        }
        
        this.setDefaults();
        this.title.setText("Add Product");
        this.nonAssociatedParts = InventoryController.getAllParts();
        this.associatedParts = new ArrayList<>();
        this.loadPartTable(nonAssociatedPartsTable, this.nonAssociatedParts);
    }
    
    private void setDefaults() {
    	this.requireFieldsWarning.setFill(Color.BLACK);
    	this.name.setText("");
    	this.unitsAvailable.setText("");
    	this.maxAllowed.setText("");
    	this.minRequire.setText("");
    	AddUpdateProductFormController.product = null;
    }
    
    public static void setProduct(ProductController product) {
    	AddUpdateProductFormController.product = product;
    }
    
    private void loadProduct(ProductController product) {
    	this.name.setText(product.getName());
    	this.unitsAvailable.setText(product.getUnitsAvailable()+"");
    	this.unitCost.setText(product.getPrice()+"");
    	this.maxAllowed.setText(product.getMaxAllowed()+"");
    	this.minRequire.setText(product.getMinRequire()+"");
    	this.associatedParts = product.getAssociatedParts();
    	this.nonAssociatedParts = InventoryController.getNonAssociatedParts(product);
    	this.loadPartTable(associatedPartsTable, this.associatedParts);
    	this.loadPartTable(nonAssociatedPartsTable, this.nonAssociatedParts);
    }
    
    private void setEventListener() {
    	
    /*	this.searchPart.textProperty().addListener((observable, oldValue, newValue) -> {
    		
    		ArrayList<PartController> foundParts = null;
    		
    		try {
    			
    	        int id = Integer.parseInt(newValue);
    	        foundParts = InventoryController.searchPartById(id);
    	        
    	    } catch (NumberFormatException e) {
    	    	
    	    	if(newValue.isBlank()) this.loadPartsTable(InventoryController.getAllParts());
    	    	else foundParts = InventoryController.searchPartByName(newValue);
    	    	
    	    }
    		
    		if(foundParts != null) this.loadPartsTable(foundParts);
    		
    	});*/
    	
    }

	@FXML
	private void handleCancelButtonClick() { 
		if(AlertBox.confirmation("Are you sure you want to cancel the process?")) 
			_FXMLUtil.setScreen(root, "MainMenu.fxml"); 
	}
	
	private void initPartsTables() {
	
		this.initPartsTable(associatedPartsTable);
		this.initPartsTable(nonAssociatedPartsTable);
		
	}
	
	private void initPartsTable(TableView<PartController> table) {
	
		table.setEditable(true);

		TableColumn<PartController, Integer> idColumn = new TableColumn<>("Part Id");
		idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

		TableColumn<PartController, String> nameColumn = new TableColumn<>("Part Name");
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		TableColumn<PartController, Integer> inventoryColumn = new TableColumn<>("Units Available");
		inventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUnitsAvailable()).asObject());

		TableColumn<PartController, Double> priceColumn = new TableColumn<>("Unit Cost");
		priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

		table.getColumns().addAll(idColumn, nameColumn, inventoryColumn, priceColumn);
		
	}
	
	private void loadPartTable(TableView<PartController> table, ArrayList<PartController> parts) {

		ObservableList<PartController> allParts = FXCollections.observableArrayList(parts);
		table.setItems(allParts);
		
	}
	
    private Object getSelectedObject(TableView<?> table) {
    	
    	return table.getSelectionModel().getSelectedItem();
    	
    }
	
	@FXML
	private void handleAddButtonClick() {
		
		PartController selectedPart = (PartController)this.getSelectedObject(this.nonAssociatedPartsTable);
		
		if(selectedPart == null) {
			this.warning.setText("Please select a part to add.");
			return;
		}
		
		this.associatedParts.add(selectedPart);
		
		this.loadPartTable(associatedPartsTable, associatedParts);
		
		for(int i = 0; i < this.nonAssociatedParts.size(); i++) {
			if(this.nonAssociatedParts.get(i).getId() == selectedPart.getId()) {
				this.nonAssociatedParts.remove(i);
				break;
			}
		}
		
		this.loadPartTable(nonAssociatedPartsTable, nonAssociatedParts);
		
	}
	
	@FXML
	private void handleRemoveButtonClick() {
		
		PartController selectedPart = (PartController)this.getSelectedObject(this.associatedPartsTable);
		
		if(selectedPart == null) {
			this.warning.setText("Please select a part to remove.");
			return;
		}
		
		this.nonAssociatedParts.add(selectedPart);
		
		this.loadPartTable(this.nonAssociatedPartsTable, this.nonAssociatedParts);
		
		for(int i = 0; i < this.associatedParts.size(); i++) {
			if(this.associatedParts.get(i).getId() == selectedPart.getId()) {
				this.associatedParts.remove(i);
				break;
			}
		}
		
		this.loadPartTable(this.associatedPartsTable, this.associatedParts);
		
	}
	
	// Check if all the required form fields are filled or not.
	private boolean checkFormFields() {
		return !this.name.getText().isEmpty()
			&& !this.unitsAvailable.getText().isEmpty()
			&& !this.unitCost.getText().isEmpty()
			&& !this.maxAllowed.getText().isEmpty()
			&& !this.minRequire.getText().isEmpty();
	}
	
	private boolean isValidPrice(ArrayList<PartController> associatedParts, double inputPrice) {
		double partsPrice = 0;
		for(PartController part: associatedParts) {
			partsPrice += part.getPrice();
		}
		return inputPrice > partsPrice;
	}
	
	@FXML
	private void handleSaveButtonClick() {
		
		// Make sure all the required fields are filled.
		if(!this.checkFormFields()) {
			this.requireFieldsWarning.setFill(Color.RED);
			return;
		}
		
		// Get all the inputs.
		String _name = this.name.getText(),
			   _unitsAvailableInput = this.unitsAvailable.getText(),
			   _unitCostInput = this.unitCost.getText(),
			   _maxAllowedInput = this.maxAllowed.getText(),
		       _minRequireInput = this.minRequire.getText();
		
		try {
			
			// Convert the required input strings into ints and doubles.
			int _unitsAvailable = Integer.parseInt(_unitsAvailableInput),
				_maxAllowed = Integer.parseInt(_maxAllowedInput),
				_minRequire = Integer.parseInt(_minRequireInput);
			double _unitCost = Double.parseDouble(_unitCostInput);
			
			if(_unitsAvailable < _minRequire || _unitsAvailable > _maxAllowed) {
				this.warning.setText("Units cannot be less than minimum require or more than maximum allowed.");
				return;
			}
			
			if(_minRequire > _maxAllowed) {
				this.warning.setText("Minimum require cannot be more than maximum allowed.");
				return;
			}
			
			// This array gets the list of all the associated parts.
			ArrayList<PartController> associatedParts = this.associatedParts;
			
			if(associatedParts.size() <= 0) {
				this.warning.setText("A product should have atleast one part associated with it.");
				return;
			}

			if(!this.isValidPrice(associatedParts, _unitCost)) {
				this.warning.setText("Price of the product cannot be less than the total price of its associated parts.");
				return;
			}
			
			int productId = -1;
			
			if(AddUpdateProductFormController.product != null) productId = AddUpdateProductFormController.product.getId();

			if(productId > 0) InventoryController.updateProduct(productId, _unitsAvailable, _minRequire, _maxAllowed, _name, _unitCost, associatedParts);
			else InventoryController.saveProduct(_unitsAvailable, _minRequire, _maxAllowed, _name, _unitCost, associatedParts);
			
			this.setDefaults();
			
			_FXMLUtil.setScreen(root, "MainMenu.fxml");
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid integer format");
			this.warning.setText("Please check the format of input data.");
		}
		
	}
	
}
