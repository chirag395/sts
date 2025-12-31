package com.tds;

class Item{
	public int value;
	
	public boolean valSet;
	
	public synchronized void setItem(int input) {
		try {
			if(valSet) {
				wait();
			}
			value = input;
			valSet = true;
			System.out.println("One item produced -> " + value);
			notify();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void getItem() {
		try {
			if(!valSet) {
				wait();
			}
			System.out.println("One item consumed -> " + value);
			valSet = false;
			notify();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Producer implements Runnable{
	
	private Item item;
	public int i;
	public Producer(Item item) {
		this.item = item;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				item.setItem(++i);
				Thread.sleep(1000);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}


class Consumer implements Runnable{
	private Item item;
	public Consumer(Item item) {
		this.item = item;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(1000);
				item.getItem();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class ITCDemo {

	public static void main(String[] args) {
		
		Item item = new Item(); 
		
		Thread pr = new Thread(new Producer(item));
		Thread cr = new Thread(new Consumer(item));
		
		pr.start();
		cr.start();

	}

}
