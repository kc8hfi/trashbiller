
import javax.swing.JTable;
import javax.swing.event.*;
import javax.swing.table.TableModel;

public class MyModelListener implements TableModelListener
{
	public MyModelListener(JTable t)
	{
		table = t;
		isOnlyAdjustLarger = true;
	}
	public void tableChanged(TableModelEvent e)
	{
// 		System.out.println("i don't know what to do here");
		int row = e.getFirstRow();
		int col = e.getColumn();
		MyTableModel model = (MyTableModel)e.getSource();
		//get the column name
		if (col >=0)
		{
			String colName = model.getColumnName(col);
// 			System.out.println("col name: " + colName);
		}
		if (row >= 0)
		{
			Object data = model.getValueAt(row,col);
// 			System.out.println("here it is!: " + data);
		
		}
// 		System.out.println("here it is!"  + e.getType());

		if (e.getType() == TableModelEvent.UPDATE)
		{
			int column = table.convertColumnIndexToView(e.getColumn());
// 			System.out.println("not sure what to do");
// 			if (isOnlyAdjustLarger)
// 			{
// 				TableColumn tableColumn = table.getColumnModel().getColumn(column);
// 				TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
// 				Component c = table.prepareRenderer(cellRenderer, row, column);
// 				int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
// 				
// 			}
		}
		
	}//end tableChanged
	
	private JTable table;
	private boolean isOnlyAdjustLarger;
}