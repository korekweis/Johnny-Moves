import java.util.concurrent.*;
import java.io.*;
import java.util.*; 
import java.time.LocalDate;


/** 
 * This class is of the object machine that holds all the processes that the machine will do such as the 
 *  adding of the parcel, computing for the right parcels depending on the sizes of the items, as well as
 *  hold all of the transactions 
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco 
 * 
 * @version 1.0
 */
public class Machine implements Serializable, Runnable
{
    /** 
     * Constructor that does not hold any parameters but initializes the array list of items, array list 
     *  of transactions, the booleans of isFlat and isBox that determines whether the parcels are valid, 
     *  it also calls the assignBooleans() functions that sets the elements in isFlat and isBox to true, 
     *  and initializes totalWeight, totalVolWeight and size to zero
     */
    public Machine() 
    { 
        assignBooleans();
        totalWeight = 0;
        totalVolWeight = 0;
        transactionIndex = 0;
        size = 0;
		this.currDate = LocalDate.now();
		this.executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(this, 10, 10, TimeUnit.SECONDS);
		
		this.transactions = new ArrayList<Transaction>();
    }

    /**
     * Resets the arrayList of items, assigns new booleans to the parcel sizes, 
     *  resets the total weight and resets the total volumetric weight
     */
    public void Reset() 
    { 
        items = new ArrayList<Item>();
        assignBooleans();
        totalWeight = 0;
        totalVolWeight = 0;
    }
	
	/**
	 * The method that increments the current date by one
	 *
	 */
	public void run()
	{
		currDate = currDate.plusDays(1);
	}
	
    /** 
     * Setter that accepts an object of the Recipient class and sets it as the recipient variable 
     * 
     * @param recipient a Recipient that holds the information of the recipient of a specific parcel
     */
    public void setRecipient(Recipient recipient) 
    { 
        this.recipient = recipient;
    }

    /** 
     * Accepts an item and adds it to the array list of items that will soon be transfered to a parcel
     * 
     * @param item an object Item that is to be added in the array list.
     */
    public void addItem (Item item) 
    { 
        items.add(item);
    }

    /** 
     * Adds a transaction to the transactions array list once a parcel is chosen and 
     *  a transaction is being made
     */
    public void addTransaction() 
    { 
        Parcel parcel; 
        /* the size increases as soon as a transaction is being made */
        this.size++;
        parcel = new Parcel(recipient, items, strParType, parcelIndex, currDate);
        transactions.add(new Transaction(parcel, size, this));
    }

    /**
     * Sets the boolean array to all true before the assigning of valid parcels 
     *  depending on the items that are to be delivered
     */
    public void assignBooleans() 
    {
        isFlat = new boolean[2];
        isBox = new boolean[4];
		int i;
        for (i=0; i<isFlat.length; i++)
            isFlat[i] = true;
        for (i=0; i<isBox.length; i++)
            isBox[i] = true;
    }

    /** 
     * Assigns the valid parcels depending on the measurements of the items that are to be shipped. 
     * 
     * @return validParcel that holds true if there are any valid parcels and false if otherwise
    */
    public boolean assignValidParcels() 
    { 
        for (int i=0; i<items.size(); i++) 
		{
            for (int j=0; j<3; j++) 
			{ 
                for (int k=0; k<2; k++) 
				{ 
                    /* The item is rotated to figure out whether its going to fit in a specific flat parcel */
                    if (measurements[i][j] > FLAT[k][j] && getTotalWeight() > 3 || (totalHeight > FLAT[k][2] || totalWidth > FLAT[k][1]))
                    isFlat[k] = false;
                }
                for (int k=0; k<4; k++) 
				{ 
                    /* The item is rotated to figure out whether its going to fit in a specific box parcel */
                    if ((measurements[i][j] > BOX[k][j]) || (totalHeight > BOX[k][2] || totalWidth > BOX[k][1]))
                    isBox[k] = false;
                }
            }
        }

        boolean validParcel = false;
        /* determines whether there are any valid parcels or not */
        for (int i=0; i<2; i++)  
            if (isFlat[i])
                validParcel = true;
        for (int i=0; i<4 && !validParcel; i++)
            if (isBox[i])
                validParcel = true;
        
        return validParcel;
    }

    /**
     * This method sets the parcel type to a flat parcel or a box parcel depending on the choice of 
     *  parcel that the user picks
     * 
     *  @param choice an integer from 1 to 6 that holds the sizes for the parcel
     */
    public void setParcelType(int choice) 
    {
        if (choice < 3){
            strParType = "FLT";
            parcelIndex = choice-1;
        }
        else
		{
            strParType = "BOX";
            parcelIndex = choice-3;
        }
    }

