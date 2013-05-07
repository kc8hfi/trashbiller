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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JScrollPane;
import java.util.Arrays;
import javax.swing.table.TableColumn;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;


//just for debugging
import java.util.Vector;

public class ReportPaidAction extends JDialog implements ActionListener
{
	public ReportPaidAction(MainWindow p)
	{
		parent = p;
// 		nothing = new JButton();
		
		months = new ArrayList<String>(0);
		months.add("January");
		months.add("February");
		months.add("March");
		months.add("April");
		months.add("May");
		months.add("June");
		months.add("July");
		months.add("August");
		months.add("September");
		months.add("October");
		months.add("November");
		months.add("December");

// 		JTable RowTable = new JTable(10,1);
// 		TableColumn col = new TableColumn();
// 		col.setHeaderValue("");
// 		RowTable.addColumn(col);
		
// 		RowTable = new JTable();

		Payments = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(Payments,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Payments.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
// 		scrollPane.setRowHeaderView(RowTable);
// 		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,RowTable.getTableHeader());
		
   		
		add(scrollPane);
		
		//run the query now
// 		getData();
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("gonna do something here, "  + evt);
		//put this back here, only get the data if they click the button
		getData();

		setLocationRelativeTo(parent);
		setTitle("Paid in the last 6 months");

		pack();
		setVisible(true);
	}
	
	
	private void getData()
	{
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

// 		System.out.println(query);
		
		//get the date
		try
		{
			PreparedStatement stmt = null;
			PreparedStatement paymentStmt = null;
			Connection con = parent.getConnection();
			stmt = con.prepareStatement(query);
			paymentStmt = con.prepareStatement(paymentq);
			ResultSet rs = stmt.executeQuery();
			ArrayList< ArrayList<String> >everything = new ArrayList< ArrayList<String> >(0);
			ArrayList<String>people = new ArrayList<String>(0);
			while(rs.next())
			{
				people.add(rs.getString(2));
				ArrayList<String>t = new ArrayList<String>(0);
// 				System.out.println(rs.getInt(1));	//id?
				//loop through the months and see if they have paid
				for (String m : months)
				{
					
// 					System.out.println("month: " + m);
					String date = m+ "042013";
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
// 					while(prs.next())
// 					{
// 						Object val = prs.getObject(1);
// 						System.out.println(val);
// 						for(int i=1;i<=rsmd.getColumnCount();i++)
// 						{
// 							Object value = prs.getObject(i);
// 							System.out.println("\t"+i+"=>"+rsmd.getColumnName(i)+"=>"+value);
// 						}
// 						if (rsmd.getColumnCount() == 1)
// 						{
// 							System.out.println("They got a record for " + m);
// 						}
// 						else
// 						{
// 							System.out.println("no record");
// 						}
// 					}
				}
				
				//add the person to the front of the little array first
				t.add(0,rs.getString(2));
				//add the little array to the big array
				everything.add(t);
			}//end while
			
// 			System.out.println("the sze of the big array: " + everything.size());
// 			System.out.println("size of an element in there: " + everything.get(0).size());
			
			ArrayList<String>newColNames = new ArrayList<String>(0);
			newColNames.add("Customer");
			for(String mon:months)
			{
				newColNames.add(mon);
			}
			
			ReportPaidModel tableModel = new ReportPaidModel(newColNames,everything);
			Payments.setModel(tableModel);
			
			Payments.getColumnModel().getColumn(0).setPreferredWidth(200);
               
//                Payments.setRowHeight(25);
// 			System.out.println("number of people: " + people.size());
// 			ReportPaidModelRow tbModel = new ReportPaidModelRow("customer",people);
// 			RowTable.setModel(tbModel);

			
// 			stmt.setString(1,date);
// // 			System.out.println("the date is: " + date);
// 			ResultSet rs = stmt.executeQuery();
// 			ResultSetMetaData rsmd = rs.getMetaData();
// 			ArrayList<String> colnames = new ArrayList<String>(0);
// 			for(int i=1;i<=rsmd.getColumnCount();i++)
// 			{
// // 				System.out.println(rsmd.getColumnName(i));
// 				colnames.add(rsmd.getColumnName(i));
// 			}
// 			ArrayList<CustomerPay> data = new ArrayList<CustomerPay>(0);
// 			while(rs.next())
// 			{
// 				CustomerPay c = new CustomerPay();
// 				c.setId(rs.getInt(1));				//id
// 				c.setAccountNo(rs.getInt(2));			//account no
// 				c.setName(rs.getString(3));			//name
// 				c.setAccountType(rs.getString(4));		//account type
// 				c.setAmount(rs.getDouble(5));			//amount
// 				//paid column, needs to be a boolean
// // 				System.out.println("change the paid int to a bool " + rs.getInt(6));
// 				if (rs.getInt(6) == 0)
// 					c.setPaid(false);
// 				else
// 					c.setPaid(true);
// 				//add the new customer to the arraylist
// 				data.add(c);
// 			}
// 			MyTablePayModel tableModel = new MyTablePayModel(nothing,c,d);
// 			table = new JTable(tableModel);
// 			
// 			JTable tb = parent.getTable();
// 			MyTablePayModel m = (MyTablePayModel)tb.getModel();
// // 			System.out.println("column names: " + colnames);
// 			m.setTableInfo(colnames,data);
// 			
// 			//remove a column
// 			TableColumnModel tcm = parent.getTable().getColumnModel();
// // 			System.out.println(tcm);
// 
// 			//remove the first column, the id
// 			TableColumn tc1 = parent.getTable().getColumnModel().getColumn(0);
// 			tcm.removeColumn(tc1);
// 			
// // 			System.out.println("how many cols are left: " + parent.getTable().getColumnCount());
// 			
// 			//loop through each column and get the max size
// 			for (int i=0;i<parent.getTable().getColumnCount(); i++)
// 			{
// 				tc1 = parent.getTable().getColumnModel().getColumn(i);
// 				switch(i)
// 				{
// 					case 0:
// 						tc1.setPreferredWidth(65);
// 					break;
// 					case 1:
// 						tc1.setPreferredWidth(375);
// 					break;
// 					case 2:
// 						tc1.setPreferredWidth(70);
// 					break;
// 					case 3:
// 						tc1.setPreferredWidth(50);
// 					break;
// 					case 4:
// 						tc1.setPreferredWidth(75);
// 					break;
// 					default:
// 						tc1.setPreferredWidth(2);
// 				}
// 			}
		}
		catch(SQLException exception)
		{
			JOptionPane.showMessageDialog(parent.getParent(),
				"query:\n" + query +"\n"+exception,
				"reportpaidaction actionPerformed",
				JOptionPane.ERROR_MESSAGE
			);
		}
		
	}//end getData
	
	private MainWindow parent;
// 	private JButton nothing;
	private ArrayList<String>months;
	private JTable Payments;
	private JTable RowTable;
}