

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class QuitAction extends AbstractAction
{
	public QuitAction(JFrame f,String text,ImageIcon i, String desc, int mnemonic,KeyStroke accelerator)
	{
		super(text,i); //text is the actual name
		parentWindow = f;
		putValue(SHORT_DESCRIPTION, desc); //used for tooltip text
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY,accelerator);
	}
	public void actionPerformed(ActionEvent e)
	{
// 		System.out.println("with the new actions stuff,  " + e.getActionCommand());
		WindowEvent event = new WindowEvent(parentWindow,WindowEvent.WINDOW_CLOSING);
		parentWindow.dispatchEvent(event);
	}
	private JFrame parentWindow;
}//end QuitAction class