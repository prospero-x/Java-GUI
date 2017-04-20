package SEARCH_STAFFDB;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * Author: Mikhail Rezazadeh, April 2017
 * 
 * JTEXTFIELDLIMIT: a Plain Document extension which 
 * is used to limit the number of characters into a 
 * JTextField. The addition of "String" covers 
 * copy and paste as well as normal keyboard typing. 
 * 
 * This prevents buffer overflow. 
 *
 */

public class JTextFieldLimit extends PlainDocument {
	private int char_limit;
	private String warning_message;
	
	public JTextFieldLimit(int char_limit, String warning_message)
	{
		super();
		this.char_limit = char_limit;
		this.warning_message = warning_message;
	}
	
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
	{
		if (str == null)
			return;
		
		if ((getLength() + str.length()) <= char_limit)
			super.insertString(offset,  str,  attr);
		else
			JOptionPane.showMessageDialog(null,
		              warning_message, "Warning: Invalid Length",
		              JOptionPane.ERROR_MESSAGE);
	}
}
