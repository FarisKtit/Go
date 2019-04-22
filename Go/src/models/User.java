package models;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
    private UserProfile profile;
    private LocalDateTime lastLoggedIn;
    private LocalDateTime joinDate;
    private ArrayList<String> gamesPlayed;
   // private String[] newPlayers;
    private int wins;
    private int losses;
    private boolean isAdmin;
    private static final long serialVersionUID = 1L;

    public User(String userName, String firstName, String lastName, boolean isAdmin){
        this.profile = new UserProfile(userName, firstName, lastName);
        this.lastLoggedIn = LocalDateTime.MIN;
        this.joinDate = LocalDateTime.now();
        this.gamesPlayed = new ArrayList<String>();
        //this.newPlayers = new String[9];
        this.isAdmin = isAdmin;
        this.wins = 0;
        this.losses = 0;
    }

    //public int getGamesPlayedSinceLastLogin(){ return 0;}

    //public String getNewPlayersSinceLastLogin(){ return ""; }

    //public String getChangeInLeaderboardPosition(){ return ""; }

    public int calculateWinPercentage(){
        return (wins/losses * 100);
    }

    public void addGamePlayed(String gameID, boolean win){
        if (win){
            wins = wins + 1;
        } else {
            losses = losses +1;
        }
        this.gamesPlayed.add(gameID);
    }

    public ArrayList<String> getGamesPlayed(){
        return this.gamesPlayed;
    }

    public UserProfile getProfile(){
        return this.profile;
    }

    public boolean isAdmin(){return isAdmin;}

    public LocalDateTime getJoinDate() {
        return this.joinDate;
    }

    public LocalDateTime getLastLoggedIn() {
        return this.lastLoggedIn;
    }

    public String toString() {
        return profile+"=="+lastLoggedIn+"=="+joinDate+"=="+gamesPlayed+"=="+wins+"=="
                +losses+"=="+isAdmin;
    }
}
