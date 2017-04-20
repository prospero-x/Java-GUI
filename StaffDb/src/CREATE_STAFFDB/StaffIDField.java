package CREATE_STAFFDB;
import java.awt.*;       
import java.awt.event.*; 
import javax.swing.*;    

import SEARCH_STAFFDB.JTextFieldLimit;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * STAFFIDFIELD: a text field with the added features that 
 * the input is restricted to at most 10 characters, 
 * and the box is highlighted when the mouse moves over it. 
 */


public class StaffIDField extends JTextField implements MouseListener
{

	private JLabel label;

	public StaffIDField(String initial)
	{
		label = new JLabel("<html>Staff ID <span style=\"color:" + 
			                "red;\"> (*Required) </span>:</html>");
		label.setHorizontalAlignment(JLabel.CENTER);
		this.setEditable(true);
		this.setBorder(BorderFactory.createMatteBorder(1,1,0,1, Color.black));
		this.setDocument(new JTextFieldLimit(10,"Staff IDs cannot be longer than 10 digits."));
		addMouseListener(this);
	}

	public JLabel getLabel()
	{
		return label;
	}


	/* Highlight when the mouse moves over it. */
	public void mouseEntered(MouseEvent event)
	{
	   this.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.blue));
	}
	
	/* Un-Highlight when the mouse leaves. */
	public void mouseExited(MouseEvent event)
	{
	   this.setBorder(BorderFactory.createMatteBorder(1,1,0,1, Color.black));
	}
	public void mouseClicked(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
}
