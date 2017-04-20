package SEARCH_STAFFDB;    
import java.awt.event.*; 
import javax.swing.*;  

/*
 * Author: Mikhail Rezazadeh 2017
 * 
 * ATTRIBUTEBOX: A wrapper for a JComboBox
 * which contains options for Staff attributes for a 
 * Database search. There is an anonymous inner class 
 * which implements an ActionListener. 
 * 
 * When a selection is made, the label next to the value 
 * field in the GUI will change to reflect the attribute
 * selection. In addition, the maximum allowable characters
 * will change if, for instance, "Social Security Number" is 
 * selected (in which case the maximum allowable characters 
 * will be set to 9. 
 */

public class AttributeBox
{
	private JComboBox<String> options;
	private JLabel label;
	
	/* The JFrame needs to be final for use inside the anonymous inner 
	 * class. */
	private final SearchStaffDb fwindow; 

	public AttributeBox(SearchStaffDb window)
	{
		this.fwindow = window;
		this.label = new JLabel("Staff Attribute");
		String[] attributes = {"Staff ID", 
                            "Full Name", 
                            "Date of Birth",
                            "Social Security Number", 
                            "Start Date", 
                            "Title", 
                            "Status",
                            "ALL"};
        options = new JComboBox<>(attributes);
        
        // Allocate an anonymous instance of an anonymous inner class that
        //  implements ActionListener as ActionEvent listener
        options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String box_value = getValue();
				String new_label = null;
				if (box_value.equals("Staff ID"))
					fwindow.value_field.setDocument(new JTextFieldLimit(10,fwindow.idTooLongError));
				else if (box_value.equals("Social Security Number"))
				{
					new_label = "Enter the SSN here:";
					fwindow.value_field.setDocument(new JTextFieldLimit(9,fwindow.ssnTooLongError));
				}
				else if (box_value.equals("Full Name"))
					fwindow.value_field.setDocument(new JTextFieldLimit(50,fwindow.nameTooLongError));
				else if (box_value.equals("ALL"))
					new_label = "";
				else if (box_value.equals("Date of Birth") || box_value.equals("Start Date"))
					new_label = "<html> Enter the " + box_value + " <br> here (mm/dd/yyyy):</html>";
				else
					new_label = "Enter the " + box_value + " here:";
				fwindow.value_field.setLabel(new_label);
				fwindow.configureSearchPanel();
				fwindow.revalidate();
				fwindow.repaint();
			}
		});
	}
	
	public JLabel getLabel()
	{
		return label;
	}

	public String getValue()
	{
		return (String)options.getSelectedItem();
	}

	public JComboBox<String> getBox()
	{
		return options;
	}
}