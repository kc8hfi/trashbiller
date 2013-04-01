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


import javax.swing.JFrame;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class program
{
	public static void main(String[] args)
	{
		MainWindow w = null;
		try
		{
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true); 
			
			w = new MainWindow();
			w.setVisible(true);
			w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
			JOptionPane.showMessageDialog(w,
					cnfe,
					"Critical Error",
					JOptionPane.ERROR_MESSAGE
			);
		}
	}//end main
}//end class
