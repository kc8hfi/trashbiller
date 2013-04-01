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

public class ReportPaidModel extends AbstractTableModel
{
	public ReportPaidModel(ArrayList<String>c,ArrayList< ArrayList<String> >d)
	{
		colNames = c;
		theData = d;
// 		colClass = new ArrayList<Class>(
// 			Arrays.asList(Integer.class,Integer.class,String.class,String.class,Double.class,Boolean.class)
// 		);
// 		changedRows = new HashSet<Integer>(0);
	}
	
	public Object getValueAt(int row,int col)
	{
// 		CustomerPay d = (CustomerPay)(theData.get(row));
// 		System.out.println("row: " + row + " col: " + col);
// 		String t = "";
		if(theData.size()!=0)
		{
			ArrayList<String> r = theData.get(row);
// 			t = r.get(col);
			return r.get(col);
		}
		else
			return null;
		
// 		return d.at(col);
// 		return t;
	}
	
	public int getColumnCount()
	{
		return colNames.size();
	}
	
	public int getRowCount()
	{
		return theData.size();
// 		if (theData.size() == 0)
// 			return 0;
// 		else
// 		{
// 			return theData.get(0).size();
// 		}
// 		return theData.size();
	}
	
// 	public Class getColumnClass(int col)
// 	{
// 		return colClass.get(col);
// 	}

	public String getColumnName(int col)
	{
		return colNames.get(col);
	}
	
	public boolean isCellEditable(int row, int col)
	{
// 		System.out.println("edit row:"+row+" edit col:"+col);
		//come back here to let the user edit the amount and paid columns
// 		if (col == 4)
// 			return true;
// 		else if (col == 5)
// 			return true;
// 		else
// 			return false;
		return false;
	}
	
// 	public void setValueAt(Object value,int row, int col)
// 	{
// 		CustomerPay d = (CustomerPay)(theData.get(row));
// // 		System.out.println(d);
// // 		System.out.println("set the new value to: " + value +" for " + row + " and " + col);
// 		
// // 		//for the amount
// 		if (col == 4)
// 		{
// 			if (value != null)
// 			{
// 				d.setAmount((double)value);
// 				paybill.enableSaveButton();
// 				changedRows.add(row);
// 				fireTableCellUpdated(row,col);
// 			}
// 		}
// 		//for the paid checkbox
// 		if (col == 5)
// 		{
// 			d.setPaid((boolean)value);
// 			paybill.enableSaveButton();
// 			changedRows.add(row);
// 			fireTableCellUpdated(row,col);
// 		}
// 	}
	private ArrayList<String>colNames;
	private ArrayList< ArrayList<String> >theData;
}