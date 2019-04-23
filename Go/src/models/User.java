package models;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
    private UserProfile profile;
    private LocalDateTime lastLoggedIn;
    private LocalDateTime joinDate;
    private ArrayList<String> gamesPlayed;
    private int wins;
    private int losses;
    private boolean isAdmin;
    private static final long serialVersionUID = 1L;

    public User(String userName, String firstName, String lastName, boolean isAdmin){
        this.profile = new UserProfile(userName, firstName, lastName);
        this.lastLoggedIn = LocalDateTime.MIN;
        this.joinDate = LocalDateTime.now();
        this.gamesPlayed = new ArrayList<String>();
        this.isAdmin = isAdmin;
        this.wins = 0;
        this.losses = 0;
    }

    public UserProfile getProfile(){
        return this.profile;
    }

    public void onLogIn(){
        lastLoggedIn = LocalDateTime.now();

    }

    public LocalDateTime getLastLoggedIn() {
        return this.lastLoggedIn;
    }

    public LocalDateTime getJoinDate() {
        return this.joinDate;
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

    public int calculateWinPercentage(){
        return (wins/(wins+losses) * 100);
    }

    public int getWins(){
       return this.wins;
    }

    public int getLosses(){
        return this.losses;
    }

    public boolean isAdmin(){return isAdmin;}

    public String toString() {
        return profile+"=="+lastLoggedIn+"=="+joinDate+"=="+gamesPlayed+"=="+wins+"=="
                +losses+"=="+isAdmin;
    }
}
