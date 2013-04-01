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



class AllPaymentsAction implements ActionListener
{
	public AllPaymentsAction(AllPayments p)
	{
		parent = p;
		months = parent.getMonths();
		theWindow = parent.getParent();
	}
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("do this: " + evt.getActionCommand());
// 		Reports r = new Reports(parent,evt.getActionCommand());
// 		r.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
// 		r.setVisible(true);
		
		String select = "select id, "+
// 			"account_no ," + 
			"first_name||' '||last_name||', '||address1||', '||city||', '||state||'  '||zip as name, "+
			"account_type "+
// 			"amount, " + 
			"from accounts " 
// 			"left join payments "+
// 			"on id = payment_id and " +
// 			"payment_date = ? "
			;
			
		String where = "";

		String order = "order by last_name,first_name ";
		
		String query = select + where + order;
		
		String paymentq = "select payment_date from payments where payment_id = ? "+
		"and strftime('%Y-%m',payment_date) = ? ";

		System.out.println("allpayments query:" + query);
		System.out.println("allpayments paymentq: " + paymentq);
		
		try
		{
			PreparedStatement stmt = null;
			PreparedStatement paymentStmt = null;
			Connection con = parent.getParent().getConnection();
			if (con != null)
			{
				stmt = con.prepareStatement(query);
				paymentStmt = con.prepareStatement(paymentq);
				ResultSet rs = stmt.executeQuery();
				ArrayList< ArrayList<String> >everything = new ArrayList< ArrayList<String> >(0);
				ArrayList<String>people = new ArrayList<String>(0);
				while(rs.next())
				{
					people.add(rs.getString(2));
					ArrayList<String>t = new ArrayList<String>(0);
					int td = Calendar.getInstance().get(Calendar.DATE);
					String todayDate = "";
					if(td <10)
						todayDate = "0" + Integer.toString(td);
					else
						todayDate = Integer.toString(td);
					//loop through the months and see if they have paid
					
					for (String m : months)
					{
	// 					System.out.println("month: " + m);
						String date = m + todayDate + (String)parent.getSelectedYear();

						paymentStmt.setInt(1,rs.getInt(1));	//id
						try
						{
							DateFormat formatter = new SimpleDateFormat("MMMddyyyy");
							//turn it into a Date first
							Date mydate = formatter.parse(date);
			// 					System.out.println("turn into date: " + mydate);
						
							//reformat it
							SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM");
							date = d1.format(mydate);
						}
						catch(ParseException e)
						{
							date = "";
						}
						catch(IllegalArgumentException iae)
						{
							System.out.println(iae);
							date = "";
						}

						paymentStmt.setString(2,date);		//date
						ResultSet prs = paymentStmt.executeQuery();
						ResultSetMetaData rsmd = prs.getMetaData();
						
						prs.next();
						String s = "";
						Object val = prs.getObject(1);
						if (val != null)
							s = "X";
	// 					System.out.println(m+": " + s);
						t.add(s);
					}
					
					//add the person to the front of the little array first
					t.add(0,rs.getString(2));
					//add the little array to the big array
					everything.add(t);
				}//end while
				
				ArrayList<String>newColNames = new ArrayList<String>(0);
				newColNames.add("Customer");

				for(String mon:months)
				{
					newColNames.add(mon);
				}
				
				ReportsModel tableModel = new ReportsModel(newColNames,everything);
				parent.getTable().setModel(tableModel);
				
				parent.getTable().getColumnModel().getColumn(0).setPreferredWidth(200);
			}
			else
			{
				JOptionPane.showMessageDialog(parent.getParent(),
					"Please check your configuration settings!",
					"No Database Connection!",
					JOptionPane.ERROR_MESSAGE
				);
			}
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
	private AllPayments parent;
	private MainWindow theWindow;
	private ArrayList<String>months;
	
}