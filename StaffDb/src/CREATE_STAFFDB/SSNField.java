package CREATE_STAFFDB;
import java.awt.*;      
import java.awt.event.*; 
import javax.swing.*;  

import SEARCH_STAFFDB.JTextFieldLimit;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * SSNFIELD: a text field with the additional properties that
 * it's highlighted when the mouse moves over it, and 
 * the input is restricted to at most 9 digits. 
 */

public class SSNField extends JTextField implements MouseListener
{
	private JLabel label;

	public SSNField(String initial)
	{
		label = new JLabel("<html>Social Security Number <span style=\"color:red;\">" + 
						 "(*Required) </span>:</html>");
		label.setHorizontalAlignment(JLabel.CENTER);
		this.setEditable(true);
		this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		this.setDocument(new JTextFieldLimit(9,"SocialSecurityNumbers cannot be longer than 9 digits."));
		addMouseListener(this);
	}

	public JLabel getLabel()
	{
		return label;
	}


	/* Highlight the box when the mouse passes over it. */
	public void mouseEntered(MouseEvent event)
	{
	   this.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.blue));
	}
	
	/* Un-highlight the box when the mouse exits. */
	public void mouseExited(MouseEvent event)
	{
	   this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
	}
	public void mouseClicked(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
}
