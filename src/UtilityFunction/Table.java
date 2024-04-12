package UtilityFunction;

import java.util.ArrayList;

import Controller.PartController;
import Controller.ProductController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Table {

	public static <T> void load(TableView<T> table, ArrayList<T> allItems) {
		
		ObservableList<T> _allItems = FXCollections.observableArrayList(allItems);
		table.setItems(_allItems);

	}
	
    public static Object getSelected(TableView<?> table) {
    	
    	return table.getSelectionModel().getSelectedItem();
    	
    }
    
	public static void initPartsColumns(TableView<PartController> table) {
		
		table.setEditable(true);

		TableColumn<PartController, Integer> idColumn = new TableColumn<>("Part Id");
		idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

		TableColumn<PartController, String> nameColumn = new TableColumn<>("Part Name");
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		TableColumn<PartController, Integer> inventoryColumn = new TableColumn<>("Units Available");
		inventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUnitsAvailable()).asObject());

		TableColumn<PartController, Double> priceColumn = new TableColumn<>("Unit Cost");
		priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

		table.getColumns().addAll(idColumn, nameColumn, inventoryColumn, priceColumn);
	
	}
	
	public static void initProductsColumns(TableView<ProductController> table) {
		
		table.setEditable(true);

		TableColumn<ProductController, Integer> idColumn = new TableColumn<>("Product Id");
		idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

		TableColumn<ProductController, String> nameColumn = new TableColumn<>("Product Name");
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		TableColumn<ProductController, Integer> inventoryColumn = new TableColumn<>("Units Available");
		inventoryColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUnitsAvailable()).asObject());

		TableColumn<ProductController, Double> priceColumn = new TableColumn<>("Unit Cost");
		priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

		table.getColumns().addAll(idColumn, nameColumn, inventoryColumn, priceColumn);
	
	}
	
}
