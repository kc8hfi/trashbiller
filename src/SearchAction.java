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
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class SearchAction extends AbstractAction
{
	public SearchAction(Search p, String t, String cmd)
	{
		super(t);
		parent = p;
		putValue(ACTION_COMMAND_KEY, cmd);	//set the action command
	}

	public void actionPerformed(ActionEvent event)
	{
// 		System.out.println("command: " + event.getActionCommand());
// 		System.out.println("the text from the cmd: " + ((JTextField)event.getSource()).getText());
		String select = "select id, " +
			"account_no as \"Acct. No\", " +
			"account_type as \"Type\", " +
			"first_name as \"First\",last_name as \"Last\", "+
			"address1 as \"Address\",city as \"City\", "+
			"state,zip,"+
			"date_begin as \"Begin\",date_end as \"End\" "+
			"from accounts ";
		String where = "";

		String order = "order by last_name,first_name ";

		if (event.getActionCommand().equals("account"))
		{
			
// 			System.out.println("search based on account number only");
			parent.setStatus("search based on account number only");
			where += "where account_no like ? ";
		}
		else
		{
// 			System.out.println("build the query based off the other text fields ");
			parent.setStatus("build the query based off the other text fields ");
			where += "where first_name like ? " +
				"and last_name like ? "+
				"and address1 like ? "+
				"and city like ? "+
				"and state like ? "+
				"and zip like ? " ;
		}
		String query = select + where + order;
		try
		{
			PreparedStatement stmt = null;
			Connection con = parent.getWindow().getConnection();
			if (con != null)
			{
				stmt = con.prepareStatement(query);
				if (event.getActionCommand().equals("account"))
				{
					if (((JTextField)event.getSource()).getText().equals(""))
						stmt.setString(1,"%");
					else
						stmt.setString(1,((JTextField)event.getSource()).getText());
				}
				else
				{
	// 				parent.setStatus("gotta get all the other text field data");
	// 				System.out.println("gotta get all the other text field data");
					ArrayList<JTextField> fields = parent.getTextFields();
	// 				System.out.println("how many fields: " + fields.size());
					for(int i=1;i<fields.size();i++)
					{
	// 					System.out.println("value: " + fields.get(i).getText());
						if (fields.get(i).getText().equals(""))
						{
	// 						System.out.println("field was empty, put a % in it");
							stmt.setString(i,"%");
						}
						else
						{
							stmt.setString(i,fields.get(i).getText());
	// 						System.out.println("field had something: " +fields.get(i).getText());
						}
					}
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				ArrayList<String> colnames = new ArrayList<String>(0);
				for(int i=1;i<=rsmd.getColumnCount();i++)
				{
	// 				System.out.println(rsmd.getColumnName(i));
					colnames.add(rsmd.getColumnName(i));
				}
				ArrayList<Customer> data = new ArrayList<Customer>(0);
				while(rs.next())
				{
					Customer c = new Customer();
					c.setId(rs.getInt(1));				//id
					c.setAccountNo(rs.getInt(2));			//account number
					c.setAccountType(rs.getString(3));		//account type
					c.setFirst(rs.getString(4));			//first name
					c.setLast(rs.getString(5));			//last name
					c.setAddress(rs.getString(6));		//address
					c.setCity(rs.getString(7));			//city
					c.setState(rs.getString(8));			//state
					c.setZip(rs.getString(9));			//zip
					c.setBegin(rs.getString(10));			//begin date
					c.setEnd(rs.getString(11));			//end date
					
					//add the new customer to the arraylist
					data.add(c);
				}
				JTable tb = parent.getTable();
				MyTableModel m = (MyTableModel)tb.getModel();
	// 			m.setTableInfo(colnames,data);
				
				m.setColNames(colnames);
				m.setData(data);
				parent.setStatus(data.size() + " record(s) returned.");
				MySorter intComp = new MySorter();
				parent.getSorter().setComparator(0,intComp);
				parent.getSorter().setComparator(1,intComp);
				
				//remove these columns from the view
				ArrayList<String> removeColNames = new ArrayList<String>(
					Arrays.asList("id"));
				TableColumnModel tcm = parent.getTable().getColumnModel();
				
	// 			foreach(TableColumn t: tcm.getColumns())
	// 			{
	// 				System.out.println(t);
	// 			}
				
				for (String n : removeColNames)
				{
					for(int i=0;i<tcm.getColumnCount();i++)
					{
						TableColumn tc = tcm.getColumn(i);
						if (tc.getHeaderValue().equals(n))
						{
							tcm.removeColumn(tc);
							break;
						}
					}
				}
	// 			System.out.println(colnames);
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
				"Search by account no. only, query:\n" + query +"\n"+exception,
				"SearchAction accountSearch",
				JOptionPane.ERROR_MESSAGE
			);
		}		
		
	}//end actionPerformed
	
	private Search parent;
}
