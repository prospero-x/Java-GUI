package SEARCH_STAFFDB;
import CREATE_STAFFDB.*;
import COMMON_STAFFDB.*;
import java.awt.*;       
import javax.swing.*;    
import javax.swing.border.*;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * SEARCHSTAFFDB: implementation of the search GUI. 
 * Allows a user to select a staff attribute and enter 
 * a value for that attribute that the user would like to match 
 * for. 
 * 
 * The result of the search is displayed in an interactive table 
 * which the user can sort by clicking the header of each column
 */

public class SearchStaffDb extends JFrame {

	private FileIO file_io;
	public AttributeBox attribute_box;
	public ValueTextField value_field;
	public MatchResultTable matches;
	public SearchButton search_button;
	public QuitButton quit_button;
	public String ssnTooLongError;
	public String nameTooLongError;
	public String idTooLongError;
	private JLabel zeroMatchMessage;
	private JPanel search_panel;
	private JPanel button_panel;
	private JPanel result_panel;
	private JPanel outer_panel;
	private JPanel word_panel;
	Container cp;

	public SearchStaffDb() {
		/* Create a database reader and writer. */
		file_io = new FileIO();

     	cp = getContentPane();
      	initializePanels();
      	introduction();
      	userSearch();
      	cp.add(outer_panel, BorderLayout.CENTER);

      	/* Exit if the user closes the window. */
      	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    setTitle("Search Staff Database"); 
	    setSize(1000, 500); 

	    /* Place the window in the middle of the screen. */
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int)((dimension.getWidth() - getWidth())/2);
	    int y = (int)((dimension.getHeight() - getHeight())/2);
	   	setLocation(x,y);
	    setVisible(true); 
	}

	/* Instantiate all the panels and set all the error messages */
	public void initializePanels()
	{
		ssnTooLongError = "Social Security Numbers cannot be longer than 9 digits.";
		nameTooLongError = "Full Names cannot be longer than 50 characters.";
		idTooLongError = "Staff IDs cannot be longer than 10 digits.";
		
		word_panel 		= new JPanel();
		search_panel 	= new JPanel(new GridLayout(1,4));
		button_panel 	= new JPanel();
		result_panel 	= new JPanel();
		outer_panel		= new JPanel(new BorderLayout());
		cp.setLayout(new BorderLayout());
	}

	/* The introduction message to the user. */
	public void introduction()
	{
		JLabel intro_message = new JLabel("<html> <span style=\"color:green;font-size:300%;\">" + 
				                   "<strong>Welcome to the Staff Database</strong></span><br>" +
                                 " Example usage: <br> To find all employees who are Junior " + 
				                   "Software Engineers, select \"Title\" in the <br> Staff " + 
                                 "Attribute box, enter \"Junior Software Engineer\" into the " +
                                 "value field, and click \"Search\". <br><br> ");
		word_panel.add(intro_message);
		cp.add(word_panel, BorderLayout.PAGE_START);

	}

	/* First step: The user enters in an attribute and a value 
	 * for that attribute, and is given the option to 
	 * search or to quit the program. */
	public void userSearch()
	{
		attribute_box = new AttributeBox(this);
		attribute_box.getLabel().setHorizontalAlignment(JLabel.CENTER);


		value_field = new ValueTextField("", this);
		value_field.getLabel().setHorizontalAlignment(JLabel.CENTER);

		configureSearchPanel();

		search_panel.setBorder(new EmptyBorder(10,10,10,10));

		quit_button = new QuitButton(false);
		search_button = new SearchButton(this);
		button_panel.add(quit_button);
		button_panel.add(search_button);


		outer_panel.add(search_panel, BorderLayout.PAGE_START);
		outer_panel.add(button_panel);
	}

	/* Add elements to the Search Panel. */
	public void configureSearchPanel()
	{
		search_panel.removeAll();
		search_panel.add(attribute_box.getLabel());
		search_panel.add(attribute_box.getBox());
		search_panel.add(value_field.getLabel());
		search_panel.add(value_field);
	}

	/* Display the results of a search in a table. If there are 
	 * zero matches, then display an error message to the User 
	 * requesting that they try another search. */
	public void displayResults()
	{
		result_panel.removeAll();
		matches = new MatchResultTable(attribute_box.getValue(), value_field.getValue(), this.file_io);
		
		if (matches.numMatches() == 0)
		{
			zeroMatchMessage = new JLabel("<html><span style=\"color:red;font-size:300%;\">" + 
		                       "No matched record is found in the database.</span><br>" + 
		                                  "</span><br><br><br><br><br></html>");
			result_panel.add(zeroMatchMessage);
		}
		else
			result_panel.add(matches.getScrollPane());

		cp.add(result_panel, BorderLayout.SOUTH);
		revalidate();
		repaint();
	}

	/* Main method: launch the program. */
	public static void main(String[] args) {
      // Run the GUI construction in the Event-Dispatching thread for thread-safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            SearchStaffDb window = new SearchStaffDb(); // Let the constructor do the job
         }
      });
   	}

}