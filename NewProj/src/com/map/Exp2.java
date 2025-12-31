package com.map;

import java.util.TreeMap;

public class Exp2 {

	public static void main(String[] args) {
		
		TreeMap<String, Integer> data = new TreeMap<>();
		
		data.put("orange", 737);
		data.put("jfjf", 77);
		data.put("gfgf", 7);
		data.put("dgggf", 37);
		data.put("ertr", 84);
		data.put("mjj", 23);
		data.put("mjj", null);
//		data.put(null, 23);
//		data.put(null, null);
		
		data.forEach((k,v) -> System.out.println("Item: " + k + ", Price: Rs " + v));

	}

}
