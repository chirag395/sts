//
//package com.example.empmgmt.dao;
//
//import com.example.empmgmt.dao.impl.InMemoryEmployeeDao;
//import com.example.empmgmt.model.Employee;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class InMemoryEmployeeDaoTest {
//
//    private EmployeeDao dao;
//
//    @BeforeEach
//    void setUp() {
//        dao = new InMemoryEmployeeDao();
//    }
//
//    // ADD EMPLOYEE
//    @Test
//    void addEmployee_positive() {
//        Employee e = new Employee(null, "Alice", "IT", new BigDecimal("70000"));
//        Employee saved = dao.addEmployee(e);
//        assertNotNull(saved.getId(), "ID should be auto-assigned");
//        assertEquals("Alice", saved.getName());
//        assertEquals("IT", saved.getDepartment());
//        assertEquals(new BigDecimal("70000"), saved.getSalary());
//    }
//
//    @Test
//    void addEmployee_negative_nullEmployee() {
//        assertThrows(IllegalArgumentException.class, () -> dao.addEmployee(null));
//    }
//
//    @Test
//    void addEmployee_negative_emptyName() {
//        Employee e = new Employee(null, "", "IT", new BigDecimal("50000"));
//        assertThrows(IllegalArgumentException.class, () -> dao.addEmployee(e));
//    }
//
//    @Test
//    void addEmployee_negative_negativeSalary() {
//        Employee e = new Employee(null, "Bob", "HR", new BigDecimal("-1"));
//        assertThrows(IllegalArgumentException.class, () -> dao.addEmployee(e));
//    }
//
//    // UPDATE  EMPLOYEE
//    @Test
//    void updateEmployee_positive() {
//        Employee e = dao.addEmployee(new Employee(null, "Carol", "Sales", new BigDecimal("50000")));
//        e.setDepartment("Marketing");
//        e.setSalary(new BigDecimal("52000"));
//        Employee updated = dao.updateEmployee(e);
//        assertEquals("Marketing", updated.getDepartment());
//        assertEquals(new BigDecimal("52000"), updated.getSalary());
//    }
//
//    @Test
//    void updateEmployee_negative_notFound() {
//        Employee e = new Employee(999, "Ghost", "NA", new BigDecimal("1"));
//        assertThrows(NoSuchElementException.class, () -> dao.updateEmployee(e));
//    }
//
//    // DELETE  EMPLOYEE
//    @Test
//    void deleteEmployee_positive() {
//        Employee e = dao.addEmployee(new Employee(null, "Dan", "Ops", new BigDecimal("60000")));
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
//        dao.addEmployee(new Employee(null, "Eve", "IT", new BigDecimal("80000")));
//        dao.addEmployee(new Employee(null, "Frank", "IT", new BigDecimal("75000")));
//        List<Employee> list = dao.getAllEmployees();
//        assertEquals(2, list.size());
//    }
//}



package com.example.empmgmt.dao;

import com.example.empmgmt.dao.impl.InMemoryEmployeeDao;
import com.example.empmgmt.model.Employee;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * Lots of auto-generated tests using:
 * - Parameterized data via MethodSource
 * - Repeated tests with random data
 * - Dynamic tests built at runtime
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
class InMemoryEmployeeDaoTest {

    private EmployeeDao dao;

    @BeforeEach
    void setUp() {
        dao = new InMemoryEmployeeDao();
    }

