package CREATE_STAFFDB;
import SEARCH_STAFFDB.*;

import java.awt.*;       
import java.awt.event.*; 
import javax.swing.*;    


/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * FULL_NAME_FIELD: a text field for the full name, with 
 * the added features that it accepts a maximum of 50 characters, 
 * and it highlights when the mouse is over it  
 *
 */

public class FullNameField extends JTextField implements MouseListener
{
	private JLabel label;

	public FullNameField(String initial)
	{
		label = new JLabel("Full Name: ");
		label.setHorizontalAlignment(JLabel.CENTER);
		this.setEditable(true);
		
		/* Prepare a warning message to the user if they attempt to input more than 50 characters. */
		this.setDocument(new JTextFieldLimit(50,"Full Names cannot be longer than 50 characters."));
		this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		addMouseListener(this);
	}

	public JLabel getLabel()
	{
		return label;
	}

	/* Highlight the box when the mouse is over it */
	public void mouseEntered(MouseEvent event)
	{
	   this.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.blue));
	}
	
	/* de-highlight the box when the mouse leaves. */
	public void mouseExited(MouseEvent event)
	{
	   this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
	}
	public void mouseClicked(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
}
