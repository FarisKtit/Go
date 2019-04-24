package models;

import java.io.Serializable;

/**
 * Implements UserProfile class.
 * @author cerriannesantos.
 * UserProfile class responsible for creating a user profile.
 */
public class UserProfile implements Serializable {
    private String userName;
    private String firstName;
    private String lastName;
    private int winPercentage;
    private ProfileImage profileImage;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for UserProfile.
     * @param userName users username.
     * @param firstName users first name.
     * @param lastName users last name.
     */
    public UserProfile (String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     *Gets the users username.
     * @return username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the users first name.
     * @return first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the users last name.
     * @return last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the users profile image.
     * @return profile image.
     */
    public ProfileImage getProfileImage() {
        return profileImage;
    }

    /**
     * Sets the users profile image.
     * @param profileImage profile image.
     */
    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * Gets the users win percentage.
     * @return returns the win percentage.
     */

    public int getWinPercentage() {
        return winPercentage;
    }

    /**
     * Sets the users win percentage.
     * @param winPercentage win percentage.
     */

    public void setWinPercentage(int winPercentage) {
        this.winPercentage = winPercentage;
    }

    /**
     * Sets the users username.
     * @param userName username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the users first name.
     * @param firstName first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the users last name.
     * @param lastName last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get all data and return it to the user interface.
     * @return All inputs and outputs.
     */
    public String toString() {
        return userName+"=="+firstName+"=="+lastName+"=="+winPercentage+"=="+profileImage;
    }
}
