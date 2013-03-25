
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class PreferencesHandler implements ActionListener
{
	public PreferencesHandler(MainWindow p)
	{
		parent = p;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		Config c = new Config(parent);
		c.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		c.setVisible(true);
	}
	private MainWindow parent;
}