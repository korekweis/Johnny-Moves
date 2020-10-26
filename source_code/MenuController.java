import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

/** 
 * This class implement the action listener and holds the 
 * 	controller for the menu which will then update the program and
 * 	what it does 
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class MenuController implements ActionListener 
{ 
	/**
	 * The controller sets the menuGui to the parameter. If no machine 
	 * 	file is found, it then creates a new machine which will store 
	 * 	the information of the multiple transactions.
	 * 
	 * @param menu an object of the MenuGUI
	 */
	public MenuController(MenuGUI menu) 
	{ 
        this.menu = menu; 
        menu.addListener(this);
		
		try 
		{
			ObjectInputStream oIs = new ObjectInputStream(new FileInputStream("machine.dat"));
				
			Object obj = oIs.readObject();
			this.machine = (Machine) obj;
			System.out.println("File successfully loaded!");
			
			oIs.close();
		}
		
		catch (FileNotFoundException e)
		{	
			System.out.println("File cannot be found, setting up new machine...");
			this.machine = new Machine();
		}
		
		catch (IOException i)
		{
			System.out.println("IOException error: " + i.getMessage());
			this.machine = new Machine();
		}
		
		catch (ClassNotFoundException e) 
		{
			System.out.println("A class was not found, setting up new machine...");
			this.machine = new Machine();
		}
    }

	/**
	 * Sets what the program will do once an action event is selected, 
	 * 	the user has the option to create a parcel, track a parcel, and 
	 * 	to exit the program with the help of an employee
	 * 
	 * @param e an ActionEvent object
	 */
	public void actionPerformed(ActionEvent e) 
	{ 
        if (e.getActionCommand().equals("create")) 
		{ 			
			CreateGui cG = new CreateGui(null, true);
				
			this.recipient = cG.getTextInfo();
			this.region = cG.getCBoxIndex() + 1;
			this.nItems = cG.getInt();
			
			machine.setRecipient(new Recipient(recipient, region));
			
			PopupGui ppG = new PopupGui();
			JPopupController jPC = new JPopupController(machine, ppG, nItems, recipient, region);
			
			machine.Reset();		
        }
		
        if (e.getActionCommand().equals("track")) 
		{ 
			TrackingOpen dialog = new TrackingOpen(null, true);
              String trackNum = dialog.getTrackingNum();
                if (trackNum != null) 
				{
					if (machine.parcelExists(trackNum)) 
					{ 
                        TrackingGUI gui = new TrackingGUI();
                        TrackingController controller = new TrackingController(gui, machine);
                    }
					else
						this.eGUI = new ErrorGUI("Invalid tracking number!");
                }
				else
					this.eGUI = new ErrorGUI("Input a tracking number");
        }
		
		if(e.getActionCommand().equals("exit"))
		{	
			CheckOutGUI checkOut = new CheckOutGUI();
			CheckOutController checkOutCont = new CheckOutController(checkOut, machine);
		}
	}

	/* holds the object for the MenuGUI */
	private MenuGUI menu;
	/* holds the object for the ErroGUI */
	private ErrorGUI eGUI;
	/* holds the object for the TrackingGUI */
	private TrackingGUI trackingGui;
	/* holds the object for the TrackingOpen */
    private TrackingOpen trackOpen; 
	/* holds the object for the machine */
	private Machine machine;
	/* holds the String for the recipient */
	private String recipient;
	/* holds the integer for the region */
	private int region;
	/* holds the integer for the number of items */
	private int nItems;
}