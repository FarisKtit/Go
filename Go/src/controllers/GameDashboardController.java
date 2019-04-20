package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Administrator;
import models.MainStorage;
import models.User;


public class GameDashboardController extends GraphicalUserInterface {
	
	@FXML
	public void goToUserDashboard(ActionEvent event) {
	    try {
	        goToView("UserEntry", event);
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
	
	@FXML
	private TextField playerOneField;
	@FXML
	private TextField playerTwoField;
	
	@FXML
	public void goToGameGrid(ActionEvent event) {
		String playerOne = playerOneField.getText().replaceAll("\\s+", "");
		String playerTwo = playerTwoField.getText().replaceAll("\\s+", "");
		if((playerOne.equals("")) || (playerTwo.equals(""))) {
			alertUser("Start Game", "Error", "Please make sure players are selected");
			return;
		}
	    try {
	    	ArrayList<User> list = MainStorage.getUserList();
	    	User player1 = null;
	    	User player2 = null;
	    	ArrayList<User> userList = new ArrayList<User>();
	    	for(int i = 0; i < list.size(); i++) {
	    		if(list.get(i).getProfile().getUserName().equals(playerOne)) {
	    			if(list.get(i).isAdmin()) {
	    				player1 = (Administrator) list.get(i);
	    				userList.add(player1);
	    			} else {
	    				player1 = list.get(i);
	    				userList.add(player1);
	    			}
	    		} else if(list.get(i).getProfile().getUserName().equals(playerTwo)) {
	    			if(list.get(i).isAdmin()) {
	    				player2 = (Administrator) list.get(i);
	    				userList.add(player2);
	    			} else {
	    				player2 = list.get(i);
	    				userList.add(player2);
	    			}
	    		}
	    	}
	    	if(player1 == null || player2 == null) {
	    		alertUser("Start Game", "Error", "Please enter correct usernames");
	    		return;
	    	}
	        goToViewWithUsers("GameGrid", event, userList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
