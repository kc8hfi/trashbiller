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


import javax.swing.JTable;
import javax.swing.event.*;
import javax.swing.table.TableModel;

public class MyModelListener implements TableModelListener
{
	public MyModelListener(JTable t)
	{
		table = t;
		isOnlyAdjustLarger = true;
	}
	public void tableChanged(TableModelEvent e)
	{
// 		System.out.println("i don't know what to do here");
		int row = e.getFirstRow();
		int col = e.getColumn();
		MyTableModel model = (MyTableModel)e.getSource();
		//get the column name
		if (col >=0)
		{
			String colName = model.getColumnName(col);
// 			System.out.println("col name: " + colName);
		}
		if (row >= 0)
		{
			Object data = model.getValueAt(row,col);
// 			System.out.println("here it is!: " + data);
		
		}
// 		System.out.println("here it is!"  + e.getType());

		if (e.getType() == TableModelEvent.UPDATE)
		{
			int column = table.convertColumnIndexToView(e.getColumn());
// 			System.out.println("not sure what to do");
// 			if (isOnlyAdjustLarger)
// 			{
// 				TableColumn tableColumn = table.getColumnModel().getColumn(column);
// 				TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
// 				Component c = table.prepareRenderer(cellRenderer, row, column);
// 				int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
// 				
// 			}
		}
		
	}//end tableChanged
	
	private JTable table;
	private boolean isOnlyAdjustLarger;
}