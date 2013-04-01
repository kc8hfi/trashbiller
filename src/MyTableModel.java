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

public class MyTableModel extends AbstractTableModel
{
	public MyTableModel()
	{
		colNames = new ArrayList<String>(0);
		theData = new ArrayList<Customer>(0);
	}
	
	public int getColumnCount()
	{
		return colNames.size();
	}
	
// 	public Class getColumnClass(int c)
// 	{
// // 		System.out.println(getValueAt(0,c).getClass());
// 		Class cl = Object.class;
// 		if (c == 1 || c == 2)
// 		{
// // 			System.out.println("first column");
// 			cl = int.class;
// 		}
// 		else
// 		{
// 			cl = String.class;
// 		}
// 		System.out.println("c: "+ c +" type: "+cl);
// 
// // 		System.out.println("size is: " + colNames.size());
// // 		System.out.println("c is : " + c);
// // 		if ( (c >=0) && (c < colNames.size()) && theData.size()>0 )
// // 		{
// // 			System.out.println("how many rows: "+ theData.size());
// // 			System.out.println("how many cols: "+ colNames.size());
// // // 			cl = getValueAt(0,c).getClass();
// // // 			System.out.println("the class is: " +cl);
// // 		}
// 		
// // 		return getValueAt(0,c).getClass();
// 		return cl;
// 	}
	
	public int getRowCount()
	{
// 		System.out.println("num rows: " + theData.size());
		return theData.size();
	}
	
	public String getColumnName(int col)
	{
// 		System.out.println("colname for "+col+ " is " + colNames.get(col));
		return colNames.get(col);
	}
	public Object getValueAt(int row,int col)
	{
		Customer c = theData.get(row);
// 		System.out.println("data is: " + c.at(col));
		return c.at(col);
	}
	
	public void setColNames(ArrayList<String> c)
	{
// 		System.out.println("incoming: " + c.size());
		colNames = c;
// 		System.out.println("outgoing: " + colNames.size());
		fireTableStructureChanged();
	}
	public void setData(ArrayList<Customer> d)
	{
		theData = d;
// 		System.out.println("gotta fire data changed");
// 		System.out.println("size is " + theData.size());
// 		fireTableDataChanged();
	}
	
	public Customer getRow(int row)
	{
		Customer r = new Customer();
		if (row <theData.size())
			r = theData.get(row);
		return r;
	}
	
	private ArrayList<String> colNames;
	private ArrayList<Customer> theData;
}