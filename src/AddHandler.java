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
import java.awt.event.ActionEvent;

public class AddHandler extends AbstractAction
{
	public AddHandler(MainWindow p, String t, String cmd)
	{
		super(t);
		parent = p;
		putValue(ACTION_COMMAND_KEY, cmd);	//set the action command
	}

	public void actionPerformed(ActionEvent event)
	{
// 		System.out.println(event.getActionCommand());
		parent.setVisible(event.getActionCommand());
	}
	private MainWindow parent;
}


//AddHandler addHandler = new AddHandler(this,"capital Add", "lower add");