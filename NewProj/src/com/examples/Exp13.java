package com.examples;


@FunctionalInterface
interface Functionalinterface{
	String greetUser(String msg);
	static void cat() {
		System.out.println("funInf cat()");
	}
	
	static void show() {
		System.out.println("hi");
	}
	
	default void dog() {
		System.out.println("funInf dog()");
		fox();
	}
	
	private void fox() {
		System.out.println("funInf fox()");
	}
}

public class Exp13 {

	public static void main(String[] args) {
		
		// JDK 8 feature
		Functionalinterface obj = (String msg) -> {
			System.out.println("funInf overrided");
//			System.out.println(msg);
			return msg;
		};
		
		System.out.println(obj.greetUser("Hi Hello Ji...."));

	}

}
