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
import models.MainStorage;
import models.User;
import models.UserStorage;


public class AdminDashboardController extends GraphicalUserInterface {
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
    
    public void initData(ArrayList<User> list) {
		Administrator currentUser = (Administrator) list.get(0);
		//adminIdLabel.setText("Administrator ID: " + currentUser);
	}
    
    @FXML
    public void initialize() {
    	populateListView();
    }
    
    @FXML
    public void createUser(ActionEvent event) {
    	String userName = userNameField.getText().replaceAll("\\s+", "");
    	String firstName = firstNameField.getText().replaceAll("\\s+", "");
    	String lastName = lastNameField.getText().replaceAll("\\s+", "");
    	if((userName.length() == 0) || (firstName.length() == 0) || (lastName.length() == 0)) {
    		alertUser("Create New User", "Error", "Please make sure all fields have been filled in");
    		return;
    	}
    	User newUser = null;
    	if(!isAdmin.isSelected()) newUser = new User(userName, firstName, lastName, false);
    	else {
    		int current= (int) System.currentTimeMillis();
    		newUser = new Administrator(current, userName, firstName, lastName);
    	}
    	try {
    		String result = MainStorage.createUser(newUser);
			if(result.equals("Success")) {
				alertUser("Create New User", "Success", "User created");
			} else if(result.equals("User exists")) {
				alertUser("Create New User", "Error", "User already exists");
				return;
			} else {
				alertUser("Create New User", "Error", "Please try again later");
				return;
			}
		} catch (Exception e) {
	    	alertUser("Create New User", "Error", "Please try again later");
	    	return;
		}

    	userNameField.setText("");
    	firstNameField.setText("");
    	lastNameField.setText("");
    	populateListView();
    }
    
    @FXML
    public void deleteUser(ActionEvent event) {
    	String userName = deleteUserField.getText().replaceAll("\\s+", "");
    	if(userName.length() == 0) {
    		alertUser("Delete User", "Error", "Please make sure the username field has been filled out");
    		return;
    	}
    	try {
			String result = MainStorage.deleteUser(userName);
			if(result.equals("No user")) {
	    		alertUser("Delete User", "Error", "Please make sure the user exists");
			} else if(result.equals("Success")) {
				alertUser("Delete User", "Success", "User deleted");
			} else {
				alertUser("Delete User", "Error", "Please try again later");
			}
		} catch (Exception e) {
			alertUser("Delete User", "Error", "Please try again later");
		}
    	deleteUserField.setText("");
    	populateListView();
    }
    
    
    public void populateListView() {
    	ObservableList<String> obs = FXCollections.observableArrayList();
    	userListView.setItems(obs);
    	ArrayList<User> users;
    	try {
			users = UserStorage.getUserList();
			for(int i = 0; i < users.size(); ++i) {
				String userName = users.get(i).getProfile().getUserName();
				String firstName = users.get(i).getProfile().getFirstName();
				String lastName = users.get(i).getProfile().getLastName();
				obs.add(userName + " " + firstName + " " + lastName);
			}
		} catch (Exception e) {
    		alertUser("Load User List", "Error", "Users could not be loaded at this time");
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
