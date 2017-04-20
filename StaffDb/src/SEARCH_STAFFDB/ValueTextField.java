package SEARCH_STAFFDB;
import java.awt.*;       
import java.awt.event.*; 
import javax.swing.*;    

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * VALUE_TEXT_FIELD: an extendion of a text field which 
 * restricts the maximum number of characters to a value 
 * corresponding to the selection of the staff attribute. 
 * 
 * The maximum characters is initialized to be 10 because 
 * Staff ID is the initial attribute. 
 * 
 * The box highlights when the mouse hovers over it. 
 */


public class ValueTextField extends JTextField implements MouseListener
{
	private JLabel label;

	public ValueTextField(String initial, SearchStaffDb window)
	{
		label = new JLabel("Enter the " + window.attribute_box.getValue() + " here:");
		this.setEditable(true);
		this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		this.setDocument(new JTextFieldLimit(10,window.idTooLongError));
		addMouseListener(this);
	}

	public void setLabel(String new_label)
	{
		label = new JLabel(new_label);
		label.setHorizontalAlignment(JLabel.CENTER);
	}

	public JLabel getLabel()
	{
		return label;
	}

	public String getValue()
	{
		return this.getText();
	}

	/* Highlight when the mouse hovers over the box. */
	public void mouseEntered(MouseEvent event)
	{
	   this.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.blue));
	}
	
	/* Un-highlight when the mouse goes away. */
	public void mouseExited(MouseEvent event)
	{
	   this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
	}
	public void mouseClicked(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
}
