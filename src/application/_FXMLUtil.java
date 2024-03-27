package application;

import java.io.IOException;
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
    
    public static Object getFXMLController(String fxmlFilePath) {
        FXMLLoader loader = new FXMLLoader(_FXMLUtil.class.getResource(fxmlFilePath));
        try {
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
