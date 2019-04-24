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
	//Store user that is logged into dashboard
	private User currentUser;
	
	//Store all GUI elements to update
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
		//Store logged in user.
		currentUser =  list.get(0);
		//Populate list views with new users since last login and games.
		populateNewUsersSinceLastLogin();
		populateNewGamesSinceLastLogin();
		//Update lastLoggedIn date
		currentUser.onLogIn();
		try {
			//Update user to save their new lastLoggedIn date
			UserStorage.updateUser(currentUser);
		} catch (Exception e1) {
			alertUser("Last logged in", "Error", "Could not update last logged in date");
		}
		//If profile image has not been set, set default as first emoticon.
	    if (currentUser.getProfile().getProfileImage() == null) {
	    	String file = new File("emoticons/emoticon-1.png").toURI().toString();
	        ProfileImage profileImage = new ProfileImage(file);
	        currentUser.getProfile().setProfileImage(profileImage);
		}
	    //update profile image
	    String url = currentUser.getProfile().getProfileImage().getURL();
	    Image i = new Image(url);
	    profileImage.setImage(i);
	    ArrayList<String> gamesPlayed = currentUser.getGamesPlayed();
	    ObservableList<String> gamesPlayedList = FXCollections.observableArrayList();
	    gamesPlayedListView.setItems(gamesPlayedList);
	    for (int k = 0; k < gamesPlayed.size(); k++) {
	    	gamesPlayedList.add(gamesPlayed.get(k));
	    }
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageOne() {
		String file = new File("emoticons/emoticon-1.png").toURI().toString();
		ProfileImage profileImage = new ProfileImage(file);
        updateUserImage(profileImage);
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageTwo() {
		String file = new File("emoticons/emoticon-2.png").toURI().toString();
		ProfileImage profileImage = new ProfileImage(file);
        updateUserImage(profileImage);
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageThree() {
		String file = new File("emoticons/emoticon-3.png").toURI().toString();
		ProfileImage profileImage = new ProfileImage(file);
        updateUserImage(profileImage);
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageFour() {
		String file = new File("emoticons/emoticon-4.png").toURI().toString();
		ProfileImage profileImage = new ProfileImage(file);
        updateUserImage(profileImage);	
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageFive() {
		String file = new File("emoticons/emoticon-5.png").toURI().toString();
		ProfileImage profileImage = new ProfileImage(file);
        updateUserImage(profileImage);
	}
	
	/**
	 * Selects a profile image from the selection of profile images available.
	 */
	public void selectImageSix() {
		String file = new File("emoticons/emoticon-6.png").toURI().toString();
		ProfileImage profileImage = new ProfileImage(file);
        updateUserImage(profileImage);
	}
	
	/**
	 * Sets the user' profile image to a new profile image.
	 * @param profileImage
	 */
	public void updateUserImage(ProfileImage image) {
		//Update profile image of current user logged in.
		currentUser.getProfile().setProfileImage(image);
		String url = currentUser.getProfile().getProfileImage().getURL();
		Image i = new Image(url);
		boolean updated;
		//Save user to disk after updating their profile image.
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
		//Update stored user in dashboard with updated version of itself.
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
		//Use UserStorage class to get all new users since last login.
		ArrayList<User> result = null;
		try {
			result = UserStorage.newUserSinceLastLogin(currentUser.getLastLoggedIn());
		} catch (Exception e) {
			alertUser("New Users", "Error", "Could not populate new users since last login");
		    return;
		}
		ObservableList<String> newUsers = FXCollections.observableArrayList();
		newUsersListView.setItems(newUsers);
		if (result == null) {
			return;
		}
		//Populate list view with all new user' username.
	    for (int k = 0; k < result.size(); k++) {
	    	newUsers.add(result.get(k).getProfile().getUserName());
	    }
		
	}
	
	/**
	 * Populates a list with new games played since the last time the logged in user had logged in.
	 */
	public void populateNewGamesSinceLastLogin() {
		//Use UserStorage class to get all new games played since last login.
		ArrayList<Game> result = null;
		try {
			result = UserStorage.gamesPlayedSinceLastLogin(currentUser.getLastLoggedIn());
		} catch (Exception e) {
			alertUser("New Users", "Error", "Could not populate new games since last login");
		    return;
		}
		ObservableList<String> newGames = FXCollections.observableArrayList();
		newGamesListView.setItems(newGames);
		if (result == null) {
			return;
		}
		//Update list view with games played since last login
	    for (int k = 0; k < result.size(); k++) {
	    	newGames.add(result.get(k).getGameID());
	    }
	}
	

	
}
