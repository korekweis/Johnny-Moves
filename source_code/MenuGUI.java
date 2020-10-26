import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 * This class holds the Graphical User Interface for the menu which holds the layout 
 * 	which the user can choose from whether he/she will create a parcel, track a parcel
 * 	or check out with the help of an employee. The class also assigns action listeners 
 * 	to its components
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class MenuGUI extends JFrame 
{
	/**
	 * The constructor sets the title of the JFrame, then calls the init() method to initialize
	 * 	the components of the Graphical User Interface. It then also sets the closet operation
	 * 	of the frame, size, whether the frame is resizable or not, visibility, and the location 
	 * 	of the frame once it opens 
	 */
	public MenuGUI() 
	{
        super("Johnny Moves");
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(720, 550);
        setResizable(false);
        setVisible(true);
		setLocationRelativeTo(null);
    }

	/**
	 * Initializes all of the components in the Graphical User Interface
	 */
	public void init() 
	{
        setLayout(new GridLayout(2,1));
		getContentPane().setBackground(Color.white);
		
		ImageIcon img = new ImageIcon("1.png");
		Image ing = img.getImage();
		ImageIcon newImg = new ImageIcon(sizeImg(ing));
		create = new JButton(newImg);
		create.setBackground(Color.WHITE);
        create.setActionCommand("create");
        
		ImageIcon img1 = new ImageIcon("2.png");
		Image ing1 = img1.getImage();
		ImageIcon newImg1 = new ImageIcon(sizeImg(ing1));
		track = new JButton(newImg1);
		track.setBackground(Color.WHITE);
        track.setActionCommand("track");
		
		ImageIcon img2 = new ImageIcon("3.png");
		Image ing2 = img2.getImage();
		ImageIcon newImg2 = new ImageIcon(sizeImg(ing2));
		exit = new JButton(newImg2);
		exit.setBackground(Color.WHITE);
        exit.setActionCommand("exit");
		
		ImageIcon lgo = new ImageIcon("MainLogo.png");
		Image imLo = lgo.getImage();
		ImageIcon newLogo = new ImageIcon(sizeLogo(imLo));
		JLabel label = new JLabel(newLogo);
		add(label);
		
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel.add(create);
		panel.add(track);
		panel.add(exit);
		add(panel);
	}
	
	/**
	 * Adds action listeners to the JButtons that the user can choose from 
	 * @param listener an Event Listener that holds the JButtons
	 */
	public void addListener(EventListener listener) 
	{ 
        create.addActionListener((ActionListener) listener);
        track.addActionListener((ActionListener) listener);
        exit.addActionListener((ActionListener) listener);
    }
	
	/**
	 * Sets the size of a specific image i
	 * 
	 * @param i that holds an image object 
	 * @return rImg
	 */
	public Image sizeImg(Image i)
	{
		BufferedImage rImg = new BufferedImage(175, 175, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = rImg.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(i, 0, 0, 175, 175, null);
		g2.dispose();
		
		return rImg;
	}
	
	/**
	 * Sets the size of the Logo image of the menu 
	 * 
	 * @param i that holds an image object 
	 * @return rImg
	 */
	public Image sizeLogo(Image i)
	{
		BufferedImage rImg = new BufferedImage(650, 250, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = rImg.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(i, 0, 0, 650, 250, null);
		g2.dispose();
		
		return rImg;
	}

	/* a JButton used to create a parcel */
	private JButton create;
	/* a JButton used to track a parcel */	
	private JButton track;
	/* a JButton used to exit the program*/
	private JButton exit;
}