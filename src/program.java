
import javax.swing.JFrame;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class program
{
	public static void main(String[] args)
	{
		MainWindow w = null;
		try
		{
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true); 
			
			w = new MainWindow();
			w.setVisible(true);
			w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
			JOptionPane.showMessageDialog(w,
					cnfe,
					"Critical Error",
					JOptionPane.ERROR_MESSAGE
			);
		}
	}//end main
}//end class
