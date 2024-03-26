package application;

import java.io.IOException;
import java.util.ArrayList;

import Controller.InventoryController;
import Controller.PartController;
import UtilityFunction.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class MainMenuController {

    @FXML
    private BorderPane root;
    private SwitchScreen switchScreen;
    @FXML
    private TreeTableView<PartController> partsTable;
    
    @FXML
    public void initialize() {
        this.switchScreen = new SwitchScreen();
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
		if(AlertBox.confirmation("Are you sure you want to delete this part?"))
			System.out.println("Delete a part");
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
		if(AlertBox.confirmation("Are you sure you want to delete this product?"))
			System.out.println("Delete a product");
	}
	
}
