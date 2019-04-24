package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Administrator;
import models.User;
import models.UserStorage;

/**
 * This class manages interactions with the administrators' dash board to manage players.
 * @author Faris Ktit
 * @version 1.5 
 */
public class AdminDashboardController extends GraphicalUserInterface {
    
	//Store all GUI elements for attaching events or updating.
    @FXML
    private ListView<String> userListView;
    @FXML
    private TextField deleteUserField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private CheckBox isAdmin;
    @FXML
    private Label adminIdLabel;
    
    /**
     * Presents a list of all users saved on the game Go94.
     */
    @FXML
    public void initialize() {
    	//On initialisation, place all users in the system 
    	populateListView();
    }
    
    /**
     * Manages navigation to the home page from the dash board.
     * @param event
     */
	@FXML
	public void goToEntryDashboard(ActionEvent event) {
		//Try to navigate to homepage, catch exception is arises and make it
		//known in the GUI.
	    try {
	        goToView("EntryDashboard", event);
		} catch (IOException e) {
			alertUser("Navigation", "Error", "Cant return to homepage");
		}
	}
    
	/**
	 * Provides the ability for the administrator to add new players to the Game.
	 * @param event
	 */
    @FXML
    public void createUser(ActionEvent event) {
    	//Remove all empty spaces from input fields for creating a user.
    	String userName = userNameField.getText().replaceAll("\\s+", "");
    	String firstName = firstNameField.getText().replaceAll("\\s+", "");
    	String lastName = lastNameField.getText().replaceAll("\\s+", "");
    	int userNameLen = userName.length();
    	int firstNameLen = firstName.length();
    	int lastNameLen = lastName.length();
    	//Check if inputs are not empty, Otherwise alert the user.
    	if ((userNameLen == 0) || (firstNameLen == 0) || (lastNameLen == 0)) {
    		String alertHeader = "Create New User";
    		String alertSubHeader = "Error";
    		String alertContent = "Please make sure all fields are complete";
    		alertUser(alertHeader, alertSubHeader, alertContent);
    		return;
    	}
    	User newUser = null;
    	//If regular user added, save as regular user, else save as admin.
    	if (!isAdmin.isSelected()) {
    		newUser = new User(userName, firstName, lastName, false);
    	} else {
    		int current= (int) System.currentTimeMillis();
    		newUser = new Administrator(current, userName, firstName, lastName);
    	}
    	try {
    		//Try to create user and save to disk, if fails, manage with alerting
    		//user.
    		String result = UserStorage.createUser(newUser);
    		String alertHeader;
    		String alertSubHeader;
    		String alertContent;
			if (result.equals("Success")) {
				//Notify admin if successful.
				alertHeader = "Create New User";
				alertSubHeader = "Success";
				alertContent = "User created";
				alertUser( alertHeader, alertSubHeader, alertContent);
			} else if (result.equals("User exists")) {
				//Notify admin is username already exists.
				alertUser("Create New User", "Error", "User already exists");
				return;
			} else {
				//Otherwise we have a generic system error.
				alertUser("Create New User", "Error", "Please try again later");
				return;
			}
		} catch (Exception e) {
			//Any exceptions are caught and alerted to the user via the GUI.
	    	alertUser("Create New User", "Error", "Please try again later");
	    	e.printStackTrace();
	    	return;
		}
        //Empty input fields and reload the list.
    	userNameField.setText("");
    	firstNameField.setText("");
    	lastNameField.setText("");
    	populateListView();
    }
    
    /**
     * Manages the removal of players from the game Go94.
     * @param event
     */
    @FXML
    public void deleteUser(ActionEvent event) {
    	//Remove empty space from input field.
    	String userName = deleteUserField.getText().replaceAll("\\s+", "");
    	//Alert user if input field is empty.
    	if (userName.length() == 0) {
    		String alertHeader = "Delete User";
    		String alertSubHeader = "Error";
    		String alertContent = "Please make sure the username field has been filled out";
    		alertUser(alertHeader, alertSubHeader, alertContent);
    		return;
    	}
		String alertHeader;
		String alertSubHeader;
		String alertContent;
    	try {
    		//Try to delete the user, manage errors by alerting admin via the GUI.
			String result = UserStorage.deleteUser(userName);
			if (result.equals("No user")) {
				alertHeader = "Delete User";
				alertSubHeader = "Error";
				alertContent = "Please make sure the user exists";
	    		alertUser( alertHeader, alertSubHeader, alertContent);
			} else if (result.equals("Success")) {
				alertHeader = "Delete User";
				alertSubHeader = "Success";
				alertContent = "User deleted";
				alertUser( alertHeader, alertSubHeader, alertContent);
			} else {
				alertHeader = "Delete User";
				alertSubHeader = "Error";
				alertContent = "Please try again later";
				alertUser( alertHeader, alertSubHeader, alertContent);
			}
		} catch (Exception e) {
			//Exception caught and generic alert message provided to admin.
			alertHeader = "Delete User";
			alertSubHeader = "Error";
			alertContent = "Please try again later";
			alertUser( alertHeader, alertSubHeader, alertContent);
		}
    	//Empty input field and reload user list.
    	deleteUserField.setText("");
    	populateListView();
    }
    
    /**
     * Sets the administrator that is in charge of the dash board.
     * @param list
     */
    public void initData(ArrayList<User> list) {
    	//Retrieves user from AdminEntryController after successful authentication.
		Administrator currentUser = (Administrator) list.get(0);
		adminIdLabel.setText("Administrator ID: " + currentUser.getAdminID());
	}
    
    /**
     * Gathers all players on the system and presents it to the Administrator as a list.
     */
    private void populateListView() {
    	ObservableList<String> obs = FXCollections.observableArrayList();
    	userListView.setItems(obs);
    	ArrayList<User> users;
    	try {
    		//Read all users from file and add to list view for admin.
			users = UserStorage.getUserList();
			for (int i = 0; i < users.size(); ++i) {
				String userName = users.get(i).getProfile().getUserName();
				String firstName = users.get(i).getProfile().getFirstName();
				String lastName = users.get(i).getProfile().getLastName();
				obs.add(userName + " " + firstName + " " + lastName);
			}
		} catch (Exception e) {
			//Any exception is handled via alerting the admin via the GUI.
			String alertHeader = "Load User List";
			String alertSubHeader = "Error";
			String alertContent = "Users could not be loaded at this time";
    		alertUser( alertHeader, alertSubHeader, alertContent);
		}
    }
   
}
