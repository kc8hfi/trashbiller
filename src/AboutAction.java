
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class AboutAction extends AbstractAction
{
	//(text,action command, tooltip,keystrokc
	public AboutAction(MainWindow p,String text, String cmd,String toolTip,ImageIcon icon, KeyStroke accelerator)
// 	public AboutAction(MainWindow p)
	{
		super(text,icon); //text is the actual name
		parentWindow = p;
		putValue(ACTION_COMMAND_KEY,cmd);	//set action command
		putValue(SHORT_DESCRIPTION, toolTip); //used for tooltip text
		putValue(ACCELERATOR_KEY,accelerator);
	}
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("show the about dialog box, " + e.getActionCommand());
		
		About about = new About(parentWindow);
		about.setModal(true);
		about.setVisible(true);
	}//end actionPerformed
	private MainWindow parentWindow;
}//end AboutAction