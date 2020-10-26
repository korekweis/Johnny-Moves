import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/** 
 * This class holds the Graphical User Interface if the user wants to check in 
 *  with the help of the employee. This class initializes the layout as well as 
 *  assigns action listeners to the components
 * 
 * @author Chrissy Vinco 
 * @author Julian Sunpayco
 * 
 * @version 1.0
 */
public class CheckInGUI extends JFrame 
{ 
    /** 
     * The constructor sets the title of the JFrame as well as its close operations,
     *  size, whether the fram is resizable as well as the frame's visibility. 
     *  Calls the init() function to initialize the Graphical User Interface
     */
    public CheckInGUI() 
    { 
        super("Checking In");
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(340, 430);
        setResizable(false);
        setVisible(true);
    }

    /** 
     * Initializes the components of the Graphical User Interface
     */
    public void init() 
    { 
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        
        JPanel p = new JPanel();
		p.setLayout(new FlowLayout()); 
		p.setBackground(Color.WHITE);
		JLabel imgLabel = new JLabel(new ImageIcon("Logo.png"));
        p.add(imgLabel);
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
		c.gridx = 0; 
		c.gridy = 0;
		add(p, c);
        
        p = new JPanel(new FlowLayout());
        JLabel title = new JLabel("CHECK IN");
        p.setBackground(new Color(49, 98, 0));
		title.setFont(new Font("SansSerif", Font.BOLD, 20));
		title.setForeground(Color.WHITE); 
		p.add(title);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
		c.gridx = 0; 
		c.gridy = 1;
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
		c.gridy = 2;
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
		c.gridy = 3;
		c.weighty = 1;
        add(p, c);

        OkButton = new JButton("Ok");
        OkButton.setBackground(Color.WHITE);
        c.fill = GridBagConstraints.EAST;
		c.gridx = 3; 
		c.gridy = 4;
        add(OkButton, c);
    }
    
    /**
     * Adds action listeners to the JRadioButtons 
     * 
     * @param jCon a CheckInController object that holds the JButtons
     */
	public void addListener(CheckInController jCon)
	{	
		J1.addActionListener(jCon);
        J2.addActionListener(jCon);
        J3.addActionListener(jCon);
        J4.addActionListener(jCon);
        J5.addActionListener(jCon);
        OkButton.addActionListener(jCon);
	}

    /**
     * Returns the password input by the employee
     * 
     * @return pw - a String that holds the password
     */
    public String getPass() 
    { 
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