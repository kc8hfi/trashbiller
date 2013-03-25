
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JTable;

public class PayTableListener implements PropertyChangeListener,TableModelListener
{
	public PayTableListener(JTable t)
	{
		table = t;
	}
	
	public void propertyChange(PropertyChangeEvent pce)
	{
		System.out.println("this is where to change the column width?");
	}
	
	public void tableChanged(TableModelEvent tme)
	{
// 		System.out.println("column width goes here");
// 		int column = table.convertColumnIndexToView(tme.getColumn());
// 		System.out.println("which column: " + column);
// 		
// 		System.out.println("now many columns: " + table.getModel().getColumnCount());
// 		MyTablePayModel tableModel = (MyTablePayModel)table.getModel();
// 		
// 		System.out.println("there is no column model for the table...:" +table.getColumnModel().getColumnCount());
// 		
// 		int c = table.getColumnModel().getColumnCount();
// 		System.out.println(c);
		
		
		
	}//end tableChanged
	
	
	
	//this is my own functions
	private JTable table;
}