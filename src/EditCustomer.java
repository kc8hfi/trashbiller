
import javax.swing.JDialog;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Collections;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import java.awt.Point;

public class EditCustomer extends JDialog
{
	public EditCustomer(Customer c, Search p)
	{
// 		super (p,true);
// 		parent = p;
// 		System.out.println(c);
		parent = p;
		textFields = new ArrayList<JTextField>(0);
		
		String[] months = {
			"Please Select","January","February","March","April","May","June",
			"July","August","September","October","November","December"
		};
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int currentDay = Calendar.getInstance().get(Calendar.DATE);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		String customerMonth = c.getBeginMonth();
		String customerDay = c.getBeginDay();
		String customerYear = c.getBeginYear();
		
		int monthIndex = 0;
		for(int i=0;i<months.length;i++)
		{
			if (months[i].equals(customerMonth))
			{
				monthIndex = i;
				break;
			}
		}
		
		int dayIndex = 0;
		ArrayList<String> days = new ArrayList<String>(0);
		for(int i=1;i<32;i++)
		{
			String addme = "";
			if (i < 9)
				addme = "0" + Integer.toString(i);
			else
				addme = Integer.toString(i);
			if (customerDay.equals(addme))
				dayIndex = i-1;
			days.add(addme);
		}
		String [] theDays = days.toArray(new String[days.size()]);
		
		int yearIndex = 0;
		ArrayList<String> years = new ArrayList<String>(0);
		for(int i=currentYear-10;i<currentYear+1;i++)
		{
			years.add(Integer.toString(i));
		}
		//add their ending year just in case it ain't there
		if (!years.contains(c.getEndYear()) && !c.getEndYear().equals(""))
			years.add(c.getEndYear());
		//sort the list
		Collections.sort(years);
		//add please select to the beginning now
		years.add(0,"Please Select");
		
		for(int i=0;i<years.size();i++)
		{
			if(years.get(i).equals(customerYear))
				yearIndex = i;
		}
		String [] theYears = years.toArray(new String[years.size()]);
		
		int endMonthIndex = 0;
		for(int i=0;i<months.length;i++)
		{
			if(months[i].equals(c.getEndMonth()))
			{
				endMonthIndex = i;
				break;
			}
		}
		int endDayIndex = 0;
		for(int i=0;i<theDays.length;i++)
		{
			if(theDays[i].equals(c.getEndDay()))
			{
				endDayIndex = i;
				break;
			}
		}
			
		int endYearIndex = 0;
		for(int i=0;i<theYears.length;i++)
		{
			if(theYears[i].equals(c.getEndYear()))
			{
				endYearIndex = i;
				break;
			}
		}
		
		Border paneEdge = BorderFactory.createEmptyBorder(10,10,10,10);	//top,left,bottom,right
		Border lowered = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border compound = BorderFactory.createCompoundBorder(paneEdge,lowered);
		
		
		JLabel acctNoLabel = new JLabel("Account No.");
		JLabel fnameLabel = new JLabel("First Name");
		JLabel lnameLabel = new JLabel("Last Name");
		JLabel addressLabel = new JLabel("Address");
		JLabel cityLabel = new JLabel("City");
		JLabel stateLabel = new JLabel("State");
		JLabel zipLabel = new JLabel("Zip");
		JLabel typeLabel = new JLabel("Type");
		JLabel beginLabel = new JLabel("Begin Date");
		JLabel endLabel = new JLabel("End Date");
		
		JTextField acctNoField = new JTextField(Integer.toString(c.getAccountNo()));
// 		System.out.println("the account no is supposed to be: " + c.getAccountNo());
		JTextField fnameField = new JTextField(c.getFirst());
		JTextField lnameField = new JTextField(c.getLast());
		JTextField addressField = new JTextField(c.getAddress());
		JTextField cityField = new JTextField(c.getCity());
		JTextField stateField = new JTextField(c.getState());
		JTextField zipField = new JTextField(c.getZip());
		
		textFields.add(acctNoField);
		textFields.add(fnameField);
		textFields.add(lnameField);
		textFields.add(addressField);
		textFields.add(cityField);
		textFields.add(stateField);
		textFields.add(zipField);
	
		bgroup = new ButtonGroup();
		JPanel choices = new JPanel();
		choices.setLayout(new BoxLayout(choices,BoxLayout.PAGE_AXIS));
		String[] buttons = {"Residential","Business"};
		for(int i=0;i<buttons.length;i++)
		{
			JRadioButton b = new JRadioButton(buttons[i]);
			bgroup.add(b);
			choices.add(b);
			if (buttons[i].equals(c.getAccountType()))
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

		JPanel beginPanel = new JPanel();
		GroupLayout l = new GroupLayout(beginPanel);
		beginPanel.setLayout(l);
// 		beginPanel.setBackground(Color.yellow);
		
		//automatically add gaps between components
		l.setAutoCreateGaps(true);
		
		//turn on gaps for components that touch the container??
// 		l.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup lhGroup = l.createSequentialGroup();
		// The sequential group in turn contains two parallel groups.
		// One parallel group contains the labels, the other the text fields.
		// Putting the labels in a parallel group along the horizontal axis
		// positions them at the same x location.
		//
		// Variable indentation is used to reinforce the level of grouping.
		
		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup lvGroup = l.createSequentialGroup();
		
		lhGroup.addGroup(l.createParallelGroup().
					addComponent(beginMonth)
		);
		lhGroup.addGroup(l.createParallelGroup().
					addComponent(beginDay)
		);

		lhGroup.addGroup(l.createParallelGroup().
					addComponent(beginYear)
		);

		//the first vertical group will contain one label and one text field
		lvGroup.addGroup(l.createParallelGroup(Alignment.BASELINE).
					addComponent(beginMonth)
					.addComponent(beginDay)
					.addComponent(beginYear)
		);

		
		l.setHorizontalGroup(lhGroup);
		l.setVerticalGroup(lvGroup);

		
		endMonth = new JComboBox<String>(months);
		endMonth.setSelectedIndex(endMonthIndex);
		
		endDay = new JComboBox<String>(theDays);
		endDay.setSelectedIndex(endDayIndex);
		
		endYear = new JComboBox<String>(theYears);
		endYear.setSelectedIndex(endYearIndex);

		JPanel endPanel = new JPanel();
		GroupLayout endPanelLayout = new GroupLayout(endPanel);
		endPanel.setLayout(endPanelLayout);
// 		endPanel.setBackground(Color.green);
		
		//automatically add gaps between components
		endPanelLayout.setAutoCreateGaps(true);
		
		//turn on gaps for components that touch the container??
// 		l.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup endhGroup = endPanelLayout.createSequentialGroup();
		// The sequential group in turn contains two parallel groups.
		// One parallel group contains the labels, the other the text fields.
		// Putting the labels in a parallel group along the horizontal axis
		// positions them at the same x location.
		//
		// Variable indentation is used to reinforce the level of grouping.
		
		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup endvGroup = endPanelLayout.createSequentialGroup();
		
		endhGroup.addGroup(endPanelLayout.createParallelGroup().
					addComponent(endMonth)
		);
		endhGroup.addGroup(endPanelLayout.createParallelGroup().
					addComponent(endDay)
		);
		endhGroup.addGroup(endPanelLayout.createParallelGroup().
					addComponent(endYear)
		);

		//the first vertical group will contain one label and one text field
		endvGroup.addGroup(endPanelLayout.createParallelGroup(Alignment.BASELINE).
					addComponent(endMonth)
					.addComponent(endDay)
					.addComponent(endYear)
		);
		
		endPanelLayout.setHorizontalGroup(endhGroup);
		endPanelLayout.setVerticalGroup(endvGroup);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(compound);
// 		panel.setBackground(Color.magenta);
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
					.addComponent(fnameLabel)
					.addComponent(lnameLabel)
					.addComponent(addressLabel)
					.addComponent(cityLabel)
					.addComponent(stateLabel)
					.addComponent(zipLabel)
					.addComponent(typeLabel)
					.addComponent(beginLabel)
					.addComponent(endLabel)
		);
		//the next horizontal group contains a parallel group with the textfields
		hGroup.addGroup(layout.createParallelGroup().
					addComponent(acctNoField)
					.addComponent(fnameField)
					.addComponent(lnameField)
					.addComponent(addressField)
					.addComponent(cityField)
					.addComponent(stateField)
					.addComponent(zipField)
					.addComponent(choices)
					.addComponent(beginPanel)
					.addComponent(endPanel)
		);

		
		//the first vertical group will contain one label and one text field
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(acctNoLabel)
					.addComponent(acctNoField)
					
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
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
					addComponent(endLabel)
					.addComponent(endPanel)
		);

		
		
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
		
		
		JButton saveChanges = new JButton("Save Changes");
		EditSave saveAction = new EditSave(this,c);
		saveChanges.addActionListener(saveAction);

