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


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JTable;

public class PayTableListener implements PropertyChangeListener,TableModelListener
{
	public PayTableListener(JTable t)
	{
		table = t;
	}
	
	public void propertyChange(PropertyChangeEvent pce)
	{
		System.out.println("this is where to change the column width?");
	}
	
	public void tableChanged(TableModelEvent tme)
	{
// 		System.out.println("column width goes here");
// 		int column = table.convertColumnIndexToView(tme.getColumn());
// 		System.out.println("which column: " + column);
// 		
// 		System.out.println("now many columns: " + table.getModel().getColumnCount());
// 		MyTablePayModel tableModel = (MyTablePayModel)table.getModel();
// 		
// 		System.out.println("there is no column model for the table...:" +table.getColumnModel().getColumnCount());
// 		
// 		int c = table.getColumnModel().getColumnCount();
// 		System.out.println(c);
		
		
		
	}//end tableChanged
	
	
	
	//this is my own functions
	private JTable table;
}