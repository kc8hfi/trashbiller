import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class History extends JPanel
{
	History () 
	{
// 		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setBackground(Color.cyan);
		
		GridLayout grid = new GridLayout(0,1);
		setLayout(grid);
		
		add(new JLabel("Account number"));
		
		add(new JLabel("First Last") );
		
		add(new JLabel("Address"));
		
		add(new JLabel("City, State  Zip"));

		JPanel stuff = new JPanel();
		stuff.add(new JLabel("put their stuff in a table here"));
		
		add(stuff);
		
		
		
// 		JPanel p = new JPanel();
// 		GridLayout grid = new GridLayout(	0,2,	//rows, cols
// 									5,5	//horizontal gap, vertical gap
// 		);
// 		
// // 		p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
// 		p.setLayout(grid);
// 		p.setBackground(Color.yellow);
// 		
// 		JLabel label;
// 		int textFieldSize = 15;
// 
// 		label = new JLabel("Last Name",JLabel.RIGHT);
// 		lastName = new JTextField(textFieldSize);
// 		p.add(label);
// 		p.add(lastName);
// 		
// 		label = new JLabel("First Name",JLabel.RIGHT);
// 		firstName = new JTextField(textFieldSize);
// 		p.add(label);
// 		p.add(firstName);
// 
// 		label = new JLabel("Address",JLabel.RIGHT);
// 		address = new JTextField(textFieldSize);
// 		p.add(label);
// 		p.add(address);
// 		
// 		label = new JLabel("City",JLabel.RIGHT);
// 		city = new JTextField(textFieldSize);
// 		p.add(label);
// 		p.add(city);
// 		
// 		label = new JLabel("State",JLabel.RIGHT);
// 		state = new JTextField(4);
// 		p.add(label);
// 		p.add(state);
// 		
// 		label = new JLabel("Zip",JLabel.RIGHT);
// 		zip = new JTextField(4);
// 		p.add(label);
// 		p.add(zip);
// 		
// // 		p.add(Box.createRigidArea(new Dimension(1,0)));
// 		
// 		JButton saveChanges = new JButton ("Save Changes");
// 
// 		add(p);
// 		add(Box.createRigidArea(new Dimension(0,10)));
// 		add(saveChanges);
		
// 		
// 		p.add(firstRow);
// 		p.add(secondRow);
// // 		add(p);
// 		
// // 		add(firstRow,BorderLayout.PAGE_START);
// // 		add(secondRow,BorderLayout.PAGE_START);
// 		add(p,BorderLayout.PAGE_START);
		
// 		JPanel test = new JPanel();
// 		test.setBackground(Color.green);
// 		test.add(p);
// 		add(test);
// 		
// 		test = new JPanel();
// 		test.setBackground(Color.yellow);
// 		test.add(saveChanges);
// 		
// 		add(test);
		

		
// 		add(label);
// 		add(zip);
		
		
	}
	
// 	private JLabel label;
// 	private JTextField accountNo;
	private JTextField lastName;
	private JTextField firstName;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
}
