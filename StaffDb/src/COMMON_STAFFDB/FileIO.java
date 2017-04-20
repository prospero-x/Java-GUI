package COMMON_STAFFDB;
import java.io.*;
import java.util.*;


/*
 * Mikhail Rezazadeh, April 2017 
 * 
 *  FILE_IO: A class which handles reading to and writing from the Staff Database. 
 *  
 *  As soon as an instance of this class is created, if the Database does not yet 
 *  exist, an empty one will be created with the relevant headers in the first line. 
 *
*/

public class FileIO {
	private String staffDataBase;
	private String line;
	private String csvSplitBy;
	private BufferedReader br;
    private FileWriter fw;
    private String attributeHeaders;
    private int all_fields;

	public FileIO()
	{
		staffDataBase = "staffDatabase.txt";
		line = "";
		csvSplitBy = "	"; 
        br = null;
        fw = null;
        
        /* A number used for searches. This indicates that all members of the Database 
         * should be matched. */
        all_fields = 7;
        attributeHeaders = "ID  NAME    DOB SSN START-DATE  TITLE   STATUS";
        verifyDataBase();
	}

	/*
	 * Verify that the database exists. If not, create one, with the relevant headers. 
	 */
    public void verifyDataBase()
    {
        try {
            File f = new File(staffDataBase);
            
            /* createNewFile is a library function which checks the existance of a file. 
             * If the file already exists, it returns 0. Otherwise, it returns 1. */
            if(f.createNewFile()) { 
                fw = new FileWriter(staffDataBase, true);
                fw.write(attributeHeaders);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * CHECK_CONFLICTS: checks if there is already a staff member in the database 
     * whose STAFF_ID or SSN is equal to those of the NEW_STAFF. 
     * 
     * Returns: 0 if no conflicts exist, 1 if there is a staff_id conflict, 
     * and 2 if there is a ssn conflict. 
     */
	public int checkConflicts(Staff new_staff)
	{
		int compare;
		boolean first_line = true;
		try {
            br = new BufferedReader(new FileReader(staffDataBase));
            while ((line = br.readLine()) != null ) {

            	/* Skip the first line (which contains attribute names, not data) */
            	if (first_line) first_line = false;
            	else
            	{
                String[] fields = line.split(csvSplitBy);

                Staff staff = new Staff();
                staff.setFields(fields);
                
                /* the COMPARE method of the STAFF class returns 0 if there are no conflicts, 
                 * 1 if there is an ID conflict, and 2 if there is an SSN conflict. */
                if ((compare = new_staff.compare(staff)) > 0)
                	return compare;
            	}
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

	/*
	 * SUBMIT_NEW_STAFF: enters the NEW_STAFF into the staff database, 
	 * in the form of a new CSV line. If there is a field that is empty, 
	 * there will be two tabs instead of one 
	 * 
	 */
    public void submitNewStaff(Staff new_staff)
    {
        try {
            fw = new FileWriter(staffDataBase, true);
            fw.write(new_staff.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * GET_MATCHES: searches through the database and finds all staff members whose
     * ATTRIBUTE field matches the STRING_VALUE. 
     * 
     * returns: A list of STAFF objects, each corresponding to a staff member who matched
     * the search. 
     */
    public List<Staff> getMatches(StaffAttribute attribute, String search_value)
    {
        List<Staff> matches = new ArrayList<Staff>();
        boolean first_line = true;
        try {
            br = new BufferedReader(new FileReader(staffDataBase));
            while ((line = br.readLine()) != null)
            {
                if (first_line) first_line = false;
                else
                {
                    String[] fields = line.split(csvSplitBy);
                    
                    /* If the search attribute is ALL, it will be passed to this function as the number 7 (the value of 
                     * ALL_FIELDS) */
                    if (attribute.getAttribute() == all_fields || fields[attribute.getAttribute()].equals(search_value))
                    {
                        Staff staff = new Staff();
                        staff.setFields(fields);
                        matches.add(staff);
                    }
                }
            }
        } catch (FileNotFoundException e) {}
          catch (IOException e) {}
          finally {
            if (br != null)
            {
                try { br.close(); }
                catch (IOException e) {}
            }
        }
        return matches;
    }
}
