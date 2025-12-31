package com.streams;

import java.util.Arrays;
import java.util.List;

public class Exp2 {

	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("kohli","dhoni","dhawan","jfjjf","fijgi");
		
		names.stream().map(String::toUpperCase).forEach(System.out::println);

	}

}

