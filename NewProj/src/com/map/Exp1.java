package com.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Exp1 {

	public static void main(String[] args) {
		
		HashMap data = new HashMap<>();
		
		data.put("java", "spring");
		data.put(101, "php");
		data.put('j', 90);
		data.put(98, 88);
		data.put(84, false);
		data.put("java", "Rohit");
		
		
		System.out.println(data);
		
		Iterator<Map.Entry> it = data.entrySet().iterator();
		
		while(it.hasNext()) {
			Entry et = it.next();
			
			System.out.println(et.getKey() + " <=> " + et.getValue());
		}
		
		data.keySet().forEach(x -> System.out.println(x + " - " + data.get(x)));

	}

}
