package SEARCH_STAFFDB;
import COMMON_STAFFDB.*;
import javax.swing.table.*;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * STAFF_TABLE_MODEL: a data model which allows for sorting
 * by the user of data columns. 
 */

public class StaffTableModel extends AbstractTableModel {

	private static final int STAFF_NO_COLUMN		= 0;
	private static final int FULL_NAME_COLUMN		= 1;
	private static final int DATE_OF_BIRTH_COLUMN	= 2;
	private static final int SSN_COLUMN				= 3;
	private static final int START_DATE_COLUMN 		= 4;
	private static final int TITLE_COLUMN			= 5;
	private static final int STATUS_COLUMN			= 6;

	private String[] columnNames = {"Staff ID", "Full Name", "Date of Birth", 
                            "Social Security Number", "Start Date", "Title",
                            "Status"};

	/* The table will be a list of STAFF objects */
    private java.util.List<Staff> listStaff;

    /* Create the table model using data that has already been created. */
	public StaffTableModel(java.util.List<Staff> listStaff) {
		this.listStaff = listStaff;
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return listStaff.size();
	}

	@Override 
	public String getColumnName(int column_index)
	{
		return columnNames[column_index];
	}

	/* This is where the maginc happens. It sorts the 
	 * Column! The Class is left ambiguous for future 
	 * scaling. */
	@Override
	public Class<?> getColumnClass(int column_index)
	{
		if(listStaff.isEmpty())
			return Object.class;
		return getValueAt(0, column_index).getClass();
	}

	@Override
	public Object getValueAt(int row_index, int column_index) 
	{
		Staff staff = listStaff.get(row_index);
		Object rv = null;

		switch (column_index)
		{
			case STAFF_NO_COLUMN:
				rv = staff.getID();
				break;
			case FULL_NAME_COLUMN:
				rv = staff.getFullName();
				break;
			case DATE_OF_BIRTH_COLUMN:
				rv = staff.getBirthDate();
				break;
			case SSN_COLUMN:
				rv = staff.getSSN();
				break;
			case START_DATE_COLUMN:
				rv = staff.getStartDate();
				break;
			case TITLE_COLUMN:
				rv = staff.getTitle();
				break;
			case STATUS_COLUMN:
				rv = staff.getStatus();
				break;
			default:
				throw new IllegalArgumentException("Invalid column index");
		}

		return rv;
	}
}
