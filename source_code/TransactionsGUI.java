import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * This class holds the Graphical User Interface of the transactions
 *  in order for the user to view all the transactions made in the 
 *  machine 
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class TransactionsGUI extends JFrame{ 

  /** 
	 * The constructor sets the title of the frame and calls the init() method for it to 
	 * 	initialize the Graphical User Interface. It also sets the close operation, size, 
	 * 	visibility and location
	 */
    public TransactionsGUI() 
    { 
        super("Transactions");
        setLayout(new GridLayout(3, 1)); 
        init();
        setSize(360, 355);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

      destination = new JLabel();
      destination.setFont(new Font("Cooper Black", Font.PLAIN, 18));
      c.fill = GridBagConstraints.WEST;
      c.gridx = 0;
      c.gridy = 0;
      add(destination, c);

      JLabel img = new JLabel(new ImageIcon("LogoSmall.png"));
      c.fill = GridBagConstraints.EAST;
      c.gridx = 1;
      c.gridy = 0; 
      add(img, c);

      trackNum = new JLabel();
      trackNum.setFont(new Font("Arial", Font.BOLD, 20));
      c.fill = GridBagConstraints.WEST;
      c.weighty = 1;
      c.gridx = 0;
      c.gridy = 1; 
      add(trackNum, c);

      recipient = new JLabel();
      recipient.setFont(new Font("SansSerif", Font.PLAIN, 12));
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weighty = 0;
      c.gridx = 0;
      c.gridy = 2; 
      add(recipient, c);

      currDate = new JLabel();
      currDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 3; 
      add(currDate, c);

      JPanel p = new JPanel(new FlowLayout());
      JLabel Info = new JLabel("<html><font color = 'Black'>Information</font></html>");
      Info.setBackground(new Color(41, 50, 65));
      Info.setFont(new Font("Cooper Black", Font.BOLD, 24));
      Info.setForeground(Color.WHITE);
      p.add(Info);
      c.fill = GridBagConstraints.HORIZONTAL;
		  c.gridx = 0; 
      c.gridy = 4;
      c.weighty = 1.5;
      add(p, c);
      
      p = new JPanel();
      p.setBackground(Color.WHITE);
      lblTransact = new JLabel();
      lblTransact.setFont(new Font("Arial", Font.PLAIN, 10));
      p.add(lblTransact);
      c.fill = GridBagConstraints.CENTER;
	  c.gridx = 0; 
      c.gridy = 5;
      c.weighty = 0.5;
      add(p, c);

      p = new JPanel(new FlowLayout());
      p.setBackground(Color.WHITE);
      JLabel total = new JLabel("Total: ");
      total.setFont(new Font("Arial", Font.BOLD, 16));
      p.add(total);
      totalAmt = new JLabel();
      totalAmt.setFont(new Font("Arial", Font.BOLD, 16));
      p.add(totalAmt);
      c.fill = GridBagConstraints.WEST;
	  c.gridx = 0; 
      c.gridy = 6;
      c.weighty = 0;
      add(p, c);
	  
	  
	  p = new JPanel(new GridLayout(1, 3));
	  
      btnPrev = new JButton("Prev");
      btnPrev.setBackground(Color.WHITE);

      btnNext = new JButton("Next");
      btnNext.setBackground(Color.WHITE);
	  
	  btnView = new JButton("View Items");
	  btnView.setBackground(Color.WHITE);

	  p.add(btnPrev);
	  p.add(btnView);
	  p.add(btnNext);
	  
	  c.fill = GridBagConstraints.CENTER;
	  c.gridy = 7;
	  add(p, c);
    }

    /** 
     * Adds listeners to the JButtons in the Graphical User
     *  Interface 
     * 
     * @param listener an ActionListener object
     */
    public void addListener(ActionListener listener) 
    { 
        btnNext.addActionListener(listener);
        btnPrev.addActionListener(listener);
		btnView.addActionListener(listener);
    }

    /**
     * Sets the lblTransact label to the String information that 
     *  is being passed in the parameter
     * 
     * @param information a String that holds the information of the
     *  transactions
     */
    public void setInfo(String information) 
    { 
		  lblTransact.setText("<html>" + information.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
    }

    /**
     * Sets the destination label to the String dest being passed 
     *  in the parameter
     * 
     * @param dest a String that holds the destination of the parcel
     */
    public void setDestination (String dest) 
    { 
      destination.setText(dest);
    }

    /** 
     * Sets the trackNum label to the String trackNum  being passed in 
     *  the parameter
     * 
     * @param trackNum a String that holds the tracking number of the parcel
     */
    public void setTrackingNum(String trackNum) 
    { 
      this.trackNum.setText(trackNum);
    }

    /** 
     * Sets the currDate label to the String date being passed in the 
     *  parameter
     * 
     * @param date a String that holds the date that the parcel was made
     */
    public void setCurrDate(String date) 
    { 
      currDate.setText(date);
    }

    /** 
     * Sets the recipient label to the String name that is being passed 
     *  in the parameter 
     * 
     * @param name a String that holds the name of the recipient
     */
    public void setRecipient(String name) 
    { 
      recipient.setText(name);
  }

    /** 
     * Sets the totalAmt label to the String total that is being passed
     *  in the parameter 
     * 
     * @param total a String that holds the total value of the parcel
     */
    public void setTotal(String total) 
    { 
      totalAmt.setText(total);
    }

    /* button that holds next parcel */
    private JButton btnNext;
    /* button that holds previous parcel */
    private JButton btnPrev;
	/* button that holds viewing of items in a parcel*/
	private JButton btnView;
    /* label that holds the tracking number */
    private JLabel trackNum;
    /* label that holds the destination of the parcel */
    private JLabel destination;
    /* label that holds the all the transaction information */
    private JLabel lblTransact;
    /* label that holds the name of recipient */
    private JLabel recipient;
    /* label that holds the current date that the parcel was made */
    private JLabel currDate;
    /* label that holds the total amount of the transaction */
    private JLabel totalAmt;
}