
public class CustomerPay
{
	public CustomerPay()
	{
		id = 0;
		accountNo = 0;
		name = "";
		accountType = "";
		amount = 0;
		paid = false;
	}

	public int size()
	{
		
		return 6;
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
				o = name;
				break;
			case 3:
				o = accountType;
				break;
			case 4:
				o = amount;
				break;
			case 5:
				o = paid;
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
	public void setAccountNo(int a)
	{
		accountNo = a;
	}
	
	public String getAccountType()
	{
		return accountType;
	}
	public void setAccountType(String t)
	{
		accountType = t;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String n)
	{
		name = n;
	}
	public void setAmount(double a)
	{
		amount = a;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setPaid(boolean b)
	{
		paid = b;
	}
	public boolean getPaid()
	{
		return paid;
	}
	public String toString()
	{
		String t = "id: " + Integer.toString(id) + "\n" +
		"account no: " + Integer.toString(accountNo) + "\n" +
		"name: " + name + "\n" +
		"accountType: " + accountType + "\n" +
		"amount: " + Double.toString(amount) + "\n" +
		"paid: " + paid;
		return t;
	}
	
	private int id;
	private int accountNo;
	private String name;
	private String accountType;
	private double amount;
	private boolean paid;
}