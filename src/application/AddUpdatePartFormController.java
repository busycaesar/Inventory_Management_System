package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class AddUpdatePartFormController {

	@FXML
    private BorderPane root;
	
	@FXML
	private void handleCancelButtonClick() {
		try {
			
			// Redirect the user to LoginForm page.
			BorderPane mainPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			root.setCenter(mainPane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
}
