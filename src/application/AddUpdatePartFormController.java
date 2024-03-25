package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class AddUpdatePartFormController {

	@FXML
    private BorderPane root;
	
	@FXML
	private void handleUpdatePartButtonClick() {
		try {
			
			// Redirect the user to LoginForm page.
			BorderPane bookRoomPane = FXMLLoader.load(getClass().getResource("AddUpdatePartForm.fxml"));
			root.setCenter(bookRoomPane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
}
