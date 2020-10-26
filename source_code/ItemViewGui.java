import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class holds the Graphic User Interface for when a user wants to 
 * view the items in a parcel. It is a JDialog that displays the contents
 * of a parcel and its information.
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */

public class ItemViewGui extends JDialog implements ActionListener, ItemListener
{	
	/** 
	 * The constructor sets the JFrame as well as the modal. It sets the size and visibility 
	 * 	of the frame and calls init() to initialize the components, as well as the array of
	 *  strings that holds the information of item/s in a specific parcel.
	 *
	 * @param items an array of strings that holds the information of items
	 * @param owner a JFrame that the GUI will user
	 * @param modal a boolean for the modal of the JDialog
	 */
	public ItemViewGui(String[] items, JFrame owner, boolean modal)
	{
		super(owner, modal);
		this.items = items;
		setBox();
		init();
		
		setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
		setSize (150, 100);
		setVisible (true);
	}
	
	/** 
	 * Initializes the components of the Graphical User Interface 
	 */
	public void init()
	{ 
		setLayout(new GridLayout(2, 1));
		
		cmb = new JComboBox<>(index);
		btn = new JButton("Exit");
		
		cmb.addItemListener(this);
		btn.addActionListener(this);
		
		add(cmb);
		add(btn);
	}
	
	/**
	 *  Disposes the JDialog if Exit button is clicked
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Exit"))
		{
			dispose();
		}
	}
	
	/**
	 *  Pops up a JOptionPane that shows the information of the 
	 *   selected item.
	 */
	public void itemStateChanged(ItemEvent e)
	{
		if (e.getStateChange () == ItemEvent.SELECTED)
		{
			if(!cmb.getSelectedItem().toString().equals("<Select Item>"))
			{
				int val = cmb.getSelectedIndex() - 1;
				JOptionPane.showMessageDialog(null, items[val]);
			}
		}
	}
	
	/**
	 *  Sets the content of the JComboBox that holds
	 *   the choices given to the user
	 */
	public void setBox()
	{
		int val = items.length;
		this.index = new String[val + 1];
		
		for(int i = 0; i < index.length; i++)
		{
			if(i == 0)
				index[i] = "<Select Item>";
			else
				index[i] = "Item " + i;
		}
	}
	
	/* JButton that holds exit button*/
	private JButton btn;
	
	/* Combo box that holds items*/
	private JComboBox cmb;
	
	/* Array of strings that holds information of items*/
	private String[] items;
	
	/* Array of strings that holds the index of items*/
	private String[] index;
}