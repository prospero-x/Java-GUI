package CREATE_STAFFDB;
import COMMON_STAFFDB.*;
import java.awt.*;      
import javax.swing.*;   
import java.util.*;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * CREATE_STAFF_DB: The main GUI class. This implements the window that the 
 * user will interact with, and this is the class that calls main. 
 */

public class CreateStaffDb extends JFrame {
	   public StaffIDField staff_id; 		// Text Field where the user enters the Staff ID number.
	   public FullNameField full_name;  	// Text Field where the user enters the Full Name. 
	   public DatePanel date_of_birth;      // Calendar and year drop-down box for date of birth.
	   public DatePanel start_date;			// Calendar and year drop-down box for start date.
	   public SSNField ssn; 				// Text field where the user enters the SSN.
	   public TitleBox title;  				// Combo-Box where user enters new member's title.
	   public StatusBox status; 			// Combo-box where user enters new member's status
	   public Staff staff;					// The GUI-wide staff object. 
	   private JPanel word_panel;			// Holds messages and instructions to the user
	   private JPanel data_panel;			// Holds fields for user to enter data 
	   private JPanel review_panel;			// Holds the table for user to review entered data. 
	   private JPanel warning_panel;		// Warning message before user clicks "submit"
	   private JPanel button_panel;			// Holds buttons on the bottom of the window. 
	   private JPanel big_panel;			// Holds everything. 
	   private JLabel intro_message;		// Introduction at the GUI-launch
	   private JLabel review_message;		// Introduction to the review page
	   private JLabel review_warning;		// Warning to the user that submissions are final 
	   private ContinueButton continue_btn;    // Button from the data-entry to data-review page
	   private SubmitButton submit_btn;			// button to submit to the data base
	   private QuitButton early_quit_btn;		// quit before data has been entered. 
	   private BackButton back_btn;			// Go back to the data-entry page. 
	   private Container cp;			//Container which holds all panels. 
	   private FileIO file_io;			// For database reading and writing. 
	 
	   public CreateStaffDb() {
		  
	      cp = getContentPane();
	      initializePanels();
	      userEntersFields();
	      cp.setLayout(new BorderLayout());
	      cp.add(big_panel, BorderLayout.CENTER);
	       
	      /* Quit the program if the User closes the window. */
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	      setTitle("Create New Staff Member"); 	
	      setSize(700, 500);  

	      
	      /* Position the window in the middle of the screen. */
	      Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	      int x = (int)((dimension.getWidth() - getWidth())/2);
	      int y = (int)((dimension.getHeight() - getHeight())/2);
	      setLocation(x,y);
	      
	      /* Set the window as visible. */
	      setVisible(true); 
	   }

	   public Date getDateOfBirth()
	   {
	      return date_of_birth.getMonthView().dateValue();
	   }

	   public Date getStartDate()
	   {
	      return start_date.getMonthView().dateValue();
	   }

	   public void initializePanels()
	   {
	      warning_panel = new JPanel();
	      review_panel  = new JPanel();
	      word_panel    = new JPanel();
	      button_panel  = new JPanel();
	      GridLayout grid = new GridLayout(7,2);
	      data_panel    = new JPanel(grid);
	      big_panel     = new JPanel(new BorderLayout());
	   }

	   public void clearPanels()
	   {
	      word_panel.removeAll();
	      data_panel.removeAll();
	      button_panel.removeAll();
	      review_panel.removeAll();
	      big_panel.removeAll();
	   }

	   /* Draws the data-entry page (page 1) of the GUI. This is where 
	    * The user will enter in the fields of the proposed new staff member. 
	    * 
	    * On this page, the user can either continue, or quit the program. 
	    */
	   public void userEntersFields()
	   {
	      clearPanels();

	      intro_message = new JLabel("<html><span style=\"color:green;font-size:300%;\">" + 
	    		                     "<strong>Welcome to the Staff Database</strong></span><br>" +
	                                 " Using this window, you can add a " +
	                                 "new employee to the Database. <br><br> " +
	                                 "Please fill out the fields below:</html>");
	      word_panel.add(intro_message);

	      /* Create and add the staff id field. */
	      staff_id = new StaffIDField("");
	      data_panel.add(staff_id.getLabel());
	      data_panel.add(staff_id);

	      /* Create and add the full name field. */
	      full_name = new FullNameField("");
	      data_panel.add(full_name.getLabel());
	      data_panel.add(full_name);

	      /* Create and add the date panel, which includes both a calendar 
	       * view and a year drop box. Immediately set the date on the 
	       * calendar to be the most recent possible date of birth 
	       * for a new employee: 18 years before today. */
	      Calendar valid_dob = Calendar.getInstance();
	      valid_dob.add(Calendar.YEAR, -18);
	      date_of_birth = new DatePanel(valid_dob, 
	                  new GridLayout(1,2), "Date of Birth: ");
	      data_panel.add(date_of_birth.getLabel());
	      data_panel.add(date_of_birth);

	      /* Create and add the SSN field. */
	      ssn = new SSNField("");
	      data_panel.add(ssn.getLabel());
	      data_panel.add(ssn);

	      /* Create and add the start date panel, holding a calendar 
	       * view and a drop-down box of years. Set the date today. */
	      start_date = new DatePanel(Calendar.getInstance(),
	               new GridLayout(1,2), "Start Date: ");
	      data_panel.add(start_date.getLabel());
	      data_panel.add(start_date);

	      /* Create and add a title field box. Initialize it to 
	       * empty, because the user might not want to enter
	       * a value for this field. */
	      title = new TitleBox();
	      data_panel.add(title.getLabel());
	      data_panel.add(title.getBox());

	      /* Create and add a status field box. It will be 
	       * initialized to "Active" */
	      status = new StatusBox();
	      data_panel.add(status.getLabel());
	      data_panel.add(status.getBox());
	      
	      /* Give the user the opportunity to quit early. TRUE
	       * is passed as an argument to display a warning to the user
	       * that when they exit, their data will not be submitted to the
	       * Database. */
	      early_quit_btn = new QuitButton(true);
	      
	      /*Button to continue to the data-review page. */
	      continue_btn = new ContinueButton(this);
	      button_panel.add(early_quit_btn);
	      button_panel.add(continue_btn);
	     
	      big_panel.add(word_panel, BorderLayout.NORTH);
	      big_panel.add(data_panel, BorderLayout.CENTER);
	      big_panel.add(button_panel, BorderLayout.SOUTH);

	      /* Redraw the window. */
	      revalidate();
	      repaint();
	   }

