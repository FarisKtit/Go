package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Game;
import models.ProfileImage;
import models.User;
import models.UserStorage;

/**
 * This class manages displaying user specific information, such as games played since last login,
 * the new users since last login. This class also provides the ability to select and display
 * a profile image. Also the list of all games played by the specific user.
 * @author Faris Ktit
 * @author Nathan Forester
 *
 */
public class UserDashboardController extends GraphicalUserInterface {
	
	private User currentUser;
	
	@FXML
	private ListView<String> leaderBoardListView;
	@FXML
	private ListView<String> gamesPlayedListView;
	@FXML
	private ListView<String> newUsersListView;
	@FXML
	private ListView<String> newGamesListView;
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
	
	/**
	 * Manages the navigation from the user' dash board back to the homepage.
	 * @param event
	 */
	@FXML
	public void goToEntryDashboard(ActionEvent event) {
	    try {
	        goToView("EntryDashboard", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialises the dashboard with the user specific information such as profile image, games played,
	 * games played since last login and new users since last login.
	 * @param list
	 */
	public void initData(ArrayList<User> list) {
		currentUser =  list.get(0);
		populateNewUsersSinceLastLogin();
		populateNewGamesSinceLastLogin();
		currentUser.onLogIn();
		try {
			UserStorage.updateUser(currentUser);
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
	    ArrayList<String> gamesPlayed = currentUser.getGamesPlayed();
	    ObservableList<String> gamesPlayedList = FXCollections.observableArrayList();
	    gamesPlayedListView.setItems(gamesPlayedList);
	    for(int k = 0; k < gamesPlayed.size(); k++) {
	    	gamesPlayedList.add(gamesPlayed.get(k));
	    }
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageOne() {
        ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-1.png").toURI().toString());
        updateUserImage(pf);
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageTwo() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-2.png").toURI().toString());
        updateUserImage(pf);
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageThree() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-3.png").toURI().toString());
        updateUserImage(pf);	
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageFour() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-4.png").toURI().toString());
        updateUserImage(pf);	
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageFive() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-5.png").toURI().toString());
        updateUserImage(pf);
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageSix() {
		ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-6.png").toURI().toString());
        updateUserImage(pf);
	}
	
	/**
	 * Sets the user' profile image to a new profile image.
	 * @param profileImage
	 */
	public void updateUserImage(ProfileImage image) {
		currentUser.getProfile().setProfileImage(image);
		String url = currentUser.getProfile().getProfileImage().getURL();
		Image i = new Image(url);
		boolean updated;
		try {
			updated = UserStorage.updateUser(currentUser);
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
			currentUser = UserStorage.getUser(currentUser.getProfile().getUserName());
		} catch (Exception e) {
			alertUser("profile image update", "Error", "Could not update profile image");
		    return;
		}
	}
	
	/**
	 * Populates a list with new users since the last time the logged in user had logged in.
	 */
	public void populateNewUsersSinceLastLogin() {
		ArrayList<User> result = null;
		try {
			result = UserStorage.newUserSinceLastLogin(currentUser.getLastLoggedIn());
		} catch (Exception e) {
			alertUser("New Users", "Error", "Could not populate new users since last login");
		    return;
		}
		ObservableList<String> newUsers = FXCollections.observableArrayList();
		newUsersListView.setItems(newUsers);
		if(result == null) {
			return;
		}
	    for(int k = 0; k < result.size(); k++) {
	    	newUsers.add(result.get(k).getProfile().getUserName());
	    }
		
	}
	
	/**
	 * Populates a list with new games played since the last time the logged in user had logged in.
	 */
	public void populateNewGamesSinceLastLogin() {
		ArrayList<Game> result = null;
		try {
			result = UserStorage.gamesPlayedSinceLastLogin(currentUser.getLastLoggedIn());
		} catch (Exception e) {
			alertUser("New Users", "Error", "Could not populate new games since last login");
		    return;
		}
		ObservableList<String> newGames = FXCollections.observableArrayList();
		newGamesListView.setItems(newGames);
		if(result == null) {
			return;
		}
	    for(int k = 0; k < result.size(); k++) {
	    	newGames.add(result.get(k).getGameID());
	    }
	}
	

	
}
