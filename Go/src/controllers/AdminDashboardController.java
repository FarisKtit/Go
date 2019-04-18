package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.MainStorage;
import models.User;

public class AdminDashboardController {
    @FXML
    private ListView userListView;
    @FXML
    private TextField deleteUserField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    
    @FXML
    public void createUser(ActionEvent event) {
    	String userName = userNameField.getText().replaceAll("\\s+", "");
    	String firstName = firstNameField.getText().replaceAll("\\s+", "");
    	String lastName = lastNameField.getText().replaceAll("\\s+", "");
    	if((userName.length() == 0) || (firstName.length() == 0) || (lastName.length() == 0)) {
    		alertUser("Create new user", "Error", "Please make sure all fields have been filled in");
    	} else {
    		try {
				MainStorage.createUser(new User(userName, firstName, lastName));
			} catch (FileNotFoundException e) {
	    		alertUser("Create new user", "Error", "Please try again later");
				e.printStackTrace();
			} catch (IOException e) {
	    		alertUser("Create new user", "Error", "Please try again later");
				e.printStackTrace();
			}
    	}
    }
    
    @FXML
    public void deleteUser(ActionEvent event) {
    	
    }
    
    private void alertUser(String title, String header, String content) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
    }
    
    
    
}
