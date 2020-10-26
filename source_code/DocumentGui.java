import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class holds the DocumentGui object which manages the Graphical User
 * 	Interface for when the user wants to add an item of the type document.
 * 	This extends a JFrame and displays the information needed
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class DocumentGui extends JFrame
{	
	/** 
	 * The constructor sets the title of the frame and calls the init() method for it to 
	 * 	initialize the Graphical User Interface. It also sets the close operation, size, 
	 * 	visibility and whether or not it's resizable
	 */
	public DocumentGui()
	{
		super("Document");
		
		init();
		
		setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
		setSize(360, 280);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	/**
	 * Initializes the components of the Graphical User Interface
	 */
	public void init()
	{
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.WHITE);

		GridBagConstraints c = new GridBagConstraints();

		JPanel p = new JPanel(new FlowLayout());
		JLabel title = new JLabel("DOCUMENT");
		p.setBackground(new Color(14, 36, 51));
		title.setFont(new Font("SansSerif", Font.BOLD, 20));
		title.setForeground(Color.WHITE); 
		p.add(title);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
		c.gridx = 0; 
		c.gridy = 0;
		add(p, c);

		JLabel name = new JLabel("Item name");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridx = 0; 
		c.gridy = 1;
		c.weighty = 0.5;
		add(name, c);
		tfN = new JTextField("", 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2; 
		c.weighty = 0.5;
		add(tfN, c);

		JLabel len = new JLabel("Length (inch) ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; 
		c.gridy = 2;
		add(len, c);
		tfL = new JTextField("", 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2; 
		c.weighty = 0.5;
		add(tfL, c);

		JLabel wid = new JLabel("Width (inch) ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; 
		c.gridy = 3;
		add(wid, c);
		tfW = new JTextField("", 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2; 
		c.weighty = 0.5;
		add(tfW, c);

		JLabel nP = new JLabel("No. of pages");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; 
		c.gridy = 4;
		add(nP, c);
		tfP = new JTextField("", 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2; 
		c.weighty = 0.5;
		add(tfP, c);

		rb = new JRadioButton("Insure");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; 
		c.gridy = 5;
		add(rb, c);

		back = new JButton("Back");
		c.fill = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 6;
		add(back, c);

		btn = new JButton("Add");
		c.fill = GridBagConstraints.EAST;
		c.gridx = 3; 
		c.gridy = 6;
		add(btn, c);
		
	}
	
	/**
	 * Adds listeners to the JTextFields and JButtons in the Graphical 
	 * 	User Interface
	 * 
	 * @param jCon a JPopoupController object
	 */
	public void addListeners (JPopupController jCon)
	{	
		btn.addActionListener(jCon);
		back.addActionListener(jCon);
		rb.addActionListener(jCon);
		tfL.addActionListener(jCon);
		tfN.addActionListener(jCon);
		tfP.addActionListener(jCon);
		tfW.addActionListener(jCon);
	}

	/**
	 * Returns the value of the length imput by the user 
	 * 
	 * @return a double value val that holds the length of the item
	 */
	public double getItemLength()
	{
		double val;
		val = Double.parseDouble(tfL.getText());
		return val;
	}
	
	/**
	 * Returns the name of the recipient of the parcel 
	 * 
	 * @return a String that holds the name of the recipient
	 */
	public String getName()
	{
		return tfN.getText();
	}
	
	/**
	 * Returns the number of pages of the document
	 * 
	 * @return an integer value val that holds the number of pages
	 */
	public int getPages()
	{
		int val;
		val = Integer.parseInt(tfP.getText());
		return val;
	}
	
	/**
	 * Returns the width of the item
	 * 
	 * @return a double value val that holds the width of the item
	 */
	public double getItemWidth()
	{
		double val;
		val = Double.parseDouble(tfW.getText());
		return val;
	}
	
	/**
	 * Returns a string that holds a yes if the user wants to insure the
	 * 	item and no if otherwise 
	 * 
	 * @return a String s that holds whether or not the user wants to insure
	 * 	the document or not
	 */
	public String getRadio()
	{
		String s = "";
		
		if(rb.isSelected())
			s += "yes";
		else
			s += "no";
		
		return s;
	}
	
	/**
	 * A JOptionPane that displays the information
	 *  of the item the user just added.
	 *
	 * @param anItem an Item object
	 * @param current the index of the item
	 */
	public void displayInfo(Item anItem, int current)
	{
		UIManager UI = new UIManager();
		String str = "";
		
		str += "Item " + current + "\n\n";
		str += "Item type: " + anItem.getType() + "\n";
		str += "Item name: " + anItem.getName() + "\n\n";
        str += anItem.toString();
		
		UI.put("OptionPane.background", Color.WHITE);
		UI.put("Panel.background", Color.WHITE);

		JOptionPane.showMessageDialog(null, str, "Item", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
	
	/* JRadioButton for the insurance of the item  */
	private JRadioButton rb;
	/* JButton for next */
	private JButton btn;
	/* JButton for back */
	private JButton back;
	/* JTextField for the name of the recipient */
	private JTextField tfN;
	/* JTextField for the length of the document */
	private JTextField tfL;
	/* JTextField for the width of the document */
	private JTextField tfW;
	/* JTextField for the number of pages of the document */
	private JTextField tfP;
}