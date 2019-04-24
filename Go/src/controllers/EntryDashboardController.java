package controllers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.Leaderboard;
import models.User;

/**
 * This class manages the home page for the game Go94 and provides a leaderboard.
 * @author farisktit
 * @version 1.3
 */
public class EntryDashboardController extends GraphicalUserInterface {
	
	//Store list view element for GUI for when it needs updating.
	@FXML
	private ListView<String> leaderBoardListView;
	
	/**
	 * Manages preparing the home screen for presenting the leaderboard.
	 */
	@FXML
	public void initialize() {
		//Add all users sorted by winning percentage on leaderboard.
		populateLeaderBoard();
	}
	
	/**
	 * Manages navigation from the home page to the user dashboard authentication screen.
	 * @param event
	 */
	@FXML
	public void goToUserDashboard(ActionEvent event) {
	    try {
	        goToView("UserEntry", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Manages the navigation from the home page to the game dashboard where two players can start a game.
	 * @param event
	 */
	@FXML
	public void goToGameDashboard(ActionEvent event) {
	    try {
	        goToView("GameDashboard", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Manages the navigation from the home page to the administrator' dashboard authentication screen.
	 * @param event
	 */
	@FXML
	public void goToAdminDashboard(ActionEvent event) {
	    try {
	        goToView("AdminEntry", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Manages the retrieval of the users and placing them in 
	 * correct order on the dashboard based on winning percentage.
	 */
	public void populateLeaderBoard() {
		//Data structure for storing sorted users by winning percentage.
		//This data is retrieved using the Leaderboard class.
        Map<User, Double> leaders = null;
	    ObservableList<String> obs = FXCollections.observableArrayList();
	    leaderBoardListView.setItems(obs);

	    try {
	    	//Retrieve sorted leaders.
			leaders = Leaderboard.showLeaders();
			ArrayList<User> leaderBoard = new ArrayList<User>(leaders.keySet());
			ArrayList<User> leaderBoardReversed = new ArrayList<User>();
			//Reverse them using array list as they are in ascending order.
			for (int j = (leaderBoard.size() - 1); j > -1; j--) {
				leaderBoardReversed.add(leaderBoard.get(j));
			}
			for (int k = 0; k < leaderBoardReversed.size(); k++) {
				//Update list view with leaders on leaderboard.
				String userName = leaderBoardReversed.get(k).getProfile().getUserName();
				DecimalFormat df = new DecimalFormat(".##"); 
				String winPct = df.format(leaderBoardReversed.get(k).calculateWinPercentage());
				double userWins = leaderBoardReversed.get(k).getWins();
				obs.add(userName + ", win pct: " + winPct + "%, wins: " + userWins);
			}
			
		} catch (Exception e) {
			//Catch exception from Leaderboard class and then alert user through GUI.
			alertUser("Leaderboard", "Error", "Could not load leaderboard");
		    return;
		}
	}

}
