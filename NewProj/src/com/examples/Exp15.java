package com.examples;

import java.util.Arrays;
import java.util.List;

public class Exp15 {

	public static void main(String[] args) {
		String[] names = {"rohit", "kohli", "sky"};
		
		Arrays.sort(names);
		
		System.out.println(names.toString());
		
		for(String dt: names) {
			System.out.println(dt);
		}
		
		List<String> list = Arrays.asList(names);
		
		list.forEach(x -> System.out.println(x));
		list.forEach(System.out::println);
	}

}
