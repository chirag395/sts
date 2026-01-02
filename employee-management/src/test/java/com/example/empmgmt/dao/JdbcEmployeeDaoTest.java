//
//package com.example.empmgmt.dao;
//
//import com.example.empmgmt.dao.impl.JdbcEmployeeDao;
//import com.example.empmgmt.model.Employee;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.sql.*;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//class JdbcEmployeeDaoTest {
//
//    private JdbcEmployeeDao dao;
//
//
//    private Connection h2Connection;
//
//    @BeforeEach
//    void setup() throws Exception {
//
//        h2Connection = DriverManager.getConnection(
//                "jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false",
//                "sa", ""
//        );
//
//
//        try (Statement st = h2Connection.createStatement()) {
//            st.execute("DROP TABLE IF EXISTS employees");
//            st.execute("""
//                CREATE TABLE employees (
//                  id INT AUTO_INCREMENT PRIMARY KEY,
//                  name VARCHAR(100) NOT NULL,
//                  department VARCHAR(100) NOT NULL,
//                  salary DECIMAL(15,2) NOT NULL
//                )
//            """);
//        }
//
//   
//        dao = new JdbcEmployeeDao() {
//            
//            private Connection getH2() throws SQLException {
//         
//                return h2Connection;
//            }
//            @Override
//            public Employee addEmployee(Employee emp) {
//                validate(emp);
//                String sql = "INSERT INTO employees (name, department, salary) VALUES (?,?,?)";
//                try (PreparedStatement ps = getH2().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//                    ps.setString(1, emp.getName());
//                    ps.setString(2, emp.getDepartment());
//                    ps.setBigDecimal(3, emp.getSalary());
//                    int affected = ps.executeUpdate();
//                    if (affected == 0) throw new SQLException("Insert failed");
//                    try (ResultSet rs = ps.getGeneratedKeys()) {
//                        if (rs.next()) emp.setId(rs.getInt(1));
//                    }
//                    return emp;
//                } catch (SQLException e) {
//                    throw new RuntimeException("Error adding employee", e);
//                }
//            }
//            @Override
//            public Employee updateEmployee(Employee emp) {
//                validate(emp);
//                if (emp.getId() == null) throw new IllegalArgumentException("Employee id is required for update");
//                String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
//                try (PreparedStatement ps = getH2().prepareStatement(sql)) {
//                    ps.setString(1, emp.getName());
//                    ps.setString(2, emp.getDepartment());
//                    ps.setBigDecimal(3, emp.getSalary());
//                    ps.setInt(4, emp.getId());
//                    int affected = ps.executeUpdate();
//                    if (affected == 0) throw new NoSuchElementException("Employee not found with id: " + emp.getId());
//                    return emp;
//                } catch (SQLException e) {
//                    throw new RuntimeException("Error updating employee", e);
//                }
//            }
//            @Override
//            public void deleteEmployee(int id) {
//                String sql = "DELETE FROM employees WHERE id=?";
//                try (PreparedStatement ps = getH2().prepareStatement(sql)) {
//                    ps.setInt(1, id);
//                    int affected = ps.executeUpdate();
//                    if (affected == 0) throw new NoSuchElementException("Employee not found with id: " + id);
//                } catch (SQLException e) {
//                    throw new RuntimeException("Error deleting employee", e);
//                }
//            }
//            @Override
//            public List<Employee> getAllEmployees() {
//                String sql = "SELECT id, name, department, salary FROM employees ORDER BY id";
//                try (PreparedStatement ps = getH2().prepareStatement(sql);
//                     ResultSet rs = ps.executeQuery()) {
//                    List<Employee> list = new java.util.ArrayList<>();
//                    while (rs.next()) {
//                        Employee e = new Employee();
//                        e.setId(rs.getInt("id"));
//                        e.setName(rs.getString("name"));
//                        e.setDepartment(rs.getString("department"));
//                        e.setSalary(rs.getBigDecimal("salary"));
//                        list.add(e);
//                    }
//                    return list;
//                } catch (SQLException e) {
//                    throw new RuntimeException("Error fetching employees", e);
//                }
//            }
//        };
//    }
//
//
//    @Test
//    void addEmployee_positive() {
//        Employee e = new Employee(null, "Alice", "IT", new BigDecimal("70000"));
//        Employee saved = dao.addEmployee(e);
//        assertNotNull(saved.getId(), "ID should be auto-assigned");
//    }
//
//    @Test
//    void addEmployee_negative_emptyName() {
//        Employee e = new Employee(null, "", "IT", new BigDecimal("50000"));
//        assertThrows(IllegalArgumentException.class, () -> dao.addEmployee(e));
//    }
//
//
//    @Test
//    void updateEmployee_positive() {
//        Employee e = dao.addEmployee(new Employee(null, "Bob", "HR", new BigDecimal("55000")));
//        e.setSalary(new BigDecimal("58000"));
//        Employee updated = dao.updateEmployee(e);
//        assertEquals(new BigDecimal("58000"), updated.getSalary());
//    }
//
//    @Test
//    void updateEmployee_negative_notFound() {
//        Employee e = new Employee(9999, "Ghost", "NA", new BigDecimal("1"));
//        assertThrows(NoSuchElementException.class, () -> dao.updateEmployee(e));
//    }
//
//
//    @Test
//    void deleteEmployee_positive() {
//        Employee e = dao.addEmployee(new Employee(null, "Carol", "Sales", new BigDecimal("45000")));
//        assertDoesNotThrow(() -> dao.deleteEmployee(e.getId()));
//    }
//
//    @Test
//    void deleteEmployee_negative_notFound() {
//        assertThrows(NoSuchElementException.class, () -> dao.deleteEmployee(12345));
//    }
//
//
//    @Test
//    void getAllEmployees_positive() {
//        dao.addEmployee(new Employee(null, "Dan", "Ops", new BigDecimal("60000")));
//        dao.addEmployee(new Employee(null, "Eve", "Ops", new BigDecimal("62000")));
//        List<Employee> all = dao.getAllEmployees();
//        assertEquals(2, all.size());
//    }
//}



