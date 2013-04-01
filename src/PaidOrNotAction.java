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


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;


// import java.util.Arrays;
// import javax.swing.Box;



class PaidOrNotAction implements ActionListener
{
	public PaidOrNotAction(PaidOrNot p, String w)
	{
		parent = p;
		whichOne = w;
		months = parent.getMonths();
		theWindow = parent.getParent();
	}
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("do this: " + evt.getActionCommand());
		System.out.println("which one: " + whichOne);
		
		int td = Calendar.getInstance().get(Calendar.DATE);
		String todayDate = "";
		if(td <10)
			todayDate = "0" + Integer.toString(td);
		else
			todayDate = Integer.toString(td);
		todayDate = parent.getSelectedMonth() + todayDate + parent.getSelectedYear();
		String date = "";
		try
		{
			DateFormat formatter = new SimpleDateFormat("MMMddyyyy");
			//turn it into a Date first
			Date mydate = formatter.parse(todayDate);
// 			System.out.println("turn into date: " + mydate);
		
			//reformat it
			SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM");
			date = d1.format(mydate);
		}
		catch(ParseException e)
		{
			System.out.println(e);
			date = "";
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println(iae);
			date = "";
		}
		String select = "select "+
// 			"id, "+
			"account_no \"Account No.\", "+
			"first_name||' '||last_name Name, "+
			"address1||' '||city||', '||state||'  '||zip Address, "+
			"account_type Type "
			//, payment_date \"Payment Date\" "
		;
		
		if(whichOne.equals("paid"))
		{
			select += ", amount \"Amt.\" " +
			"from accounts,payments "+
			"where payment_id = id "+
			"and date_end = '' " + 
			"and strftime('%Y-%m',payment_date) = ? " //'201303
			;
		}
		if(whichOne.equals("not_paid"))
		{
			select += "from accounts "+
			"left join payments "+
			"on id=payment_id "+
			//"and strftime('%Y-%m',payment_date) = ? "+
			"where payment_date is null "+
			"or strftime('%Y-%m',payment_date) != ? " + 
			"and date_end = '' ";
		}
		String where = "";
		String order = "order by last_name,first_name ";
		
		String query = select + where + order;
		
		System.out.println("query: " + query);
		System.out.println("date: " + date);
		
		try
		{
			PreparedStatement stmt = null;
			Connection con = parent.getParent().getConnection();
			if (con != null)
			{
				stmt = con.prepareStatement(query);
				stmt.setString(1,date);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				ArrayList<String>colNames = new ArrayList<String>(0);
				for(int i=1;i<=rsmd.getColumnCount();i++)
				{
					colNames.add(rsmd.getColumnName(i));
	// 				System.out.println("\t" + rsmd.getColumnName(i));
				}
				int colCount = rsmd.getColumnCount();
				ArrayList< ArrayList<String> >everything = new ArrayList< ArrayList<String> >(0);

				
				while(rs.next())
				{
					ArrayList<String>people = new ArrayList<String>(0);
					for(int i=1;i<=colCount;i++)
					{
						System.out.println(rs.getObject(i));
						people.add(rs.getString(i));
					}
					everything.add(people);
				}
				//give the column headers and data to a new model
				ReportsModel tableModel = new ReportsModel(colNames,everything);
				parent.getTable().setModel(tableModel);
			}
			else
			{
				JOptionPane.showMessageDialog(parent.getParent(),
					"Please check your configuration settings!",
					"No Database Connection!",
					JOptionPane.ERROR_MESSAGE
				);
			}
	// 			parent.getTable().getColumnModel().getColumn(1).setPreferredWidth(200);
				
				
	// 			ArrayList< ArrayList<String> >everything = new ArrayList< ArrayList<String> >(0);
	// 			ArrayList<String>people = new ArrayList<String>(0);
	// 			while(rs.next())
	// 			{
	// 				people.add(rs.getString(2));
	// 				ArrayList<String>t = new ArrayList<String>(0);
	// 				int td = Calendar.getInstance().get(Calendar.DATE);
	// 				String todayDate = "";
	// 				if(td <10)
	// 					todayDate = "0" + Integer.toString(td);
	// 				else
	// 					todayDate = Integer.toString(td);
	// 				//loop through the months and see if they have paid
	// 				
	// 				for (String m : months)
	// 				{
	// // 					System.out.println("month: " + m);
	// 					String date = m + todayDate + (String)parent.getSelectedYear();
	// 
	// 					paymentStmt.setInt(1,rs.getInt(1));	//id
	// 					try
	// 					{
	// 						DateFormat formatter = new SimpleDateFormat("MMMddyyyy");
	// 						//turn it into a Date first
	// 						Date mydate = formatter.parse(date);
	// 		// 					System.out.println("turn into date: " + mydate);
	// 					
	// 						//reformat it
	// 						SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM");
	// 						date = d1.format(mydate);
	// 					}
	// 					catch(ParseException e)
	// 					{
	// 						date = "";
	// 					}
	// 					catch(IllegalArgumentException iae)
	// 					{
	// 						System.out.println(iae);
	// 						date = "";
	// 					}
	// 
	// 					paymentStmt.setString(2,date);		//date
	// 					ResultSet prs = paymentStmt.executeQuery();
	// 					ResultSetMetaData rsmd = prs.getMetaData();
	// 					
	// 					prs.next();
	// 					String s = "";
	// 					Object val = prs.getObject(1);
	// 					if (val != null)
	// 						s = "X";
	// // 					System.out.println(m+": " + s);
	// 					t.add(s);
	// 				}
	// 				
	// 				//add the person to the front of the little array first
	// 				t.add(0,rs.getString(2));
	// 				//add the little array to the big array
	// 				everything.add(t);
	// 			}//end while
	// 			
	// 			ArrayList<String>newColNames = new ArrayList<String>(0);
	// 			newColNames.add("Customer");
	// 
	// 			for(String mon:months)
	// 			{
	// 				newColNames.add(mon);
	// 			}
	// 			
	// 			ReportPaidModel tableModel = new ReportPaidModel(newColNames,everything);
	// 			parent.getTable().setModel(tableModel);
	// 			
	// 			parent.getTable().getColumnModel().getColumn(0).setPreferredWidth(200);
		}
		catch(SQLException exception)
		{
			JOptionPane.showMessageDialog(parent.getParent(),
				"query:\n" + query +"\n"+exception,
				"reportpaidaction actionPerformed",
				JOptionPane.ERROR_MESSAGE
			);
		}
		
	}
	private PaidOrNot parent;
	private String whichOne;
	private MainWindow theWindow;
	private ArrayList<String>months;
	
}