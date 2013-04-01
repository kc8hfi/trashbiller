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


import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import java.util.Enumeration;
import java.util.Vector;


public class MyTablePayModel extends AbstractTableModel
{
	public MyTablePayModel(PayBill pb,ArrayList<String>c,ArrayList<CustomerPay>d)
	{
		paybill = pb;
		colNames = c;
		theData = d;
		colClass = new ArrayList<Class>(
			Arrays.asList(Integer.class,Integer.class,String.class,String.class,Double.class,Boolean.class)
		);
		changedRows = new HashSet<Integer>(0);
	}
	
	public Object getValueAt(int row,int col)
	{
		CustomerPay d = (CustomerPay)(theData.get(row));
		return d.at(col);
	}
	
	public int getColumnCount()
	{
		return colNames.size();
	}
	
	public int getRowCount()
	{
		return theData.size();
	}
	
	public Class getColumnClass(int col)
	{
		return colClass.get(col);
	}

	public String getColumnName(int col)
	{
		return colNames.get(col);
	}
	
	public boolean isCellEditable(int row, int col)
	{
// 		System.out.println("edit row:"+row+" edit col:"+col);
		//come back here to let the user edit the amount and paid columns
		if (col == 4)
			return true;
		else if (col == 5)
			return true;
		else
			return false;
	}
	
	public void setValueAt(Object value,int row, int col)
	{
		CustomerPay d = (CustomerPay)(theData.get(row));
// 		System.out.println(d);
// 		System.out.println("set the new value to: " + value +" for " + row + " and " + col);
		
// 		//for the amount
		if (col == 4)
		{
			if (value != null)
			{
				d.setAmount((double)value);
				paybill.enableSaveButton();
				changedRows.add(row);
				fireTableCellUpdated(row,col);
			}
		}
		//for the paid checkbox
		if (col == 5)
		{
			d.setPaid((boolean)value);
			paybill.enableSaveButton();
			changedRows.add(row);
			fireTableCellUpdated(row,col);
		}
	}
	
	//this is my functions
	public void setTableInfo(ArrayList<String> colnames,ArrayList<CustomerPay>data)
	{
		colNames = colnames;
		theData = data;
		fireTableStructureChanged();
	}
	public Set getChangedRows()
	{
		return changedRows;
	}
	
	public int getMaxWidth(int col)
	{
		int width = 0;
		for(CustomerPay d : theData)
		{
			int length = 0;
			if (getColumnClass(col) == Integer.class)
			{
				length = String.valueOf(d.at(col)).trim().length();
			}
			if (getColumnClass(col) == String.class)
			{
				length = ((String)d.at(col)).length();
			}
			if (getColumnClass(col) == Boolean.class)
			{
				length = 8;
			}
			if (width < length)
				width = length;
		}
		return width;
	}
	public CustomerPay getCustomer(int row)
	{
		CustomerPay c = new CustomerPay();
		c = (CustomerPay)(theData.get(row));
		return c;
	}
	
	private PayBill paybill;
	private ArrayList<String> colNames;
	private ArrayList<CustomerPay> theData;
	private ArrayList<Class> colClass;
	private Set<Integer> changedRows;

}