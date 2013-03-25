
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class AddHandler extends AbstractAction
{
	public AddHandler(MainWindow p, String t, String cmd)
	{
		super(t);
		parent = p;
		putValue(ACTION_COMMAND_KEY, cmd);	//set the action command
	}

	public void actionPerformed(ActionEvent event)
	{
// 		System.out.println(event.getActionCommand());
		parent.setVisible(event.getActionCommand());
	}
	private MainWindow parent;
}


//AddHandler addHandler = new AddHandler(this,"capital Add", "lower add");