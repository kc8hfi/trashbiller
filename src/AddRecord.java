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


import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Box;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.BorderFactory; 
import javax.swing.border.EtchedBorder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AddRecord extends JPanel
{
	AddRecord (MainWindow p) 
	{
		parent = p;
		textFields = new ArrayList<JTextField>(0);
		
		String[] months = {
			"January","February","March","April","May","June",
			"July","August","September","October","November","December"
		};

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int currentDay = Calendar.getInstance().get(Calendar.DATE);
		
		//set up the current month's index
		int monthIndex = 0;
		for(int i=0;i<months.length;i++)
		{
			if (i == currentMonth)
			{
				monthIndex = i;
				break;
			}
		}
		
		//set up the current day's index
		ArrayList<String> days = new ArrayList<String>(0);
		int dayIndex = 0;
		for(int i=1;i<=31;i++)
		{
			String addme = "";
			if (i<=9)
				addme = "0" + Integer.toString(i);
			else
				addme = Integer.toString(i);
			
			days.add(addme);
			if(i == currentDay)
				dayIndex = i-1;
		}
		String [] theDays = days.toArray(new String[days.size()]);
		
		int yearIndex = 0;
		ArrayList<String> years = new ArrayList<String>(0);
		int t = 0;
		for(int i=currentYear-10;i<currentYear+1;i++)
		{
			years.add(Integer.toString(i));
			if (i == currentYear)
				yearIndex = t;
			t++;
		}
		String [] theYears = years.toArray(new String[years.size()]);
/*
 *create a grouplayout here
 */
		//this is my stuff here
		Border paneEdge = BorderFactory.createEmptyBorder(10,10,10,10);
		Border gridEdge = BorderFactory.createEmptyBorder(5,5,5,5);	//top,left,bottom,right		
		Border lowered = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border compound = BorderFactory.createCompoundBorder(paneEdge,lowered);

		JLabel acctNoLabel = new JLabel("Account No.");
          JLabel stickerNoLabel = new JLabel("Sticker No.");
		JLabel fnameLabel = new JLabel("First Name");
		JLabel lnameLabel = new JLabel("Last Name");
		JLabel addressLabel = new JLabel("Address");
		JLabel cityLabel = new JLabel("City");
		JLabel stateLabel = new JLabel("State");
		JLabel zipLabel = new JLabel("Zip");
		JLabel typeLabel = new JLabel("Type");
		JLabel beginLabel = new JLabel("Begin Date");
		JLabel endLabel = new JLabel("End Date");
		
		JTextField acctNoField = new JTextField();
          JTextField stickerNoField = new JTextField();
		JTextField fnameField = new JTextField();
		JTextField lnameField = new JTextField();
		JTextField addressField = new JTextField();
		JTextField cityField = new JTextField();
		JTextField stateField = new JTextField();
		JTextField zipField = new JTextField();
		
		textFields.add(acctNoField);
          textFields.add(stickerNoField);
		textFields.add(fnameField);
		textFields.add(lnameField);
		textFields.add(addressField);
		textFields.add(cityField);
		textFields.add(stateField);
		textFields.add(zipField);
		
		JButton saveChanges = new JButton("Save Changes");
		AddRecordAction addRecordAction = new AddRecordAction(this);
		saveChanges.addActionListener(addRecordAction);

	
		bgroup = new ButtonGroup();
		JPanel choices = new JPanel();
		choices.setLayout(new BoxLayout(choices,BoxLayout.PAGE_AXIS));
		String[] buttons = {"Residential","Business"};
		for(int i=0;i<buttons.length;i++)
		{
			JRadioButton b = new JRadioButton(buttons[i]);
			bgroup.add(b);
			choices.add(b);
			if (i==0)
				b.setSelected(true);
		}
		

		beginMonth = new JComboBox<String>(months);
		beginMonth.setBackground(Color.white);
		beginMonth.setSelectedIndex(monthIndex);
		
		beginDay = new JComboBox<String>(theDays);
		beginDay.setBackground(Color.white);
		beginDay.setSelectedIndex(dayIndex);
		
		beginYear = new JComboBox<String>(theYears);
		beginYear.setBackground(Color.white);
		beginYear.setSelectedIndex(yearIndex);

		
		JPanel beginPanel = new JPanel(new FlowLayout(FlowLayout.LEADING,5,0));
// 		beginPanel.setBackground(Color.yellow);
		beginPanel.add(beginMonth);
		beginPanel.add(beginDay);
		beginPanel.add(beginYear);
	

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		//automatically add gaps between components
		layout.setAutoCreateGaps(true);
		
		//turn on gaps for components that touch the container??
		layout.setAutoCreateContainerGaps(true);
		
		//sequential group, horizontal axis
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		// The sequential group in turn contains two parallel groups.
		// One parallel group contains the labels, the other the text fields.
		// Putting the labels in a parallel group along the horizontal axis
		// positions them at the same x location.
		//
		// Variable indentation is used to reinforce the level of grouping.
		
		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		// The sequential group contains two parallel groups that align
		// the contents along the baseline. The first parallel group contains
		// the first label and text field, and the second parallel group contains
		// the second label and text field. By using a sequential group
		// the labels and text fields are positioned vertically after one another.

		
		//the first horizontal group will contain a parallel group with the labels
		hGroup.addGroup(layout.createParallelGroup().
					addComponent(acctNoLabel)
                         .addComponent(stickerNoLabel)
					.addComponent(fnameLabel)
					.addComponent(lnameLabel)
					.addComponent(addressLabel)
					.addComponent(cityLabel)
					.addComponent(stateLabel)
					.addComponent(zipLabel)
					.addComponent(typeLabel)
					.addComponent(beginLabel)
// 					.addComponent(endLabel)
		);
		//the next horizontal group contains a parallel group with the textfields
		hGroup.addGroup(layout.createParallelGroup().
					addComponent(acctNoField)
                         .addComponent(stickerNoField)
					.addComponent(fnameField)
					.addComponent(lnameField)
					.addComponent(addressField)
					.addComponent(cityField)
					.addComponent(stateField)
					.addComponent(zipField)
					.addComponent(choices)
					.addComponent(beginPanel)
// 					.addComponent(endPanel)
		);

		
		//the first vertical group will contain one label and one text field
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(acctNoLabel)
					.addComponent(acctNoField)
					
		);
          vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
                         addComponent(stickerNoLabel)
                         .addComponent(stickerNoField)
                         
          );
		//next vertical group
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(fnameLabel)
					.addComponent(fnameField)
		);
		//next vertical group
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(lnameLabel)
					.addComponent(lnameField)
		);
		//next vertical group
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(addressLabel)
					.addComponent(addressField)
		);
		//next vertical group
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(cityLabel)
					.addComponent(cityField)
		);
		//next vertical group
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(stateLabel)
					.addComponent(stateField)
		);
		//next vertical group
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(zipLabel)
					.addComponent(zipField)
		);
				//next vertical group
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(typeLabel)
					.addComponent(choices)
		);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(beginLabel)
					.addComponent(beginPanel)
		);
