package com.examples;

public class Exp17 {
	
	public static void main(String[] args) {
		String a = "hello";
		String b = "hello";
		String e = "hello";
		
		String c = new String("hello");
		String d = new String("hello");
		
		System.out.println(a == b);
		System.out.println(a == e);
		System.out.println(a == c);
		System.out.println(b == d);
		System.out.println(c == d);
		System.out.println();
		System.out.println();
		
		
		a.concat("hiiiii");
		System.out.println(a);
		
	}
}
