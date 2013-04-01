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


import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.sql.SQLException; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Connection;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class StartPaymentAction extends AbstractAction
{
	public StartPaymentAction(PayBill p, String t)	//t is the name on the item, 
	{
		super(t);
		parent = p;
	}

	public void actionPerformed(ActionEvent event)
	{
		String date = parent.getMonth()+parent.getDay()+parent.getYear();
		
		String select = "select id, "+
			"account_no ," + 
			"first_name||' '||last_name||', '||address1||', '||city||', '||state||'  '||zip as name, "+
			"account_type, "+
			"amount, " + 
			"payment_date " +
			"from accounts " +
			"left join payments "+
			"on id = payment_id and " +
			"strftime('%Y-%m',payment_date) = ? "
			;
			
		String where = "where date_end = \"\" ";

		String order = "order by last_name,first_name ";
		
		String query = select + where + order;
// 		System.out.println(query);
		try
		{
			PreparedStatement stmt = null;
			Connection con = parent.getWindow().getConnection();
			if (con != null)
			{
				stmt = con.prepareStatement(query);
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
				
				
	// 			System.out.println("fix the date: " + date);
				stmt.setString(1,date);
	// 			System.out.println("start payments for this date: " + date);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				ArrayList<String> colnames = new ArrayList<String>(0);
				for(int i=1;i<=rsmd.getColumnCount();i++)
				{
	// 				System.out.println(rsmd.getColumnName(i));
					colnames.add(rsmd.getColumnName(i));
				}
				ArrayList<CustomerPay> data = new ArrayList<CustomerPay>(0);
				while(rs.next())
				{
	// 				for(int i=1;i<=rsmd.getColumnCount();i++)
	// 				{
	// 					Object value = rs.getObject(i);
	// 					System.out.println("\t"+i+"=>"+rsmd.getColumnName(i)+"=>"+value);
	// 				}
					CustomerPay c = new CustomerPay();
					c.setId(rs.getInt(1));				//id
					c.setAccountNo(rs.getInt(2));			//account no
					c.setName(rs.getString(3));			//name
					c.setAccountType(rs.getString(4));		//account type
					c.setAmount(rs.getDouble(5));			//amount
					if (rs.getString(6) == null)			//paid or not
						c.setPaid(false);
					else
						c.setPaid(true);
					//add the new item to the array
					data.add(c);
	// 				System.out.println(c);
				}
				JTable tb = parent.getTable();
				MyTablePayModel m = (MyTablePayModel)tb.getModel();
	// 			System.out.println("column names: " + colnames);
				m.setTableInfo(colnames,data);
				
				//remove a column
				TableColumnModel tcm = parent.getTable().getColumnModel();
	// 			System.out.println(tcm);

				//remove the first column, the id
				TableColumn tc1 = parent.getTable().getColumnModel().getColumn(0);
				tcm.removeColumn(tc1);
				
	// 			System.out.println("how many cols are left: " + parent.getTable().getColumnCount());
				
				//loop through each column and get the max size
				for (int i=0;i<parent.getTable().getColumnCount(); i++)
				{
					tc1 = parent.getTable().getColumnModel().getColumn(i);
					switch(i)
					{
						case 0:
							tc1.setPreferredWidth(65);
						break;
						case 1:
							tc1.setPreferredWidth(375);
						break;
						case 2:
							tc1.setPreferredWidth(70);
						break;
						case 3:
							tc1.setPreferredWidth(50);
						break;
						case 4:
							tc1.setPreferredWidth(75);
						break;
						default:
							tc1.setPreferredWidth(2);
					}
				}
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
				exception,
				"StartPaymentAction actionPerformed",
				JOptionPane.ERROR_MESSAGE
			);
		}
	}
	
	private PayBill parent;
}//end class
