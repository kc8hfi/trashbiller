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


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import java.sql.Connection;
import java.sql.SQLException; 
import java.sql.PreparedStatement;

public class AddRecordAction implements ActionListener
{
	public AddRecordAction(AddRecord p)
	{
		parent = p;
	}
	
	public void actionPerformed(ActionEvent event)
	{
// 		System.out.println(event);
		ArrayList<JTextField> fields = parent.getFields();
		String msg = "";

		//check the first text field
		if (!fields.get(0).getText().equals(""))
		{
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
			String insertq = "insert into accounts (account_no,first_name,last_name,address1,"+
				"city,state,zip,account_type,date_begin,date_end) values (?,?,?,?,?,?,?,?,?,?)";
			System.out.println(insertq);
			try
			{
				PreparedStatement stmt = null;
				//AddRecord.MainWindow.getConnection
				Connection c = parent.getWindow().getConnection();
				if (c != null)
				{
					stmt = c.prepareStatement(insertq);
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
					stmt.setString(i,parent.getBeginDate());

					//set the end date, 
					i++;
					stmt.setString(i,"");
					
					//execute the insert
					stmt.executeUpdate();
					
					//update the status bar
					String statusMsg = "Account No.: "+ fields.get(0).getText() + " ";	//account number
					statusMsg += "Name: " + fields.get(1).getText() + " ";		//first name
					statusMsg += fields.get(2).getText();			//last name
					
					parent.setStatusMsg(statusMsg);
					
					//clear the text fields
					for(int j=0;j<fields.size();j++)
					{
						fields.get(j).setText("");
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
					"query: " + insertq+"\n"+exception,
					"addrecordaction actionPerformed",
					JOptionPane.INFORMATION_MESSAGE
				);
			}
			catch(NumberFormatException nfe)
			{
				System.out.println(nfe);
				JOptionPane.showMessageDialog(parent.getParent(),
					//nfe.toString() +"\n"+ "Numbers only\n",			//this is with the full path
					nfe.getMessage()+"\n"+ "Numbers only\n",
					"Input Error!",
					JOptionPane.ERROR_MESSAGE
				);
			}
		}//end if

		else
		{
			msg += "Account No.";
// 			System.out.println("gotta have an account number");
			msg = "Please fill out the following fields:\n" + msg;
			JOptionPane.showMessageDialog(parent.getParent(),
					msg,
					"Incomplete form",
					JOptionPane.QUESTION_MESSAGE
			);
		}
	}//end actionPerformed
	
	private AddRecord parent;
}//end class AddRecordAction