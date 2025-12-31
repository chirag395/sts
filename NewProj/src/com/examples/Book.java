package com.examples;

public class Book {
	
	static int pincode = 5252552;
	int atmpin = 1234;
	
	public Book() {
		System.out.println("Constructor");
	}
	
	
	// factory method return statement
	public static Book getObj() {
		System.out.println("factory method");
		return new Book();
	}


public Book(int atmpin) {
    this.atmpin = atmpin;
    System.out.println("Constructor with atmpin=" + atmpin);
}

	public static void main(String[] args) {
		System.out.println("main method");
		
		Book obj1 = new Book();
		Book obj2 = Book.getObj();
		Book obj3 = obj1;
		
//		Class.forName("Book");
	}

}
