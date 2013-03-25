
import javax.swing.table.TableColumnModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;

import javax.swing.table.TableColumn;

public class MyTablePayColumnModel extends DefaultTableColumnModel
{
	//override
	public void addColumn(TableColumn tc)
	{
		super.addColumn(tc);
	}
	
	
// 	//override
// 	public void addColumnModelListener(TableColumnModelListener tcml)
// 	{
// 	}
// 	
// 	//override
// 	public void removeColumnModelListener(TableColumnModelListener tcml)
// 	{
// 	}
// 	
// 	//override
// 	public ListSelectionModel getSelectionModel()
// 	{
// 		ListSelectionModel m = new DefaultListSelectionModel();
// 		return m;
// 	}
// 	
// 	//override
// 	public void setSelectionModel(ListSelectionModel m)
// 	{
// 		
// 	}
// 	
// 	//override
// 	public int getSelectedColumnCount()
// 	{
// 		return 1;
// 	}
// 	
// 	//override
// 	public int[] getSelectedColumns()
// 	{
// 		int[] t = {0};
// 		return t;
// 	}
// 	//override
// 	public boolean getColumnSelectionAllowed()
// 	{
// 		return true;
// 	}
// 	
// 	//override
// 	public void setColumnSelectionAllowed(boolean t)
// 	{
// 	}
// 	
// 	public int getTotalColumnWidth()
// 	{
// 		return 10;
// 	}
// 	public int getColumnIndexAtX(int t)
// 	{
// 		return 1;
// 	}
// 	public int getColumnMargin()
// 	{
// 		return 10;
// 	}
	
}