package CREATE_STAFFDB;
import java.awt.*;       
import java.awt.event.*;


/*
 * Author: Mikhail Rezazadeh April 2017
 * 
 * BACK_BUTTON: The button to return back to the first page (the page where 
 * staff data is entered by the user). 
 */

import javax.swing.JButton;

public class BackButton extends JButton implements MouseListener, ActionListener 
{

	private CreateStaffDb db;

	public BackButton(String label, CreateStaffDb db)
	{
		this.db = db;
		this.setText(label);
		this.setFont(new Font("Arial", Font.PLAIN, 18));
		addMouseListener(this);
		addActionListener(this);
	}

	public void mouseEntered(MouseEvent event)
	{
	   this.setForeground(Color.BLUE);
	   this.setFont(new Font("Arial", Font.BOLD, 18));
	}
	public void mouseExited(MouseEvent event)
	{
	   this.setForeground(Color.BLACK);
	   this.setFont(new Font("Arial", Font.PLAIN, 18));
	}
	public void mouseClicked(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}

	
	/* On click, return to the data-entry page of the GUI. */
	public void actionPerformed(ActionEvent evt)
	{
      db.userEntersFields();
	}
}
