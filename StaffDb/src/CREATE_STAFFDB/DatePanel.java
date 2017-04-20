package CREATE_STAFFDB;
import java.awt.*;       

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * DATE_PANEL: this panel holds both a calendar view MONTHVIEW
 * and a year drop-down box YEARSBOX. 
 * 
 * This design is because the month view alone does not allow for simple 
 * changing between years (changing years must be done by scrolling through 
 * months). Therefore there is also a year drop-down box, and both are 
 * bound to each other. This means changes to one are reflected in 
 * the other. A user choosing (1973) in the year drop-down box will 
 * result in the month-view changing to 1973. 
 * 
 * The maximum range is set to 82 years, because employees are assumed
 * to be less than or equal to 100 years old. 
 * 
 * The Date Panel constructor takes in a most_recent Date object, 
 * which represents the latest possible date for this date panel. 
 * 
 * For the date of birth, most_recent will equal 18 years ago, 
 * because the company does not employ people who are younger than 
 * 18 years old. For the start date, the most_recent is equal to 
 * today. 
 * 
 * For either the date of birth or the start date, the earliest 
 * possible date is 82 years before the most_recent. In either case, this restricts employees to being 
 * 100 years old. 
 */
import javax.swing.*;    // Using Swing components and containers
import java.util.*;
import java.text.*;
import java.lang.*;

public class DatePanel extends JPanel {
	private YearsBox years_box;
	private MonthView month_view;
	private Calendar most_recent;
	private int maxRange;
	private JLabel label;

	
	public DatePanel(Calendar most_recent, GridLayout grid, String label)
	{
		this.label = new JLabel(label);
		this.label.setHorizontalAlignment(JLabel.CENTER);
		maxRange = 82;
		this.most_recent = most_recent;

		/* Get a String array corresponding to year options for the drop-down box */
		String[] year_options = initializeYearOptions(most_recent);
		
		/*Create a YEARS_BOX object with the array of years. */
		this.years_box = new YearsBox(year_options);
		
		/* Create a calendar month view with the most_recent date and the years. */
		this.month_view = new MonthView(most_recent, years_box.getBox());
		
		/* When the user chooses a year in the year-box, the month view will 
		 * reflec this change. */
		this.years_box.bindMonthView(this.month_view);

		this.add(month_view);
		this.add(years_box.getBox());
	}
	
	/* Return an array of year options for the drop-down box. 
	 * This corresponds to the most_recent year, most_recent year - 1, 
	 * most_recent year - 2, ... , most_recent year - 82. */
	public String[] initializeYearOptions(Calendar most_recent)
	{
		String[] year_options = new String[this.maxRange + 1];
		for (int i = 0; i <= this.maxRange; i++)
		{
			year_options[i] = Integer.toString(most_recent.get(Calendar.YEAR) - i);
		}
		return year_options;
	}

	public JLabel getLabel()
	{
		return label;
	}

	public MonthView getMonthView()
	{
		return month_view;
	}
}
