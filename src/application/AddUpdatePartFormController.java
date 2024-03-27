package application;

import Controller.*;
import UtilityFunction.AlertBox;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddUpdatePartFormController {

	@FXML
    private BorderPane root;
    @FXML
    private RadioButton inHouseRadioButton,
    					outsourcedRadioButton;
    @FXML
    private Label machineIdLabel;
    @FXML
    private Text warning, requireFieldsWarning;
    @FXML
    private TextField name, unitsAvailable, unitCost, maxAllowed, minRequire, manufactureInformation; 
    @FXML
    private ToggleGroup partManufacture;
    @FXML
    private Text title;
    
    @FXML
    public void initialize() {;
    	this.partManufacture = new ToggleGroup();
    	this.inHouseRadioButton.setToggleGroup(partManufacture);
    	this.outsourcedRadioButton.setToggleGroup(partManufacture);
    	this.setDefaults();
    }
    
    private void setDefaults() {
    	this.requireFieldsWarning.setFill(Color.BLACK);
    	this.addEventListener();
    	this.inHouseRadioButton.setSelected(true);
    	this.outsourcedRadioButton.setSelected(false);
    	this.name.setText("");
    	this.unitsAvailable.setText("");
    	this.maxAllowed.setText("");
    	this.minRequire.setText("");
    	this.manufactureInformation.setText("");
    }
    
    public void setPart(PartController part) {
    	System.out.println("Working");
    }
    
    private void addEventListener() {
        this.inHouseRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                machineIdLabel.setText("Machine Id*");
            }
        });

        this.outsourcedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                machineIdLabel.setText("Company Name*");
            }
        });
    }
    
	@FXML
	private void handleCancelButtonClick() { 
		if(AlertBox.confirmation("Are you sure you want to cancel the process?"))
			_FXMLUtil.setScreen(root, "MainMenu.fxml"); 
	}
	
	// Check if all the required form fields are filled or not.
	private boolean checkFormFields() {
		return !this.name.getText().isEmpty()
			&& !this.unitsAvailable.getText().isEmpty()
			&& !this.unitCost.getText().isEmpty()
			&& !this.maxAllowed.getText().isEmpty()
			&& !this.minRequire.getText().isEmpty()
			&& !this.manufactureInformation.getText().isEmpty();
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
		       _minRequireInput = this.minRequire.getText(),
			   _manufactureInformationInput = this.manufactureInformation.getText();
		
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
			
			// Based on the manufacturing, pass the part information to inventory controller.
			if(this.inHouseRadioButton.isSelected()) {
				int _machineId = Integer.parseInt(_manufactureInformationInput);
				InventoryController.saveInHousePart(_name, _unitsAvailable, _unitCost, _maxAllowed, _minRequire, _machineId);
			}
			else InventoryController.saveOutsourcePart(_name, _unitsAvailable, _unitCost, _maxAllowed, _minRequire, _manufactureInformationInput);
			
			_FXMLUtil.setScreen(root, "MainMenu.fxml");
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid integer format");
			this.warning.setText("Please check the format of input data.");
		}
		
	}
	
}
