package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Administrator;
import models.MainStorage;
import models.User;


public class AdminEntryController extends GraphicalUserInterface {
    
	@FXML
	private TextField adminUserName;
	@FXML
	private TextField adminPassword;
	
	@FXML
	public void goToAdminDashboard(ActionEvent event) {
		String userName = adminUserName.getText().replaceAll("\\s+", "");
		String password = adminPassword.getText().replaceAll("\\s+", "");
		ArrayList<User> users = null;
		User user = null;
		if(userName.equals("") || password.equals("")) {
			alertUser("Login", "Error", "Please fill in the fields");
			return;
		}
	    try {
	    	users = MainStorage.getUserList();
		} catch (Exception e) {
			alertUser("Login", "Error", "System error");
			return;
		}
    	for(int i = 0; i < users.size(); i++) {
    		if(users.get(i).getProfile().getUserName().equals(userName)) {
    			user = users.get(i);
    			if(!(user instanceof Administrator)) {
    				alertUser("Login", "Error", "User is not an admin");
    				return;
    			}
    			if(!(password.equals(user.getProfile().getUserName() + "94"))) {
    				alertUser("Login", "Error", "Password is incorrect");
    				return;
    			}
    		}
    	}
    	if(user == null) {
    		alertUser("Login", "Error", "Admin does not exist");
			return;
    	}
    	ArrayList<User> admin = new ArrayList<User>();
    	admin.add(user);
    	try {
			goToViewWithUsers("AdminDashboard", event, admin);
		} catch (IOException e) {
			alertUser("Login", "Error", "System error");
			return;
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
