package CREATE_STAFFDB;
import javax.swing.*;    // Using Swing components and containers

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * TITLEBOX: a simple wrapper around a JComboBox 
 * which offers options for staff position. 
 * 
 * The default value is empty because the user 
 * does not have to enter data for this field. 
 */

public class TitleBox {
	private JComboBox<String> titles;
	private JLabel label;

	public TitleBox()
	{
		this.label = new JLabel("Title: ");
		this.label.setHorizontalAlignment(JLabel.CENTER);
		String[] positions = {"", "Junior Software Engineer", 
                            "Senior Software Engineer", 
                            "Network Engineer",
                            "Software Test Designer", 
                            "System Administrator", 
                            "Security Consultant", 
                            "Project Manager",
                            "Project Supervisor", 
                            "Senior Manager", 
                            "Vice President", 
                            "President"};
        titles = new JComboBox<>(positions);
	}
	public JLabel getLabel()
	{
		return label;
	}

	public String getValue()
	{
		return (String)titles.getSelectedItem();
	}

	public JComboBox<String> getBox()
	{
		return titles;
	}
}