    // ---------- Helpers ----------
    private static Employee validRandomEmployee() {
        String[] names = {"Alice", "Bob", "Carol", "Dan", "Eve", "Frank", "Grace", "Heidi"};
        String[] depts = {"IT", "HR", "Sales", "Marketing", "Ops", "Finance"};
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        String name = names[rnd.nextInt(names.length)] + "-" + rnd.nextInt(1000);
        String dept = depts[rnd.nextInt(depts.length)];
        BigDecimal salary = BigDecimal.valueOf(rnd.nextDouble(30000, 150000)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return new Employee(null, name, dept, salary);
    }

    private static Stream<Employee> manyValidEmployees() {
        return Stream.generate(InMemoryEmployeeDaoTest::validRandomEmployee).limit(50);
    }

    private static Stream<Employee> invalidEmployees() {
        // Various invalid cases: nulls, blanks, negative salary
        return Stream.of(
            new Employee(null, null, "IT", new BigDecimal("50000")),
            new Employee(null, "", "IT", new BigDecimal("50000")),
            new Employee(null, "Alice", null, new BigDecimal("50000")),
            new Employee(null, "Alice", "", new BigDecimal("50000")),
            new Employee(null, "Alice", "IT", null),
            new Employee(null, "Alice", "IT", new BigDecimal("-1"))
        );
    }

    // ---------- Parameterized-like via MethodSource (manual) ----------

    @Test
    @DisplayName("1) Add many valid employees (50 cases)")
    void add_many_valid() {
        manyValidEmployees().forEach(emp -> {
            Employee saved = dao.addEmployee(emp);
            assertNotNull(saved.getId(), "ID should be assigned");
            assertEquals(emp.getName(), saved.getName());
        });
        assertEquals(50, dao.getAllEmployees().size(), "Store should contain 50 employees");
    }

    @Test
    @DisplayName("2) Add invalid employees (multiple cases) should throw")
    void add_many_invalid_shouldThrow() {
        invalidEmployees().forEach(emp ->
            assertThrows(IllegalArgumentException.class, () -> dao.addEmployee(emp))
        );
        assertTrue(dao.getAllEmployees().isEmpty(), "Invalid adds should not modify store");
    }

    // ---------- Repeated tests with random data ----------

    @RepeatedTest(20)
    @DisplayName("3) Repeated add/update/delete with random data")
    void repeated_random_crud_test() {
        // Add
        Employee e = validRandomEmployee();
        Employee saved = dao.addEmployee(e);
        assertNotNull(saved.getId());

        // Update
        saved.setDepartment("QA");
        saved.setSalary(saved.getSalary().add(new BigDecimal("1000")));
        Employee updated = dao.updateEmployee(saved);
        assertEquals("QA", updated.getDepartment());

        // Delete
        assertDoesNotThrow(() -> dao.deleteEmployee(updated.getId()));
        assertTrue(dao.getAllEmployees().stream().noneMatch(x -> x.getId().equals(updated.getId())));
    }

    // ---------- Dynamic tests built at runtime ----------

    @TestFactory
    @DisplayName("4) Dynamic tests: bulk adds + updates")
    Stream<DynamicTest> dynamic_bulk_add_update() {
        List<Employee> batch = new ArrayList<>();
        for (int i = 0; i < 30; i++) batch.add(validRandomEmployee());

        // Build dynamic tests: add each, then update each
        Stream<DynamicTest> addTests = batch.stream().map(emp ->
            dynamicTest("Add " + emp.getName(), () -> {
                Employee saved = dao.addEmployee(emp);
                assertNotNull(saved.getId());
            })
        );

        Stream<DynamicTest> updateTests = batch.stream().map(emp ->
            dynamicTest("Update " + emp.getName(), () -> {
                // Assume added already
                List<Employee> all = dao.getAllEmployees();
                Optional<Employee> match = all.stream().filter(e -> e.getName().equals(emp.getName())).findFirst();
                assertTrue(match.isPresent(), "Employee should exist");
                Employee toUpdate = match.get();
                toUpdate.setDepartment("UpdatedDept");
                Employee updated = dao.updateEmployee(toUpdate);
                assertEquals("UpdatedDept", updated.getDepartment());
            })
        );

        return Stream.concat(addTests, updateTests);
    }

    // ---------- Negative dynamic deletes ----------
    @Test
    @DisplayName("5) Delete non-existent IDs should throw")
    void delete_nonexistent_many() {
        int[] badIds = { -1, 0, 9999, 101010, Integer.MAX_VALUE };
        for (int id : badIds) {
            assertThrows(NoSuchElementException.class, () -> dao.deleteEmployee(id));
        }
    }
}
