package com.collection;

import java.util.Iterator;
import java.util.TreeSet;

public class Exp4 {

	public static void main(String[] args) {
		
		TreeSet<String> data = new TreeSet<>();
		
		data.add("assd");
		data.add("java");
		data.add("sgfgfg");
		data.add("kgkkf");
		data.add("string");
		
		Iterator<String> it = data.descendingIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
