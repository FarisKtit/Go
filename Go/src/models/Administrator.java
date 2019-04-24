package models;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * Implements Administrator class.
 * @author cerriannesantos.
 * Administator class responsible for creating an administrator.
 */
public class Administrator extends User implements Serializable {
    private int adminNumber;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for an Administrator.
     * @param adminNumber Administrators admin number.
     * @param userName Administrators user name.
     * @param firstName Administrators first name.
     * @param lastName Administrators last name.
     */
    public Administrator(int adminNumber, String userName, String firstName, String lastName) {
        super(userName, firstName, lastName, true);
        this.adminNumber = adminNumber;
    }

    /**
     * Used to create a new user account, not implemented here.
     * @param AdministratorDashboard
     */
    public void createNewUserAccount(String AdministratorDashboard) {
    }

    /**
     * Returns admin number only if user is also an administrator.
     * @return admin number.
     */
    public boolean isAdmin(){return true;}
    public int getAdminID () {
        return this.adminNumber;
    }

    /**
     * Get all data and return it to the user interface.
     * @return All inputs and outputs.
     */
    public String toString() {
        return super.toString() + "==" + this.adminNumber;
    }
}
