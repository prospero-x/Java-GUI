package COMMON_STAFFDB;
import java.util.*;
import java.text.*;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * STAFF: a class which contains all the data corredponding to 
 * a single staff member.  
 */

public class Staff
	{
		private String id;					//A long because IDs can be 10 digits, exceeding MAX_INT
		private String full_name;
		private String birth_date;
		private String ssn;
		private String start_date;
		private String title;
		private String status;
		private int validSSNLength;
		private int validIDLength;

		public Staff()
		{
			id = null;
			full_name = null;
			birth_date = null;
			ssn = null;
			start_date = null;
			title = null;
			status = null;
			validSSNLength = 9;
			validIDLength = 10;
		}

		/* The GUI accepts strings, which will be converted to integers. 
		 * SETID returns 0 on success ONLY if the string corresponds to 
		 * numeric value, AND if it's exactly 10 digits. */
		public void setID(String new_id)
		{
			if (new_id.length() == 0)
				id = "0";
			else if (!isNumber(new_id))
				id = "-1";
			else if (new_id.length() != validIDLength)
				id = "-2";
			else
				id = new_id;
		}
		public String getID()
		{
			return id;
		}

		public void setFullName(String new_full_name)
		{
			full_name = new_full_name;
		}

		public String getFullName()
		{
			return full_name;
		}

		public void setBirthDate(Date new_birth_date)
		{
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String date_str = df.format(new_birth_date);
			birth_date = date_str;
		}
		public String getBirthDate()
		{
			return birth_date;
		}
		
		/* The GUI accepts strings, which will be converted to integers. 
		 * SETID returns 0 on success ONLY if the string corresponds to 
		 * numeric value, AND if it's exactly 9 digits. */
		public void setSSN(String new_ssn)
		{
			if (new_ssn.length() == 0)
				ssn = "0";
			else if (!isNumber(new_ssn)) 
				ssn = "-1";
			else if (new_ssn.length() != validSSNLength)
				ssn = "-2";
			else
				ssn = new_ssn;
		}
		public String getSSN()
		{
			return ssn;
		}
		public void setStartDate(Date new_start_date)
		{
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String date_str = df.format(new_start_date);
			start_date = date_str;
		}
		public String getStartDate()
		{
			return start_date;
		}
		public void setTitle(String new_title)
		{
			title = new_title;
		}
		public String getTitle()
		{
			return title;
		}
		public void setStatus(String new_status)
		{
			status = new_status;
		}
		public String getStatus()
		{
			return status;
		}

		/* Verifies that a string corresponds to a numeric value.*/
		private boolean isNumber(String str)
		{
			int length = str.length();
			for (int i = 0; i < length; i++)
				if (Character.getNumericValue(str.charAt(i)) < 0 || 
					Character.getNumericValue(str.charAt(i)) > 9)
	                return false;
	        return true;
		}

		public void setFields(String[] fields)
		{
			id = fields[0];
			full_name = fields[1];
			birth_date = fields[2];
			ssn = fields[3];
			start_date = fields[4];
			title = fields[5];
			status = fields[6];
	 	}

		/* Converts a staff member to a CSV text line. If a field is empty, 
		 * Then it is entered into the database as such. */
	 	public String toString()
	 	{
	 		String tab = "	";
	 		String staff_str = "\n" + id + tab + full_name + tab + birth_date + 
	 							tab + ssn + tab + start_date + tab + title + 
	 							tab + status;
	 		return staff_str;
	 	}

	 	/* For verifying Database conflicts before adding a new member. */
	 	public int compare(Staff compare_staff)
	 	{
	 		if (Long.parseLong(compare_staff.getID()) == Long.parseLong(id))
	 			return 1;
	 		else if (Integer.parseInt(compare_staff.getSSN()) == Integer.parseInt(ssn))
	 			return 2;
	 		else
	 			return 0;
	 	}
}

