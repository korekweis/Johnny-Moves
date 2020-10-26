import java.io.*;

/**
 * This class has an object Recipient which contains the information of the person whom the parcel is 
 *  addressed to as well as the destination to where the parcel will be delivered
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class Recipient implements Serializable{
    /** 
     * The constructor sets the name and the destination of the parcel
     * 
     * @param name a String the contains the name of the person to whom the parcel is addressed to 
     * @param destination an integer that holds the destination chosen by the user
     */
    public Recipient (String name, int destination) {
        this.name = name; 
        this.destination = destination;
    }

    /** 
     * Getter that returns a String which holds the name of the recipient
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /** 
     * Getter that returns the integer of the destination of the parcel 
     * 
     * @return destination
     */
    public int getDestination() { 
        return destination;
    }

    /** String that holds the name of the recipient */
    private String name; 
    /** integer that holds the destination of the recipient */
    private int destination; 
}