/*
* Copyright 2013 Charles Amey
*
* This file is part of Trashbiller.
*
* Trashbiller is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Trashbiller is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Trashbiller.  If not, see<http://www.gnu.org/licenses/>.
*/


import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.URL;

import javax.help.*;

public class MainWindow extends JFrame 
{
     MainWindow() throws ClassNotFoundException//,IOException
     {
          //load up the jdbc drivers
          Class.forName("org.sqlite.JDBC");
          
          //get ready to set up everything
          setup = new Initialize(this);
          
          //setup the configuration file and dbase location
          setup.initialize();
          
          //set up the dbase connection
          connection = null;
          setupConnection();
          

          
          Toolkit tk = Toolkit.getDefaultToolkit();
          Dimension scr = tk.getScreenSize();
          int width = scr.width;
          int height = scr.height;
     // 		System.out.println("width: " + width + " height: " + height);
     // 		width = width/2;
     // 		width = width/2;
     // 		width = width/2;
          
     // 		height = height/2;
          System.out.println("width: " + width + " height: " + height);
          
          //set up the interface
          //put this back later
          setLocation(100,100);
          
          //take this line out for distribution
     // 		setLocation(width/2,height/2);
     // 		setLocationRelativeTo(null);

          setTitle("Smalley Sanitation"); 
          
          //setLayout(new GridLayout(0,1));	//rows,cols
          setLayout(new BorderLayout());
          
          //action handlers for the menu and toolbars
          //parent, text that goes on the menu item itself,  text that is the command for that item
          MenuButtonHandler addHandler = new MenuButtonHandler(this,"Add",
                                                       createIcon("/add.png", "cut icon"),
                                                       "Add a new customer",
                                                       "add");
          MenuButtonHandler searchHandler = new MenuButtonHandler(this,"Search",
                                                       createIcon("/Actions-edit-find-icon.png", "cut icon"),
                                                       "Search for a customer",
                                                       "search");
          MenuButtonHandler payBillHandler = new MenuButtonHandler(this,"Payments",
                                                       createIcon("/payment.png", "cut icon"),
                                                       "Make a payment",
                                                       "paybill");
          //parent, text that goes on the menu item itself,  text that is the command for that item
          
          //add a menu
          JMenuBar menu = new JMenuBar();
          
          JMenu fileMenu = new JMenu("File");
          fileMenu.setMnemonic(KeyEvent.VK_F);
          
          JMenuItem searchItem = new JMenuItem(searchHandler);
          
     // 		searchItem.setIcon(createIcon("cut.gif", "cut icon"));
          searchItem.setMnemonic(KeyEvent.VK_S);
          searchItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
          
          JMenuItem addItem = new JMenuItem(addHandler);
          addItem.setMnemonic(KeyEvent.VK_A);
          addItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
          
          JMenuItem editItem = new JMenuItem(payBillHandler);
          editItem.setMnemonic(KeyEvent.VK_E);
          editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
          
          fileMenu.add(searchItem);
          fileMenu.add(addItem);
          fileMenu.add(editItem);
          fileMenu.addSeparator();
          
          JMenuItem quitItem = new JMenuItem("Quit",createIcon("/Status-dialog-error-icon.png","Quit"));
          
          
          QuitAction quitAction = new QuitAction(this,"Quit",
                                   createIcon("/Status-dialog-error-icon.png","Quit"),
                                   "quit",KeyEvent.VK_Q,
                                   KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
          fileMenu.add(quitAction);

          
          JMenu editMenu = new JMenu("Edit");
          editMenu.setMnemonic(KeyEvent.VK_E);
          
          JMenuItem preferencesItem = new JMenuItem("Preferences", createIcon("/Actions-configure-icon.png","preferences"));
          preferencesItem.setMnemonic(KeyEvent.VK_P);
          preferencesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
          
          PreferencesHandler ph = new PreferencesHandler(this);
          preferencesItem.addActionListener(ph);
          editMenu.add(preferencesItem);
          
          menu.add(fileMenu);
          menu.add(editMenu);
          
     // 		AllPayments allPaymentAction = new AllPayments(this);
          ReportsHandler reportsHandler = new ReportsHandler(this);
          
          JMenu reportsMenu = new JMenu("Reports");
          reportsMenu.setMnemonic(KeyEvent.VK_R);
          
          
          ExportCustomerAction exportCustomerAction = new ExportCustomerAction(this,"Export Customers","export_customer",
                                                                                "Export a list of all the current customers");
          JMenuItem exportCustomer = new JMenuItem(exportCustomerAction);
          
          reportsMenu.add(exportCustomer);
          reportsMenu.addSeparator();
          
          JMenuItem allPaymentHistory = new JMenuItem("All Payment History");
          allPaymentHistory.setActionCommand("all_payments");
          allPaymentHistory.addActionListener(reportsHandler);
          
          JMenuItem unpaid = new JMenuItem("Who Has Not Paid");
          unpaid.setActionCommand("not_paid");
          unpaid.addActionListener(reportsHandler);
          
          JMenuItem paid = new JMenuItem("Who Has Paid");
          paid.setActionCommand("paid");
          paid.addActionListener(reportsHandler);
          
          reportsMenu.add(allPaymentHistory);
          reportsMenu.add(paid);
          reportsMenu.add(unpaid);
          
          menu.add(reportsMenu);
          
          JMenu helpMenu = new JMenu("Help");
          helpMenu.setMnemonic(KeyEvent.VK_H);
          
          AboutAction aboutAction = new AboutAction(this,"About","about",
                                        "About trash biller",
                                        null,
                                        KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
          
          JMenuItem about = new JMenuItem(aboutAction);
          about.setMnemonic(KeyEvent.VK_B);
          

          
     //@here        
          ClassLoader classLoader = getClass().getClassLoader();
          URL helpUrl = getClass().getResource("/resources/help/trashbiller.hs");
          try
          {
               helpSet = new HelpSet(classLoader,helpUrl);
               helpBroker = helpSet.createHelpBroker("Main Window");
          }
          catch(HelpSetException hse)
          {
               System.out.println(hse);
          }
          

          
          JMenuItem help = new JMenuItem("Contents");
          ImageIcon helpIcon = createIcon("/help.png","Help");
          help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
          help.setIcon(helpIcon);
          help.setToolTipText("Help Contents");
          helpBroker.enableHelpOnButton(help,"overview",helpSet);
          
          
          helpMenu.add(help);
          helpMenu.addSeparator();
          helpMenu.add(about);

          menu.add(helpMenu);
          
          
          
          
          
          
          setJMenuBar(menu);
          //add a toolbar
          JToolBar toolbar = new JToolBar("Tool Bar",JToolBar.HORIZONTAL);
          JButton payButton = new JButton(payBillHandler);
          payButton.setText("");
          JButton searchButton = new JButton(searchHandler);
          searchButton.setText("");
          JButton addButton = new JButton(addHandler);
          addButton.setText("");
          toolbar.add(payButton);
          toolbar.addSeparator();
          toolbar.add(searchButton);
          toolbar.addSeparator();
          toolbar.add(addButton);
          
          JButton helpButton = new JButton(helpIcon);
          helpButton.setToolTipText("Help Contents");
     // 		aboutButton.setText("");
          toolbar.addSeparator();
          toolbar.add(helpButton);
          helpBroker.enableHelpOnButton(helpButton,"overview",helpSet);
          
          add(toolbar,BorderLayout.PAGE_START);
          
          AddRecord add = new AddRecord(this);
          Search s = new Search(this);
          PayBill pb = new PayBill(this);
          
          //make a card layout to hold all the panels
          cardLayout = new CardLayout();
          cards = new JPanel(cardLayout);
          
          
          //@here, flip s and pb later
          cards.add(pb);
          cards.add(s);
          cards.add(add);


          /*java 1.7 switches the order of the params for the addLayoutComponent
          (string,component)
          */
          
          cardLayout.addLayoutComponent(s,"search");
          cardLayout.addLayoutComponent(add,"add");
          cardLayout.addLayoutComponent(pb,"paybill");
          
     // 		cardLayout.addLayoutComponent(edit,"edit");
     // 		cardLayout.addLayoutComponent(history,"history");
          
          
          
     // 		add(s,BorderLayout.CENTER);
     // 		s.setVisible(true);
          
          
     // 		add(addEdit,BorderLayout.CENTER);
     // 		addEdit.setVisible(false);
          

     // 		add(history,BorderLayout.CENTER);
     // 		history.setVisible(false);

          //add the panels to the array
     // 		panels.add(s);
     // 		panels.add(addEdit);
     // 		panels.add(history);
          
          
          //add the panels to the cards
     // 		cards.add(s);
     // 		cards.add(addEdit);
          
          //add all the items to the center now
          add(cards,BorderLayout.CENTER);
          
          
          
          pack();
          
     // 		revalidate();
     // 		repaint();

     }//end constructor

     public void setupConnection() //throws IOException
     {
          Properties prop = new Properties();
          String f = setup.getConfigFile();
          String dbLocation = "";
          String dbName = "";
          String db = "";
          try
          {
               prop.load(new FileInputStream(f));
               dbLocation = prop.getProperty("dbase_location");
               dbName = prop.getProperty("dbase_name");
               if (dbLocation != null && dbName != null && !dbLocation.isEmpty() && !dbName.isEmpty())
               {
                    db = dbLocation + System.getProperty("file.separator") + dbName;
     // 				System.out.println("dbasehandle, dbase: " + db);
                    connection = DriverManager.getConnection("jdbc:sqlite:"+db);
                    //setup the database
                    String badQuery = "";
                    String[] queries = {
                         "create table if not exists accounts ("+ 
                              "id integer primary key on conflict fail autoincrement, "+
                              "account_no integer not null, "+
                              "sticker_no text," + 
                              "account_type text," +
                              "first_name text, "+
                              "last_name text," +
                              "address1 text, "+
                              "city text, "+
                              "state text," +
                              "zip text, "+
                              "date_begin, "+
                              "date_end "+
                              ")",
                         "create table if not exists payments ("+
                              "payment_id integer not null, "+ 
                              "payment_accountno integer not null, " +
                              "payment_date text, "+
                              "amount text, "+
                              "paid integer, " + 
                              "foreign key(payment_id) references accounts(id) )"
                    };
                    Statement stmt = connection.createStatement();
                    stmt.setQueryTimeout(20);	//set timeout to 20 seconds
                    for (int i=0;i<queries.length;i++)
                    {
                         badQuery = queries[i];
                         stmt.executeUpdate(badQuery);
                    }
                    //commit changes first
     //				connection.commit();
                    //add a column to the accounts table
//                     String q = "pragma table_info(accounts)";
//                     stmt = connection.createStatement();
//                     ResultSet rs = stmt.executeQuery(q);
//                     int found = 0;
//                     while(rs.next())
//                     {
// //                          System.out.println(rs.getString(2));
//                          if (rs.getString(2).equals("sticker_no"))
//                          {
//                               found = 1;
//                               break;
//                          }
//                     }
//                     if (found == 0)
//                     {
//                          //add that column to the table
//                          q = "alter table accounts add column sticker_no text";
//                          stmt = connection.createStatement();
//                          stmt.executeUpdate(q);
//                          System.out.println("found was false");
//                     }
               }
               else
               {
     // 				throw(new SQLException("nothing in the "+f+ "! Please delete it and try again"));
                    JOptionPane.showMessageDialog(this,
                         "Please select a location for the database file!\n" +
                              "Click Edit->Preferences to set this.",
                         "WARNING!",
                         JOptionPane.WARNING_MESSAGE
                    );
               }
          }
          catch(IOException ioException)
          {
               JOptionPane.showMessageDialog(this,
                         "cannot open \n" + f + "!\n"+ioException.toString(),
                         "Config File",
                         JOptionPane.ERROR_MESSAGE
               );
     // 			should die here
     // 			throw(ioException);
          }
     // 		try
     // 		{
     // 			connection = DriverManager.getConnection("jdbc:sqlite:"+db);
     // 		}
          catch(SQLException e)
          {
               JOptionPane.showMessageDialog(this,
                    "something bad happened!\n"+e.toString(),
                    "DbaseHandle constructor",
                    JOptionPane.ERROR_MESSAGE
               );
               //should definitely die here
          }
     }

     public void setVisible(String whichOne)
     {
     // 		System.out.println("we hafta hide all of them and only show " + whichOne);
          cardLayout.show(cards,whichOne);
     }

     public Connection getConnection()
     {
          return connection;
     }

     protected ImageIcon createIcon (String path, String desc)
     {
          URL imgUrl = getClass().getResource("/resources" + path);
          if(imgUrl != null)
               return new ImageIcon(imgUrl,desc);
          else
               return null;
     }

     // 	private void debugging()
     // 	{
     // 		Config c = new Config(this);
     // 		c.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     // 		c.setVisible(true);
     // 	}

     public Initialize getSetupConfig()
     {
          return setup;
     }

     private Connection connection;
     private JPanel cards;
     private CardLayout cardLayout;
     private Initialize setup;

     private HelpSet helpSet;
     private HelpBroker helpBroker;

}
