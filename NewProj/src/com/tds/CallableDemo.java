package com.tds;

import java.util.concurrent.Callable;

class SimplMaths implements Callable<Integer>{
	
	private final int count;
	
	public SimplMaths(int count) {
		this.count = count;
	}
	
	@Override
	public Integer call() throws Exception{
		
		int add = 0;
		for(int i = 1; i <= count; i++) {
			add += 1;
			Thread.sleep(500);
		}
		
		System.out.println("the count value is: " + add + " " + Thread.currentThread().getName());
		return add;
	}
}

public class CallableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
