
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableMouseListener extends MouseAdapter
{
	public TableMouseListener(Search p)
	{
		parent = p;
	}
	public void mouseClicked(MouseEvent event)
	{
		if (event.getClickCount() == 2)
		{
			JTable target = (JTable)event.getSource();
			int row = target.getSelectedRow();
			int col = target.getSelectedColumn();
// 			System.out.println("row: "+row +" col: "+col);
			MyTableModel tableModel = (MyTableModel)target.getModel();
			Customer c = tableModel.getRow(row);
			
// 			JOptionPane.showMessageDialog(parent.getParent(),
// 				"this is where we will show the record\n",
// 				"tablemouseListener",
// 				JOptionPane.INFORMATION_MESSAGE
// 			);
			EditCustomer editme = new EditCustomer(c,parent);
			editme.setModal(true);
			editme.setVisible(true);
		}
	}
	private Search parent;
}
