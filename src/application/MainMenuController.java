package application;

import Controller.PartController;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableView;
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
	private void handleAddProductButtonClick() { 
		System.out.println("Add new product form");
		this.switchScreen.screen(root, "AddUpdateProductForm.fxml"); 
	}
	
	@FXML
	private void handleUpdateProductButtonClick() {
		System.out.println("Update new product form");
		this.switchScreen.screen(root, "AddUpdateProductForm.fxml"); 
	}
	
}
