package models;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * @author cerriannesantos
 * Administator class responsible for creating an administrator
 */
public class Administrator extends User implements Serializable{
    private int adminNumber;
    private static final long serialVersionUID = 1L;

    public Administrator(int adminNumber, String userName, String firstName, String lastName){
        super(userName, firstName, lastName, true);
        this.adminNumber = adminNumber;
    }
    public void createNewUserAccount(String AdministratorDashboard){
    }
    public boolean isAdmin(){return true;}
    public int getAdminID () {
        return this.adminNumber;
    }
    public String toString() {
        return super.toString() + "==" + this.adminNumber;
    }
}
