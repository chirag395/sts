package com.dtm;

import java.util.Arrays;
import java.util.List;

class Customer
{
	
}

public class Exp3 {
	
	private static double add(List<? extends Number> list)
	{
		double a = 0.0;
		
		for(Number n : list)
		{
			
			a += n.doubleValue();
		}
		
		return a;
	}
	
	//lower bounded
	public static void lowerBounded(List<? super Integer> list)
	{
		System.out.println(list);
	}
	
	//unbounded wildcards
	public static void unboundedData(List<?> list)
	{
		System.out.println(list);
	}
	
	
	
	public static void main(String[] args) {
		
		//upper bounded
		List<Integer> list1 = Arrays.asList(12,3,4,5);
		
		add(list1);
		
		List<Double> list2 = Arrays.asList(34.44,33.55,55.44);
		
		add(list2);
		
		lowerBounded(list1);
		
		unboundedData(list2);
		
		List<String> names = Arrays.asList("java", "python", "spring");
		
		unboundedData(names);
		
		List<Customer> lstc = Arrays.asList(new Customer());
		
		unboundedData(lstc);
		
	}
	
}




//develop a code for accessing unbounded custom generics and print customer data starts with and ends with.

//develop a code for getting the singleton design pattern with timestamps.

//how to create custom immutable class