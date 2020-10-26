import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class holds the JDialog if a user wants to track his/her parcel
 *  by inputting his/her tracking number
 * 
 * @author Christianne Marie Vinco
 * @author Julian Rafael Sunpayco 
 * 
 * @version 1.0
 */
public class TrackingOpen extends JDialog implements ActionListener 
{
    /**
     * The constructor initializes the JDialog then calls the init() method to 
     *  initialize the Graphical User Interface then sets the close operation, 
     *  size, visibility, and location
     * 
     * @param track an object JFrame
     * @param modal a boolean for the modal of the JDialog
     */
    public TrackingOpen(JFrame track, boolean modal) 
    {
        super(track, modal);
        init();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 140);
        setVisible(true);
		setLocationRelativeTo(null);
    }

    /** 
     * Initializes the component of the Graphical User Interface 
     */
    public void init() 
    {
        getContentPane().setBackground(new Color(49, 98, 0));
        setLayout (new GridLayout(2, 1));          
        tfTrackingNumber = new JTextField(17);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(49, 98, 0));
        JLabel trackNum = new JLabel("Tracking Number: ");
        trackNum.setForeground(Color.WHITE);
        trackNum.setFont(new Font("Montserrat", Font.PLAIN, 16));
        panel.add(trackNum);
        panel.add(tfTrackingNumber);
        add(panel);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(49, 98, 0));
        buttonTrack = new JButton("Track Parcel");
        buttonTrack.setBackground(Color.WHITE);
        buttonTrack.setFont(new Font("Montserrat", Font.PLAIN, 12));
        panel.add(buttonTrack);
        buttonTrack.addActionListener(this);
        add(panel);
    }

    /**
     * Sets the action on what the program will do once an action even is   
     *  selected
     */
    public void actionPerformed (ActionEvent event) 
    { 
        boolean valid = true;
        if (event.getActionCommand().equals("Track Parcel")) 
        {
            dispose();
        }
     }

     /**
      * Returns the tracking number input by the user
      * @return tracking number of the parcel
      */
    public String getTrackingNum() 
    {
        return tfTrackingNumber.getText();
    }

    /* a filed that holds the tracking number of a parcel */
    private JTextField tfTrackingNumber;
    /* a button to track the parcel */
    private JButton buttonTrack;
}