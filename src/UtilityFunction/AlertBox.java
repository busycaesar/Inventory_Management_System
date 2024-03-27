package UtilityFunction;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertBox {

	public static boolean confirmation(String message) {
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Please confirm");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();

	    return alert.getResult() == ButtonType.OK;
	}
	
}
