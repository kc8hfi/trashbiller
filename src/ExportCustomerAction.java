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
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;


public class ExportCustomerAction extends AbstractAction
{
     public ExportCustomerAction(MainWindow p,String text, String cmd,String toolTip)
     {
//           super(text,icon); //text is the actual name
          super(text);
          putValue(ACTION_COMMAND_KEY,cmd);  //set action command
          putValue(SHORT_DESCRIPTION, toolTip); //used for tooltip text
//           putValue(ACCELERATOR_KEY,accelerator);
          window = p;
     }
     
     public void actionPerformed(ActionEvent event)
     {
          String select = "select id, " +
               "account_no as \"Acct. No\", " +
               "account_type as \"Type\", " +
               "first_name as \"First\",last_name as \"Last\", "+
               "address1 as \"Address\",city as \"City\", "+
               "state,zip " +
               "from accounts ";
          String where = "where date_end is null or date_end = '' ";

          String order = "order by last_name,first_name ";
          String query = select + where + order;
          try
          {
               PreparedStatement stmt = null;
               Connection con = window.getConnection();
               if (con != null)
               {
                    stmt = con.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    //get the column names
                    ArrayList<String> colnames = new ArrayList<String>(0);
                    for(int i=1;i<=rsmd.getColumnCount();i++)
                    {
                         colnames.add(rsmd.getColumnName(i));
                    }
                    ArrayList< ArrayList<String> > theData = new ArrayList<ArrayList<String>>(0);
                    //put the column names on the array first
                    theData.add(colnames);
                    while(rs.next())
                    {
                         ArrayList<String> temp = new ArrayList<String>(0);
                         for(int i=1; i<rsmd.getColumnCount();i++)
                         {
                              temp.add(rs.getString(i));
                         }
                         //add the little array to the big array
                         theData.add(temp);
                    }
//                     System.out.println("size: " + theData.size());
//                     System.out.println("now we need to show the file dialog, pass in the theData");
                    SaveToDisk saveme = new SaveToDisk(window,theData);
               }
          }
          catch(SQLException exception)
          {
               System.out.println(query + " and " + exception);
               JOptionPane.showMessageDialog(window,
                    "Couldn't execute the query: \n" + exception,
                    "Export Customer",
                    JOptionPane.ERROR_MESSAGE
               );
          }         
     }
     private MainWindow window;
}