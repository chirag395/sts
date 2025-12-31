package com.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Book implements Comparable<Book>
{
	private int id;
	
	private String name;
	
	private int price;
	
	public Book(int id, String name, int price)
	{
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Book o) {
		
		return this.price - o.price;
	}
	
}

class NameComparator implements Comparator<Book>
{

	@Override
	public int compare(Book o1, Book o2) {
		
		return o2.getName().compareTo(o1.getName());
	}
	
}

public class Exp6 {
	
	public static void main(String[] args) {
		
		List<Book> data = new ArrayList<>();
		
		data.add(new Book(101,"java", 340));
		data.add(new Book(201, "php", 10));
		data.add(new Book(501, "python", 43));
		data.add(new Book(401, "kotlin", 25));
		data.add(new Book(301, "core java", 55));
		
		System.out.println(data);
		
		Collections.shuffle(data);
		
		Collections.reverse(data);
		
		Collections.sort(data);
		
		data.forEach(System.out::println);
		
		Collections.sort(data, new NameComparator());
		
		data.forEach(System.out::println);
	}
}
