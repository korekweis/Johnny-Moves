import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * The class handles the action listeners of the ControllerGUI class. 
 *  It checks whether the user is able to checkIn the program by selecting
 *  a department and by placing the corresponding input password for the 
 *  program to start
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class CheckInController implements ActionListener 
{ 

    /**
     * The constructor sets the CheckInGUI as well as creates a new machine
     *  in order to check whether the password corresponds to the selected 
     *  department
     * 
     * @param gui a CheckInGUI object
     */
    public CheckInController(CheckInGUI gui) 
    { 
        this.gui = gui;
        gui.addListener(this);
        this.machine = new Machine();
    }

    /**
     * Operates the action listeners on what action is to be done depending
     *  on what the user intends to do with the program. It also then checks
     *  whether the department and the password input by the user corresponds
     *  with each other, and if it does, the program then opens the main menu
     * 
     * @param action an ActionEvent object
     */
    public void actionPerformed(ActionEvent action) 
    { 
        ErrorGUI errG;
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

        if (action.getActionCommand().equals("Ok")) 
        { 
            /* if the department corresponds with the input password, 
                it then proceeds to the main menu */
            if (machine.checkOut(num, gui.getPass())) 
            {
                MenuGUI menu = new MenuGUI();
                MenuController cont = new MenuController(menu);
                gui.dispose();
            }
            else /* else an error message appears */
                this.errG = new ErrorGUI("Invalid Password");
        }
    }

    /* variable that holds the CheckInGUI */
    private CheckInGUI gui;
	/*variable that holds Error GUI*/
	private ErrorGUI errG;
    /* an integer that holds the value for the JRadioButton selected */
    private int num;
    /* variable that holds the machine object */
    private Machine machine;
}