    /**
     * This method sets the total weight and total volumetric weight of the items that are to be added 
     *  as well as sets the all of the lengths of each item to index 0 of every row in the 
     *  measurements array, the width at index 1 and height at index 2 then calls the arrangeSizes()
     *  method
     */
    public void setSizes() 
    { 
        measurements = new double[items.size()][3];
        for (int i=0; i<items.size(); i++) 
        { 
            totalWeight += items.get(i).getWeight();
            totalVolWeight += items.get(i).getVolWeight();

            measurements[i][0] =  items.get(i).getLength();
            measurements[i][1] = items.get(i).getWidth();
            measurements[i][2] = items.get(i).getHeight();
        }
        arrangeSizes();
    }

    /**
     * Arranges the sizes of the items in decreasing order in order to rotate and 
     *  measure the items
     */
    public void arrangeSizes() 
    { 
        double temp;
        for (int i=0; i<items.size(); i++) 
        { 
            for (int j=0; j<3-1; j++) 
            { 
                for (int k=0; k<3-i-1; k++) 
                {  
                    if (measurements[i][k] < measurements[i][k+1]) 
                    { 
                        temp = measurements[i][k];
                        measurements[i][k] = measurements[i][k+1];
                        measurements[i][k+1] = temp;
                    }
                }
            }
        }
        setTotals();
    }

    /** 
     * Setter that sets the total Height and totalWidth of all the items that are to be added in a  
     * specific parcel. It then calls the assignValidParcels() method
     */
    public void setTotals() 
    { 
        double totalHeight = 0, totalWidth = 0;
        for(int i=0; i<items.size(); i++) 
        { 
            totalHeight += measurements[i][2];
            totalWidth += measurements[i][1];
        }
        assignValidParcels();
    }

    /** 
     * Determines whether or not the parcel exists given the trackingNum of a specific parcel 
     * 
     * @param trackingNum a String that consists of numbers and letters that holds the tracking num 
     *                      of a specific parcel
     * @return exists that holds true if there is an existing parcel and false if otherwise
     */
    public boolean parcelExists(String trackingNum) 
    { 
        boolean exists = false;
        for (int i=0; i<transactions.size() && !exists; i++) 
        { 
            if (transactions.get(i).getTrackingNum().equalsIgnoreCase(trackingNum)) 
            { 
                exists = true;
                this.trackingNum = trackingNum;
                tracking = new Tracking(transactions.get(i), currDate);
            }
        }
        return exists;
    }

    /**
     * To determine whether there is still a next valid date in the delivery 
     *  process of the parcel
     * @return a boolean to check whether the parcel is already delivered or not
     */
    public boolean nextDate() 
    { 
        return tracking.nextDate();
    } 

    /** 
     * Determines whether the employee is able to check out the program with the right input of 
     *  department and its corresponding password
     * 
     * @param dept an integer from 1 to 5 that will determine the department of the employee
     * @param password a String containing numbers and letters that holds the password of the employee 
     * 
     * @return true if the check out is valid and false if otherwise
     */
    public boolean checkOut(int dept, String password) 
    { 
        Employee employee = new Employee();
        return employee.checkPassword(dept, password);
    }
	
	/**
	 * Stops the executor from performing
	 */
    public void closeExe()
    {
        this.executor.shutdown();
    }
	
	/**
	 * Creates an executor to increment the current day
	 */
    public void openExe()
    {
        this.executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(this, 10, 10, TimeUnit.SECONDS);
    }

    /** 
     * Getter that returns the total weight of all the items that are to be added in a parcel
     * 
     * @return totalWeight
     */
    public double getTotalWeight() 
    { 
        return totalWeight;
    }

    /** 
     * Getter that returns the total volumetric weight of all the items that are added in a parcel 
     * 
     * @return totalVolWeight
     */
    public double getTotalVolWeight() 
    { 
        return totalVolWeight;
    }

    /** 
     * Getter that gets the current Tracking object in the machine
     * 
     * @return tracking 
     */
    public Tracking getTracking() 
    { 
        return tracking;
    }
	
	/**
	 * Getter that returns the current date
	 *
	 * @return currDate
	 */
	public LocalDate getDate()
	{
		return currDate;
	}
	
    /**
     * Returns a boolean whether there is a next or a previous transaction 
     *  based on the current transaction being viewed by the user 
     * 
	 * @param curr a String that holds what transaction to display
     * @return bool
     */
    public boolean currentTransaction(String curr) 
    {
        boolean bool = true; 
        if (curr.equals("prev")) { 
            if (transactionIndex > 0) { 
                transactionIndex--; 
                bool = true;
            }
            else 
                bool = false;
        }
        else if (curr.equals("next")) { 
            if (transactionIndex < (transactions.size()-1)) { 
                transactionIndex++;
                bool = true;
            }
            else
                bool = false;
        }
        return bool;
    }

