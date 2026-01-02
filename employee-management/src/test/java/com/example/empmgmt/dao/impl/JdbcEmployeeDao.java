
package com.example.empmgmt.dao.impl;

import com.example.empmgmt.dao.EmployeeDao;
import com.example.empmgmt.db.ConnectionFactory;
import com.example.empmgmt.model.Employee;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class JdbcEmployeeDao implements EmployeeDao {

    protected void validate(Employee emp) {
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
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?,?,?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setBigDecimal(3, emp.getSalary());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Insert failed, no rows affected.");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    emp.setId(rs.getInt(1));
                }
            }
            return emp;
        } catch (SQLException e) {
            throw new RuntimeException("Error adding employee", e);
        }
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        validate(emp);
        if (emp.getId() == null) {
            throw new IllegalArgumentException("Employee id is required for update");
        }
        String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setBigDecimal(3, emp.getSalary());
            ps.setInt(4, emp.getId());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new NoSuchElementException("Employee not found with id: " + emp.getId());
            }
            return emp;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating employee", e);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new NoSuchElementException("Employee not found with id: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting employee", e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT id, name, department, salary FROM employees ORDER BY id";
        List<Employee> list = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setDepartment(rs.getString("department"));
                e.setSalary(rs.getBigDecimal("salary"));
                list.add(e);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching employees", e);
        }
    }
}
