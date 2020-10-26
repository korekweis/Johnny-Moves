import java.io.*;

/**
 * This is a class Document that inherits the class item. This class has an object document and computes
 * for the measurements of the weight and height given the number of pages as well as other information 
 * needed for further computations in the other classes 
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class Document extends Item implements Serializable
{ 
    /** This constructor will pass the type, name, length, width, and insurance of the document to
     *  the super class and will set the number of pages which will later be used to compute further 
     *  measurements of the document
     * 
     * @param type a String that contains the type of item that was passed
     * @param name a String composed of letters and numbers given by the user that will hold the name 
     *              of the item
     * @param length a double that holds the length of the document 
     * @param width a double that holds the width of the document
     * @param insurance a string that is equal to "yes" or "no" that will determine whether the item 
     *                  is insured or not
     * @param numPages an integer beginning from 1 that holds the number of pages that the document
     *                  contains 
     */
    public Document (String type, String name, double length, double width, String insurance, int numPages) 
    {
        super(type, name, length, width, insurance);
        setNumPages(numPages);
        setDocWeight();
        setDocHeight();
    }

    /** 
     * Getter that returns the number of pages that the document contains
     * 
     * @return numPages
     */
    public int getNumPages() 
    { 
        return numPages;
    }
    
    /**
     * Setter that accepts the number of pages and sets it to 1 if the value passed is either a 
     * zero or a negative value
     * 
     * @param numPages an integer that holds the number of pages the document has
     */
    public void setNumPages(int numPages) 
    { 
        /* If the number of pages is equal to 0 or a negative number, it will be set to 1 */
        if(numPages < 1)
            this.numPages = 1;
        else 
            this.numPages = numPages;
    }

    /** 
     * Getter that extends the abstract method found in item class and computes for the base rate of
     *  the document with the weight accessed in the super class
     * 
     * @return computed base rate given the weight of the object
     */
    public double getBaseRate() 
    {
        /*the base rate is computed by multiplying the weight of the object by 40 */
        return this.getWeight() * 40;
    }

   /** 
    * Getter that extends the abstract method found in the item class and returns a zero considering
    *   that documents don't have a volumetric weight
    * 
    * @return 0;
    */
   public double getVolWeight() 
   { 
       return 0;
   }

    /**
     * Setter that computes for the weight of the document with the number of pages that it has, 
     *  then sets the weight of the item in the item class
     */
    public void setDocWeight() 
    { 
        /* Sets the weight in the item class with the computed value */
        this.setWeight(Math.ceil((numPages/25.00) * 200 / 1000.00));
    } 

    /** 
     * Setter that computes for the height of the document given the number of pages that it has,
     *  then sets the height of the item in the item class
     */
    public void setDocHeight() 
    { 
        /* Sets the height in the item class with the computed value */
        this.setHeight(Math.ceil(numPages/25.00));
    }

    /** 
     * Converts all of the document information to a String which will be used to 
     *  print and inform the user of the document that they are to add in their parcel
     * 
     * @return string
     */
    public String toString() 
    { 
        String string = "";
        string = super.toString(); 
        string += "\t Number of Pages: " + getNumPages() + "\n";
        return string;
    }

    /** an integer that holds the number of pages that the document contains */
    private int numPages;
}