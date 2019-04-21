package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.User;

public class UserDashboardController extends GraphicalUserInterface {
	
	public void initData(ArrayList<User> list) {
		User user =  list.get(0);
		System.out.println(user.getProfile().getUserName());
	}
	
	@FXML
	public void goToEntryDashboard(ActionEvent event) {
	    try {
	        goToView("EntryDashboard", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goToGameDashboard(ActionEvent event) {
	    try {
	        goToView("GameDashboard", event);
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
}
