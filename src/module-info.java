module inventory_management_system {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;
	
	opens View to javafx.graphics, javafx.fxml;
	opens Controller to javafx.base;
}