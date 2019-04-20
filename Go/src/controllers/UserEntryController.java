package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class UserEntryController extends GraphicalUserInterface {
	
	@FXML
	public void goToUserDashboard(ActionEvent event) {
	    try {
	        goToView("UserDashboard", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goToEntryDashboard(ActionEvent event) {
	    try {
	        goToView("EntryDashboard", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
