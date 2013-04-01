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


import javax.swing.table.TableColumnModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;

import javax.swing.table.TableColumn;

public class MyTablePayColumnModel extends DefaultTableColumnModel
{
	//override
	public void addColumn(TableColumn tc)
	{
		super.addColumn(tc);
	}
	
	
// 	//override
// 	public void addColumnModelListener(TableColumnModelListener tcml)
// 	{
// 	}
// 	
// 	//override
// 	public void removeColumnModelListener(TableColumnModelListener tcml)
// 	{
// 	}
// 	
// 	//override
// 	public ListSelectionModel getSelectionModel()
// 	{
// 		ListSelectionModel m = new DefaultListSelectionModel();
// 		return m;
// 	}
// 	
// 	//override
// 	public void setSelectionModel(ListSelectionModel m)
// 	{
// 		
// 	}
// 	
// 	//override
// 	public int getSelectedColumnCount()
// 	{
// 		return 1;
// 	}
// 	
// 	//override
// 	public int[] getSelectedColumns()
// 	{
// 		int[] t = {0};
// 		return t;
// 	}
// 	//override
// 	public boolean getColumnSelectionAllowed()
// 	{
// 		return true;
// 	}
// 	
// 	//override
// 	public void setColumnSelectionAllowed(boolean t)
// 	{
// 	}
// 	
// 	public int getTotalColumnWidth()
// 	{
// 		return 10;
// 	}
// 	public int getColumnIndexAtX(int t)
// 	{
// 		return 1;
// 	}
// 	public int getColumnMargin()
// 	{
// 		return 10;
// 	}
	
}