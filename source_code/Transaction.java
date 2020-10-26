import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/** 
 * This class is of an object Transaction that computes for the total price of the parcel, as well as 
 *  the other information that the user needs before he/she delivers the parcel 
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco 
 * 
 * @version 1.0
 */
public class Transaction implements Serializable
{ 
    /** 
     * The constructor sets the parcel, the destination index and as well as the transaction num 
     * 
     * @param parcel an object of the class Parcel that is to be used for the transaction
     * @param transactionNum the number of the transaction
	 * @param machine a Machine object
     */
    public Transaction (Parcel parcel, int transactionNum, Machine machine) 
    { 
        this.parcel = parcel; 
        setDestinationIndex(parcel.getRecipient().getDestination());
        this.transactionNum = transactionNum;
    }

    public String getRecipient() 
    { 
        return parcel.getRecipient().getName();
    }

    /** 
     * Sets the destination index of the parcel given a number
     * 
     * @param num an integer that holds the choice of destination of the user
     */
    public void setDestinationIndex(int num) 
    { 
        destRegionIndex = num-1;
    }

    /** 
     * Getter that returns a String that contains the destination of the parcel 
     * 
     * @return destination of the parcel
     */
    public String getDestination() 
    { 
        return DESTINATION[destRegionIndex];
    }

    /** 
     * Getter that returns an integer that holds the number of days it takes to deliver 
     *  the parcel 
     * 
     * @return number of days it takes to deliver the parcel
     */
    public int getNumDays() 
    { 
        return DAYS[destRegionIndex];
    }

    /** 
     * Getter that returns a String containing the destination code of the parcel 
     * 
     * @return destCode
     */
    public String getDestinationCode() 
    { 
        String destCode="";
        if (destRegionIndex == 0)
            destCode = "MML";
        else if (destRegionIndex == 1)
            destCode = "LUZ";
        else if (destRegionIndex == 2)
            destCode = "VIS";
        else if (destRegionIndex == 3)
            destCode = "MIN";
        return destCode;
    }

    /** 
     * Getter that returns a double value containing the base rate of the parcel
     * 
     * @return baseRate
     */
    public double getBaseRate() 
    { 
        double baseRate = 0; 
        /* if the parcel type is flat */
        if (parcel.getParcelType().equals("FLT")) 
        { 
            /* if the smallest flat parcel is chosen, the base rate is 30*/
            if (parcel.getParcelIndex() == 0)
                baseRate = 30;
            /* if the largest flat parcel is chosen, then the base rate is 50 */
            else if (parcel.getParcelIndex() == 1) 
                baseRate = 50;
        }

        else { 
            /* the base rate of the box parcels are equal to the sum of the base rates of 
                all the items in the parcel */
            for (int i=0; i<parcel.getItems().size(); i++) 
            { 
                baseRate += parcel.getItems().get(i).getBaseRate();
            }
        }

        return baseRate;
    }

    /** 
     * Getter that returns a double that contains the sum of all the insurance fees of all the items 
     *  in the parcel 
     * 
     * @return insurance
     */
    public double getInsurance() 
    { 
        double insurance = 0; 
        for (int i=0; i<parcel.getItems().size(); i++) 
        { 
            insurance += parcel.getItems().get(i).getInsurance();
        }
        return insurance;
    }
    
	
    /** 
     * Getter that returns a String containing the current date the parcel was made 
     * 
     * @return str
     */
    public String getDate()
    { 
		String str = parcel.getDate().toString().substring(5, 7) + parcel.getDate().toString().substring(8);
		return str;
    }

    /**
     * Getter that returns a String containing the longer format of the current
     *  date that the parcel is being made
     * 
     * @return str 
     */
    public String getCurrDate() 
    { 
        String str = parcel.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return str;
    }

