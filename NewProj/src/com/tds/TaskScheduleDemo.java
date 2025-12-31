package com.tds;
 
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
 
public class TaskScheduleDemo {
 
	static class systemTask implements Runnable{
 
		@Override
		public void run() {
			
			System.out.println("running background task...");
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new systemTask(), 0, 5, TimeUnit.SECONDS);
 
	}
 
}
 
 