    /**
     * Getter that returns the current array list of items in the machine
     * 
     * @return items
     */
    public ArrayList<Item> getItems() 
    { 
        return items;
    }

    /**
     * Getter that returns the array list of transactions in the machine
     * 
     * @return transactions
     */
    public ArrayList<Transaction> getTransactions() 
    { 
        return transactions;
    }

    /**
     * Getter that returns the Transaction that the user is currently viewig
     * 
     * @return the current transaction being viewed
     */
    public Transaction getTransaction() 
    { 
        return transactions.get(transactionIndex);
    }

    /** 
     * Returns the information of all the transactions that were made in this machine 
     * 
     * @return string containing of all the information of all the transactions
     */
    public String toString() 
    { 
        String string = "";
        for (int i=0; i<transactions.size(); i++) 
        { 
            string += transactions.get(i).toString();
        }
        return string;
    }

    /** 
     * Returns in string form all of the parcels that are valid after all of the measurements of 
     *  all of the items were computed and rotated
     * 
     * @return string containing all of the valid parcels
     */
    public ArrayList<String> toStringValidParcels() 
	{
        int i, x;
        ArrayList<String> s = new ArrayList<String>(); 
        boolean found = false;
        for (i=0; i<2; i++) 
		{
            if (isFlat[i])
			{
				x = i+1;
				s.add("FLAT" + x + ": " + FLAT[i][0] + " x " +  FLAT[i][1] + " x " + FLAT[i][2]);
                found = true;
			}
                
        }
		
        for (i=0; i<4; i++) 
		{
            if (isBox[i])
			{
				x = i+1;
				s.add("BOX" + x + ": " + BOX[i][0] + " x " +  BOX[i][1] + " x " + BOX[i][2]);
                found = true;
			}    
        }
        
        if (!found)
            s.add("NO VALID PARCEL!");

        assignBooleans();
        return s;
    }
	
	/** 
     * Returns in string form all of the items that are stored in the
     *   parcel chosen by the user. 
     * 
     * @return string containing all of the items in a parcel
     */
	 
	public ArrayList<String> toStringItems()
	{
		ArrayList<String> itemList = new ArrayList<String>();
		for(int i = 0; i<items.size(); i++)
			itemList.add("Name: " + items.get(i).getName() + "\n" + 
						 "Type: " + items.get(i).getType() + "\n" + 
						items.get(i).toString());
		return itemList;
	}
	
    /** 
     * a 2D array containing the different measurements for the length, width, and height
     *  of the flat parcels
     */
    private final int[][] FLAT = {{14, 9, 1}, {18, 12, 3}};
    /**
     * a 2D array containing the different measurements for the length, width, and height 
     *  of the box parcels
     */
    private final int[][] BOX = {{12, 10, 5},
                                {14, 11, 7},
                                {18, 12, 9},
                                {20, 16, 12}}; 


    /** holds the items that are to be added to a specific parcel */
    private ArrayList<Item> items;
    /** holds the transactions that are to be done to the chosen parcel*/
    private ArrayList<Transaction> transactions;
    /**  holds the recipient of the parcel */
    private Recipient recipient;
	/** holds the current date*/
	private LocalDate currDate;

    /** holds the total volumetric weight of the items that are to be added */
    private double totalVolWeight;
    /** holds the total weight of the items that are to be added */
    private double totalWeight;
    /** holds the total height of the items that are to be added */
    private double totalHeight;
    /** holds the total width of the items that are to be added */
    private double totalWidth; 
	/* holds the scheduled time it takes to run the dates */
	private ScheduledExecutorService executor;
	
    /** holds the size of the number of transactions that were made by the user*/
    private int size;
    /** holds the index of the number */
    private int parcelIndex;

    /** 2D array that holds the measurements of all of the items that are to be added */
    private double[][] measurements;
    /** array that contains booleans to determine whether a flat parcel is valid */
    private boolean[] isFlat;
    /** array that contains booleans to determine whether a box parcel is valid */
    private boolean[] isBox;
    /** String that holds the type of parcel whether it is FLT or BOX */
    private String strParType;
    /** String that holds the tracking num of the parcel*/
    private String trackingNum;
    /** Variable that holds the current Tracking object */
    private Tracking tracking;
    /* The current transaction index being viewed */
    private int transactionIndex;
}