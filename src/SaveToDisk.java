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

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class SaveToDisk
{
     public SaveToDisk(MainWindow p,ArrayList<ArrayList<String>> d)
     {
          parent = p;
          data = d;
          save();
//           super(text,icon); //text is the actual name
//           myTreeClass = t;
//           putValue(ACTION_COMMAND_KEY,actionCmd);     //action command
//           putValue(SHORT_DESCRIPTION, toolTip); //used for tooltip text
//           putValue(MNEMONIC_KEY, mnemonic);
//           putValue(ACCELERATOR_KEY,accelerator);
     }//end constructor
     
     protected void save()
     {
//           System.out.println("show the file picker");
          JFileChooser filePicker = new JFileChooser();
          
//           filePicker.setFileHidingEnabled(false);
//           filePicker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          boolean exportData = false;
          File f = null;
          do
          {
               int returnValue = filePicker.showSaveDialog(parent);
               if (returnValue == filePicker.APPROVE_OPTION)
               {
                    f = filePicker.getSelectedFile();
                    String split[] = f.getName().split("\\.");
                    String newname = "";
                    if (split.length == 1)
                    {
     //                     System.out.println("no extension, so put one");
                         newname = split[0] + ".csv";
                    }
                    else if(!split[1].equals("csv") )
                    {
     //                     System.out.println("not csv, so put it");
                         newname = split[0] + ".csv";
                    }
                    else
                         newname = f.getName();
                    
                    newname = f.getParentFile() + System.getProperty("file.separator") + newname;
                    System.out.println("new name: " + newname);
                    //make a new file
                    
                    f = new File(newname);
                    if (f.exists())
                    {
//                          System.out.println("the file exists, ask them if they want to overwrite it");
                         int overwrite = JOptionPane.showConfirmDialog(null,"The file exists, overwrite?","File Exists",
                                                                       JOptionPane.YES_NO_OPTION);
                         System.out.println("overwrite: " + overwrite);
                         if(overwrite == 0)//overwrite the file, 0 = yes, 1 = no
                         {
                              exportData = true;
                         }
                         else //don't overwrite the file
                         {
                              exportData = false;
                         }
                    }
                    else
                    {
                         exportData = true;
                    }
               }
               //don't save the file at all
               else
               {
                    break;
               }
          }
          while(exportData == false);
          
          if (exportData)
          {
               try 
               {
                    FileWriter writer = new FileWriter(f);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    PrintWriter out = new PrintWriter(bufferedWriter);
                    //loop through the tree
                    Csv combine = new Csv();
                    for(int i=0;i<data.size();i++)
                    {
                         String s = combine.combine(data.get(i));
//                          System.out.println("write this: " + s);
                         out.println(s);
                    }
                    out.close();
                    bufferedWriter.close();
                    writer.close();
               }
               catch (Exception fe)
               {
                    System.out.println("something happened, " + fe);
                    JOptionPane.showMessageDialog(parent,
                         "Can't write to\n" + fe,
                         "Export data error!",
                         JOptionPane.ERROR_MESSAGE
                    );
               }
          }//end write the file
     }//end save

     private ArrayList<ArrayList<String>> data;
     private MainWindow parent;
}//end SaveAction

