package SEARCH_STAFFDB;
import COMMON_STAFFDB.*;
import java.awt.*;       
import javax.swing.table.*;
import javax.swing.*;   

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * MATCHRESULTTABLE: A scrollable, interavtive table 
 * which displays the results of a user search. If there
 * are more features than can fit inside the window, then the 
 * user can scroll through. 
 * 
 * In addition, the user can click on a column header 
 * and sort (in ascending or descending order) the data 
 * according to that column. 
 */

public class MatchResultTable
{

  private JTable table;
  private JScrollPane scroll_pane;
  private StaffAttribute attribute;
  private int numRows;
  private int numCols;

	public MatchResultTable(String attribute, String value, FileIO database)
	{
    
    numCols = 7;
    this.attribute = new StaffAttribute(attribute);
    
    /* Get the matches from the database */
    java.util.List<Staff> matches = database.getMatches(this.attribute, value);
    
    /* ONLY create a table if there were more than zero matches! */
    if ((numRows = matches.size()) > 0)
    {
    	/* Make a model according to the data. This model contains 
    	 * methods for sorting.  */
	    TableModel tableModel = new StaffTableModel(matches);
	    
	    /* Make a JTable according to the above model. */
	    table = new JTable(tableModel);
	    
	    /* Make the JTable sortable. */
	    table.setAutoCreateRowSorter(true);
	    configColumnWidth();
	    configRowHeight();
	    table.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
	    table.setGridColor(Color.BLACK);
	

	    table.setPreferredScrollableViewportSize(new Dimension(980,250));
	    table.setFillsViewportHeight(true);
	    
	    scroll_pane = new JScrollPane(table);
	    scroll_pane.setVisible(true);
    }
  }

  void configRowHeight()
  {
    for (int i = 0; i < numRows; i++)
      table.setRowHeight(i,30);
  }


  void configColumnWidth()
  {
    int width;
    for (int i = 0; i < numCols; i++)
    {
      if (i == 1) // The name column can be at most 50 characters wide. 
        width = 330;
      else if (i == 5) // The title column. Wide to accomadate long title values 
        width = 160;
      else if (i == 3) // The ssn column. Wide to accomadate "Social Security Number"
        width = 150;
      else 
        width = 85; // All else exactly 85 
  
      table.getColumnModel().getColumn(i).setMinWidth(width);
    }
  }

  public JTable getTable()
  {
    return table;
  }
	
  public int numMatches()
  {
	  return numRows;
  }
	
 /* The scroll pane is what will be added to the window panels. */
  public JScrollPane getScrollPane()
  {
    return scroll_pane;
  }
}