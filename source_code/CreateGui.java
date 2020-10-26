import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class holds the Graphic User Interface for when a user wants to 
 * 	create a parcel. It is a JDialog that will ask for the input of the 
 * 	name, destination, and number of items that will be placed in the parcel 
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class CreateGui extends JDialog implements ActionListener
{	
	/** 
	 * The constructor sets the JFrame as well as the modal. It sets the size and visibility 
	 * 	of the frame and calls init() to initialize the components
	 *
	 * @param f a JFrame that holds the frame of the gui
	 * @param modal a boolean for the modal of the JDialog
	 */
	public CreateGui(JFrame f, boolean modal)
	{
		super(f, modal);
		init();
		
		setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
		setSize(310, 330);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	/** 
	 * Initializes the components of the Graphical User Interface 
	 */
	public void init()
	{
		setLayout(new FlowLayout());
		getContentPane().setBackground(Color.WHITE);

		JPanel p = new JPanel();
		p.setLayout(new FlowLayout()); 
		p.setBackground(Color.WHITE);
		JLabel imgLabel = new JLabel(new ImageIcon("Logo.png"));
		p.add(imgLabel);
		add(p);

		JPanel n = new JPanel();
		n.setLayout(new GridLayout(4,1));

		p = new JPanel();
		p.setLayout(new GridBagLayout()); 
		p.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		JLabel lOne = new JLabel("Name of recipient: ");
		lOne.setFont(new Font("Comfortaa", Font.BOLD, 14));
		lOne.setForeground(new Color(49, 98, 0));
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		p.add(lOne, c);
		txt = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		p.add(txt, c);
		n.add(p);

		p = new JPanel();
		p.setLayout(new GridBagLayout()); 
		p.setBackground(Color.WHITE);
		c = new GridBagConstraints();
		JLabel lTwo = new JLabel("Region ");
		lTwo.setFont(new Font("Comfortaa", Font.BOLD, 14));
		lTwo.setForeground(new Color(49, 98, 0));
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 2;
		p.add(lTwo);
		cBox = new JComboBox<>(regions);
		cBox.setBackground(new Color(49, 98, 0));
		cBox.setFont(new Font("Comfortaa", Font.PLAIN, 14));
		cBox.setForeground(Color.WHITE);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1; 
		p.add(cBox);
		n.add(p);

		p = new JPanel();
		p.setLayout(new GridBagLayout()); 
		p.setBackground(Color.WHITE);
		c = new GridBagConstraints();
		JLabel lThr = new JLabel("Number of items ");
		lThr.setFont(new Font("Comfortaa", Font.BOLD, 14));
		lThr.setForeground(new Color(49, 98, 0));
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		p.add(lThr);
		nI = new JTextField(2);
		nI.setBackground(Color.WHITE);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1; 
		p.add(nI);
		n.add(p);

		p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBackground(Color.WHITE); 
		c = new GridBagConstraints();
		btn = new JButton("Continue");
		btn.setBackground(new Color(49, 98, 0));
		btn.setFont(new Font("Comfortaa", Font.BOLD, 14));
		btn.setForeground(new Color(255, 255, 255));
		btn.addActionListener(this);
		c.anchor = GridBagConstraints.EAST;
		p.add(btn);
		n.add(p);
		add(n);
	}
	
	/**
	 * Returns the name of the recipient input by the user
	 * 
	 * @return a String that holds the name of the recipient
	 */
	public String getTextInfo()
	{
		return txt.getText();
	}
	
	/** 
	 * Returns true if the value is an integer else returns false if
	 * 	otherwise
	 * 
	 * @return boolean if the input is an integer or not
	 */
	public boolean isInt()
	{	
		try
		{
			String str = nI.getText();
			int val = Integer.valueOf(str);
			
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	/**
	 * Returns true if the JTextField is empty or if the number of 
	 * 	items that were input is less than or equal to zero and false if otherwise
	 * 
	 * @return a boolean to check whether or not the field is empty
	 */
	public boolean isEmpty()
	{
		String s = nI.getText();
		int val = Integer.valueOf(s);
		String s2 = txt.getText();
		
		if(s.equals("") || s2.equals("") || val <= 0)
			return true;
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns the index of the destination chosen by the user
	 * 
	 * @return integer that holds the index of the destination chosen
	 */
	public int getCBoxIndex()
	{
		return (Integer)cBox.getSelectedIndex();
	}
	
	
	/**
	 * Returns the number of items the user wishes to 
	 * add to a parcel.
	 *
	 * @return integer that holds the number of items wished to add
	 */
	public int getInt()
	{
		int val;
		
		String s = nI.getText();
		val = Integer.valueOf(s);
		
		return val;
	}
	
	/**
	 * Checks if the inputs are valid and the program then shows an error 
	 * 	message if there are errors in the input of the user
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Continue")) 
		{
			if(isInt() && !isEmpty())
				dispose();
				
			else
			{
				ErrorGUI error = new ErrorGUI("Invalid Inputs!");
				txt.setText("");
				nI.setText("");
			}			
		}
	}
	
	/*Array of strings that holds the destinations to choose from*/
	private String regions[] = {"Metro Manila", "Provincial Luzon", "Visayas", "Mindanao"};
	/* Holds the button for the program to continue */
	private JButton btn;
	/* JTextField for the user to place the name of the recipient */
	private JTextField txt;
	/* JTextField for the user to place the number of items */
	private JTextField nI;
	/*	JComboBox to choose the destination where the parcel will be delivered */
	private JComboBox cBox; 
}