package CREATE_STAFFDB;
import java.awt.*;      
import java.awt.event.*; 
import javax.swing.JButton;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * SUBMIT_BUTTON: a button which calls the GUI's trySubmit() function, 
 * and has the feature that it's highlighted when the mouse passes over
 * it. 
 */

public class SubmitButton extends JButton implements MouseListener,
		ActionListener {
	private CreateStaffDb db;

	public SubmitButton(CreateStaffDb db)
	{
		this.db = db;
		this.setText("Submit");
		this.setFont(new Font("Arial", Font.PLAIN, 18));
		addMouseListener(this);
		addActionListener(this);
	}

	/* Highlight when the mouse passes over */
	public void mouseEntered(MouseEvent event)
	{
	   this.setForeground(Color.BLUE);
	   this.setFont(new Font("Arial", Font.BOLD, 18));
	}
	
	/* Unhighlight when the mouse leaves. */
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
      db.trySubmit();
	}
}
