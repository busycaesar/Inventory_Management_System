package application;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class AddUpdateProductFormController {

	@FXML
    private BorderPane root;
    private SwitchScreen switchScreen;
    
    @FXML
    public void initialize() {
        this.switchScreen = new SwitchScreen();
    }
    
	@FXML
	private void handleCancelButtonClick() { this.switchScreen.screen(root, "MainMenu.fxml"); }
	
}
