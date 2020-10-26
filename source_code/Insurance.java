/** 
 * This is an interface Insurance which sets the insurance of the items that people want to insure or not
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco 
 * 
 * @version 1.0
 */
public interface Insurance { 
    /** 
     * Accepts a String which contains "yes" or "no" and the insurance amount 
     *  will then be computed
     * 
     * @param insurance a String that should contain "yes" or "no"
     */
    public void setInsurance(String insurance);
}