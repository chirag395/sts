package com.design;

class Employee
{
	private int id;
	
	private String name;
	
	private String empType;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}
	
	public void display()
	{
		System.out.println(id+" "+name+" "+empType);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", empType=" + empType + "]";
	}
}

interface EmpBuilder
{
	void employeeDetails();
	void employeeType();
	
	Employee employeeData();
}

class ImplBuilder implements EmpBuilder
{

	private Employee emp = new Employee();
	
	@Override
	public void employeeDetails() {
		
		emp.setId(101);
		emp.setName("Rohit");
		
	}

	@Override
	public void employeeType() {
		
		emp.setEmpType("REGULAR");
		
	}
	
	@Override
	public Employee employeeData()
	{
		return emp;
	}
	
}

class BuilderTest
{
	public void employeeInfo(EmpBuilder builder)
	{
		builder.employeeDetails();
		builder.employeeType();
		builder.employeeData();
	}
}

public class BuilderDemo {
	
	public static void main(String[] args) {
		
		ImplBuilder empObj = new ImplBuilder();
		
		BuilderTest testBuilder = new BuilderTest();
		
		testBuilder.employeeInfo(empObj);
		
		Employee emp = empObj.employeeData();
		
		emp.display();
	}
}
