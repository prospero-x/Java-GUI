package CREATE_STAFFDB;
import javax.swing.*;   

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * STATUX_BOX: a simple wrapper around a jcombo box which 
 * offers the options "Active, "Retired", and "Resigned"
 */

public class StatusBox {
	private JComboBox<String> status_options;
	private JLabel label;

	public StatusBox()
	{
		label = new JLabel("Status: ");
		label.setHorizontalAlignment(JLabel.CENTER);
		String[] options = {"Active", "Retired", "Resigned"};
        status_options = new JComboBox<>(options);
	}
	public JLabel getLabel()
	{
		return label;
	}

	public String getValue()
	{
		return (String)status_options.getSelectedItem();
	}

	public JComboBox<String> getBox()
	{
		return status_options;
	}
}
