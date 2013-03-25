
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Customer
{
	public Customer()
	{
		id = 0;
		accountNo = 0;
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
	public Customer(int i, int a, String t,String f,String l,String ad,String c,String s,String z,String b,String e)
	{
		id = i;
		accountNo = a;
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
		
		return 11;
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
				o = accountType;
				break;
			case 3:
				o = first;
				break;
			case 4:
				o = last;
				break;
			case 5:
				o = address;
				break;
			case 6:
				o = city;
				break;
			case 7:
				o = state;
				break;
			case 8:
				o = zip;
				break;
			case 9:
				o = begin;
				break;
			case 10:
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
	
	private int id;
	private int accountNo;
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