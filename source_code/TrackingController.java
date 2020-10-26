import java.awt.event.*;
import java.util.concurrent.*;

import javax.swing.ImageIcon;
/**
 * This class is a controller to track the parcel which implements the ActionListener
 * 	and the runnable. It shows whether the parcel is still being prepared, on the process
 * 	of shipping and if it is already delivered 
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class TrackingController implements ActionListener, Runnable 
{ 
	/** 
	 * The controller sets the trackingGUI the machine to its parameters and adds its
	 * 	action listeners. Status is then set to one and the executors for the dates 
	 * 	is being set as well.
	 * 
	 * @param gui an object of the TrackinGUI
	 * @param machine an object of the Machine 
	 */
	public TrackingController (TrackingGUI gui, Machine machine) 
	{ 
        this.gui = gui; 
        gui.addListener(this);
        this.machine = machine;
		this.status = 1;
		machine.closeExe();
		this.executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(this, 1, 2, TimeUnit.SECONDS);
		
		updateDisplay();
        updateDate();
		updateStatus();
    }

	/**
	 * Updates the information that is to be displayed in the Graphical User Interface
	 */
	public void updateDisplay() 
	{ 
		gui.setDestination(machine.getTracking().getTransaction().getDestination());
		gui.setTrackingNum(machine.getTracking().getTransaction().getTrackingNum());
		gui.setRecipient(machine.getTracking().getTransaction().getRecipient());
	}

	/**
	 * Updates the date as it increments, which will be shown in the program
	 */
	public void updateDate() 
	{ 
        gui.setDate(machine.getTracking().toString());
    }
	
	/**
	 * Updates the status of the delivery depending on the date
	 */
	public void updateStatus()
	{
		if (machine.getTracking().getStatus().equals("Preparing"))
			gui.setStatus(new ImageIcon("Dot1.png"));
		else if (machine.getTracking().getStatus().equals("Shipping"))
			gui.setStatus(new ImageIcon("Dot2.png"));
		else if (machine.getTracking().getStatus().equals("Delivered"))
		{
			gui.setStatus(new ImageIcon("Dot3.png"));
			this.executor.shutdown();
			machine.openExe();
		}
			
	}
	
	/**
	 * Runs the number of days for the date to run until the delivery is delivered
	 */
	public void run()
	{
		
		if(machine.getTracking().nextDate())
		{
			updateDisplay();
			updateDate();
			updateStatus();
		}
		else
			this.executor.shutdown();
	}
	
	/**
	 * Accepts the action being performed by the user and updates what the program will do
	 * 
	 * @param action an object of the ActionEvent
	 */
    public void actionPerformed(ActionEvent action) 
	{ 
		if (action.getActionCommand().equals("Next"))
		{
			gui.dispose();
		}
		else 
			gui.dispose();
    }

	/* a whole number that holds the status of the delivery*/
	private int status;
	/* holds the scheduled time it takes to run the dates */
	private ScheduledExecutorService executor;
	/* holds the object for the TrackingGUI */
	private TrackingGUI gui; 
	/* holds the object for the machine */
	private Machine machine; 
}