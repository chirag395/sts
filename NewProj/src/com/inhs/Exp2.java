package com.inhs;

class C{
	public void show() {
		System.out.println("top most parent");
	}
	
	class D extends C{
		public void cat() {
			System.out.println("intermediate parent 1");
		}
	}
	
	class E extends D{
		public void fox() {
			System.out.println("intermediate parent 2");
		}
	}
	
	class F extends E{
		public void dog() {
			System.out.println("bottom most class");
		}
	}
}

public class Exp2 {
	public static void main(String[] args) {
		
	}

}
