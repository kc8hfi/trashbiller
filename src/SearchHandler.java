
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class SearchHandler extends AbstractAction
{
	public SearchHandler(MainWindow p, String t, String cmd)
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
