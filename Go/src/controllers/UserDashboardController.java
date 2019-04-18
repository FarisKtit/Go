package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserDashboardController {
	@FXML
	private Button EntryDashboardBtn;
	public void goToEntryDashboard() {
		EntryDashboardBtn.setOnAction(event -> {
	        try {
	        	goToView("EntryDashboard", EntryDashboardBtn);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	@FXML
	private Button GameDashboardBtn;
	public void goToGameDashboard() {
		GameDashboardBtn.setOnAction(event -> {
	        try {
	        	goToView("GameDashboard", GameDashboardBtn);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	@FXML
	private Button AdminDashboardBtn;
	public void goToAdminDashboard() {
		AdminDashboardBtn.setOnAction(event -> {
	        try {
	        	goToView("AdminDashboard", AdminDashboardBtn);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void goToView(String view, Button obj) throws IOException {
		Parent userDash = FXMLLoader.load(getClass().getResource("./../Views/" + view + ".fxml"));
		Scene s = new Scene(userDash);
		Stage stage = (Stage) obj.getScene().getWindow();
		stage.setScene(s);
	}
}
