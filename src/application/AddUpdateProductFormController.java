package application;

import java.util.ArrayList;

import Controller.InventoryController;
import Controller.PartController;
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
    private SwitchScreen switchScreen;
    @FXML
    private TextField name, unitsAvailable, unitCost, maxAllowed, minRequire;
    @FXML
    private Text requireFieldsWarning, warning;
    @FXML
    private TableView<PartController> nonAssociatedParts, associatedParts;
    
    @FXML
    public void initialize() {
        this.switchScreen = new SwitchScreen();
        this.setDefaults();
        this.loadNonAssociatedPartsTable();
        this.loadAssociatedPartsTable();
    }
    
    public void setDefaults() {
    	this.requireFieldsWarning.setFill(Color.BLACK);
    	this.name.setText("");
    	this.unitsAvailable.setText("");
    	this.maxAllowed.setText("");
    	this.minRequire.setText("");
    }

	@FXML
	private void handleCancelButtonClick() { 
		if(AlertBox.confirmation("Are you sure you want to cancel the process?")) 
			this.switchScreen.screen(root, "MainMenu.fxml"); 
	}
	
	private void loadNonAssociatedPartsTable() {
		
		ObservableList<PartController> allParts = FXCollections.observableArrayList(InventoryController.getAllParts());
			
		this.nonAssociatedParts.setEditable(true);

		TableColumn<PartController, Integer> idColumn = new TableColumn<>("Part Id");
		idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

		TableColumn<PartController, String> nameColumn = new TableColumn<>("Part Name");
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		TableColumn<PartController, Integer> inventoryColumn = new TableColumn<>("Units Available");
		inventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUnitsAvailable()).asObject());

		TableColumn<PartController, Double> priceColumn = new TableColumn<>("Unit Cost");
		priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

		this.nonAssociatedParts.getColumns().addAll(idColumn, nameColumn, inventoryColumn, priceColumn);
	    
		this.nonAssociatedParts.setItems(allParts);

	}
	
	private void loadAssociatedPartsTable() {
		
		ObservableList<PartController> allParts = FXCollections.observableArrayList(InventoryController.getAllParts());
			
		this.associatedParts.setEditable(true);

		TableColumn<PartController, Integer> idColumn = new TableColumn<>("Part Id");
		idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

		TableColumn<PartController, String> nameColumn = new TableColumn<>("Part Name");
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		TableColumn<PartController, Integer> inventoryColumn = new TableColumn<>("Units Available");
		inventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUnitsAvailable()).asObject());

		TableColumn<PartController, Double> priceColumn = new TableColumn<>("Unit Cost");
		priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

		this.associatedParts.getColumns().addAll(idColumn, nameColumn, inventoryColumn, priceColumn);
	    
		this.associatedParts.setItems(allParts);

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
			ArrayList<PartController> associatedParts = InventoryController.getAllParts();
			
			if(associatedParts.size() <= 0) {
				this.warning.setText("A product should have atleast one part associated with it.");
				return;
			}

			if(!this.isValidPrice(associatedParts, _unitCost)) {
				this.warning.setText("Price of the product cannot be less than the total price of its associated parts.");
				return;
			}
			
			InventoryController.saveProduct(_unitsAvailable, _minRequire, _maxAllowed, _name, _unitCost, associatedParts);
			
			this.switchScreen.screen(root, "MainMenu.fxml");
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid integer format");
			this.warning.setText("Please check the format of input data.");
		}
		
	}
	
}
