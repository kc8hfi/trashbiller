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


import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Initialize
{
	public Initialize(MainWindow p)
	{
		parent = p;
		cfgFile = "config.txt";
		cfgFullPath = "";
		
		dbPath = "";
		dbName = "trashbiller.db";
		dbFullPath = "";
	}
	
	public void initialize()
	{
		if (System.getProperty("os.name").toLowerCase().contains("win"))
		{
			win();
		}
		else
		{
			linux();
		}
	}	
	
	private void win()
	{
		cfgFullPath = System.getProperty("user.dir") + System.getProperty("file.separator") + cfgFile;
		dbPath = System.getProperty("user.dir");
		dbFullPath = dbPath + System.getProperty("file.separator") + dbName;
		File configFile = new File(cfgFullPath);
		Config cfgDialog = null;
		if (!configFile.exists())
		{
			try 
			{
				configFile.createNewFile();
				cfgDialog = new Config(parent);
				cfgDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				cfgDialog.setVisible(true);
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(parent,
					"cannot create: \n" + configFile + "\nPlease check permissions "+
					"\n"+e.toString()+"\nthe program should die now"
					,
					"mainwindow",
					JOptionPane.ERROR_MESSAGE
				);
				
			}
		}
	}//end win
	
	
	private void linux()
	{
// 		JOptionPane.showMessageDialog(parent,System.getProperty("os.name"),"not windows, initialize",
// 							JOptionPane.INFORMATION_MESSAGE);
		String dirp = System.getProperty("user.home") + System.getProperty("file.separator") + ".config"+
				System.getProperty("file.separator") + "trashbiller";
		dbPath = dirp;
		dbFullPath = dirp + System.getProperty("file.separator") + dbName;
		File dir = new File(dirp);
		//create the directory if it doesn't exist,  ~/.config/trashbiller should now be there
		if (!dir.exists())
		{
			dir.mkdir();
		}
		//find out if the config file is there
		cfgFullPath = dir + System.getProperty("file.separator") + cfgFile;
		File cf = new File(cfgFullPath);
		Config cfgDialog = null;
		
		if (!cf.exists())
		{
			//config file doesn't exist,  do stuff to make one
			try
			{
				cf.createNewFile();
				cfgDialog = new Config(parent);
				cfgDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				cfgDialog.setVisible(true);
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(parent,
					"cannot create: \n" + cfgFile + "\nPlease check permissions on "+ dirp + 
					"\n"+e.toString(),
					"mainwindow",
					JOptionPane.ERROR_MESSAGE
				);
			}
			
		}
// 		else
// 		{
// 			//show the config dialog anyway
// 			//remove this else part later
// 			cfgDialog = new Config(parent);
// 			cfgDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
// 			cfgDialog.setVisible(true);
// 		}
	}//end linux
	
	
	
	
	
	public String getConfigFile()
	{
		return cfgFullPath;
	}
	public String getDbName()
	{
		return dbName;
	}
	public String getDbPath()
	{
		return dbPath;
	}
	
	private MainWindow parent;
	private String cfgFile;
	private String cfgFullPath;
	
	private String dbName;
	private String dbPath;
	private String dbFullPath;

}//end Initialize