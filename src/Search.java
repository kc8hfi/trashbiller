import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.TableRowSorter;
import javax.swing.border.Border;
import javax.swing.BorderFactory; 
import javax.swing.border.EtchedBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class Search extends JPanel
{
	Search (MainWindow p) 
	{
		parent = p;

		//initialize a new arraylist with nothing in it
		textFields = new ArrayList<JTextField>(0);
		
		Border paneEdge = BorderFactory.createEmptyBorder(10,10,10,10);
		Border gridEdge = BorderFactory.createEmptyBorder(5,5,5,5);		//top,left,bottom,right
		Border noEdge = BorderFactory.createEmptyBorder(0,10,0,10);		//top,left,bottom,right
		Border lowered = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border lowerpane = BorderFactory.createCompoundBorder(lowered,paneEdge);
		Border panelower = BorderFactory.createCompoundBorder(paneEdge,lowered);
		Border borderNoSpace = BorderFactory.createCompoundBorder(noEdge,lowered);
		
		SearchAction byAccount = new SearchAction(this,"Account No.","account");
		SearchAction byEverythingElse = new SearchAction(this, "dont know","everything else" );
// 		t.setAction(action);
// 		Collections.addAll(commands,"account","first","last","address","city","state","zip");

		JLabel accountNoLabel = new JLabel("Account No.");
		JLabel fnameLabel = new JLabel("First Name");
		JLabel lnameLabel = new JLabel("Last Name");
		JLabel addressLabel = new JLabel("Address");
		JLabel cityLabel = new JLabel("City");
		JLabel stateLabel = new JLabel("State");
		JLabel zipLabel = new JLabel("Zip");
		
		JTextField accountNo = new JTextField();
		accountNo.setAction(byAccount);
		JTextField fname = new JTextField();
		fname.setAction(byEverythingElse);
		JTextField lname = new JTextField();
		lname.setAction(byEverythingElse);
		JTextField address = new JTextField();
		address.setAction(byEverythingElse);
		JTextField city = new JTextField();
		city.setAction(byEverythingElse);
		JTextField state = new JTextField();
		state.setAction(byEverythingElse);
		JTextField zip = new JTextField();
		zip.setAction(byEverythingElse);
		
		textFields.add(accountNo);
		textFields.add(fname);
		textFields.add(lname);
		textFields.add(address);
		textFields.add(city);
		textFields.add(state);
		textFields.add(zip);
		
		JPanel acctNoPanel = new JPanel();
		acctNoPanel.setBorder(lowerpane);
// 		acctNoPanel.setBackground(Color.yellow);
		acctNoPanel.setLayout(new BoxLayout(acctNoPanel,BoxLayout.LINE_AXIS));
		acctNoPanel.add(accountNoLabel);
		acctNoPanel.add(Box.createRigidArea(new Dimension(10,0)));	//width,height
		accountNo.setAction(byAccount);
		acctNoPanel.add(accountNo);
		

		JPanel panel = new JPanel();
		panel.setBorder(lowerpane);
// 		panel.setBackground(Color.green);
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		//automatically add gaps between components
		layout.setAutoCreateGaps(true);
		
		//turn on gaps for components that touch the container??
// 		layout.setAutoCreateContainerGaps(true);
		
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
					addComponent(fnameLabel)
					.addComponent(addressLabel)
					.addComponent(stateLabel)
		);
		//the next horizontal group contains a parallel group with the textfields
		hGroup.addGroup(layout.createParallelGroup().
					addComponent(fname)
					.addComponent(address)
					.addComponent(state)
		);		
		
		//the next horizontal group will contain a parallel group with the labels
		hGroup.addGroup(layout.createParallelGroup().
					addComponent(lnameLabel)
					.addComponent(cityLabel)
					.addComponent(zipLabel)
		);
		//the next horizontal group contains a parallel group with the textfields
		hGroup.addGroup(layout.createParallelGroup().
					addComponent(lname)
					.addComponent(city)
					.addComponent(zip)
		);		
		
		
		//the first vertical group will contain one label and one text field,label, textfield
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(fnameLabel)
					.addComponent(fname)
					.addComponent(lnameLabel)
					.addComponent(lname)
		);
		//second group, label,textfield,label,textfield
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(addressLabel)
					.addComponent(address)
					.addComponent(cityLabel)
					.addComponent(city)
		);
		//third group, label,textfield,label,textfield
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(stateLabel)
					.addComponent(state)
					.addComponent(zipLabel)
					.addComponent(zip)
		);
		
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		JPanel topPanel = new JPanel();
// 		topPanel.setBackground(Color.red);
		topPanel.setBorder(paneEdge);
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.PAGE_AXIS));
		topPanel.add(acctNoPanel);
		topPanel.add(Box.createRigidArea(new Dimension(0,10)));
		topPanel.add(panel);
		
		
		MyTableModel tableModel = new MyTableModel();
		table = new JTable(tableModel);
		
		MyModelListener tableListener = new MyModelListener(table);
		tableModel.addTableModelListener(tableListener);

		trs = new TableRowSorter<MyTableModel>(tableModel);
		
		TableMouseListener listener = new TableMouseListener(this);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(800, 300));	//width,height
// 		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowSorter(trs);
		table.addMouseListener(listener);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(borderNoSpace);
		
		statusText = new JLabel(" ");
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(gridEdge);
		statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.LINE_AXIS));
		statusPanel.add(statusText);
		
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.LINE_AXIS));
// 		bottomPanel.setBackground(Color.magenta);
		bottomPanel.setBorder(panelower);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(statusPanel);
		
		
		
		setLayout(new BorderLayout());
		add(topPanel,BorderLayout.PAGE_START);
		add(scrollPane,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.PAGE_END);
		
	}//end constructor
	
	public void setStatus(String s)
	{
		statusText.setText(s);
	}
	

	
	
	
	public ArrayList<JTextField> getTextFields()
	{
		return textFields;
	}
	
	public ArrayList<String> getLabels()
	{
		return labels;
	}
	
	public ArrayList<String> getCmds()
	{
		return commands;
	}
	
	public JTable getTable()
	{
		return table;
	}
	public TableRowSorter<MyTableModel> getSorter()
	{
		return trs;
	}
	public MainWindow getWindow()
	{
		return parent;
	}
	
	private ArrayList<JTextField> textFields;
	private ArrayList<String> labels;
	private ArrayList<String> commands;
	private JLabel statusText;
	private JTable table;
	private TableRowSorter<MyTableModel> trs;
	
	private MainWindow parent;
	
}//end class
