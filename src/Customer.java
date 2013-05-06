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


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Customer
{
	public Customer()
	{
		id = 0;
		accountNo = 0;
          stickerNo = "";
		accountType = "";
		first = "";
		last = "";
		address = "";
		city = "";
		state = "";
		zip = "";
		begin = "";
		end = "";
	}
	public Customer(int i, int a, String sn,String t,String f,String l,String ad,String c,String s,String z,String b,String e)
	{
		id = i;
		accountNo = a;
          stickerNo = sn;
		accountType = t;
		first = f;
		last = l;
		address = ad;
		city = c;
		state = s;
		zip = z;
		begin = b;
		end = e;
	}

	public int size()
	{
		
		return 12;
	}
	
	public Object at(int col)
	{
		Object o = new Object();
		switch (col)
		{
			case 0:
				o = id;
				break;
			case 1:
				o = accountNo;
				break;
               case 2:
                    o = stickerNo;
                    break;
			case 3:
				o = accountType;
				break;
			case 4:
				o = first;
				break;
			case 5:
				o = last;
				break;
			case 6:
				o = address;
				break;
			case 7:
				o = city;
				break;
			case 8:
				o = state;
				break;
			case 9:
				o = zip;
				break;
			case 10:
				o = begin;
				break;
			case 11:
				o = end;
				break;
		}
		return o;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int i)
	{
		id = i;
	}
	public int getAccountNo()
	{
		return accountNo;
	}
	public void setAccountNo(int i)
	{
		accountNo = i;
	}
     public String getStickerNo()
     {
          return stickerNo;
     }
     public void setStickerNo(String i)
     {
          stickerNo = i;
     }
	
	public String getAccountType()
	{
		return accountType;
	}
	public void setAccountType(String t)
	{
		accountType = t;
	}
	public String getFirst()
	{
		return first;
	}
	public void setFirst(String f)
	{
		first = f;
	}
	public String getLast()
	{
		return last;
	}
	public void setLast(String l)
	{
		last = l;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String a)
	{
		address = a;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String c)
	{
		city = c;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String s)
	{
		state = s;
	}
	public String getZip()
	{
		return zip;
	}
	public void setZip(String z)
	{
		zip = z;
	}
	public String getBegin()
	{
		return begin;
	}
	public void setBegin(String b)
	{
		begin = b;
	}
	public String getBeginMonth()
	{
		String t = "";
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//turn it into a Date first
			Date mydate = formatter.parse(begin);
			//reformat it
			SimpleDateFormat d1 = new SimpleDateFormat("MMMMM");
			t = d1.format(mydate);
		}
		catch(ParseException e)
		{
			t = "";
		}
		return t;
	}
	public String getBeginDay()
	{
		String t = "";
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//turn it into a Date first
			Date mydate = formatter.parse(begin);
			//reformat it
			SimpleDateFormat d1 = new SimpleDateFormat("dd");
			t = d1.format(mydate);
		}
		catch(ParseException e)
		{
			t = "";
		}
		return t;
	}
	public String getBeginYear()
	{
		String t = "";
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//turn it into a Date first
			Date mydate = formatter.parse(begin);
			//reformat it
			SimpleDateFormat d1 = new SimpleDateFormat("yyyy");
			t = d1.format(mydate);
		}
		catch(ParseException e)
		{
			t = "";
		}
		return t;
	}


	
	
	public String getEndMonth()
	{
		String t = "";
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//turn it into a Date first
			Date mydate = formatter.parse(end);
			//reformat it
			SimpleDateFormat d1 = new SimpleDateFormat("MMMMM");
			t = d1.format(mydate);
		}
		catch(ParseException e)
		{
			t = "";
		}
		return t;
	}
	public String getEndDay()
	{
		String t = "";
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//turn it into a Date first
			Date mydate = formatter.parse(end);
			//reformat it
			SimpleDateFormat d1 = new SimpleDateFormat("dd");
			t = d1.format(mydate);
		}
		catch(ParseException e)
		{
			t = "";
		}
		return t;
	}
	
	public String getEndYear()
	{
		String t = "";
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//turn it into a Date first
			Date mydate = formatter.parse(end);
			//reformat it
			SimpleDateFormat d1 = new SimpleDateFormat("yyyy");
			t = d1.format(mydate);
		}
		catch(ParseException e)
		{
			t = "";
		}
		return t;
	}
	
	public String getEnd()
	{
		return end;
	}
	public void setEnd(String e)
	{
		end = e;
	}
	
	public String toString()
	{
		String t = "id: " + id + "\n" +
			"account no: " + accountNo + "\n" +
               "sticker no: " + stickerNo + "\n" +
			"account type: " + accountType + "\n" +
			"first: " + first + "\n" +
			"last: " + last + "\n" +
			"address: " + address + "\n" +
			"city: " + city + "\n" +
			"state: " + zip + "\n" +
			"begin: " + begin + "\n" + 
			"end: "+ end;
		return t;
	}    

	public ArrayList< ArrayList<String> > getPayments(Connection con)
     {
          ArrayList< ArrayList<String> > d = new ArrayList<ArrayList<String> >(0);
          //get all their years
          String query = "select distinct(strftime('%Y',payment_date)) tyear " +
               "from payments "+
               "where payment_id  =  ?" +
               "order by tyear desc "
               ;
//           System.out.println("query: " + query);
          try
          {
               PreparedStatement stmt = null;
//                Connection con = w.getConnection();
               if (con != null)
               {
                    stmt = con.prepareStatement(query);
                    stmt.setInt(1,getId());
                    ResultSet rs = stmt.executeQuery();
                    ArrayList<String> years = new ArrayList<String>(0);
                    while(rs.next())
                    {
                         //gather up all the years
                         years.add(rs.getString(1));
                    }
//                     System.out.println("how many years: " + years.size());
                    //loop through each year and match that up to each month
                    String[] m = {"01","02","03","04","05","06","07","08","09","10","11","12"};
                    for (String y: years)
                    {
                         ArrayList<String> eachrow = new ArrayList<String>(0);
                         eachrow.add(y);
                         for(int i=0;i<m.length;i++)
                         {
                              String q = "select payment_date " +
                                   "from payments " + 
                                   "where payment_id = ? " +
                                   "and strftime('%Y',payment_date) = ? " +
                                   "and strftime('%m',payment_date) = ? " 
                              ;
                              stmt = con.prepareStatement(q);
                              stmt.setInt(1,getId());
                              stmt.setString(2,y);
                              stmt.setString(3,m[i]);
                              rs = stmt.executeQuery();
                              if (rs.next())
                                   eachrow.add("X");
                              else
                                   eachrow.add(" ");
                         }//end loop of months
                         //add eachrow to the big array
                         d.add(eachrow);
                    }//end loop of years
               }
               else
               {
                    //connection is null
//                     JOptionPane.showMessageDialog(parent.getParent(),
//                          "Please check your configuration settings!",
//                          "No Database Connection!",
//                          JOptionPane.ERROR_MESSAGE
//                     );
               }
          }
          catch(SQLException exception)
          {
//                JOptionPane.showMessageDialog(parent.getParent(),
//                     "query:\n" + query +"\n"+exception,
//                     "reportpaidaction actionPerformed",
//                     JOptionPane.ERROR_MESSAGE
//                );
               System.out.println(exception);
          }          
          
          return d;
     }//end getPayments
	
	
	private int id;
	private int accountNo;
     private String stickerNo;
	private String accountType;
	private String first;
	private String last;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String begin;
	private String end;
	
	
}