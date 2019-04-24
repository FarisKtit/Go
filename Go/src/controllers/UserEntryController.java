package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.PasswordField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.MainStorage;
import models.User;
import models.UserStorage;


public class UserEntryController extends GraphicalUserInterface {
	@FXML
	private TextField userNameField;
	@FXML
	private TextField userPasswordField;
	
	@FXML
	public void goToUserDashboard(ActionEvent event) {
		String userName = userNameField.getText().replaceAll("\\s+", "");
		String userPassword = userPasswordField.getText().replaceAll("\\s+", "");
		if(!userPassword.equals(userName + "94")) {
			alertUser("User Dashboard", "Error", "Incorrect login details");
			return;
		}
		User u = null;
		try {
			u = UserStorage.getUser(userName);
		} catch (IOException e1) {
			alertUser("User Dashboard", "Error", "Please try again later");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(userName.equals("") || userPassword.equals("")) {
			alertUser("User Dashboard", "Error", "Please complete all fields");
			return;
		} else if(u == null) {
			alertUser("User Dashboard", "Error", "Incorrect login details");
			return;
		}
		ArrayList<User> list = new ArrayList<User>();
		list.add(u);
	    try {
	    	goToViewWithUsers("UserDashboard", event, list);
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
