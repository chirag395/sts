package com.examples;

public class Exp18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "java";
		
		StringBuffer sb = new StringBuffer(str);
		
		str.concat(" 25 V");        // wont work
		System.out.println(str);   
		
		sb.append(" v25.0");
		System.out.println(sb);
		
		sb.repeat("dev", 2);
		System.out.println(sb);
		
		System.out.println(str.repeat(3));
	}

}
