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


import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.Calendar;
import javax.swing.table.TableRowSorter;
import javax.swing.border.Border;
import javax.swing.BorderFactory; 
import javax.swing.border.EtchedBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class PayBill extends JPanel
{
	public PayBill(MainWindow p)
	{
		parent = p;
		statusText = new JLabel();
		
		String[] months = {
			"January","February","March","April","May","June",
			"July","August","September","October","November","December"
		};

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentDay = Calendar.getInstance().get(Calendar.DATE);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		
		int monthIndex = 0;
		for(int i=0;i<months.length;i++)
		{
			if (i == currentMonth)
			{
				monthIndex = i;
				break;
			}
		}
		
		int dayIndex = 1;
		ArrayList<String> days = new ArrayList<String>(0);
		for(int i=1;i<=31;i++)
		{
			String addme = "";
			if (i<=9)
				addme = "0" + Integer.toString(i);
			else
				addme = Integer.toString(i);
			if (currentDay == i)
				dayIndex = i-1;
			days.add(addme);
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


		Border paneEdge = BorderFactory.createEmptyBorder(10,10,10,10);	//top,left,bottom,right
		Border smallEdge = BorderFactory.createEmptyBorder(5,5,5,5);		//top,left,bottom,right
		Border gridEdge = BorderFactory.createEmptyBorder(0,0,10,0);	//top,left,bottom,right
		Border noEdge = BorderFactory.createEmptyBorder(0,10,0,10);		//top,left,bottom,right
		Border lowered = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border compound = BorderFactory.createCompoundBorder(paneEdge,lowered);
		Border borderSpace = BorderFactory.createCompoundBorder(lowered, smallEdge);
		Border borderNoSpace = BorderFactory.createCompoundBorder(noEdge,lowered);

		JLabel label = new JLabel("Choose the month and year for the payment.");
		JLabel monthLabel = new JLabel("Month");
		JLabel dayLabel = new JLabel("Day");
		JLabel yearLabel = new JLabel("Year");
		
		month = new JComboBox<String>(months);
		month.setBackground(Color.white);
		month.setSelectedIndex(monthIndex);

		day = new JComboBox<String>(theDays);
		day.setBackground(Color.white);
		day.setSelectedIndex(dayIndex);
		
		year = new JComboBox<String>(theYears);
		year.setBackground(Color.white);
		year.setSelectedIndex(yearIndex);
		
		StartPaymentAction submitAction = new StartPaymentAction(this,"Submit");
		JButton submitButton = new JButton(submitAction);

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		//automatically add gaps between components
		layout.setAutoCreateGaps(true);
		
		//turn on gaps for components that touch the container??
		layout.setAutoCreateContainerGaps(true);
		
		//sequential group, horizontal axis
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		JPanel labelPanel = new JPanel();
		labelPanel.add(label);
			
		//contains a horizontal group of a parallel group with the labels
		
		hGroup.addGroup(layout.createParallelGroup().
				addComponent(monthLabel)
				.addComponent(dayLabel)
				.addComponent(yearLabel)
		);
		
		//the next horizontal group will be a parallel group of the combo boxes
		hGroup.addGroup(layout.createParallelGroup().
			addComponent(month)
			.addComponent(day)
			.addComponent(year)
		);
		
		//the first vertical layout will be the label and the combo box
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(monthLabel)
				.addComponent(month)
		);
		//the first vertical layout will be the label and the combo box
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(dayLabel)
				.addComponent(day)
		);
		//the second layout is the same way
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(yearLabel)
				.addComponent(year)
		);
		
		
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		
		JPanel cpanel = new JPanel();
		cpanel.setLayout(new BoxLayout(cpanel,BoxLayout.PAGE_AXIS));
		cpanel.add(labelPanel);
		cpanel.add(panel);
		cpanel.add(buttonPanel);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(compound);
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(cpanel,BorderLayout.LINE_START);
		
		ArrayList<String> c = new ArrayList<String>(0);
		ArrayList<CustomerPay> d = new ArrayList<CustomerPay>(0);
		
// 		MyTablePayModel tableModel = new MyTablePayModel(this,c,d);
		MyTablePayModel tableModel = new MyTablePayModel(this,c,d);
		table = new JTable(tableModel);
		
		trs = new TableRowSorter<MyTablePayModel>(tableModel);
		
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(800, 300));	//width,height
		table.setRowSorter(trs);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(borderNoSpace);
		
		SavePaymentAction saveAction = new SavePaymentAction(this,"Save Changes");
		saveChanges = new JButton(saveAction);
		saveChanges.setEnabled(false);
		JPanel saveButtonPanel = new JPanel();
		saveButtonPanel.setBorder(gridEdge);
// 		saveButtonPanel.setBackground(Color.yellow);
		saveButtonPanel.add(saveChanges);

		statusText = new JLabel(" ");
		
		JPanel statusPanel = new JPanel();
// 		statusPanel.setBackground(Color.green);
		statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.LINE_AXIS));
		statusPanel.setBorder(borderSpace);
		statusPanel.add(Box.createHorizontalGlue());
		statusPanel.add(statusText);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.PAGE_AXIS));
		bottomPanel.setBorder(paneEdge);
		bottomPanel.add(saveButtonPanel);
		bottomPanel.add(statusPanel);
		
		//add everything now
		setLayout(new BorderLayout());
		add(controlPanel,BorderLayout.PAGE_START);
		add(scrollPane,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.PAGE_END);
	}
	
	public void setStatus(String s)
	{
		statusText.setText(s);
	}

	public JTable getTable()
	{
		return table;
	}
	public TableRowSorter<MyTablePayModel> getSorter()
	{
		return trs;
	}
	public void enableSaveButton()
	{
		saveChanges.setEnabled(true);
	}
	public void disableSaveButton()
	{
		saveChanges.setEnabled(false);
	}

	public String getMonth()
	{
		return month.getSelectedItem().toString();
	}
	public String getDay()
	{
		return day.getSelectedItem().toString();
	}
	public String getYear()
	{
		return year.getSelectedItem().toString();
	}
	
	
	public MainWindow getWindow()
	{
		return parent;
	}
	
	private MainWindow parent;
	private JTable table;
	private TableRowSorter<MyTablePayModel> trs;
	private JLabel statusText;
	private JButton saveChanges;
	private JComboBox<String> month;
	private JComboBox<String> day;
	private JComboBox<String> year;
}