// 		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
// 					addComponent(endLabel)
// 					.addComponent(endPanel)
// 		);

		
		
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
		
		
		//make a panel just for the save changes button
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(saveChanges);
		
		
		//make a panel to hold the "panel" and the buttonpanel
		JPanel lfbPanel = new JPanel();
		lfbPanel.setLayout(new BoxLayout(lfbPanel,BoxLayout.PAGE_AXIS));
		lfbPanel.setBorder(compound);
		lfbPanel.add(panel);
		lfbPanel.add(buttonPanel);
		

		JPanel slPanel = new JPanel();
		slPanel.setLayout(new BoxLayout(slPanel,BoxLayout.LINE_AXIS));
		slPanel.setBorder(gridEdge);
		statusText = new JLabel(" ");
		slPanel.add(Box.createHorizontalGlue());
		slPanel.add(statusText);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.LINE_AXIS));
		statusPanel.setBorder(compound);
		statusPanel.add(slPanel);
		
		setLayout(new BorderLayout());
		add(lfbPanel,BorderLayout.PAGE_START);
		add(statusPanel,BorderLayout.PAGE_END);


// 		add(saveChanges);
	}//end constructor

	public ArrayList<JTextField> getFields()
	{
		return textFields;
	}
	
	public ButtonGroup getButtons()
	{
		return bgroup;
	}

	public void setStatusMsg(String t)
	{
		statusText.setText(t);
	}

	public String getBeginDate()
	{
		String t = (String)beginMonth.getSelectedItem() + (String)beginDay.getSelectedItem() + 
					(String)beginYear.getSelectedItem();
		System.out.println("the date: " + t );
		try
		{
			DateFormat formatter = new SimpleDateFormat("MMMddyyyy");
			//turn it into a Date first
			Date mydate = formatter.parse(t);
			System.out.println("turn into date: " + mydate);
		
			//reformat it
			SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
			t = d1.format(mydate);
		}
		catch(ParseException e)
		{
			t = "";
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println(iae);
		}
		return t;
	}
	
	public MainWindow getWindow()
	{
		return parent;
	}
	
	private MainWindow parent;
	private ArrayList<JTextField> textFields;
	private ButtonGroup bgroup;
	private JLabel statusText;
	
	private JComboBox<String> beginMonth;
	private JComboBox<String> beginDay;
	private JComboBox<String> beginYear;

	
}
