import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.io.*;

/** This class is of the object tracking to track the parcel given a specific 
 *  tracking number. This will also compute for how many days it will take for 
 *  the parcel to be delivered 
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco 
 * 
 * @version 1.0
 */
public class Tracking implements Serializable
{
    
    /**
     * This constructor accepts a parcel in order to compute for the number of 
     *  days it will take to deliver as well as the status for each day
     * 
     * @param transaction a Transaction object
	 * @param currDate a LocalDate that holds the current date
     */
    public Tracking(Transaction transaction, LocalDate currDate) 
	{
        this.transaction = transaction;
		this.currDate = currDate;
		this.orderDate = transaction.getParcel().getDate();
        numDays = transaction.getNumDays();
        nextDate = Integer.parseInt(currDate.toString().substring(8)) - Integer.parseInt(orderDate.toString().substring(8));
        nextDate();
		setStatus();
		this.current = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(currDate);
    }

    /**
     * Returns a boolean to check whether there is still a next date or 
     *  if the parcel is already delivered based on the number of days it
     *  takes to deliver the parcel depending on its destination
     * @return a boolean that holds true when a next date is valid and false
     *  if otherwise
     */
    public boolean nextDate() 
	{ 
        if (nextDate < numDays) 
		{ 
            this.currDate = currDate.plusDays(1);
            this.current = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(currDate);
            setStatus();  
            nextDate++;
            return true; 
        }

        else return false;
    }

    /**
     * This method sets the status of the delivery depending on the number of days
     *  it takes to deliver the parcel
     */
    public void setStatus() 
	{
        if (nextDate == 0)
            status = "Preparing";
        else if (nextDate >= (numDays-1))
            status = "Delivered";
        else 
            status = "Shipping";
    }

    /**
     * This method converts to String the information that will be displayed
     *  regarding the tracking process
     */
    public String toString() 
	{		
		return current;
    }
    
    /**
     * A getter that returns a string that holds the status of the delivery
     * 
     * @return status
     */
	public String getStatus()
	{
		return status;
    }
    
    /**
     * A getter that returns the transaction object of the parcel
     * 
     * @return transaction
     */
    public Transaction getTransaction() 
	{ 
        return transaction;
    }

    /* String that holds the status of the delivery */
    private String status;
    /* integer that holds the number of days it takes to deliver the parcel */
    private int numDays;
    /* the number of the day that the user is currently viewing */
    private int nextDate;
	/* a localDate variable that contains the date the parcel was ordered*/
	private LocalDate orderDate;
    /* a String that holds the current date that the viewer is looking at*/
	private String current;
	/* a local date variable that holds the current date being viewed*/
    private LocalDate currDate;
    /* holds the transaction object */
    private Transaction transaction;
}