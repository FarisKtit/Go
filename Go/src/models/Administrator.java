package models;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Administrator extends User implements Serializable{
    private LocalDateTime joinDate;
    private int adminNumber;
    private static final long serialVersionUID = 1L;

    public Administrator(int adminNumber){
        super("","", "", true);
        this.adminNumber = adminNumber;
    }
    public void createNewUserAccount(String AdministratorDashboard){
    }
    public String toString() {
        return joinDate + "=="+adminNumber+"==";
    }
}
