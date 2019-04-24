package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Administrator;
import models.MainStorage;
import models.User;
import models.UserStorage;


public class GameDashboardController extends GraphicalUserInterface {
	
	@FXML
	public void goToEntryDashboard(ActionEvent event) {
	    try {
	        goToView("EntryDashboard", event);
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
		if(playerOne.equals(playerTwo)) {
			alertUser("Start Game", "Error", "Please make sure different players are selected");
			return;
		}
	    try {
	    	ArrayList<User> list = UserStorage.getUserList();
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
	    		} 
	    	}
	    	for(int i = 0; i < list.size(); i++) {
		    	if(list.get(i).getProfile().getUserName().equals(playerTwo)) {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
