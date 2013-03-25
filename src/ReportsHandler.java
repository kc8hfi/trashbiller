
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReportsHandler implements ActionListener
{
	public ReportsHandler(MainWindow p)
	{
		parent = p;
	}
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("which one to open: " + evt.getActionCommand());
		if (evt.getActionCommand().equals("all_payments"))
		{
			AllPayments everything = new AllPayments(parent);
			everything.setModal(true);
			everything.setVisible(true);
		}
		if (evt.getActionCommand().equals("paid") || evt.getActionCommand().equals("not_paid"))
		{
			PaidOrNot p = new PaidOrNot(parent,evt.getActionCommand());
			p.setModal(true);
			p.setVisible(true);
		}
	}//end actionPerformed
	private MainWindow parent;
}