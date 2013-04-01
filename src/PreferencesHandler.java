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
import javax.swing.JDialog;
import javax.swing.JFrame;

public class PreferencesHandler implements ActionListener
{
	public PreferencesHandler(MainWindow p)
	{
		parent = p;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		Config c = new Config(parent);
		c.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		c.setVisible(true);
	}
	private MainWindow parent;
}