package com.example.empmgmt.dao;

import com.example.empmgmt.dao.impl.JdbcEmployeeDao;
import com.example.empmgmt.model.Employee;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class JdbcEmployeeDaoTest {

    private Connection h2;
    private JdbcEmployeeDao dao;

    @BeforeEach
    void setup() throws Exception {
        h2 = DriverManager.getConnection("jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false", "sa", "");
        try (Statement st = h2.createStatement()) {
            st.execute("DROP TABLE IF EXISTS employees");
            st.execute("""
                CREATE TABLE employees (
                  id INT AUTO_INCREMENT PRIMARY KEY,
                  name VARCHAR(100) NOT NULL,
                  department VARCHAR(100) NOT NULL,
                  salary DECIMAL(15,2) NOT NULL
                )
            """);
        }

        // Override the DAO to use this H2 connection (instead of MySQL)
        dao = new JdbcEmployeeDao() {
            private Connection get() throws SQLException { return h2; }
            @Override public Employee addEmployee(Employee emp) {
                validate(emp);
                String sql = "INSERT INTO employees (name, department, salary) VALUES (?,?,?)";
                try (PreparedStatement ps = get().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, emp.getName());
                    ps.setString(2, emp.getDepartment());
                    ps.setBigDecimal(3, emp.getSalary());
                    int affected = ps.executeUpdate();
                    if (affected == 0) throw new SQLException("Insert failed");
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) emp.setId(rs.getInt(1));
                    }
                    return emp;
                } catch (SQLException e) { throw new RuntimeException("Error adding employee", e); }
            }
            @Override public Employee updateEmployee(Employee emp) {
                validate(emp);
                if (emp.getId() == null) throw new IllegalArgumentException("Employee id is required for update");
                String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
                try (PreparedStatement ps = get().prepareStatement(sql)) {
                    ps.setString(1, emp.getName());
                    ps.setString(2, emp.getDepartment());
                    ps.setBigDecimal(3, emp.getSalary());
                    ps.setInt(4, emp.getId());
                    int affected = ps.executeUpdate();
                    if (affected == 0) throw new NoSuchElementException("Employee not found with id: " + emp.getId());
                    return emp;
                } catch (SQLException e) { throw new RuntimeException("Error updating employee", e); }
            }
            @Override public void deleteEmployee(int id) {
                String sql = "DELETE FROM employees WHERE id=?";
                try (PreparedStatement ps = get().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    int affected = ps.executeUpdate();
                    if (affected == 0) throw new NoSuchElementException("Employee not found with id: " + id);
                } catch (SQLException e) { throw new RuntimeException("Error deleting employee", e); }
            }
            @Override public List<Employee> getAllEmployees() {
                String sql = "SELECT id, name, department, salary FROM employees ORDER BY id";
                try (PreparedStatement ps = get().prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {
                    List<Employee> list = new ArrayList<>();
                    while (rs.next()) {
                        Employee e = new Employee();
                        e.setId(rs.getInt("id"));
                        e.setName(rs.getString("name"));
                        e.setDepartment(rs.getString("department"));
                        e.setSalary(rs.getBigDecimal("salary"));
                        list.add(e);
                    }
                    return list;
                } catch (SQLException e) { throw new RuntimeException("Error fetching employees", e); }
            }
        };
    }

    private static Employee randEmp() {
        String[] names = {"Alice", "Bob", "Carol", "Dan", "Eve", "Frank", "Grace", "Heidi"};
        String[] depts = {"IT", "HR", "Sales", "Marketing", "Ops", "Finance"};
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        String name = names[rnd.nextInt(names.length)] + "-" + rnd.nextInt(1000);
        String dept = depts[rnd.nextInt(depts.length)];
        BigDecimal salary = BigDecimal.valueOf(rnd.nextDouble(40000, 180000)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return new Employee(null, name, dept, salary);
    }

    @Test
    @DisplayName("1) Bulk add 100 valid employees")
    void bulk_add_100() {
        for (int i = 0; i < 100; i++) {
            Employee e = randEmp();
            Employee saved = dao.addEmployee(e);
            assertNotNull(saved.getId());
        }
        assertEquals(100, dao.getAllEmployees().size());
    }

    @RepeatedTest(25)
    @DisplayName("2) Repeated add+update+list")
    void repeated_add_update_list() {
        Employee e = dao.addEmployee(randEmp());
        e.setDepartment("QA");
        e.setSalary(e.getSalary().add(new BigDecimal("500")));
        Employee updated = dao.updateEmployee(e);
        assertEquals("QA", updated.getDepartment());
        List<Employee> all = dao.getAllEmployees();
        assertTrue(all.stream().anyMatch(x -> Objects.equals(x.getId(), e.getId())));
    }

    @TestFactory
    @DisplayName("3) Dynamic deletes for many IDs")
    Stream<DynamicTest> dynamic_deletes() {
        // Seed some employees
        List<Employee> seeded = new ArrayList<>();
        for (int i = 0; i < 20; i++) seeded.add(dao.addEmployee(randEmp()));
        Stream<DynamicTest> deleteValid = seeded.stream().map(emp ->
            dynamicTest("Delete id=" + emp.getId(), () -> {
                assertDoesNotThrow(() -> dao.deleteEmployee(emp.getId()));
            })
        );
        int[] badIds = { -5, 0, 99999, Integer.MAX_VALUE };
        Stream<DynamicTest> deleteInvalid = Arrays.stream(badIds).mapToObj(id ->
            dynamicTest("Delete non-existent id=" + id, () -> {
                assertThrows(NoSuchElementException.class, () -> dao.deleteEmployee(id));
            })
        );
        return Stream.concat(deleteValid, deleteInvalid);
    }

    @Test
    @DisplayName("4) Invalid adds should throw (name/department/salary)")
    void invalid_adds_throw() {
        List<Employee> invalids = List.of(
            new Employee(null, null, "IT", new BigDecimal("50000")),
            new Employee(null, "", "IT", new BigDecimal("50000")),
            new Employee(null, "Alice", null, new BigDecimal("50000")),
            new Employee(null, "Alice", "", new BigDecimal("50000")),
            new Employee(null, "Alice", "IT", null),
            new Employee(null, "Alice", "IT", new BigDecimal("-1"))
        );
        for (Employee e : invalids) {
            assertThrows(IllegalArgumentException.class, () -> dao.addEmployee(e));
        }
    }
}
