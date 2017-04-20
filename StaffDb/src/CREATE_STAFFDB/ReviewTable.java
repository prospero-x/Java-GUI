package CREATE_STAFFDB;
import COMMON_STAFFDB.*;
import java.awt.*;      
import javax.swing.*;    
import java.text.*;


/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * REVIEW_TABLE: A table to display on page 2 of the GUI
 * for the user to review the entered data before submitting. 
 */

public class ReviewTable
{

  private JTable table;
  private int numRows;
  private int numCols;

	public ReviewTable(Staff new_staff)
	{
    numRows = 7;
    numCols = 2;
	String[] columnNames = {"Employee Attribute" , "Value"};
	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	
	/* Grab the data from the GUI */
	String[][] data = {
      {"Staff ID", new_staff.getID()},
      {"Full Name", new_staff.getFullName()},
      {"Date of Birth", new_staff.getBirthDate()},
      {"Social Security Number", new_staff.getSSN()},
      {"Start Date", new_staff.getStartDate()},
      {"Title", new_staff.getTitle()},
      {"Status", new_staff.getStatus()}
    };
    table = new JTable(data, columnNames);
    configColumnWidth();
    configRowHeight();
    table.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
    table.setGridColor(Color.BLACK);
  }

  void configRowHeight()
  {
    for (int i = 0; i < numRows; i++)
      table.setRowHeight(i,30);
  }

  void configColumnWidth()
  {
    for (int i = 0; i < numCols; i++)
      table.getColumnModel().getColumn(i).setMinWidth(200);
  }

	public JTable getTable()
  {
    return table;
  }
}
