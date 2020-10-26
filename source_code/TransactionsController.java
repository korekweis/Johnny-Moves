import java.awt.event.*;
import java.util.*;

/** 
 * This class holds the controller of the transactionGUI.
 *  It then sets the displaying of the information in the   
 *  Graphical User Interface for the user to know the
 *  additional information of their added items 
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco 
 * 
 * @version 1.0
 */
public class TransactionsController implements ActionListener 
{ 
    /** 
     * Constructor sets parameters to the gui and machine variable
     *  as well as adds listeners to this class 
     * @param gui CheckOutGUI object that holds the Graphical User Interface for 
     *  checking out the program
     * @param machine holds the machine object used to access the program
     */
    public TransactionsController(TransactionsGUI gui, Machine machine) 
    { 
        this.gui = gui; 
        gui.addListener(this);
        this.machine = machine; 
        updateDisplay();
    }

    /** 
     * Updates the display of the program regarding the important information
     *  in every transaction
     */
    public void updateDisplay() { 
        gui.setInfo(machine.getTransaction().toString());
        gui.setDestination(machine.getTransaction().getDestination());
        gui.setTrackingNum(machine.getTransaction().getTrackingNum());
        gui.setCurrDate(machine.getTransaction().getCurrDate());
        gui.setRecipient(machine.getTransaction().getRecipient());
        gui.setTotal(machine.getTransaction().getTotalAmount());
    }

    /** 
     * Prompts the update display method whenever the user decides which
     *  button to select in viewing all the transactions that was made.
     *  @param action an ActionEvent object 
     */
    public void actionPerformed(ActionEvent action) { 
        if (action.getActionCommand().equals("Prev")) { 
            if(machine.currentTransaction("prev")) { 
                updateDisplay();
            }
            else 
                gui.dispose();
        }
        if (action.getActionCommand().equals("Next")) { 
            if(machine.currentTransaction("next")) { 
                updateDisplay();
            }
            else 
                gui.dispose();
        }
		if(action.getActionCommand().equals("View Items"))
		{
			ArrayList<String> itemList = new ArrayList<String>();
			itemList = machine.toStringItems();
			
			String[] itemsList = new String[itemList.size()];
			
			for(int i = 0; i<itemList.size(); i++)
				itemsList[i] = itemList.get(i);
			
			ItemViewGui viewGui = new ItemViewGui(itemsList, null, true);
		}
    }

    /* holds the TransactionsGUI object */
    private TransactionsGUI gui; 
    /* holds the machine object */
    private Machine machine;
}