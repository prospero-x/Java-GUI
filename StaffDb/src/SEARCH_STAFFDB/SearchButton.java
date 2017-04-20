package SEARCH_STAFFDB;
import java.awt.*;       
import java.awt.event.*; 
import javax.swing.*;    

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * SEARCHBUTTON: a button that initiates a search. 
 * Highlighted when the mouse hovers over it. 
 */

public class SearchButton extends JButton implements MouseListener,
ActionListener
{
  private SearchStaffDb window;

	public SearchButton(SearchStaffDb window)
	{
    this.window = window;
		this.setText("Search");
		this.setFont(new Font("Arial", Font.PLAIN, 18));
		addMouseListener(this);
		addActionListener(this);
	}

	/* Highlight when the mouse moves over */
	public void mouseEntered(MouseEvent event)
	{
	   this.setForeground(Color.BLUE);
	   this.setFont(new Font("Arial", Font.BOLD, 18));
	}
	
	/* un-highlight when the mouse exits. */
	public void mouseExited(MouseEvent event)
	{
	   this.setForeground(Color.BLACK);
	   this.setFont(new Font("Arial", Font.PLAIN, 18));
	}
	public void mouseClicked(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}

	
	/* When the button is clicked... */
	public void actionPerformed(ActionEvent evt)
	{
     window.displayResults();
	}
}