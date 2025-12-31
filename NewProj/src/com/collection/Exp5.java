package com.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exp5 {

	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("sddssd", "gfddf", "kkjkff", "kjikdf");
		
		Collections.sort(names);
		
		names.forEach(System.out::println);

		System.out.println("---------------------");
		
		Collections.shuffle(names);
		
		names.forEach(System.out::println);
	}

}
                  