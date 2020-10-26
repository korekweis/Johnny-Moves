import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * This is a class ParcelGUI that sets the commands and operations for 
 * 	the Graphic User Interface of the object Parcel
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class ParcelGui extends JDialog implements ActionListener
{	
	/** 
	 * The constructor sets the JFrame as welll as the modal and sets the 
	 * String as the choices which will be used for the options in the JComboBox.
	 * It then calls the init() function to initialize the controllers and output
	 * of the frame
	 * 
	 * @param f an object of JFrame
	 * @param modal a boolean for the modal of the JDialog
	 * @param choices a String array that holds the choices for the JComboBox 
	 * 			which the user can choose from
	 */
	public ParcelGui(JFrame f, boolean modal, String[] choices)
	{
		super(f, modal);
		this.choices = choices;
		init();
		
		setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
		setSize(335, 130);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	/**
	 * This method initializes the components of the Graphic User Interface
	 */
	public void init()
	{		
		setLayout(new GridBagLayout());
		getContentPane().setBackground(new Color(41, 50, 65));

		GridBagConstraints c = new GridBagConstraints();

		JLabel parcel = new JLabel("Choose a parcel");
		parcel.setFont(new Font("SansSerif", Font.BOLD, 15));
		parcel.setForeground(Color.WHITE); 
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 5;
		c.gridx = 0; 
		c.gridy = 0;
		add(parcel, c);

		cBox = new JComboBox<>(choices);
		cBox.setBackground(Color.WHITE);
		cBox.addActionListener(this);
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0; 
		c.gridy = 1;
		add(cBox, c);

		btn = new JButton("Confirm");
		btn.addActionListener(this);
		btn.setBackground(Color.WHITE);
		btn.setForeground(new Color(49, 52, 57));
		c.fill = GridBagConstraints.EAST;
		c.gridx = 0; 
		c.gridy = 2;
		add(btn, c);
	}
	
	/**
	 * Returns an integer that holds the type of parcel selected 
	 * 	by the user
	 * 
	 * @return an integer that holds the selected parcel type
	 */
	public int getCBox()
	{
		int val;
		String str = (String) cBox.getSelectedItem();
		
		if(str.substring(0, 3).equals("BOX"))
			val = Integer.parseInt(str.substring(3, 4)) + 2;
		else
			val = Integer.parseInt(str.substring(4, 5));
		
		return val;
	}

	/**
	 * Method sets the GUI to dispose once the user clicks confirm
	 * 
	 * @param e an object that holds an action event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Confirm"))
			dispose();
			
	}

	/* an array of string for the choices to be chosen from */
	private String[] choices;
	/* a button to confirm the parcel size chosen  */
	private JButton btn;
	/* a combo box to show the valid parcels for the user to choose from */
	private JComboBox cBox;
}