package com.design;

abstract class UserData
{
	public abstract void display();
}

class OriginalData extends UserData
{

	String username; 
	String password;
	
	//String userData = username+" "+password;
	
	public OriginalData(String user, String pass)
	{
		this.username = user;
		this.password = pass;
				
	}
	
	@Override
	public void display() {
		
		System.out.println("Original UserData : "+username+" "+password);
	}	
}

class ProxyData extends UserData
{
	private String username;
	private String password;
	
	//private String userData = username+" "+password;
	
	private OriginalData oData = null;
	
	public ProxyData(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	@Override
	public void display() {
		
		if(oData == null)
		{
			oData = new OriginalData(username,password);
		}
		oData.display();
	}
	
}

public class ProxyDemo {
	
	public static void main(String[] args) {
		
		UserData obj1 = new OriginalData("java", "hello");
		
		obj1.display();
		
		UserData obj2 = new ProxyData("test", "java");
		
		obj2.display();
		
	}
}
