package com.examples;

interface FunInf1{
	void userData();
}

public class Exp14 {

	public static void main(String[] args) {
		
		// before java 8
		FunInf1 obj = new FunInf1() {
			
			@Override
			public void userData() {
				System.out.println("overrided");
			}
		};
		
		obj.userData();
		new FunInf1() {
			
			@Override
			public void userData() {
				System.out.println("nameless object(using java < 8)");
			}
		}.userData();
		
		
		// Java 8
		FunInf1 obj1 = () -> {
			System.out.println("default method using java 8 and above)");
		};
		obj1.userData();

	}

}
