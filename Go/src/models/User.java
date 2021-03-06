package models;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implements User class.
 * @author cerriannesantos.
 * User class used for creating a user.
 */
public class User implements Serializable {
    private UserProfile profile;
    private LocalDateTime lastLoggedIn;
    private LocalDateTime joinDate;
    private ArrayList<String> gamesPlayed;
    private double wins;
    private double losses;
    private boolean isAdmin;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for class User.
     * @param userName Users Username.
     * @param firstName Users first name.
     * @param lastName users last name.
     * @param isAdmin boolean value whether or not User is an admin.
     */
    public User(String userName, String firstName, String lastName, boolean isAdmin) {
        this.profile = new UserProfile(userName, firstName, lastName);
        this.lastLoggedIn = LocalDateTime.now();
        this.joinDate = LocalDateTime.now();
        this.gamesPlayed = new ArrayList<String>();
        this.isAdmin = isAdmin;
        this.wins = 0;
        this.losses = 0;
    }

    /**
     * Gets the User profile.
     * @return returns users profile.
     */
    public UserProfile getProfile() {
        return this.profile;
    }

    /**
     * Sets Last date time logged in for User.
     */
    public void onLogIn(){
        lastLoggedIn = LocalDateTime.now();

    }

    /**
     * Gets last log in date for User.
     * @return last log in date time.
     */
    public LocalDateTime getLastLoggedIn() {
        return this.lastLoggedIn;
    }

    /**
     * Gets Users join date.
     * @return join date.
     */
    public LocalDateTime getJoinDate() {
        return this.joinDate;
    }

    /**
     * Records games played and wins or losses of User.
     * @param gameID gameID.
     * @param win Wins.
     */
    public void addGamePlayed(String gameID, boolean win) {
        if (win){
            wins = wins + 1;
        } else {
            losses = losses +1;
        }
        this.gamesPlayed.add(gameID);
    }

    /**
     * Gets games played by User.
     * @return Games played by User.
     */
    public ArrayList<String> getGamesPlayed() {
        return this.gamesPlayed;
    }

    /**
     * Calculates win percentage for user.
     * @return The Users win percentage.
     */
    public double calculateWinPercentage() {
    	if ((wins + losses) == 0) {
    		return 0d;
    	}
        return wins/(wins+losses) * 100;
    }

    /**
     * Gets number of wins for User.
     * @return Users wins.
     */
    public double getWins() {
       return this.wins;
    }

    /**
     * Gets number of losses for User.
     * @return Users losses.
     */
    public double getLosses() {
        return this.losses;
    }

    /**
     * Returns whether or not User is an admin.
     * @return true if User is admin, false otherwise.
     */
    public boolean isAdmin(){return isAdmin;}

    /**
     * Get all data and return it to the user interface.
     * @return All inputs and outputs.
     */
    public String toString() {
        return profile+"=="+lastLoggedIn+"=="+joinDate+"=="+gamesPlayed+"=="+wins+"=="
                +losses+"=="+isAdmin;
    }
}