	   /*Data-review page of the GUI (page 2). This is where the user sees what 
	    * data is about to be entered into the database. This page is only arrived 
	    * at if the StaffID has been entered and is a 10-digit numeric value, and if 
	    * the social security number has been entered and is a 9-digit numeric value. 
	    * 
	    * These fields are required because they are how staff members in the database
	    * are compared. The program uses these fields to verify that the new employee 
	    * is not already in the database. 
	    */
	   public void reviewEnteredData()
	   {
		   
		  /* We don't want to remove everything here, which is why we don't call 
		   * clearPanels(). */
	      word_panel.remove(intro_message);
	      big_panel.remove(data_panel);
	      button_panel.remove(continue_btn);
	      big_panel.remove(button_panel);


	      setTitle("Review Submission");
	      
	      /* Create and set a message to the user at the top of the window. */
	      review_message = new JLabel("<html>Great! Let's take a look at the data you entered:<br><br><br><br><br><br></html> ");
	      word_panel.add(review_message);
	      
	      /* Create a review table of fields and attribute of the GUI-wide staff variable, 
	       * which was set when the user clicked "Continue". */
	      ReviewTable rt = new ReviewTable(this.staff);
	      review_panel.add(rt.getTable());
	      big_panel.add(review_panel, BorderLayout.CENTER);

	      /* Warn the user that submissions are final. */
	      review_warning = new JLabel("<html>Are you sure these fields are correct? <strong> " +
	    		                      "Entries into the Database are final. <br></strong> Once " + 
	    		                       "you have reviewed the material, click <strong>Submit" + 
	    		                       "</strong> below.</html>");
	      warning_panel.add(review_warning);
	      
	      
	      big_panel.add(warning_panel, BorderLayout.SOUTH);
	      button_panel.setLayout(new FlowLayout());
	      
	      
	      submit_btn = new SubmitButton(this);
	      back_btn = new BackButton("Go Back",this);
	      button_panel.add(early_quit_btn);
	      button_panel.add(back_btn);
	      button_panel.add(submit_btn);
	      cp.add(button_panel, BorderLayout.SOUTH);
	      
	      /* Redraw the window. */
	      revalidate();
	      repaint();

	   }

	   /*
	    * This function is called when the user clicks "Submit". 
	    * It opens the database and reads through every employee, 
	    * checking for conflicts. 
	    * 
	    * If conflicts arise, a message is displayed to the user 
	    * and they are asked to go back to the data-entry page. 
	    * 
	    * If there are no conflicts, the new staff member is appended
	    * to the end of the database and the user is given a success message. 
	    */
	   public void trySubmit()
	   {
	      clearPanels();
	      setTitle("Submission Result");
	      
	      /* Open the Database for reading and writing. */
	      file_io = new FileIO();
	      int rv = file_io.checkConflicts(this.staff);
	      JLabel message;
	      JButton button;
	      if (rv == 1)
	      {
	         message = new JLabel("<html><strong><span style=\"color:red;\">Error: </span></strong> there is already an employee in the Database with Staff ID <br>" + 
	                                                staff.getID() + ". Please go back and change this " +
	                                                "field.</html>");
	         button = back_btn;
	      }

	      else if (rv == 2)
	      {
	         message = new JLabel ("<html><strong><span style=\"color:red;\">Error: </span></strong> there is already an employee in the Database with Social <br>" + 
	                                                "Securty Number " + staff.getSSN() + ". Please go back and change this " +
	                                                "field.</html>");
	         button = back_btn;
	      }

	      else
	      {
	         file_io.submitNewStaff(this.staff);
	         message = new JLabel("<html><strong><span style=\"color:green;\">Success! </span></strong> <br> <br> <br>" + 
	                                                " Thank you for using the Staff Database, and have a nice day! </html>");
	         button = new QuitButton(false);
	         button_panel.add(new BackButton("Add Another Staff", this));
	      }
	      word_panel.add(message);
	      big_panel.add(word_panel, BorderLayout.CENTER);
	      button_panel.add(button);
	      
	      /* Redraw the windwow. */
	      revalidate();
	      repaint();
	   }

	   public static void main(String[] args) {
		   
	      /* Run the GUI construction in the Event-Dispatching thread for thread-safety */
	      SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            CreateStaffDb window = new CreateStaffDb();
	         }
	      });
	   }
}
