package com.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Exp3 {

	public static void main(String[] args) {
		
//		HashSet data = new HashSet<>();
		
//		LinkedHashSet data = new LinkedHashSet<>();
		
		TreeSet data = new TreeSet<>();
		
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
		
		System.out.println(data.size());
		
		Iterator it = data.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
