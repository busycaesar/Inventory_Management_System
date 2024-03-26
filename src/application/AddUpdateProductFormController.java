package application;

import java.util.ArrayList;

import Controller.InventoryController;
import Controller.PartController;
import javafx.fxml.FXML;
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
    private InventoryController inventoryController;
    
    @FXML
    public void initialize() {
        this.switchScreen = new SwitchScreen();
        this.setDefaults();
        inventoryController = new InventoryController();
    }
    
    public void setDefaults() {
    	this.requireFieldsWarning.setFill(Color.BLACK);
    }
    
	@FXML
	private void handleCancelButtonClick() { 
		this.switchScreen.screen(root, "MainMenu.fxml"); 
	}
	
	// Check if all the required form fields are filled or not.
	private boolean checkFormFields() {
		return !this.name.getText().isEmpty()
			&& !this.unitsAvailable.getText().isEmpty()
			&& !this.unitCost.getText().isEmpty()
			&& !this.maxAllowed.getText().isEmpty()
			&& !this.minRequire.getText().isEmpty();
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
			
			if(_minRequire > _maxAllowed) {
				this.warning.setText("Minimum require cannot be more than maximum allowed.");
				return;
			}
			
			ArrayList<PartController> associatedParts = new ArrayList<>();
			
			this.inventoryController.saveProduct(_unitsAvailable, _minRequire, _maxAllowed, _name, _unitCost, associatedParts);
			
			this.setDefaults();
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid integer format");
			this.warning.setText("Please check the format of input data.");
		}
		
	}
	
}
