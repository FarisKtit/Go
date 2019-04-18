package models;

import java.time.LocalDateTime;
import java.io.Serializable;

public class User implements Serializable{
    private UserProfile profile;
    private LocalDateTime lastLoggedIn;
    private int[] gamesPlayed;
    private String[] newPlayers;
    private boolean isAdmin;
    private static final long serialVersionUID = 1L;

    public User(String userName, String firstName, String lastName, boolean isAdmin){
        this.profile = new UserProfile(userName, firstName, lastName);
        this.lastLoggedIn = LocalDateTime.MIN;
        this.gamesPlayed = new int[9];
        this.newPlayers = new String[9];
        this.isAdmin = isAdmin;
    }
    public int getGamesPlayedSinceLastLogin(){
    return 0;}

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
    public UserProfile getProfile(){
        return this.profile;
    }
    public boolean isAdmin(){return isAdmin;}
    public String toString() {
        return profile + "=="+lastLoggedIn+"=="+gamesPlayed+"=="+newPlayers
                +isAdmin+"==";
    }
}
