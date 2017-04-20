package CREATE_STAFFDB;
import java.awt.*;      
import java.awt.event.*; 
import javax.swing.*;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * QUIT_BUTTON: Give the user the opportunity to quit the whole program. 
 * 
 * If displayWARNING is TRUE, then a warning is displayed to the user
 * that data will be lost before quitting, and they are given the option 
 * to continue working. 
 */

public class QuitButton extends JButton implements MouseListener, ActionListener {

	private boolean displayWarning;
	
	public QuitButton(boolean displayWarning)
	{
		this.displayWarning = displayWarning;
		this.setText("Quit");
		this.setFont(new Font("Arial", Font.PLAIN, 18));
		addMouseListener(this);
		addActionListener(this);
	}

	/* Highlight the button then the mouse is over it. */
	public void mouseEntered(MouseEvent event)
	{
	   this.setForeground(Color.BLUE);
	   this.setFont(new Font("Arial", Font.BOLD, 18));
	}
	
	/* De-Highlight the button when the mouse leaves. */
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
	  if (displayWarning)
	  {
		  /* The "buttons" consist of a dummy-button which does nothing ("Keep Working"), 
		   * and second Quit button which itself does not display a warning message and 
		   * quits the program immediately. */
		  Object[] Buttons = {"Keep Working", new QuitButton(false)};
		  JOptionPane.showOptionDialog(null, "<html>Warning: Your data has not been entered into the Database yet. <br>" + 
		                                     " If you quit now, your data will be lost. Are you sure you want to quit? <br>" + 
				                             "If not, click \"Keep Working\"</html>", "Warning", JOptionPane.WARNING_MESSAGE, 0, null, Buttons, Buttons[0]);
	  }
	  else
		  System.exit(0);
	}
}
