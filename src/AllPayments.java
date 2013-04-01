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
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.util.Calendar;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.util.ArrayList;

public class AllPayments extends JDialog
{
	public AllPayments(MainWindow p)
	{
		parent = p;
		months = new ArrayList<String>(0);
		months.add("January");
		months.add("February");
		months.add("March");
		months.add("April");
		months.add("May");
		months.add("June");
		months.add("July");
		months.add("August");
		months.add("September");
		months.add("October");
		months.add("November");
		months.add("December");
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int yearIndex = 0;
		ArrayList<String> years = new ArrayList<String>(0);
		int q = 0;
		for(int i=currentYear-10;i<currentYear+1;i++)
		{
			years.add(Integer.toString(i));
			if (i == currentYear)
				yearIndex = q;
			q++;
		}
		String [] theYears = years.toArray(new String[years.size()]);
		String [] theMonths = months.toArray(new String[months.size()]);
		
		
		Border paneEdge = BorderFactory.createEmptyBorder(10,10,10,10);		//top,left,bottom,right
		Border littleEdge = BorderFactory.createEmptyBorder(5,10,5,10);	//top,left,bottom,right
		Border lowered = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border el = BorderFactory.createCompoundBorder(paneEdge,lowered);
		Border littleBorder = BorderFactory.createCompoundBorder(littleEdge,lowered);
		
		
// 		JLabel instr1 = new JLabel("2.  Select a month");
		JLabel instr2 = new JLabel("1.  Select a year");
		JLabel instr3 = new JLabel("2.  Click \"Generate\" to create the report");
		
		JPanel instrPanel = new JPanel();
		instrPanel.setLayout(new BoxLayout(instrPanel,BoxLayout.PAGE_AXIS));
// 		instrPanel.add(instr1);
		instrPanel.add(instr2);
		instrPanel.add(instr3);
		
		JPanel instragain = new JPanel(new FlowLayout(FlowLayout.LEADING));
// 		instragain.setBackground(Color.magenta);
		instragain.setBorder(littleEdge);
		instragain.add(instrPanel);
		
		JLabel yearLabel = new JLabel("Year");
		year = new JComboBox<String>(theYears);
		year.setBackground(Color.white);
		year.setSelectedIndex(yearIndex);
		
// 		JLabel monthLabel = new JLabel("Month");
// 		month = new JComboBox<String>(theMonths);
// 		month.setBackground(Color.white);
		

		JPanel yearSelection = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
// 		yearSelection.setBackground(Color.green);
		yearSelection.setBorder(littleEdge);
// 		yearSelection.add(monthLabel);
// 		yearSelection.add(month);
		yearSelection.add(yearLabel);
		yearSelection.add(year);
		
		JButton genButton = new JButton("Generate");
		AllPaymentsAction r = new AllPaymentsAction(this);
		genButton.addActionListener(r);
		JPanel genPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
// 		genPanel.setBackground(Color.cyan);
		genPanel.setBorder(littleEdge);
		genPanel.add(genButton);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.PAGE_AXIS));
		topPanel.setBorder(el);
		topPanel.add(instragain);
		topPanel.add(yearSelection);
 		topPanel.add(genPanel);
		
		
		payments = new JTable();
		payments.setFillsViewportHeight(true);
		payments.setPreferredScrollableViewportSize(new Dimension(700, 300));	//width,height
		
		JScrollPane scrollPane = new JScrollPane(payments,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		payments.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setBorder(littleBorder);
		
// 		scrollPane.setRowHeaderView(RowTable);
// 		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,RowTable.getTableHeader());
   		
		add(topPanel,BorderLayout.PAGE_START);
		add(scrollPane,BorderLayout.CENTER);
		
// 		setLocationRelativeTo(parent);
		setLocation(new Point(parent.getX()+40, parent.getY()+50));
		
		setTitle("All Payment History");

		pack();

	}
	
	public MainWindow getParent()
	{
		return parent;
	}
	public JTable getTable()
	{
		return payments;
	}
	public ArrayList<String> getMonths()
	{
		return months;
	}
	public String getSelectedYear()
	{
		return (String)year.getSelectedItem();
	}
	private MainWindow parent;
	private JTable payments;
	private ArrayList<String>months;
	private JComboBox<String> year;
}