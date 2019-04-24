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
 * This class manages the selection of two users from which to start a game of Go94.
 * @author Faris Ktit
 *
 */
public class GameDashboardController extends GraphicalUserInterface {
	
	@FXML
	private TextField playerOneField;
	@FXML
	private TextField playerTwoField;
	@FXML
	
	/**
	 * Manages the navigation from the Game Dashboard back to the homepage.
	 * @param event
	 */
	public void goToEntryDashboard(ActionEvent event) {
	    try {
	        goToView("EntryDashboard", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if players are existing players or administrators 
	 * then navigates players to the game Go94.
	 * @param event
	 */
	@FXML
	public void goToGameGrid(ActionEvent event) {
		//Remove empty spaces from input fields.
		String playerOne = playerOneField.getText().replaceAll("\\s+", "");
		String playerTwo = playerTwoField.getText().replaceAll("\\s+", "");
		//Check if either input field is empty.
		if ((playerOne.equals("")) || (playerTwo.equals(""))) {
			alertUser("Start Game", "Error", "Please make sure players are selected");
			return;
		}
		//Make sure two unique users have been selected.
		if (playerOne.equals(playerTwo)) {
			alertUser("Start Game", "Error", "Please make sure different players are selected");
			return;
		}
	    try {
	    	//Check if users are real users by comparing to saved file.
	    	ArrayList<User> list = UserStorage.getUserList();
	    	User player1 = null;
	    	User player2 = null;
	    	ArrayList<User> userList = new ArrayList<User>();
	    	for (int i = 0; i < list.size(); i++) {
	    		//If they are an admin they should be instantiated as one, otherwise as a User.
	    		if (list.get(i).getProfile().getUserName().equals(playerOne)) {	
	    			if (list.get(i).isAdmin()) {
	    				player1 = (Administrator) list.get(i);
	    				userList.add(player1);
	    			} else {
	    				player1 = list.get(i);
	    				userList.add(player1);
	    			}
	    		} 
	    	}
	    	//Carry out same logic for second user.
	    	for (int i = 0; i < list.size(); i++) {
		    	if (list.get(i).getProfile().getUserName().equals(playerTwo)) {
	    			if (list.get(i).isAdmin()) {
	    				player2 = (Administrator) list.get(i);
	    				userList.add(player2);
	    			} else {
	    				player2 = list.get(i);
	    				userList.add(player2);
	    			}
	    		}
	    	}
	    	//If either of the users are still null, they do not exist as real users.
	    	if (player1 == null || player2 == null) {
	    		//Alert to user then return.
	    		alertUser("Start Game", "Error", "Please enter correct usernames");
	    		return;
	    	}
	    	//Go to view with users to play the game if all is successful.
	        goToViewWithUsers("GameGrid", event, userList);
		} catch (IOException e) {
			alertUser("Start Game", "Error", "Try again later");
		} catch (Exception e) {
			alertUser("Start Game", "Error", "Try again later");
		}
	}
}
