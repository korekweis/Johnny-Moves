import java.io.*;

/** 
 * This class holds an object product that inherits the class Item, this class then computes for the base
 *  rate of the product as well as the volumetric weight of the product
 */
public class Product extends Item implements Serializable
{ 
    /** 
     * The constructor passes the type, name, length, width and insurance to the super class while it sets 
     *  the height and weight of the product
     * 
     * @param type a String that holds the type of the product 
     * @param name a String consisting of letters and numbers that holds the length of the product 
     * @param length a double that holds the length of the product 
     * @param width a double that holds the width of the product 
     * @param height a double that holds the height of the product 
     * @param weight a double that holds the weight of the product 
     * @param insurance a String that should hold a "yes" or "no"
     */
    public Product (String type, String name, double length, double width, double height, 
                    double weight, String insurance) { 
        super(type, name, length, width, insurance);
        this.setHeight(height);
        this.setWeight(weight);
    }

    /** 
     * Getter that computes for the base rate of the product 
     * 
     * @return a double that holds the base rate of the product
     */
    public double getBaseRate() 
    { 
        return this.getWeight() * 40;
    }
    
    /** 
     * Getter that computes for the volumetric weight of the product 
     * 
     * @return a double that holds the volumetric weight of the product
     */
    public double getVolWeight() 
    { 
        return this.getLength() * this.getWidth() * this.getHeight() / 305;
    }
}