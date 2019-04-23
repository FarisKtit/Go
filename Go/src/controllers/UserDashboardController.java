package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.MainStorage;
import models.ProfileImage;
import models.User;

public class UserDashboardController extends GraphicalUserInterface {
	
	private User currentUser;
	
	public void initData(ArrayList<User> list) {
		currentUser =  list.get(0);
		System.out.println(currentUser.getProfile().getUserName());
	    if (currentUser.getProfile().getProfileImage() == null) {
	        ProfileImage pf = new ProfileImage(new File("emoticons/emoticon-1.png").toURI().toString());
	        currentUser.getProfile().setProfileImage(pf);
		}
	        String url = currentUser.getProfile().getProfileImage().getURL();
	        Image i = new Image(url);
	        profileImage.setImage(i);
	}
	
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
}
