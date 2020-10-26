import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/** 
 * This class holds the Graphical User Interface for checking out the program 
 *  by an employee. This will initialize the components and set action listeners
 *  as well as set the layouts for the final outcome
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco
 */
public class CheckOutGUI extends JFrame { 
    /** 
     * The constructor sets the name of the JFrame as well as sets sizes,
     * sets the frame to not be resizable as well as sets it to be visible
     */
    public CheckOutGUI() { 
        super("Checking Out");
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(350, 250);
        setResizable(false);
        setVisible(true);
    }

    /**
     * This method initializes all the components in the Graphical User Interface
     */
    public void init() { 
        setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        
        JPanel p = new JPanel(new FlowLayout());
        JLabel title = new JLabel("Check Out");
        p.setBackground(new Color(14, 36, 51));
		title.setFont(new Font("SansSerif", Font.BOLD, 20));
		title.setForeground(Color.WHITE); 
		p.add(title);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
		c.gridx = 0; 
		c.gridy = 0;
		add(p, c);

        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        J1 = new JRadioButton("Accounting and Finance");
        J2 = new JRadioButton("Courier Driver");
        J3 = new JRadioButton("Human Resources");
        J4 = new JRadioButton("Information Technology");
        J5 = new JRadioButton("Learning and Development");
		

        ButtonGroup group = new ButtonGroup();
        group.add(J1);
        group.add(J2);
        group.add(J3);
        group.add(J4);
        group.add(J5);

        Box box = Box.createVerticalBox();
        box.add(J1);
        box.add(J2);
        box.add(J3);
        box.add(J4);
        box.add(J5);
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.setBackground(Color.WHITE);
        p.setBackground(Color.WHITE);
        p.add(box);
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0; 
		c.gridy = 1;
		c.weighty = 1;
        add(p, c);

        p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setBackground(Color.WHITE);
        p.add(new JLabel("Password: "));
        password = new JPasswordField(14);
        p.add(password);
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0; 
		c.gridy = 2;
		c.weighty = 1;
        add(p, c);

        OkButton = new JButton("Ok");
        c.fill = GridBagConstraints.EAST;
		c.gridx = 3; 
		c.gridy = 7;
        add(OkButton, c);
    }
    
    /** 
     * Adds actionlisteners to all the buttons and options
     *  for the user to operate what to do with the program
     * 
     * @param jCon  a CheckOut controller
     */
	public void addListener(CheckOutController jCon)
	{	
		J1.addActionListener(jCon);
        J2.addActionListener(jCon);
        J3.addActionListener(jCon);
        J4.addActionListener(jCon);
        J5.addActionListener(jCon);
        OkButton.addActionListener(jCon);
		password.addActionListener(jCon);
	}

    /**
     * Returns the password that was placed by the employee for the 
     *  program to check if the password is correct and if it matches the
     *  department that was chosen
     * 
     * @return pw a String that holds the password input by the employee
     */
    public String getPass() { 
        String pw = new String(password.getPassword());
		return pw;
    }

    /* JRadioButton that holds the Accounting and Finance Department */
    private JRadioButton J1; 
    /* JRadioButton that holds the Courier Driver Department */
    private JRadioButton J2;
    /* JRadioButton that holds the Human Resources Department */
    private JRadioButton J3;
    /* JRadioButton that holds the Information Technology Department */
    private JRadioButton J4;
    /* JRadioButton that holds the Learning and Development Department */
    private JRadioButton J5;
    /* JButton that holds the Ok for the user to proceed */
    private JButton OkButton;
    /* A field that holds the password input by the employee */
    private JPasswordField password;
}