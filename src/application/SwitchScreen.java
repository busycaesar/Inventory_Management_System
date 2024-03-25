package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class SwitchScreen {
    
    public void screen(BorderPane root, String fxmlFileName) {
			BorderPane requiredScreenPane;
			try {
				requiredScreenPane = FXMLLoader.load(getClass().getResource(fxmlFileName));
				root.setCenter(requiredScreenPane);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
