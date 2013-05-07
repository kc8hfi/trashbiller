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


import javax.swing.JDialog;
import javax.swing.JLabel;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

public class About extends JDialog
{
     public About(MainWindow p)
     {
          parent = p;
          setLayout(new BorderLayout());
          
          Border paneEdge = BorderFactory.createEmptyBorder(10,10,10,10);
          //Border gridEdge = BorderFactory.createEmptyBorder(5,5,5,5);	//top,left,bottom,right
          Border noTopBorder = BorderFactory.createEmptyBorder(0,10,0,10);	//top,left,bottom,right
          Border lowered = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
          Border compound = BorderFactory.createCompoundBorder(paneEdge,lowered);
          Border noTop = BorderFactory.createCompoundBorder(noTopBorder,lowered);
          
          JPanel labels = new JPanel();
          labels.setBorder(compound);
          labels.setLayout(new BoxLayout(labels,BoxLayout.Y_AXIS));
          
          JLabel authorLabel = new JLabel("Author: " + author);
          //authorLabel.setFont(new Font("Monospaced",Font.PLAIN,20));
          //authorLabel.setHorizontalTextPosition(JLabel.LEFT);
          
          JLabel versionLabel = new JLabel("Version: " + version);
          //versionLabel.setFont(new Font("Monospaced",Font.PLAIN,20));

          labels.add(authorLabel);
          labels.add(versionLabel);
          

          JTextArea textArea = new JTextArea(10,10);
          JScrollPane textView = new JScrollPane(textArea);
          textView.setBorder(noTop);
          textArea.setText(license());
          textArea.setEditable(false);
          textArea.setCaretPosition(0);
          
          JPanel icons = new JPanel();
          icons.setBorder(compound);
          String [] files = {"/resources/javalogo.gif","/resources/gplv3-127x51.png"};
          for (int i=0;i<files.length;i++)
          {
               JLabel l = new JLabel(createImageIcon(files[i]));
               icons.add(l);
          }
          
          
          
          add(labels,BorderLayout.PAGE_START);
          add(textView,BorderLayout.CENTER);
          add(icons,BorderLayout.PAGE_END);
          
          //pack();
          setTitle("About...");
          setLocation(new Point(parent.getX()+40, parent.getY()+50));
          setSize(590,400);

     }

     public MainWindow getParent()
     {
          return parent;
     }

     private String license()
     {
          InputStream input = null;
          BufferedReader reader = null;
          String filename = "/resources/COPYING";
          String string = "";
          String line = "";
          try
          {
               input = getClass().getResourceAsStream(filename);
               reader = new BufferedReader(new InputStreamReader(input));
               string = "";
               while (null != (line = reader.readLine()))
               {
                    string = string + line + "\n";
               }
               reader.close();
               input.close();
          }
          catch(Exception e)
          {
               string = "This program is licensed under the GPLv3\n";
               string += "Please see http://www.gnu.org/licenses/gpl.html";
          }
          return string;
     }

     protected ImageIcon createImageIcon(String path)
     {
          URL imgUrl = getClass().getResource(path);
          if (imgUrl != null)
               return new ImageIcon(imgUrl);
          else
               return null;
     }


     private MainWindow parent;
     private String author = "Charles Amey";
     private String version = "0.3";

}
