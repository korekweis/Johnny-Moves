import java.awt.image.*;
import javax.swing.*;
import java.awt.*;

/**
 * A class that holds the Graphical User Interface of an error message 
 * 	whenever an error is found in one of the classes 
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco 
 * 
 * @version 1.0
 */
public class ErrorGUI extends JDialog
{
	/** 
	 * Accepts a message s that is to be displayed whenever an error occurs then calls the 
	 * init() to initialize the Graphical User Interface
	 * 
	 * @param s a String that holds the message to be displayed
	 */
	public ErrorGUI(String s)
	{
		init(s);
	}
	
	/**
	 * Initializes the Graphical User Interface
	 * 
	 * @param s a String that holds the message to be displayed
	 */
	public void init(String s)
	{
		ImageIcon myImg = new ImageIcon("exit.png");
		Image img = myImg.getImage();
		ImageIcon newImg = new ImageIcon(sizeImg(img));
        
		JOptionPane.showMessageDialog(null, s, "Error!", JOptionPane.WARNING_MESSAGE, newImg);
	}
	
	/**
	 * Sets the size of the image
	 * 
	 * @param i an image that is to be placed on the Graphical User Interface
	 * @return the image after the sizes are set
	 */
	public Image sizeImg(Image i)
	{
		BufferedImage rImg = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = rImg.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(i, 0, 0, 50, 50, null);
		g2.dispose();
		
		return rImg;
	}
}