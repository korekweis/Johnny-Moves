import java.text.DecimalFormat;
import java.io.*;

/**
  * 
    This class is with an object item that contains all the measurements that will be accessed by 
      other classes to determine which parcel sizes are valid for the user to choose from. 
      This class also implements the interface Insurance which will be used to determine 
      the total price of the item.

  @author Christianne Marie Vinco
  @author Julian Sunpayco

  @version 1.0
*/

public abstract class Item implements Insurance, Serializable{ 
    /** 
     * This constructor sets the type of the item, name, length, width as well as the insurance
     * 
     * @param type a String that holds the type of the items whether it is a document, 
     *              product, or an irregular product
     * @param name a String that consists of number and letters that holds the name of the item
     * @param length a double value that holds the length of the item 
     * @param width a double value that holds the width of the item 
     * @param insurance a String that should be either "yes" or "no" that determines whether 
     *                  the item is insured or not
     */
    public Item(String type, String name, double length, double width, String insurance) { 
        strType = type;
        strName = name;
        setLength(length);
        setWidth(width);
        setInsurance(insurance);
    }

    /** 
     * Getter method returns a String that holds the name of the item 
     * 
     * @return strName;
     */
    public String getName() { 
      return strName;
    }

    /** 
     * Getter method returns a String that holds the type of the item 
     * 
     * @return strType
     */
    public String getType() { 
      return strType;
    }

    /** 
     * Getter method returns a double that holds the length of the item 
     * 
     * @return dLength
     */
    public double getLength() { 
      return dLength; 
    }

    /** 
     * Getter method returns a double that holds the width of the item 
     * 
     * @return dWidth 
     */
    public double getWidth() {
      return dWidth;
    }

    /** 
     * Getter method returns a double that holds the height of the item 
     * 
     * @return dHeight
     */
    public double getHeight() { 
      return dHeight;
    }

    /** 
     * Getter method returns a double that holds the weight of the item 
     * 
     * @return dWeight
     */
    public double getWeight() { 
      return dWeight;
    }
    
    /** 
     * Getter method returns a double that holds the insurance price of the item
     * 
     * @return dInsurance
     */
    public double getInsurance() { 
      return dInsurance;
    }

    /** 
     * Method that computes the base rate of the item 
     * 
     * @return a double that contains the base rate of the item
     */
    public abstract double getBaseRate();

    /** 
     * Method that computes the volumetric weight of the item 
     * 
     * @return a double that contains the volumatric weight of the item
     */
    public abstract double getVolWeight();


    /** 
     * Setter that accepts a double number and sets the height to the absolute value of the parameter
     * 
     * @param num a double that holds the height of the item
     */
    public void setHeight(double num) { 
      dHeight = Math.abs(num);
    }

    /** 
     * Setter that accepts a double number and sets the weight to the absolute value of the parameter
     * 
     * @param num a double that holds the weight of the item
     */
    public void setWeight(double num) {
      dWeight = Math.abs(num);
    }

    /** 
     * Setter that accepts a double number and sets the length to the absolute value of the parameter
     * 
     * @param num a double that holds the length of the item
     */
    public void setLength(double num) { 
      dLength = Math.abs(num);
    }

    /** 
     * Setter that accepts a double number and sets the width to the absolute value of the parameter
     * 
     * @param num a double that holds the length of the item
     */
    public void setWidth(double num) {
      dWidth = Math.abs(num);
    }

    /** 
     * Setter that accepts a String that should hold a "yes" or "no" that will help determine 
     *  the price of the insurance fee of the item
     * 
     * @param insurance a String that contains "yes" or "no";
     */
    public void setInsurance(String insurance) { 
      /* sets the insurance fee to zero if the user input a "no" */
      if (insurance.equalsIgnoreCase("NO"))
        dInsurance = 0.00; 
      /* sets the insurance fee to Php 5.00 if the input is not equal to a "no" */
      else 
        dInsurance = 5.00;

    }

    /** 
     * Returns a String that contains all the necessary information of a 
     *  specific item 
     * 
     * @return string that contains all information needed
     */
    public String toString() { 
      DecimalFormat df = new DecimalFormat("0.00");
      return "\t Length: " + df.format(getLength()) + " inches\n" + 
      "\t Width: " + df.format(getWidth()) + " inches\n" +
      "\t Height: " + df.format(getHeight()) + " inches\n" +
      "\t Weight: " + df.format(getWeight()) + " kilos\n" +
      "\t Volumetric Weight: " + df.format(getVolWeight()) + " kilos\n" +
      "\t Insurance: Php " + df.format(getInsurance()) + "\n";
  }
  
    /** String that holds the name of the item */
    private String strName; 
    /** String that holds the type of the item */
    private String strType;
    /** double value that holds the length of the item */
    private double dLength; 
    /** double value that holds the width of the item */
    private double dWidth;
    /** double value that holds the height of the item */
    private double dHeight; 
    /** double value that holds the weight of the item */
    private double dWeight;
    /** double value that holds the insurance of the item */
    private double dInsurance;
}