package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class MainMenuController {

    @FXML
    private BorderPane root;
    
	@FXML
	private void handleAddPartButtonClick() {
		try {
			
			// Redirect the user to LoginForm page.
			BorderPane partFormPane = FXMLLoader.load(getClass().getResource("AddUpdatePartForm.fxml"));
			root.setCenter(partFormPane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void handleUpdatePartButtonClick() {
		try {
			
			// Redirect the user to LoginForm page.
			BorderPane partFormPane = FXMLLoader.load(getClass().getResource("AddUpdatePartForm.fxml"));
			root.setCenter(partFormPane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
