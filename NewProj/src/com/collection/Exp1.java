package com.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Exp1 {

	public static void main(String[] args) {
		
		
		
//		ArrayList data = new ArrayList();
		CopyOnWriteArrayList data = new CopyOnWriteArrayList();
		
		data.add('a');
		data.add("java");
		data.add('a');
		data.add(true);
		data.add(233);
		data.add(22.33f);
		data.add(12.12);
		data.add(54);
		data.add(false);
		
		
		System.out.println(data);
		
//		iterator, list iterator, enumeration
		
		
		Iterator it = data.iterator();
		
		ListIterator itr = data.listIterator(data.size());
		
		while(it.hasNext()) {
			System.out.println(it.next());
			data.add("queen");  // Concurrent Modification Exception
		}
		System.out.println("size: " + data.size());
		
		System.out.println("-------------");
		
		while(itr.hasPrevious()) {
			System.out.println(itr.previous());
		}
		
		System.out.println(data.get(0));
		System.out.println(data.size());
		
		data.set(0, "MLA");
		System.out.println(data.get(0));
		System.out.println(data.size());

	}

}
