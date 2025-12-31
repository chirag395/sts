package com.design;

interface Book
{
	Book bookDetails();
	
}

class Parent1
{
	public void greetUser()
	{
		System.out.println("parent");
	}
}

class Child1 extends Parent1
{
	public void sendMsg(String msg)
	{
		//greetUser();
		
		System.out.println("calling parent method "+msg);
	}
	
	@Override
	public void greetUser()
	{
		System.out.println("overrided");
		
		sendMsg("Hi factory method");
	}
	//factory method
	public static Parent1 getInstance()
	{
		return new Parent1();
	}
}

public class FactoryDemo {
	
	public static void main(String[] args) {
		
		Parent1 obj = Child1.getInstance();
		
		obj.greetUser();
	}
}
