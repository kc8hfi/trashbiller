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
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableMouseListener extends MouseAdapter
{
	public TableMouseListener(Search p)
	{
		parent = p;
	}
	public void mouseClicked(MouseEvent event)
	{
		if (event.getClickCount() == 2)
		{
			JTable target = (JTable)event.getSource();
			int row = target.getSelectedRow();
			int col = target.getSelectedColumn();
// 			System.out.println("row: "+row +" col: "+col);
			MyTableModel tableModel = (MyTableModel)target.getModel();
			Customer c = tableModel.getRow(row);
			
// 			JOptionPane.showMessageDialog(parent.getParent(),
// 				"this is where we will show the record\n",
// 				"tablemouseListener",
// 				JOptionPane.INFORMATION_MESSAGE
// 			);
			EditCustomer editme = new EditCustomer(c,parent);
			editme.setModal(true);
			editme.setVisible(true);
		}
	}
	private Search parent;
}
