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


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CustomerHistoryModel extends AbstractTableModel
{
     public CustomerHistoryModel()
     {
          colNames = new ArrayList<String>(0);
          theData = new ArrayList< ArrayList<String> >(0);
     }
     
     public int getColumnCount()
     {
          return colNames.size();
     }

     public int getRowCount()
     {
//        System.out.println("num rows: " + theData.size());
          return theData.size();
     }
     
     public String getColumnName(int col)
     {
//        System.out.println("colname for "+col+ " is " + colNames.get(col));
          return colNames.get(col);
     }
     public Object getValueAt(int row,int col)
     {
          ArrayList c = theData.get(row);
//        System.out.println("data is: " + c.at(col));
          return c.get(col);
     }
     
     public void setColNames(ArrayList<String> c)
     {
//        System.out.println("incoming: " + c.size());
          colNames = c;
//        System.out.println("outgoing: " + colNames.size());
          fireTableStructureChanged();
     }
     public void setData(ArrayList< ArrayList<String> > d)
     {
          theData = d;
//        System.out.println("gotta fire data changed");
//        System.out.println("size is " + theData.size());
//        fireTableDataChanged();
     }
     
     public ArrayList<String> getRow(int row)
     {
          ArrayList<String> r = new ArrayList<String>(0);          
          if (row <theData.size())
               r = theData.get(row);
          return r;
     }
     
     private ArrayList<String> colNames;
     private ArrayList< ArrayList<String> > theData;
}