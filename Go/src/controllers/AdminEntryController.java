package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Administrator;
import models.User;
import models.UserStorage;

/**
 * This class deals with authentication to ensure only administrators 
 * can access their dash board.
 * @author Faris Ktit
 * @version 1.3
 */
public class AdminEntryController extends GraphicalUserInterface {
    
	//Store all GUI input fields.
	@FXML
	private TextField adminUserName;
	@FXML
	private TextField adminPassword;
	
	/**
	 * Manages the authentication of administrators for accessing 
	 * the administrator' dash board.
	 * @param event
	 */
	@FXML
	public void goToAdminDashboard(ActionEvent event) {
		//Remove all empty space from input fields.
		String userName = adminUserName.getText().replaceAll("\\s+", "");
		String password = adminPassword.getText().replaceAll("\\s+", "");
		ArrayList<User> users = null;
		User user = null;
		//Root gets direct access incase any errors reading the files and no users are available.
		if (userName.equals("root")) {
			try {
				goToView("AdminDashboard", event);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		//Alert user is input is empty.
		if (userName.equals("") || password.equals("")) {
			alertUser("Login", "Error", "Please fill in the fields");
			return;
		}
		//Read all users from disk, catch exception and alert admin via GUI.
	    try {
	    	users = UserStorage.getUserList();
		} catch (Exception e) {
			alertUser("Login", "Error", "System error");
			return;
		}
	    //Check if user is an admin, otherwise they cannot log into the dashboard.
    	for (int i = 0; i < users.size(); i++) {
    		if(users.get(i).getProfile().getUserName().equals(userName)) {
    			user = users.get(i);
    			if (!(user instanceof Administrator)) {
    				alertUser("Login", "Error", "User is not an admin");
    				return;
    			}
    			if (!(password.equals(user.getProfile().getUserName() + "94"))) {
    				alertUser("Login", "Error", "Password is incorrect");
    				return;
    			}
    		}
    	}
    	//Alert admin is user doesn not exist.
    	if (user == null) {
    		alertUser("Login", "Error", "Admin does not exist");
			return;
    	}
    	//If admin is found, place into array list and send to admin dashboard view.
    	ArrayList<User> admin = new ArrayList<User>();
    	admin.add(user);
    	try {
			goToViewWithUsers("AdminDashboard", event, admin);
		} catch (IOException e) {
			alertUser("Login", "Error", "System error");
			return;
		}
	}
	
	/**
	 * Manages the navigation from administrator authentication back to home.
	 * @param event
	 */
	@FXML
	public void goToEntryDashboard(ActionEvent event) {
		//Try to navigate to home page, catch exception if raised and alert user.
	    try {
	        goToView("EntryDashboard", event);
		} catch (IOException e) {
			alertUser("Navigation", "Error", "Unable to return home, try again later;");
		}
	}
}