		//make a panel just for the save changes button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(compound);
// 		buttonPanel.setBackground(Color.cyan);
		buttonPanel.add(saveChanges);

		
		//make a panel to hold the big grouplayout panel
		JPanel testPanel = new JPanel();
		testPanel.setLayout(new BoxLayout(testPanel,BoxLayout.LINE_AXIS));
		testPanel.add(panel);
		
		
//  		add(new JLabel("type up some instructions to go at the top right here"),BorderLayout.PAGE_START);
		JPanel instrPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));		
		instrPanel.setBorder(compound);
		instrPanel.add(new JLabel("instructions"));
		
		add(instrPanel,BorderLayout.PAGE_START);
		add(panel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.PAGE_END);
		
// 		setLocationRelativeTo(parent.getWindow());
          setLocation(new Point(parent.getWindow().getX()+40, parent.getWindow().getY()+50));
		setTitle("Edit Customer");

		pack();
	}
	
	public ButtonGroup getButtons()
	{
		return bgroup;
	}
	public ArrayList<JTextField> getFields()
	{
		return textFields;
	}
	public String getBeginMonth()
	{		
		return (String)beginMonth.getSelectedItem();
	}
	public String getBeginDay()
	{
		return (String)beginDay.getSelectedItem();
	}
	public String getBeginYear()
	{
		return (String)beginYear.getSelectedItem();
	}

	public String getEndMonth()
	{
		return (String)endMonth.getSelectedItem();
	}
	public String getEndDay()
	{
		return (String)endDay.getSelectedItem();
	}
	public String getEndYear()
	{
		return (String)endYear.getSelectedItem();
	}
	
	public MainWindow getWindow()
	{
		return parent.getWindow();
	}
	
	private ArrayList<JTextField> textFields;
	private ButtonGroup bgroup;
	private JComboBox<String> beginMonth;
	private JComboBox<String> beginDay;
	private JComboBox<String> beginYear;
	
	private JComboBox<String> endMonth;
	private JComboBox<String> endDay;
	private JComboBox<String> endYear;
	
	private Search parent;
}
