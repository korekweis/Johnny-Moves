import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * This class is of the TrackingGUI which manages the Graphical User 
 *  Interface of the trackng for the user to know the estimated dates 
 *  and how long it will take to deliver the parcel. The class will 
 *  also be displaying the status of the delivery based on the curr date
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco 
 * 
 * @version 1.0
 */
public class TrackingGUI extends JFrame { 
    /** 
	 * The constructor sets the title of the frame and calls the init() method for it to 
	 * 	initialize the Graphical User Interface. It also sets the close operation, size, 
	 * 	visibility and whether or not it's resizable
	 */
    public TrackingGUI() { 
        super("Tracking of Parcel"); 
        init();
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
    }

    /** 
     * Initializes the components of the Graphical User Interface
     */
    private void init() 
    {
        setLayout(new GridBagLayout()); 
        getContentPane().setBackground(Color.white);

        GridBagConstraints c = new GridBagConstraints();

        JLabel img = new JLabel(new ImageIcon("LogoSmall.png"));
        c.fill = GridBagConstraints.EAST;
        c.gridx = 1;
        c.gridy = 0; 
        c.gridheight = 1;
        add(img, c);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.setBackground(Color.WHITE);
        trackNum = new JLabel();
        trackNum.setFont(new Font("Cooper Black", Font.BOLD, 20));
        trackNum.setForeground(new Color(49, 98, 0));
        panel.add(trackNum);
        destination = new JLabel();
        destination.setFont(new Font("Arial", Font.PLAIN, 12));
        destination.setForeground(new Color(49, 98, 0));
        panel.add(destination);
        recipient = new JLabel();
        recipient.setFont(new Font("Arial", Font.PLAIN, 12));
        recipient.setForeground(new Color(49, 98, 0));
        panel.add(recipient);
        c.fill = GridBagConstraints.WEST;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0; 
        add(panel, c);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblDate = new JLabel();
        lblDate.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(lblDate);
        c.fill = GridBagConstraints.WEST;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        add(panel, c); 
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBackground(Color.WHITE);
        lblStat = new JLabel();
        lblStat.setBackground(Color.white);
        panel.add(lblStat); 
        c.fill = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 2;
		add(panel, c); 
		
        btnNext = new JButton("Next");
		btnNext.setBackground(Color.WHITE);
        c.fill = GridBagConstraints.EAST;
        c.gridx = 1; 
        c.gridy = 3;
        add(btnNext, c);
    }

    /**
	 * Adds listeners to the JButtons that will be used 
     * in the Graphical User Interface
	 * 
	 * @param listener an Action Listener object
	 */
    public void addListener(ActionListener listener) 
    {
        btnNext.addActionListener(listener);
    }

    /** 
     * Sets the date that is to be displayed depending on the 
     *  String tha to be passed 
     * 
     * @param date a String that holds the date of what parcel
     *  being tracked
     */
    public void setDate(String date) 
    { 
        lblDate.setText(date);
    }


	/** 
     * Sets the stats of the delivery of the parcel with the use 
     *  of images 
     * 
     * @param icon which holds the image that is to be displayed
     */
	public void setStatus(ImageIcon icon)
	{
		lblStat.setIcon(icon);
    }
    
    /** 
     * Sets the trackNum to the String that is being passed in the
     *  parameter 
     * 
     * @param trackNum a String that holds the tracking number of the parcel
     */
    public void setTrackingNum(String trackNum) 
    { 
        this.trackNum.setText(trackNum);
    }

    /** 
     * Sets the recipient to the String that is being passed in
     *  the parameter 
     * 
     * @param name a String that holds the name of the recipient
     */
    public void setRecipient(String name) 
    { 
        recipient.setText(name);
    }

    /** 
     * Sets the destination of the parcel that is being tracked 
     * 
     * @param dest a String that holds the destination of the parcel
     */
    public void setDestination(String dest) 
    { 
        destination.setText(dest);
    }

    /* holds the dates that are to be displayed */
    private JLabel lblDate;
    /* holds the status of the delivery */
    private JLabel lblStat;
    /* holds the track number of the parcel */
    private JLabel trackNum;
    /* holds the destination on where the parcel will be delivered */
    private JLabel destination;
    /* holds the name of the recipient */
    private JLabel recipient;
    /* holds the JButton if the user wants to go to the next option */
    private JButton btnNext;
}