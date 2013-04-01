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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Enumeration;
import java.sql.SQLException;

public class ConfigAction implements ActionListener
{
	public ConfigAction(Config p)
	{
		parent = p;
		window = parent.getWindow();
		configFile = window.getSetupConfig().getConfigFile();
		Properties prop = new Properties();
		try
		{
			prop.load(new FileInputStream(configFile));
			Enumeration e = prop.propertyNames();
			while(e.hasMoreElements())
			{
				String key = (String)e.nextElement();
			}
		}
		catch(IOException ioException)
		{
// 			JOptionPane.showMessageDialog(parent.getParent(),
// 					"cannot open \n" + configFile + "!\n"+ioException.toString(),
// 					"configaction constructor",
// 					JOptionPane.ERROR_MESSAGE
// 			);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Select Location"))
		{
			locationPicker();
		}
		if(e.getActionCommand().equals("Apply"))
		{
// 			System.out.println("apply the changes");
			saveChanges();
		}
		if (e.getActionCommand().equals("Ok"))
		{
// 			System.out.println("ok, save changes, close");
			saveChanges();
// 			//i think we need to reload the settings once ok is clicked
// 			System.out.println("reload the settings? ");
			window.setupConnection();
// 			try
// 			{
// 				window.setupConnection();
// 			}
// 			catch(IOException ioe)
// 			{
// 			}
// 			catch(SQLException sqle)
// 			{
// 			}
			parent.dispose();
		}
		if (e.getActionCommand().equals("Cancel"))
		{
// 			System.out.println("cancel,do nothing, close");
			parent.dispose();
		}
		
	}
	
	private void saveChanges()
	{
// 		System.out.println("saveChanges configFile: " + configFile);
		Properties prop = new Properties();
		String dbaseLocation = "";
		if (parent.getPath().equals(""))
		{
			//since they didn't pick a place to put it, we need a default full path to the database
			dbaseLocation = window.getSetupConfig().getDbPath();
			System.out.println("path was empty, set it to: " + dbaseLocation);
			
			//update the text field
			parent.setPath(window.getSetupConfig().getDbPath());
			
			//write out to the configuration file
			prop.setProperty("dbase_location",dbaseLocation);
			prop.setProperty("dbase_name", window.getSetupConfig().getDbName());
		}
		else
		{
			System.out.println("set the path to whatever they picked: "+ parent.getPath());
			//what the selected,  then the file separator, then the name of the dbase
			String nfp = parent.getPath();
			prop.setProperty("dbase_location",nfp);
			prop.setProperty("dbase_name", window.getSetupConfig().getDbName());
		}

		//save the file
		try
		{
			prop.store(new FileOutputStream(window.getSetupConfig().getConfigFile()),null);
		}
		catch(IOException ioException)
		{
			JOptionPane.showMessageDialog(parent.getParent(),
					"cannot open \n" + configFile + "!\n"+ioException.toString(),
					"configaction constructor",
					JOptionPane.ERROR_MESSAGE
			);
		}
// // 		catch(FileNotFoundException fnf)
// // 		{
// // 			JOptionPane.showMessageDialog(parent.getParent(),
// // 					"cannot open \n" + configFile + "!\n"+fnf.toString(),
// // 					"saveChanges",
// // 					JOptionPane.ERROR_MESSAGE
// // 			);
// // 		}

	}//end saveChanges
	
	private void locationPicker()
	{
		JFileChooser fc = new JFileChooser();
		fc.setFileHidingEnabled(false);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
// 		fc.setAcceptAllFileFilterUsed(false);

		String dbaseLocation  = parent.getPath();
		int returnVal = fc.showOpenDialog(parent);
// 		System.out.println(returnVal);
		
		switch(returnVal)
		{
			case JFileChooser.APPROVE_OPTION:
				String t = fc.getSelectedFile().getAbsolutePath();
				System.out.println("approve: " + t);
				parent.setPath(t);
				
// 				String t = fc.getSelectedFile().getAbsolutePath() + System.getProperty("file.separator") + 
// 					parent.getParent().getSetupConfig().getDbasePath();
// 				
// 				prop.setProperty("dbase_location",t);
// 				System.out.println(t);
// // 				parent.setPath(fc.getSelectedFile().getAbsolutePath() + System.getProperty("file.separator"));
				
			break;
// 			case JFileChooser.CANCEL_OPTION:
// 				System.out.println("set default location if the current path is empty");
// // 				if (parent.getPath().equals(""))
// // 				{
// // // 					System.out.println(Initialize.getFilePath()+ System.getProperty("file.separator")+Initialize.getDbaseName());
// // 					dbaseLocation = Initialize.getFilePath()+ System.getProperty("file.separator")+Initialize.getDbaseName();
// // 					parent.setPath(dbaseLocation);
// // // 					prop.setProperty("dbase_location",dbaseLocation);
// // 				}
// 			break;
		}
	}//end locationPicker
	
	private Config parent;
	private MainWindow window;
	private Properties prop;
	private String configFile;

}
