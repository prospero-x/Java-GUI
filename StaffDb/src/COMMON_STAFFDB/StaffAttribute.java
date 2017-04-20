package COMMON_STAFFDB;

/*
 * Author: Mikhail Rezazadeh April 2017
 * 
 * STAFF_ATTRIBUTE: a simple extension of an enum for simple 
 * conversion between attribute types and database row indeces. 
 */

public class StaffAttribute
{
	private enum Attribute { 
		STAFF_ID(0), 
		FULL_NAME(1), 
		DOB (2),
		SSN (3), 
		START_DATE (4), 
		TITLE(5), 
		STATUS(6), 
		ALL(7); 

		private final int number;
		Attribute(int number) {
			this.number = number;
		}

		public int getNumber() {return number; }
	}

	private int value;

	public StaffAttribute(String attribute) {
		switch(attribute)
 		{
 			case "Staff ID" : value = Attribute.STAFF_ID.getNumber();
 				break;
 			case "Full Name" : value = Attribute.FULL_NAME.getNumber();
 				break;
 			case "Date of Birth" : value = Attribute.DOB.getNumber();
 				break;
 			case "Social Security Number" : value = Attribute.SSN.getNumber();
 				break;
 			case "Start Date" : value = Attribute.START_DATE.getNumber();
 				break;
 			case "Title" : value = Attribute.TITLE.getNumber();
 				break;
 			case "Status" : value = Attribute.STATUS.getNumber();
 				break;
 			default: value = Attribute.ALL.getNumber();
 				break;
 		}
	}

	public int getAttribute()
	{
		return value;
	}
}