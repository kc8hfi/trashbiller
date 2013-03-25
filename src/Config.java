
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory; 
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Config extends JDialog
{
	public Config(MainWindow p)
	{
		super(p, true);
		parent = p;

		ConfigAction fpa = new ConfigAction(this);
		dbasePath = new JTextField();
		
		Properties prop = new Properties();
		
		String cfg = parent.getSetupConfig().getConfigFile();

// 		JOptionPane.showMessageDialog(p,
// 		"constructor for config, \n" + cfg + "!\n",
// 		"configuration",
// 		JOptionPane.ERROR_MESSAGE
// 		);

		try
		{
			prop.load(new FileInputStream(cfg));
			if (prop.getProperty("dbase_location") == null)
				dbasePath.setColumns(15);
			else
				dbasePath.setText(prop.getProperty("dbase_location"));
			
		}
		catch(IOException ioException)
		{
			//we don't care if the file ain't there, will create it anyway
// 			JOptionPane.showMessageDialog(p,
// 					"cannot open \n" + t + "!\n"+ioException.toString(),
// 					"configuration",
// 					JOptionPane.ERROR_MESSAGE
// 			);
		}
		
		
		Border paneEdge = BorderFactory.createEmptyBorder(10,10,10,10);	//top,left,bottom,right
		Border lowered = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border el = BorderFactory.createCompoundBorder(paneEdge,lowered);
		
		TitledBorder title = BorderFactory.createTitledBorder("Database Location");
		
		Border te = BorderFactory.createCompoundBorder(title,paneEdge);
		Border ete = BorderFactory.createCompoundBorder(paneEdge,te);
		
// 		String[] items = {"Database"};
// 		JList<String> configItems = new JList<String>(items);
// 		configItems.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
// 		configItems.setSelectedIndex(0);
// 		
// 		JScrollPane listScroller = new JScrollPane(configItems);
// 		listScroller.setBorder(paneEdge);
		
		JLabel locLabel = new JLabel("Location:");
		JLabel filenameLabel = new JLabel("Filename:");
		
		JTextField filename = new JTextField("trashbiller.db");
		filename.setBackground(Color.white);
		filename.setEditable(false);
		

// 		dbasePath.setEditable(false);
		JButton selectButton = new JButton("Select...");
		selectButton.setActionCommand("Select Location");
		selectButton.addActionListener(fpa);

		JPanel selectPanel = new JPanel();
		selectPanel.setPreferredSize(new Dimension(450,150));	//width,height
// 		selectPanel.setBackground(Color.green);
		selectPanel.setBorder(ete);
		//this works nicely
// 		selectPanel.setBorder(compound1);
		GroupLayout layout = new GroupLayout(selectPanel);
		selectPanel.setLayout(layout);
		
		//automatically add space between components
		layout.setAutoCreateGaps(true);
		
		//create a horizontal group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		
		//horizontal group that contains label,textfield,button
		hGroup.addGroup(layout.createParallelGroup()
					.addComponent(locLabel)
					.addComponent(filenameLabel)
		);
		hGroup.addGroup(layout.createParallelGroup()
					.addComponent(dbasePath)
					.addComponent(filename)
		);
		hGroup.addGroup(layout.createParallelGroup()
					.addComponent(selectButton)
		);
		
		//create a vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		// The sequential group contains two parallel groups that align
		// the contents along the baseline. The first parallel group contains
		// the first label and text field, and the second parallel group contains
		// the second label and text field. By using a sequential group
		// the labels and text fields are positioned vertically after one another.
		
		//vertical group, label,textfield,button
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
					.addComponent(locLabel)
					.addComponent(dbasePath)
					.addComponent(selectButton)
		);
		//next group,  label,textfield
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
					.addComponent(filenameLabel)
					.addComponent(filename)
		);
		
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
		
		//the ok,apply,cancel buttons
		JPanel buttons = new JPanel();
// 		buttons.setBackground(Color.red);
		buttons.setBorder(paneEdge);
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.LINE_AXIS));
		buttons.add(Box.createHorizontalGlue());
		String[] b = {"Ok","Apply","Cancel"};
// 		String[] b = {"Ok","Apply"};
		for(int i=0;i<b.length;i++)
		{
			JButton button = new JButton(b[i]);
			button.addActionListener(fpa);
			buttons.add(button);
			buttons.add(Box.createRigidArea(new Dimension(5, 0)));
		}
		
		
// 		add(listScroller,BorderLayout.LINE_START);
		add(selectPanel,BorderLayout.CENTER);
		add(buttons,BorderLayout.PAGE_END);
		
		
		setTitle("Configuration");
		setLocation(new Point(parent.getX()+40, parent.getY()+50));
		pack();
		
	}
	public void setPath(String t)
	{
		dbasePath.setText(t);
	}
	public String getPath()
	{
		return dbasePath.getText();
	}
	public MainWindow getWindow()
	{
		return parent;
	}
	
	private JTextField dbasePath;
	private MainWindow parent;
}