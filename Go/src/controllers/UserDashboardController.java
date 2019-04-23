package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Leaderboard;
import models.MainStorage;
import models.ProfileImage;
import models.User;

public class UserDashboardController extends GraphicalUserInterface {
	
	private User currentUser;
	
	@FXML
	private ListView<String> leaderBoardListView;
	@FXML
	private ListView<String> gamesPlayedListView;
	@FXML
	private ImageView profileImage;
	@FXML
	private ImageView imageOne;
	@FXML
	private ImageView imageTwo;
	@FXML
	private ImageView imageThree;
	@FXML
	private ImageView imageFour;
	@FXML
	private ImageView imageFive;
	@FXML
	private ImageView imageSix;
	
	public void initData(ArrayList<User> list) {
		currentUser =  list.get(0);
		currentUser.onLogIn();
		try {
			MainStorage.updateUser(currentUser);
		} catch (Exception e1) {
			alertUser("Last logged in", "Error", "Could not update last logged in date");
		}
		
	    if (currentUser.getProfile().getProfileImage() == null) {
	        ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-1.png").toURI().toString());
	        currentUser.getProfile().setProfileImage(pf);
		}
	    String url = currentUser.getProfile().getProfileImage().getURL();
	    Image i = new Image(url);
	    profileImage.setImage(i);
	    
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
				String winPct = Double.toString(leaderBoardReversed.get(k).calculateWinPercentage());
				obs.add(userName + ", win pct: " + winPct);
			}
			
		} catch (Exception e) {
			alertUser("Leaderboard", "Error", "Could not load leaderboard");
		    return;
		}
	    ArrayList<String> gamesPlayed = currentUser.getGamesPlayed();
	    ObservableList<String> gamesPlayedList = FXCollections.observableArrayList();
	    gamesPlayedListView.setItems(gamesPlayedList);
	    for(int k = 0; k < gamesPlayed.size(); k++) {
	    	gamesPlayedList.add(gamesPlayed.get(k));
	    }
	}
	
	public void selectImageOne() {
        ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-1.png").toURI().toString());
        updateUserImage(pf);
	}
	
	public void selectImageTwo() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-2.png").toURI().toString());
        updateUserImage(pf);
	}
	
	public void selectImageThree() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-3.png").toURI().toString());
        updateUserImage(pf);	
	}
	public void selectImageFour() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-4.png").toURI().toString());
        updateUserImage(pf);	
	}
	public void selectImageFive() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-5.png").toURI().toString());
        updateUserImage(pf);
	}
	public void selectImageSix() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-6.png").toURI().toString());
        updateUserImage(pf);
	}
	
	public void updateUserImage(ProfileImage pf) {
		currentUser.getProfile().setProfileImage(pf);
		String url = currentUser.getProfile().getProfileImage().getURL();
		Image i = new Image(url);

		boolean updated;
		try {
			updated = MainStorage.updateUser(currentUser);
		} catch (Exception e) {
			alertUser("profile image update", "Error", "Could not update profile image");
		    return;
		}
		if (updated == true) {
			
			profileImage.setImage(i);
		}
		else {
		    alertUser("profile image update", "Error", "Could not update profile image");
		    return;
		}
		try {
			currentUser = MainStorage.getUser(currentUser.getProfile().getUserName());
		} catch (Exception e) {
			alertUser("profile image update", "Error", "Could not update profile image");
		    return;
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
