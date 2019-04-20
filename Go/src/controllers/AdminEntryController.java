package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class AdminEntryController extends GraphicalUserInterface {

	@FXML
	public void goToAdminDashboard(ActionEvent event) {
	    try {
	        goToView("AdminDashboard", event);
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
