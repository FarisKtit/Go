package controllers;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.MainStorage;
import models.User;

public class AdminDashboardController {
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
    	} else {
    		User newUser = new User(userName, firstName, lastName);
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
    
    private void alertUser(String title, String header, String content) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
    }
    
    public void populateListView() {
    	ObservableList<String> obs = FXCollections.observableArrayList();
    	userListView.setItems(obs);
    	ArrayList<User> users;
    	try {
			users = MainStorage.getUserList();
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
}
