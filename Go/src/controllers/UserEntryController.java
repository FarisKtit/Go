package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserEntryController {
	
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
	        goToView("EntryEntry", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToView(String view, ActionEvent e) throws IOException {
		Parent userDash = FXMLLoader.load(getClass().getResource("./../Views/" + view + ".fxml"));
		Scene s = new Scene(userDash);
		Stage stage = (Stage) ((Node) e.getTarget()).getScene().getWindow();
		stage.setScene(s);
	}
}
