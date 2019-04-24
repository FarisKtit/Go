package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import models.Leaderboard;
import models.User;


public class EntryDashboardController extends GraphicalUserInterface {
	
	@FXML
	private ListView<String> leaderBoardListView;
	
	@FXML
	public void initialize() {
		populateLeaderBoard();
	}
	
	@FXML
	public void goToUserDashboard(ActionEvent event) {
	    try {
	        goToView("UserEntry", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goToGameDashboard(ActionEvent event) {
	    try {
	        goToView("GameDashboard", event);
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
	
	public void populateLeaderBoard() {
        Map<User, Double> leaders = null;
	    
	    ObservableList<String> obs = FXCollections.observableArrayList();
	    leaderBoardListView.setItems(obs);

	    try {
			leaders = Leaderboard.showLeaders();
			ArrayList<User> leaderBoard = new ArrayList<User>(leaders.keySet());
			ArrayList<User> leaderBoardReversed = new ArrayList<User>();
			for(int j = (leaderBoard.size() - 1); j > -1; j--) {
				leaderBoardReversed.add(leaderBoard.get(j));
			}
			for(int k = 0; k < leaderBoardReversed.size(); k++) {
				String userName = leaderBoardReversed.get(k).getProfile().getUserName();
				System.out.println(userName);
				String winPct = Double.toString(leaderBoardReversed.get(k).calculateWinPercentage());
				obs.add(userName + ", win pct: " + winPct + "%, wins: " + leaderBoardReversed.get(k).getWins());
				System.out.println(userName + ", win pct: " + winPct + ", wins: " + leaderBoardReversed.get(k).getWins() + ", losses: " + leaderBoardReversed.get(k).getLosses());
			}
			
		} catch (Exception e) {
			alertUser("Leaderboard", "Error", "Could not load leaderboard");
		    return;
		}
	}

}
