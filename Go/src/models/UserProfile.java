package models;

import java.io.Serializable;

public class UserProfile implements Serializable{
    private String userName;
    private String firstName;
    private String lastName;
    private int winPercentage;
    private String profileImage;
    private String[] favouritePlayers;
    private int gameCredits;
    private static final long serialVersionUID = 1L;

    public UserProfile (String userName, String firstName, String lastName){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getUserName (){
        return userName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getProfileImage(){
        return profileImage;
    }
    public String[] getFavouritePlayers(){
        return favouritePlayers;
    }
    public int getCredits(){
        return gameCredits;
    }
    public int getWinPercentage(){
        return winPercentage;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setWinPercentage(int winPercentage){
        this.winPercentage = winPercentage;
    }
    public String toString() {
        return userName + "=="+firstName+"=="+lastName+"=="+winPercentage+"=="+profileImage
                +"=="+favouritePlayers+"=="+gameCredits;
    }
}