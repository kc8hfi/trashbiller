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


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReportsHandler implements ActionListener
{
	public ReportsHandler(MainWindow p)
	{
		parent = p;
	}
	public void actionPerformed(ActionEvent evt)
	{
// 		System.out.println("which one to open: " + evt.getActionCommand());
		if (evt.getActionCommand().equals("all_payments"))
		{
			AllPayments everything = new AllPayments(parent);
			everything.setModal(true);
			everything.setVisible(true);
		}
		if (evt.getActionCommand().equals("paid") || evt.getActionCommand().equals("not_paid"))
		{
			PaidOrNot p = new PaidOrNot(parent,evt.getActionCommand());
			p.setModal(true);
			p.setVisible(true);
		}
	}//end actionPerformed
	private MainWindow parent;
}