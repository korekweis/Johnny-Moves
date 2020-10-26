import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/** 
 * The class that holds the controller for the PopupGUI which gathers the measurements
 * 	of the item as well as the name, and whether or not the item is to be insured
 * 
 * @author Christianne Marie Vinco 
 * @author Julian Rafael Sunpayco
 * 
 * @version 1.0
 */
public class JPopupController implements ActionListener
{	
	/** 
	 * The constructor sets the PopupGui, machine, total, recipient
	 * 	and region to the parameters being pased. It then sets the name to null 
	 * 	and the current to zero as well as adds the Action Listener of the class
	 * 
	 * @param m a Machine object
	 * @param ppg a PopupGui object 
	 * @param n an integer that holds the total number of items
	 * @param re a String that holds the recipient
	 * @param rg a whole number that holds the region on where the parcel is to be
	 * 			delivered
	 */
	public JPopupController(Machine m, PopupGui ppg, int n, String re, int rg)
	{
		this.ppG = ppg;
		this.machine = m;
		
		this.curr = 0;
		this.total = n;
		
		this.recipient = re;
		this.region = rg;

		this.name = null;
		
		ppG.addListeners(this);
	}
	
	/** 
	 * Sets what the program will do once an action event is selected 
	 * 
	 * @param e an ActionEvent object that holds the action that the user wants
	 * 	to do with the program
	 */
	public void actionPerformed(ActionEvent e)
	{
		ErrorGUI error;

		if(e.getActionCommand().equals("Next"))
		{
			type = ppG.getCBox();
			ppG.dispose();

			if(type.equals("Document"))
			{
				this.dGui = new DocumentGui();
				dGui.addListeners(this);
			}
			
			else
			{
				this.pGui = new ProductGui();
				String s = "Item " + curr + " / " + total;
				pGui.addListeners(this);
			}
		}
		
		if(e.getActionCommand().equals("Add"))
		{
			Boolean valid;
			if(this.type.equals("Document"))
			{
				try 
				{ 
					valid = true;
					this.name = dGui.getName();
					this.insurance = dGui.getRadio();
				
					this.length = dGui.getItemLength();
					this.width = dGui.getItemWidth();
					this.nPages = dGui.getPages();
				}
				catch (NumberFormatException a){ 
					error = new ErrorGUI("Please input values");
					valid = false;
				}
				catch (NullPointerException a) {
					error = new ErrorGUI("Please fill up the missing areas");
					valid = false;
				}
				catch (Exception a) { 
					error = new ErrorGUI("Error found!");
					valid = false;
				}
				if (length <= 0 || width <= 0 || nPages <= 0 && valid) { 
					error = new ErrorGUI("Values are invalid");
					valid = false;
				}
			
				if (valid == true) { 
					this.items.add(new Document(type, name, length, width, insurance, nPages));
					machine.addItem(items.get(curr));
					
					dGui.displayInfo(items.get(curr), curr + 1);
					
					this.curr++;
				}
				else { 
					dGui.dispose();
				}
			}
			else
			{
				try { 
					valid = true;
					this.name = pGui.getName();
					this.insurance = pGui.getRadio();
				
					this.length = pGui.getItemLength();
					this.width = pGui.getItemWidth();
					this.height = pGui.getItemHeight();
					this.weight = pGui.getItemWeight();
				}
				catch (NumberFormatException a){ 
					error = new ErrorGUI("Please input values");
					valid = false;
				}
				catch (NullPointerException a) {
					error = new ErrorGUI("Please fill up the missing areas");
					valid = false;
				}
				catch (Exception a) { 
					error = new ErrorGUI("Error found!");
					valid = false;
				}

				if (length <= 0 || width <= 0 || height <= 0 || weight <= 0 && valid) { 
					error = new ErrorGUI("Negative values are invalid");
					valid = false;
				}
				if (valid == true) { 
					if(type.equals("Regular Product"))
					{
						this.items.add(new Product(type, name, length, width, height, weight, insurance));
						machine.addItem(items.get(curr));
					}

					else
					{
						this.items.add(new Irregular(type, name, length, width, height, weight, insurance));
						machine.addItem(items.get(curr));
					}
					
					pGui.displayInfo(items.get(curr), curr + 1);
					
					this.curr++;
				}

				else {
					pGui.dispose();
				}
			}
			
			this.type = "";
			if(curr == total)
			{					
				machine.setSizes();
				ArrayList<String> parcelList = new ArrayList<String>(machine.toStringValidParcels());
				
				if (machine.assignValidParcels())
				{ 
					String[] parChoices = new String[parcelList.size()];
					for(int i=0; i < parcelList.size(); i++)
						parChoices[i] = parcelList.get(i);
					
					ParcelGui parG = new ParcelGui(null, true, parChoices);
					int choice = parG.getCBox();
					machine.setParcelType(choice);
					machine.addTransaction();
					TransactionsGUI transactG = new TransactionsGUI();
					TransactionsController transactC = new TransactionsController(transactG, machine);
				}
				else
					error = new ErrorGUI("No available parcels!");
			}
			else
			{
				this.ppG = new PopupGui();
				ppG.addListeners(this);
			}
		}

		if(e.getActionCommand().equals("Back"))
		{
			if(this.type.equals("Document")) { 
				dGui.dispose();
				this.ppG = new PopupGui();
				ppG.addListeners(this);
			}
			else { 
				pGui.dispose();
				this.ppG = new PopupGui();
				ppG.addListeners(this);
			}
		}
	}

	/* holds the PopupGui object */
	private PopupGui ppG;
	/* holds the DocumentGui object */
	private DocumentGui dGui;
	/* holds the ProductGui object */
	private ProductGui pGui;
	/* holds the machine object */
	private Machine machine;
	/* holds the arrayList for items */
	private ArrayList<Item> items = new ArrayList<Item>();
	/* holds the integer for the current item number */
	private int curr;
	/* holds the integer for the total number of items */
	private int total;
	/* holds the String for the recipient */
	private String recipient;
	/* holds the integer for the region */
	private int region;
	/* holds the String for the name of the item */
	private String name;
	/* holds the String for the insurance */
	private String insurance;
	/* holds the String for the type of item */
	private String type;
	/* holds the double value for the length */
	private double length;
	/* holds the double value for the width */
	private double width;
	/* holds the double value for the weight */
	private double weight;
	/* holds the double value for the height */
	private double height;
	/* holds the integer value for the number of pages */
	private int nPages;
}