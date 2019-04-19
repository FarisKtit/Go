package models;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Administrator extends User implements Serializable{
    private LocalDateTime joinDate;
    private int adminNumber;
    private static final long serialVersionUID = 1L;

    public Administrator(int adminNumber, String userName, String firstName, String lastName){
        super(userName, firstName, lastName, true);
        this.adminNumber = adminNumber;
        this.joinDate = LocalDateTime.now();
    }
    public void createNewUserAccount(String AdministratorDashboard){
    }
    public boolean isAdmin(){return true;}
    public String toString() {
        return joinDate+"=="+adminNumber;
    }
}
