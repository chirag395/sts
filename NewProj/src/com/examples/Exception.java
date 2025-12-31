package com.examples;

public class Exception {

	public static void main(String[] args) {
		try {
			System.out.println(10/4);
			String str = null;
			
			System.out.println(str.charAt(0));
		}
		catch(ArithmeticException ae) {
			System.out.println("dont enter 0 for denominator");
		}
		catch(NullPointerException npe) {
			System.out.println("string input mandatory");
		}

	}

}
