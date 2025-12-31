package com.examples;

public class Employee {
	int id;
	String name;
	String cmp;
	
	public Employee() {
		System.out.println("default constructor");
	}
		
	public Employee(int id, String name) {
		System.out.println("parameterised constructor");
	}		
	public Employee(int id, String name, String cmp) {
		System.out.println("overloaded constructor");
	}
	
	public Employee(Employee obj) {
		System.out.println("object parameterised constructor");
	}

	public static void main(String[] args) {
    Employee obj1 = new Employee();
    Employee obj2 = new Employee(101, "Alice"); 
    Employee obj3 = new Employee(102, "Bob", "Acme Corp"); 
    Employee obj4 = new Employee(obj1); 
    }
}