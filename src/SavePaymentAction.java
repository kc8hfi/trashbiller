
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.Set;
import java.util.Iterator;
import java.sql.SQLException; 
import java.sql.PreparedStatement;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Connection;

public class SavePaymentAction extends AbstractAction
{
	public SavePaymentAction(PayBill p, String t)	//t is the name on the item, 
	{
		super(t);
		parent = p;
		window = parent.getWindow();
	}
	public void actionPerformed(ActionEvent event)
	{
// 		System.out.println("save the data to the dbase");
// 		System.out.println(event);
		
		MyTablePayModel tableModel = (MyTablePayModel)parent.getTable().getModel();
		Set changedRows = tableModel.getChangedRows();
// 		System.out.println("how many rows: " + changedRows.size());
		
		String selectedDate = parent.getMonth()+parent.getDay()+parent.getYear();//date
		String updateDF = "";
		String insertDF = "";
		try
		{
			DateFormat formatter = new SimpleDateFormat("MMMddyyyy");
			//turn it into a Date first
			Date mydate = formatter.parse(selectedDate);
// 					System.out.println("turn into date: " + mydate);
		
			//reformat it
			SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM");
			updateDF = d1.format(mydate);
			
			SimpleDateFormat d2 = new SimpleDateFormat("yyyy-MM-dd");
			insertDF = d2.format(mydate);
		}
		catch(ParseException e)
		{
			System.out.println(e);
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println(iae);
		}
// 		System.out.println("update format: " + updateDF);
// 		System.out.println("insert format: " + insertDF);

		
		String updateq = "update payments set " +
		"amount = ? " +
		"where payment_id = ? " +
		"and strftime('%Y-%m',payment_date) = ? "
		;
		String insertq = "insert into payments (payment_id, payment_accountno,payment_date,amount) "+
		"values (?,?,?,?) ";
		
		String deleteq = "delete from payments where payment_id = ? and strftime('%Y-%m',payment_date) = ?";
		try
		{
			PreparedStatement stmt = null;
			PreparedStatement insertStmt = null;
			PreparedStatement deleteStmt = null;
			Connection c = window.getConnection();
			stmt = c.prepareStatement(updateq);
			insertStmt = c.prepareStatement(insertq);
			deleteStmt = c.prepareStatement(deleteq);
		
			Iterator through = changedRows.iterator();
			int howmany = 0;
			while(through.hasNext())
			{
				int row = (int)through.next();
				CustomerPay customer = tableModel.getCustomer(row);
// 				System.out.println(customer);
				
				int id = (int)tableModel.getValueAt(row,0);				//id
				int accountno = (int)tableModel.getValueAt(row,1);		//account number
				double amount = (double)tableModel.getValueAt(row,4);		//amount
				boolean paid = (boolean)tableModel.getValueAt(row,5);		//paid
				
				if (!paid)
				{
// 					System.out.println("delete the record");
					deleteStmt.setInt(1,customer.getId());
					deleteStmt.setString(2,updateDF);
					int result = deleteStmt.executeUpdate();
// 					System.out.println("result: " + result);
				}
				else
				{
					
// 					System.out.println("update the record");
// 					System.out.println(updateq);
// 					System.out.println("date: " + updateDF);
// 					System.out.println("amount: "+amount);
// 					System.out.println("id: " + id);
					stmt.setDouble(1,amount);
					stmt.setInt(2,id);
					stmt.setString(3,updateDF);
					
					int result = stmt.executeUpdate();
					if (result==0)
					{
// 						System.out.println("insert the record");
						insertStmt.setInt(1,id);
						insertStmt.setInt(2,accountno);
						insertStmt.setString(3,insertDF);
						insertStmt.setDouble(4,amount);
						//payment_id, payment_accountno,payment_date,amount,paid
						int r = insertStmt.executeUpdate();
// 						System.out.println("insert result: "+r);
						
					}
				}
// 				System.out.println("id:" + id + " amount:"+amount+" paid:" + paid);
// 				System.out.println("month: "+parent.getMonth());
// 				System.out.println("year: " + parent.getYear());
// 				System.out.println("after formatted: " + date);
// 				
// 				int result = stmt.executeUpdate();
// 				System.out.println("result: " + result);
// 				if (result == 0)
// 				{
// 					System.out.println("now we insert");
// 					insertStmt.setInt(1,id);
// 					insertStmt.setInt(2,accountno);
// 					insertStmt.setString(3,date);
// 					insertStmt.setDouble(4,amount);
// 					insertStmt.setInt(5,paid);
// 					//payment_id, payment_accountno,payment_date,amount,paid
// 					int r = insertStmt.executeUpdate();
// // 					System.out.println("insert result: "+r);
// 				}
				howmany++;
			}//end while
			parent.setStatus(howmany + " record(s) updated!");
			parent.disableSaveButton();
		}//end try
		catch(SQLException exception)
		{
			JOptionPane.showMessageDialog(parent.getParent(),
				"query: " + updateq+"\n"+exception,
				"SavePaymentAction actionPerformed",
				JOptionPane.ERROR_MESSAGE
			);
		}

	}
	private PayBill parent;
	private MainWindow window;
}