package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class _FXMLUtil {
    
    public static void setScreen(BorderPane root, String fxmlFilePath) {
			BorderPane requiredScreenPane;
			try {
				requiredScreenPane = FXMLLoader.load(_FXMLUtil.class.getResource(fxmlFilePath));
				root.setCenter(requiredScreenPane);
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
    
}
