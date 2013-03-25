
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MenuButtonHandler extends AbstractAction
{
	public MenuButtonHandler(MainWindow p, String t,ImageIcon i, String tip, String cmd)
	{
		super(t,i);
		parent = p;
		putValue(SHORT_DESCRIPTION, tip); //used for tooltip text
		putValue(ACTION_COMMAND_KEY, cmd);	//set the action command
	}

	public void actionPerformed(ActionEvent event)
	{
// 		System.out.println(event.getActionCommand());
		parent.setVisible(event.getActionCommand());
	}
	private MainWindow parent;
}
