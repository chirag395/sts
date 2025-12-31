
package com.example.empmgmt.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Employee {
    private Integer id;
    private String name;
    private String department;
    private BigDecimal salary;

    public Employee() {}

    public Employee(Integer id, String name, String department, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Employee{id=" + id +
               ", name='" + name + '\'' +
               ", department='" + department + '\'' +
               ", salary=" + salary + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee emp = (Employee) o;
        return Objects.equals(id, emp.id) &&
               Objects.equals(name, emp.name) &&
               Objects.equals(department, emp.department) &&
               Objects.equals(salary, emp.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department, salary);
    }
}
