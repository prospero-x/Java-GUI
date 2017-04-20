package CREATE_STAFFDB;
import COMMON_STAFFDB.*;
import java.awt.*;       
import java.awt.event.*; 
import javax.swing.*;    
import java.util.*;



import javax.swing.JButton;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * CONTINUE_BUTTON: to be to change from the data-entry page to 
 * the data-review page.
 * 
 * The data from the entry boxes is saved and then presented 
 * to the user in a table for review. 
 */

public class ContinueButton extends JButton implements MouseListener,
		ActionListener {

	private CreateStaffDb db;

	public ContinueButton(CreateStaffDb db)
	{
		this.db = db;
		this.setText("Continue");
		this.setFont(new Font("Arial", Font.PLAIN, 18));
		addMouseListener(this);
		addActionListener(this);
	}

	/* Grab the values from the field boxes and returns a STAFF object. */
	public Staff createStaff(CreateStaffDb db)
	{
		Staff staff = new Staff();
		staff.setID(db.staff_id.getText());
		staff.setFullName(db.full_name.getText());
		staff.setBirthDate(db.date_of_birth.getMonthView().dateValue());
		staff.setSSN(db.ssn.getText());
		staff.setStartDate(db.start_date.getMonthView().dateValue());
		staff.setTitle(db.title.getValue());
		staff.setStatus(db.status.getValue());

		return staff;
	}

	/* Mouse Entered: highlight the box when the mouse is over it. */
	public void mouseEntered(MouseEvent event)
	{
	   this.setForeground(Color.BLUE);
	   this.setFont(new Font("Arial", Font.BOLD, 18));
	}
	
	/* Un-highlight the box when the mouse leaves. */
	public void mouseExited(MouseEvent event)
	{
	   this.setForeground(Color.BLACK);
	   this.setFont(new Font("Arial", Font.PLAIN, 18));
	}
	public void mouseClicked(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}

	
	/* When the user clicks the button... */
	public void actionPerformed(ActionEvent evt)
	{
		Date b_day = db.getDateOfBirth();
        Date s_day = db.getStartDate();
        Calendar c = new GregorianCalendar();
        c.setTime(s_day);
        c.add(Calendar.YEAR, -18);
        /* If the user entered a start date less than 18 years after the person's birth date, 
         * this implies that the new staff member is less than 18 years old. */
        if (c.getTime().before(b_day)) {
           JOptionPane.showMessageDialog(null, 
              "Hang on there! Valid Employee age is 18+.\n Please correct the Start Date field to be at least \n 18 years after the Date of Birth." , "Warning: Invalid Start Date", 
              JOptionPane.ERROR_MESSAGE);
        }

        /* Create a new STAFF object. */
        Staff new_staff = createStaff(db);
        if (Long.parseLong(new_staff.getID()) == 0) 
        {
        	 JOptionPane.showMessageDialog(null, 
              "Whoa there champ! Employees cannot be entered into the Database\n " + 
              " without a valid id number. \n" + 
              "Please go back and complete the ID field.", "Warning: Invalid Staff ID", 
              JOptionPane.ERROR_MESSAGE);
        }
        else if (Long.parseLong(new_staff.getID()) == -1)
        {
           JOptionPane.showMessageDialog(null,
              "Not so fast! The Staff ID Must be a numeric value. Please go back and correct this field.", "Warning: Incorrect Format",
              JOptionPane.ERROR_MESSAGE);
           return;
        }
        else if (Long.parseLong(new_staff.getID()) == -2)
        {
           JOptionPane.showMessageDialog(null,
              "Hold it right there! The Staff ID must be exactly 10 digits. \nPlease go back and correct this field.", "Warning: Invalid Value",
              JOptionPane.ERROR_MESSAGE);
           return;
        }
        else if (Integer.parseInt(new_staff.getSSN()) == 0)
        {
        	JOptionPane.showMessageDialog(null, 
              "Freeze! Employees cannot be entered into the Database\n " + 
              " without a social security number. \n" + 
              "Please go back and complete the Social Security Number field.", 
              "Warning: Missing Social Secuity Number", 
              JOptionPane.ERROR_MESSAGE);
        }
        else if (Integer.parseInt(new_staff.getSSN()) == -1)
        {
           JOptionPane.showMessageDialog(null,
              "Whoops! The Social Securty Number Must be a numeric value. \nPlease go back and correct this field.", "Warning: Incorrect Format",
              JOptionPane.ERROR_MESSAGE);
           return;
        }
        else if (Integer.parseInt(new_staff.getSSN()) == -2)
        {
           JOptionPane.showMessageDialog(null,
              "Slow down! Social Securty Numbers must be exactly 9 digits. \nPlease go back and correct this field.", "Warning: Invalid Value",
              JOptionPane.ERROR_MESSAGE);
           return;
        }
        else
        {
        	/* If we get to this point, we verified that the new STAFF object is valid.
        	 * Set the GUI's staff variable to be equal to it, and then review the data. */
           db.staff = new_staff;
           db.reviewEnteredData();
        }
	}
}
