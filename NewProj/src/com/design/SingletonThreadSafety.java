package com.design;

class SingletonTest1{
	public static SingletonTest1 obj;
	
	private SingletonTest1() {
		
	}
	
	public static synchronized SingletonTest1 getInstance() {
		if(obj == null) {
			synchronized (SingletonTest1.class) {
				if(obj == null) {
					obj = new SingletonTest1();
				}
			}
		}
		
		return obj;
	}
}
public class SingletonThreadSafety {

	public static void main(String[] args) {
		

	}

}
