package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GameDashboardController {
	
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
	
	public void goToView(String view, ActionEvent e) throws IOException {
		Parent userDash = FXMLLoader.load(getClass().getResource("/Views/" + view + ".fxml"));
		Scene s = new Scene(userDash);
		Stage stage = (Stage) ((Node) e.getTarget()).getScene().getWindow();
		stage.setScene(s);
	}
	
    private void alertUser(String title, String header, String content) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
    }
}
