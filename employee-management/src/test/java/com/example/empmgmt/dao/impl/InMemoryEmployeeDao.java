
package com.example.empmgmt.dao.impl;

import com.example.empmgmt.dao.EmployeeDao;
import com.example.empmgmt.model.Employee;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryEmployeeDao implements EmployeeDao {
    private final Map<Integer, Employee> store = new HashMap<>();
    private final AtomicInteger idSeq = new AtomicInteger(0);

    private void validate(Employee emp) {
        if (emp == null) throw new IllegalArgumentException("Employee cannot be null");
        if (emp.getName() == null || emp.getName().isBlank())
            throw new IllegalArgumentException("Employee name cannot be empty");
        if (emp.getDepartment() == null || emp.getDepartment().isBlank())
            throw new IllegalArgumentException("Employee department cannot be empty");
        if (emp.getSalary() == null || emp.getSalary().compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Employee salary must be >= 0");
    }

    @Override
    public Employee addEmployee(Employee emp) {
        validate(emp);
        int id = idSeq.incrementAndGet();
        emp.setId(id);
        store.put(id, new Employee(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        return emp;
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        validate(emp);
        if (emp.getId() == null || !store.containsKey(emp.getId())) {
            throw new NoSuchElementException("Employee not found with id: " + emp.getId());
        }
        store.put(emp.getId(), new Employee(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        return emp;
    }

    @Override
    public void deleteEmployee(int id) {
        if (!store.containsKey(id)) {
            throw new NoSuchElementException("Employee not found with id: " + id);
        }
        store.remove(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(store.values());
    }
}
