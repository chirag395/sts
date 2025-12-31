package com.collection;

import java.util.Stack;

public class Exp2 {

	public static void main(String[] args) {
		
		Stack data = new Stack();
		
		data.add('a');
		data.add("java");
		data.add('a');
		data.add(true);
		data.add(233);
		data.add(22.33f);
		data.add(12.12);
		data.add(54);
		data.add(false);
		data.add("string");
		
		
//		| string |                                 
//		| false  |                              
//		|  54    |  
//		|  12.12 |
//		|  22.33f|
//		|  233   |
//		|  true  |
// 		|  a     |
//		|  java  |
//		|__ a____|

		
		System.out.println(data);
		
		System.out.println(data.peek());
		
		System.out.println(data.search("string"));

	}

}
