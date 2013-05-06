/*
 * Copyright 2013 Charles Amey
 *
 * This file is part of Trashbiller.
 *
 * Trashbiller is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Trashbiller is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Trashbiller.  If not, see<http://www.gnu.org/licenses/>.
*/


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.sql.SQLException; 
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.sql.Connection;

public class EditSave implements ActionListener
{
	public EditSave(EditCustomer p,Customer c)
	{
		parent = p;
		customer = c;
		window = parent.getWindow();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		ArrayList<JTextField> fields = parent.getFields();
		
		ButtonGroup bg = parent.getButtons();
		Enumeration<AbstractButton> buttons = bg.getElements();
		String customerType = "";
		while(buttons.hasMoreElements())
		{
			AbstractButton b = buttons.nextElement();
			if (b.isSelected())
			{
				customerType = b.getText();
			}
		}
		String q = "update accounts set " +
		"account_no = ?, " +
          "sticker_no = ?, " +
		"first_name = ?, " +
		"last_name = ?, " + 
		"address1 = ?, " +
		"city = ?, " +
		"state = ?, " +
		"zip = ? , "  +
		"account_type = ?, " + 
		"date_begin = ?, "+
		"date_end = ? " +
		"where id = ? "
		;
// 		if (!end.equals(""))
// 			q += ", date_end = ? ";
// 		
// 		JOptionPane.showMessageDialog(parent.getParent(),
// 				"query: " + q,
// 				"editsave handler",
// 				JOptionPane.ERROR_MESSAGE
// 		);
		
		try
		{
			PreparedStatement stmt = null;
			Connection c = window.getConnection();
			stmt = c.prepareStatement(q);
			for(int i=0;i<fields.size();i++)
			{
				if(i == 0)
					stmt.setInt(i+1,Integer.parseInt(fields.get(i).getText()));
				else
					stmt.setString(i+1,fields.get(i).getText());
			}
			//set the customer type
			int i=fields.size() +1;
			stmt.setString(i,customerType);
			i++;
			
			
			//set the begin date
			String beginDate = "";
			String m = parent.getBeginMonth();
			String d = parent.getBeginDay();
			String y = parent.getBeginYear();
			
			if (m.equals("Please Select") || y.equals("Please Select"))
				beginDate = "";
			else
			{
				beginDate = m + d + y;
				try
				{
					DateFormat formatter = new SimpleDateFormat("MMMddyyyy");
					//turn it into a Date first
					Date mydate = formatter.parse(beginDate);
// 					System.out.println("turn into date: " + mydate);
				
					//reformat it
					SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
					beginDate = d1.format(mydate);
				}
				catch(ParseException e)
				{
					beginDate = "";
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println(iae);
					beginDate = "";
				}
			}
// 			System.out.println("the begin date: " + beginDate );
			stmt.setString(i,beginDate);
			i++;
			
			
			//set the end date
			String endDate = "";
			m = parent.getEndMonth();
			d = parent.getEndDay();
			y = parent.getEndYear();
			
			if (m.equals("Please Select") || y.equals("Please Select"))
				endDate = "";
			else
			{
				endDate = m + d + y;
				try
				{
					DateFormat formatter = new SimpleDateFormat("MMMddyyyy");
					//turn it into a Date first
					Date mydate = formatter.parse(endDate);
// 					System.out.println("turn into date: " + mydate);
				
					//reformat it
					SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
					endDate = d1.format(mydate);
				}
				catch(ParseException e)
				{
					endDate = "";
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println(iae);
					endDate = "";
				}
			}
// 			System.out.print("the end date:  " + endDate);
			stmt.setString(i,endDate);
			i++;
			
			
			//set the id
			stmt.setInt(i,customer.getId());
			
			stmt.executeUpdate();

			//put this back when i'm done
			parent.setVisible(false);

		}
		catch(SQLException exception)
		{
			JOptionPane.showMessageDialog(parent.getParent(),
				"query: " + q,
				"editsave handler",
				JOptionPane.ERROR_MESSAGE
			);

// 			JOptionPane.showMessageDialog(parent.getParent(),
// 				"query: " + q+"\n"+exception,
// 				"addrecordaction actionPerformed",
// 				JOptionPane.INFORMATION_MESSAGE
// 			);
		}
		catch(NumberFormatException nfe)
		{
// 			System.out.println(nfe);
			JOptionPane.showMessageDialog(parent.getParent(),
// 				nfe.toString() +"\n"+ "Numbers only\n",			//this is with the full path
// 				nfe.getMessage()+"\n"+ 
				"Please type numbers only for: \nAccount No",
				"Input Error!",
				JOptionPane.ERROR_MESSAGE
			);
		}
		
	}
	
	private EditCustomer parent;
	private Customer customer;
	private MainWindow window;
}