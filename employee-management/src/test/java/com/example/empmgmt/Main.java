
package com.example.empmgmt;

import com.example.empmgmt.dao.EmployeeDao;
import com.example.empmgmt.dao.impl.InMemoryEmployeeDao;
import com.example.empmgmt.dao.impl.JdbcEmployeeDao;
import com.example.empmgmt.db.ConnectionFactory;
import com.example.empmgmt.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String daoType = ConnectionFactory.getDaoType();
        EmployeeDao employeeDao = "jdbc".equalsIgnoreCase(daoType)
                ? new JdbcEmployeeDao()
                : new InMemoryEmployeeDao();

        System.out.println("Employee Management");
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                printMenu();
                int choice = readInt(scanner, "Enter choice: ");
                try {
                    switch (choice) {
                        case 1 -> add(scanner, employeeDao);
                        case 2 -> update(scanner, employeeDao);
                        case 3 -> delete(scanner, employeeDao);
                        case 4 -> listAll(employeeDao);
                        case 0 -> exit = true;
                        default -> System.out.println("Invalid choice. Try again.");
                    }
                } catch (IllegalArgumentException | NoSuchElementException ex) {
                    System.out.println("Error: " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Unexpected error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
        System.out.println("Have a nice day");
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. List All Employees");
        System.out.println("0. Exit");
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static BigDecimal readBigDecimal(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return new BigDecimal(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g., 50000.00).");
            }
        }
    }

    private static void add(Scanner sc, EmployeeDao dao) {
        System.out.println("\n[Add Employee]");
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Department: ");
        String dept = sc.nextLine().trim();
        BigDecimal salary = readBigDecimal(sc, "Salary: ");

        Employee emp = new Employee(null, name, dept, salary);
        Employee saved = dao.addEmployee(emp);
        System.out.println("Added: " + saved);
    }

    private static void update(Scanner sc, EmployeeDao dao) {
        System.out.println("\n[Update Employee]");
        int id = readInt(sc, "Existing Employee ID: ");
        System.out.print("New Name: ");
        String name = sc.nextLine().trim();
        System.out.print("New Department: ");
        String dept = sc.nextLine().trim();
        BigDecimal salary = readBigDecimal(sc, "New Salary: ");
 
        Employee emp = new Employee(id, name, dept, salary);
        Employee updated = dao.updateEmployee(emp);
        System.out.println("Updated: " + updated);
    }

    private static void delete(Scanner sc, EmployeeDao dao) {
        System.out.println("\n[Delete Employee]");
        int id = readInt(sc, "Employee ID to delete: ");
        dao.deleteEmployee(id);
        System.out.println("Deleted employee with id " + id);
    }

    private static void listAll(EmployeeDao dao) {
        System.out.println("\n[All Employees]");
        List<Employee> all = dao.getAllEmployees();
        if (all.isEmpty()) {
            System.out.println("(none)");
        } else {
            all.forEach(System.out::println);
        }
    }
}
