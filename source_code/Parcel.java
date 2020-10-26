import java.util.*;
import java.io.*;
import java.time.LocalDate;

/** 
 * This class holds the object parcel that will contain an array list of items, a name, the parcel 
 *  type as well as the index of the parcel
 */
public class Parcel implements Serializable { 
    
	/**
	 * This constructor accepts the recipient's name and items to be added in the parcelIndex
	 *  along with the parcel type and index of the current parcel
	 *
	 * @param recipient a Recipient object that holds the recipient information of the parcel
	 * @param items an array list that holds the items that are to be added to the parcel
	 * @param parcelType a String that holds the type of parcel either "FLT" or "BOX"
	 * @param index an integer that holds the index of the parcel
	 * @param oDate a LocalDate that holds the date the parcel was ordered
	 */
	public Parcel(Recipient recipient, ArrayList<Item> items, String parcelType, int index, LocalDate oDate) 
    { 
        this.recipient = recipient;
        this.items = items;
        this.parcelType = parcelType;
		this.orderDate = oDate;
        parcelIndex = index;
    }

    /** 
     * Getter that returns an array list of the class Item that holds all of the 
     *  items in the array list 
     * 
     * @return items
     */
    public ArrayList<Item> getItems() 
    { 
        return items; 
    }

    /** 
     * Getter that returns a Recipient that holds the recipient of the parcel
     * 
     * @return recipient
     */
    public Recipient getRecipient() 
    { 
        return recipient;
    }

    /** 
     * Getter that returns a String that holds the parcel type of the parcel 
     * 
     * @return parcelType
     */
    public String getParcelType() 
    { 
        return parcelType;
    }
    
    /** 
     * Getter that returns an integer that returns the index of the parcel chosen
     * 
     * @return parcelIndex
     */
    public int getParcelIndex() 
    { 
        return parcelIndex;
    }
	
	/**
	 * Getter that returns the date the parcel was ordered
	 *
	 * @return orderDate
	 */
	public LocalDate getDate()
	{
		return orderDate;
	}

    /* an array list of the object Item that holds all the items in the parcel */
    private ArrayList<Item> items; 
    /* an object of the Recipient class that holds the recipient of the parcel */
    private Recipient recipient;
    /* a String that holds the type of the parcel */
    private String parcelType;
    /* an integer that holds the index of the parcel */
    private int parcelIndex;
	/* a localDate variable that contains the date the parcel was ordered*/
	private LocalDate orderDate;
}