    /** 
     * Getter that returns a double value that contains the delivery fee of the parcel 
     * 
     * @return deliveryFee
     */
    public double getDeliveryFee() 
    { 
        double deliveryFee=0;
        double totalWeight=0;
        double totalVolWeight=0; 

        for (int i=0; i<parcel.getItems().size(); i++) 
        { 
            totalWeight += parcel.getItems().get(i).getWeight();
            totalVolWeight += parcel.getItems().get(i).getVolWeight();
        }

        /* if the parcel is delivered to metro manila, delivery fee is equal to 50*/
        if (destRegionIndex == 0)
            deliveryFee = 50; 

        /* if the parcel is delivered to provincial luzon, it will have a fixed delivery 
            fee of 100 */
        else if (destRegionIndex == 1)
            deliveryFee = 100;

        /* if the parcel is delivered to visayas*/
        else if (destRegionIndex == 2) 
        { 
            /* delivery fee will be set to 10% of the weight if it is greater than 10% of the 
                volumetric weight*/
            if ((totalWeight * 0.10) > totalVolWeight * 0.10)
                deliveryFee = totalWeight * 0.10; 
            else 
            /* delivery fee will be set to 10% of the volumetric weight if otherwise */
                deliveryFee = totalVolWeight * 0.10;
            /* if the delivery fee is less than 1000 then it will be set to 1000 */
            if (deliveryFee < 1000)
                deliveryFee = 1000;
        }

        else if (destRegionIndex == 3) 
        { 
            /* delivery fee will be set to 10% of the weight if it is greater than 25% of the 
                volumetric weight*/
            if ((totalWeight * 0.25) > totalVolWeight * 0.25)
                deliveryFee = totalWeight * 0.25; 
            /* delivery fee will be set to 25% of the volumetric weight if otherwise */
            else 
                deliveryFee = totalVolWeight * 0.25;
            /* if the delivery fee is less than 3000 then it will be set to 3000 */
            if (deliveryFee < 3000)
                deliveryFee = 3000;
        }

        return deliveryFee;
    }

    /** 
     * Getter that returns a String containg the tracking number of the parcel 
     * 
     * @return trackingNum
     */
    public String getTrackingNum() 
    { 
        DecimalFormat dfItem = new DecimalFormat("00");
        DecimalFormat dfParcel = new DecimalFormat("000");

        String trackingNum = parcel.getParcelType() + getDate() + 
                        getDestinationCode() + 
                        dfItem.format(parcel.getItems().size()) 
                        + dfParcel.format(transactionNum);

        return trackingNum;
    }

    /** 
     * Getter that returns a double value containing the total cost of the parcel 
     * 
     * @return sum of the base rate, delivery fee and insurance fee of the parcel 
     */
    public String getTotalAmount() 
    { 
        double total;
        DecimalFormat df = new DecimalFormat("0.00");
        total = getBaseRate() + getDeliveryFee() + getInsurance();
        return df.format(total);
    }
	
	/**
	 * Getter than returns the parcel in the current transaction
	 *
	 * @return parcel
	 */
	public Parcel getParcel()
	{
		return parcel;
	}
	
    /** 
     * Returns a String that contains all of the information that is needed for a transaction 
     * 
     * @return string
     */
    public String toString() 
    { 
        DecimalFormat df = new DecimalFormat("0.00");
        String string = "";
        string += "Transaction " + transactionNum + "\n"; 
        string += "\n";
        string += "\t Base rate: Php " + df.format(getBaseRate()) + "\n" + 
                "\t Delivery Fee: Php " + df.format(getDeliveryFee()) + "\n" + 
                "\t Insurance Fee: Php " + df.format(getInsurance()) + "\n\n";
        return string;
    }

    /* an object parcel of the class Parcel */
    private Parcel parcel; 
	/* and object machine of the class Machine*/
	private Machine machine;
    /* an integer containing the index of the destination of the parcel */
    private int destRegionIndex;
    /* an integer containing the number of the transaction */
    private int transactionNum;
    /* a final String array that contains all of the destinations that can be chosen from */
    private final String[] DESTINATION = { 
        "Metro Manila", 
        "Provincial Luzon",
        "Visayas",
        "Mindanao"
    };
    /* a final integer array that contains the number of days it takes to deliver depending 
        on the destination chosen */
    private int[] DAYS = {2, 3, 5, 8}; 
}