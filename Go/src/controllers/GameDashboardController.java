package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class GameDashboardController extends GraphicalUserInterface {
	
	@FXML
	public void goToUserDashboard(ActionEvent event) {
	    try {
	        goToView("UserEntry", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goToAdminDashboard(ActionEvent event) {
	    try {
	        goToView("AdminEntry", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private TextField playerOneField;
	@FXML
	private TextField playerTwoField;
	
	@FXML
	public void goToGameGrid(ActionEvent event) {
		String playerOne = playerOneField.getText().replaceAll("\\s+", "");
		String playerTwo = playerTwoField.getText().replaceAll("\\s+", "");
		if((playerOne.equals("")) || (playerTwo.equals(""))) {
			alertUser("Start Game", "Error", "Please make sure players are selected");
			return;
		}
	    try {
	        goToView("GameGrid", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
