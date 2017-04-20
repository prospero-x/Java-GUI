package CREATE_STAFFDB;     
import java.awt.event.*; 
import javax.swing.*;    
import java.util.*;
import java.text.*;
import org.jdesktop.swingx.JXDatePicker; 

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * MONTH_VIEW: a calendar view for easy-to-use date picking. 
 * 
 */

public class MonthView extends JXDatePicker implements ActionListener {

	private JComboBox<String> years_box;

	public MonthView(Calendar most_recent, JComboBox<String> years_box)
	{
		this.setFormats(new SimpleDateFormat("MMM dd yyyy"));
		
		/* Set the date to that indicated by MOST_RECENT */
		this.setDate(most_recent.getTime());
		
		/* MOST_RECENT is the latest possible date for this field, so set it as an upper bound. */
		this.getMonthView().setUpperBound(most_recent.getTime());
		most_recent.add(Calendar.YEAR, -82);
		
		/* MOST_RECENT minus 82 always means the employee is 100 years old (assuming 18 years old 
		 * before starting at the company). */
		this.getMonthView().setLowerBound(most_recent.getTime());

		this.years_box = years_box;

		addActionListener(this);
	}

	/* Just as the month view is bound to the years_box, the years_box is bound to the month view. 
	 * Every time a date is selected on the month view, the years_box is updated. So if the user
	 * (for some reason) uses the month view to scroll through so many months that the year in the
	 * month view changes, the years-box will reflect that change. */
	@Override 
	public void actionPerformed(ActionEvent evt)
	{
		Calendar c = this.getMonthView().getCalendar();
		years_box.setSelectedItem(Integer.toString(c.get(c.YEAR)));
	}

	public Date dateValue()
	{
		return this.getDate();
	}
}
