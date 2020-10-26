import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * This class holds the Graphical User Interface that allows the user to choose an item type 
 * 	whether it is a Document, Regular product, or irregular product that they want to place 
 * 	in the parcel
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class PopupGui extends JFrame
{	
	/** 
	 * The constructor sets the title of the JFrame then calls the init() method to initialize 
	 * the Graphical User Interface, then it sets the close operation, size, visibility and the 
	 * 	location of the JFrame
	 */
	public PopupGui ()
	{
		super("Parcel");
		init();
		
		setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
		setSize(280, 120);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	/** 
	 * Initializes all the components of the Graphical User Interface
	 */
	public void init()
	{
		setLayout(new GridLayout (3, 1));
		
		JLabel lbl = new JLabel("Choose an item type");
		lbl.setFont(new Font("Montserrat", Font.BOLD, 16));
		lbl.setForeground(new Color(49, 98, 0));

		cmb = new JComboBox<>(type);
		cmb.setFont(new Font("Montserrat", Font.PLAIN, 14));
		cmb.setForeground(new Color(49, 98, 0));

		btn = new JButton("Next");
		btn.setFont(new Font("Montserrat", Font.BOLD, 14));
		lbl.setForeground(new Color(49, 98, 0));
		
		JPanel pnl = new JPanel ();
		pnl.setLayout (new FlowLayout ());
		pnl.setBackground(Color.WHITE);
		pnl.add(lbl);
		add(pnl);
		
		pnl = new JPanel ();
		pnl.setLayout (new FlowLayout ());
		pnl.setBackground(Color.WHITE);
		pnl.add(cmb);
		add(pnl);
		
		pnl = new JPanel ();
		pnl.setLayout (new FlowLayout ());
		pnl.setBackground(Color.WHITE);
		pnl.add(btn);
		add(pnl);
	}
	
	/** 
	 * This getter returns the string of the selected item type of the user 
	 * 
	 * @return a String that holds the item type selected by the user
	 */
	public String getCBox()
	{
		return (String)cmb.getSelectedItem();
	}
	
	/** 
	 * Adds listeners to the buttons as well as the jComboBox being used in the 
	 * 	Graphical User Interface
	 * 
	 * @param jCon a JpopupController object 
	 */
	public void addListeners (JPopupController jCon)
	{			
		btn.addActionListener(jCon);
		cmb.addActionListener(jCon);
	}

	/* String array that holds the item types */
	private String type[] = {"Document", "Regular Product", "Irregular Product"};
	/* a Combo box that holds the different item types */
	private JComboBox cmb;
	/* a Button that continues the program */
	private JButton btn;
}