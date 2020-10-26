import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

/**
 * This class is of the CheckOutController that implements an action listener
 *  for the program to operate once the user is able to select an option 
 *  on what to do with the program. This class also holds the Graphical
 *  User Interface for when the user wants to check out with the supervision
 *  of an employee
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class CheckOutController implements ActionListener {
    
    /**
     * Constructor sets parameters to the gui and machine variable as well as 
     *  sets the action listeners
     * @param gui is a CheckOutGUI object that holds the Graphical User Interface
     *          for checking out the program
     * @param machine holds the machine object used to access the program
     */
    public CheckOutController(CheckOutGUI gui, Machine machine) { 
        this.gui = gui;
        gui.addListener(this);
        this.machine = machine;
    }

    /**
     * This method has a parameter action which holds the action event being done by
     *  the user, it also sets the number which will hold the index to check 
     *  whether or not the password is valid and if it matches with the corresponding
     *  department
     * 
     * @param action variable that holds the ActionEvent done by the user
     */
    public void actionPerformed(ActionEvent action) { 
        if (action.getActionCommand().equals("Accounting and Finance")) 
            num = 1;
        else if (action.getActionCommand().equals("Courier Driver")) 
            num = 2;
        else if (action.getActionCommand().equals("Human Resources")) 
            num = 3;
        else if (action.getActionCommand().equals("Information Technology")) 
            num = 4;
        else if (action.getActionCommand().equals("Learning and Development")) 
            num = 5;

        if (action.getActionCommand().equals("Ok")) { 
            /* 
                If the password corresponds with the department chosen, the data
                held by the machine will then be saved and the program with exit
             */
            if (machine.checkOut(num, gui.getPass())) {
                
				try 
				{ 
					ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("machine.dat"));
					System.out.println("File saved!");
					os.writeObject(machine);
					os.close();
				}

				catch(FileNotFoundException e)
				{
					System.out.println("File not Found " + e.getMessage());
				}
				catch(IOException e)
				{
					System.out.println("IOException " + e.getMessage());
				}
				
                System.exit(0);
            }
    
            else /* 
                    if department and password don't correspond with each other, 
                    an error message will appear 
                */
                this.errG = new ErrorGUI("Invalid Password");
        }
    }
    /* variable that holds the CheckOutGUI object */
    private CheckOutGUI gui;
	/* variable that holds the ErrorGUI object*/
	private ErrorGUI errG;
    /* variable that holds the num if a radio button is chosen */
    private int num;
    /* variable that holds the machine object */
    private Machine machine;
}