package CREATE_STAFFDB;  
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import java.util.*;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * YEARS_BOX: a wrapper around a JCombobox for year options, 
 * with the added feature that it can be bound to a calendar view. 
 * 
 * When it is bound, any updates to the year box will also result in 
 * changes to the month view. 
 */

public class YearsBox {
	private MonthView month_view;
	private JComboBox<String> years_box;

	public YearsBox(String[] year_options)
	{
		years_box = new JComboBox<>(year_options);	
		years_box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* Get the year value from the box. */
				String new_yr_str = (String)years_box.getSelectedItem();
				int new_year = Integer.parseInt(new_yr_str);
				
				/* Get the old date from the month view. Save it because
				 * we want to save the day and month of the month view and only 
				 * want to change the year. */
				Date prev_date = month_view.getDate();
				
				/* Create a new calendar object. */
				Calendar c = new GregorianCalendar();
				c.setTime(prev_date);
				int prev_month = c.get(c.MONTH);
				int prev_day = c.get(c.DATE);
				
				/* Set the new year, but save the old day and old month. */
				c.set(Calendar.YEAR, new_year);
				c.set(Calendar.MONTH, prev_month);
				c.set(Calendar.DATE, prev_day);
				month_view.setDate(c.getTime());
			}
		});
	}

	public void bindMonthView(MonthView month_view)
	{
		this.month_view = month_view;
	}

	public JComboBox<String> getBox()
	{
		return years_box;
	}
}
