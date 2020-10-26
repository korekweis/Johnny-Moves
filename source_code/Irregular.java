import java.io.*;

/**
 * This class contains an object Irregular which extends the Product class. This class will be computing
 *  for the baseRate of the item given its specific measurements
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco 
 * 
 * @version 1.0 
 */
public class Irregular extends Product implements Serializable
{ 
    /** 
     * This constructor accepts the information needed of a regular item. The variables containing the
     *  type, name, length, width, height, weight, and insurance will be passed to the super class 
     *  which will then be set for future computations
	 *
	 * @param type a string that holds the type of item
	 * @param name a string that holds the name of the item
	 * @param length a double that holds the length of the item
	 * @param width a double that holds the width of the item
	 * @param height a double that holds the height of the item
	 * @param weight a double that holds the weight of the item
	 * @param insurance a string that holds if the item is insured or not
     */
    public Irregular (String type, String name, double length, double width, double height, 
                        double weight, String insurance) { 
        super(type, name, length, width, height, weight, insurance);
    }

    /** 
     * Getter that computes the baseRate of the item by accessing its weight and volumetric weight 
     *  in the super class.
     * 
     * @return a double baseRate that holds the base rate of the item
     */
    public double getBaseRate() { 
        double baseRate, actual, volumetric; 
        /* This holds the base rate with the actual weight */
        actual = this.getWeight() * 40; 
        /* This holds the base rate with the volumetric weight */
        volumetric = this.getVolWeight() * 30;

        /* if the actual base rate of the item is greater than the volumetric base rate, 
         the base rate will be set to the actual base rate*/
        if (actual > volumetric) 
            baseRate = actual;
        /* the base rate will be set to the volumetric base rate if otherwise*/
        else 
            baseRate = volumetric; 
        return baseRate;
    }
}