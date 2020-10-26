/**
 * This is a class of the object Employee which determines whether the password entered by a specific 
 *  employee corresponds to the employee's department
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayo 
 * 
 * @version 1.0 
 */
public class Employee { 
    /** 
     * This method checks whether the password entered by the employee corresponds to his/her department.
     * The method returns true if the department and the password matches and returns false if otherwise.
     * 
	 * @param dept an int that holds the index of a department
	 * @param password a string that holds the password that the user entered
     * @return checkPassword
     */
    public boolean checkPassword(int dept, String password) { 
        boolean checkPassword;
        if (dept < 1 || dept > 5)
            checkPassword = false;
        if (password.equals(AdminPasswords[dept-1]))
            checkPassword = true;
        else 
            checkPassword = false;
        return checkPassword;
    }

    /** an array containing all of the passwords depending on the department of the employee */
    private final String[] AdminPasswords = {"ACCFIN-1221", 
									        "DRIVER-3141", 
									        "HUMRES-5643", 
									        "INFTEC-7008", 
                                            "LRNDEV-0910"};
}