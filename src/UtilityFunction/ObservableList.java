package UtilityFunction;

import java.util.ArrayList;

import Controller.InventoryController;
import javafx.collections.FXCollections;

public class ObservableList {

	public static ObservableList getList(ArrayList<Object> array){
		
		return (ObservableList) FXCollections.observableArrayList(InventoryController.getAllParts());
		
	}
	
}
