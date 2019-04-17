package models;

import java.time.LocalDateTime;

public class User {
    private UserProfile profile;
    private LocalDateTime lastLoggedIn;
    private int[] gamesPlayed;
    private String[] newPlayers;

    public User(String userName, String firstName, String lastName){
        //this.profile = new UserProfile(userName, firstName, lastName);
        //this.lastLoggedIn = LocalDateTime.MIN;
        //this.gamesPlayed = new int[];
        //this.newPlayers = new String[];
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
        return "hi";
    }
}
