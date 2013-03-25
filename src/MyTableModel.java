
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel
{
	public MyTableModel()
	{
		colNames = new ArrayList<String>(0);
		theData = new ArrayList<Customer>(0);
	}
	
	public int getColumnCount()
	{
		return colNames.size();
	}
	
// 	public Class getColumnClass(int c)
// 	{
// // 		System.out.println(getValueAt(0,c).getClass());
// 		Class cl = Object.class;
// 		if (c == 1 || c == 2)
// 		{
// // 			System.out.println("first column");
// 			cl = int.class;
// 		}
// 		else
// 		{
// 			cl = String.class;
// 		}
// 		System.out.println("c: "+ c +" type: "+cl);
// 
// // 		System.out.println("size is: " + colNames.size());
// // 		System.out.println("c is : " + c);
// // 		if ( (c >=0) && (c < colNames.size()) && theData.size()>0 )
// // 		{
// // 			System.out.println("how many rows: "+ theData.size());
// // 			System.out.println("how many cols: "+ colNames.size());
// // // 			cl = getValueAt(0,c).getClass();
// // // 			System.out.println("the class is: " +cl);
// // 		}
// 		
// // 		return getValueAt(0,c).getClass();
// 		return cl;
// 	}
	
	public int getRowCount()
	{
// 		System.out.println("num rows: " + theData.size());
		return theData.size();
	}
	
	public String getColumnName(int col)
	{
// 		System.out.println("colname for "+col+ " is " + colNames.get(col));
		return colNames.get(col);
	}
	public Object getValueAt(int row,int col)
	{
		Customer c = theData.get(row);
// 		System.out.println("data is: " + c.at(col));
		return c.at(col);
	}
	
	public void setColNames(ArrayList<String> c)
	{
// 		System.out.println("incoming: " + c.size());
		colNames = c;
// 		System.out.println("outgoing: " + colNames.size());
		fireTableStructureChanged();
	}
	public void setData(ArrayList<Customer> d)
	{
		theData = d;
// 		System.out.println("gotta fire data changed");
// 		System.out.println("size is " + theData.size());
// 		fireTableDataChanged();
	}
	
	public Customer getRow(int row)
	{
		Customer r = new Customer();
		if (row <theData.size())
			r = theData.get(row);
		return r;
	}
	
	private ArrayList<String> colNames;
	private ArrayList<Customer> theData;
}