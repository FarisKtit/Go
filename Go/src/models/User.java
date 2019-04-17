package models;

import java.time.LocalDateTime;
import java.io.Serializable;

public class User implements Serializable{
    private UserProfile profile;
    private LocalDateTime lastLoggedIn;
    private int[] gamesPlayed;
    private String[] newPlayers;
    private static final long serialVersionUID = 1L;

    public User(String userName, String firstName, String lastName){
        this.profile = new UserProfile(userName, firstName, lastName);
        this.lastLoggedIn = LocalDateTime.MIN;
        this.gamesPlayed = new int[9];
        this.newPlayers = new String[9];
    }
    public int getGamesPlayedSinceLastLogin(){
    return 0;}   //I think system is a bad name..

    public String getNewPlayersSinceLastLogin(){
        return "";
    }
    public String getChangeInLeaderboardPosition(){
        return "";
    }
    public String calculateWinPercentage(){
        return "";
    }
    public String getGamesPlayed(){
        return "";
    }
    public String getProfile(){
        return "";
    }
    public String toString() {
        return profile + "=="+lastLoggedIn+"=="+gamesPlayed+"=="+newPlayers;
    }
}
