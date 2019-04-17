package models;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Administrator implements Serializable{
    private LocalDateTime joinDate;
    private int adminNumber;
    private static final long serialVersionUID = 1L;

    public Administrator(int adminNumber){
        this.adminNumber = adminNumber;
    }
    public void createNewUserAccount(String AdministratorDashboard){
    }
    public void evaluateApplication(String System, String Player){
    }
    public void giveCredits(String Player){
    }
    public String toString() {
        return joinDate + "=="+adminNumber+"==";
    }
}
