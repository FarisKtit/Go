package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.User;
import models.UserStorage;

/**
 * This class manages the authentication of users when logging into their dashboard.
 * @author Faris Ktit
 *
 */
public class UserEntryController extends GraphicalUserInterface {
	@FXML
	private TextField userNameField;
	@FXML
	private TextField userPasswordField;
	
	/**
	 * Manages the logging in of an .
	 * @param event
	 */
	@FXML
	public void goToUserDashboard(ActionEvent event) {
		//Remove empty spaces from input fields.
		String userName = userNameField.getText().replaceAll("\\s+", "");
		String userPassword = userPasswordField.getText().replaceAll("\\s+", "");
		//Password must be username with 94 on the end, otherwise reject.
		if (!userPassword.equals(userName + "94")) {
			alertUser("User Dashboard", "Error", "Incorrect login details");
			return;
		}
		User u = null;
		//Try to retrieve user, check if they are a real user.
		try {
			u = UserStorage.getUser(userName);
		} catch (IOException e1) {
			alertUser("User Dashboard", "Error", "Please try again later");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Make sure input fields are not empty
		if (userName.equals("") || userPassword.equals("")) {
			alertUser("User Dashboard", "Error", "Please complete all fields");
			return;
		} else if (u == null) {
			alertUser("User Dashboard", "Error", "Incorrect login details");
			return;
		}
		//If we reach here it is successful and navigate to userdashboard.
		ArrayList<User> list = new ArrayList<User>();
		list.add(u);
	    try {
	    	goToViewWithUsers("UserDashboard", event, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	public void goToEntryDashboard(ActionEvent event) {
	    try {
	        goToView("EntryDashboard", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
