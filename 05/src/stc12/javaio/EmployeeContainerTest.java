package stc12.javaio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeContainerTest {
    private final String TEST_FILENAME = "testDataFile.dat";

    @BeforeEach
    void setUp() {
        assertFalse(
            new File(TEST_FILENAME).exists(),
            "File " + TEST_FILENAME + " exists. Cannot use it for tests"
        );
    }

    @AfterEach
    void tearDown() {
        new File(TEST_FILENAME).delete();
    }

    @Test
    public void testSaveAndGetByName() throws IOException {
        EmployeeContainer container1 = new EmployeeContainer(TEST_FILENAME);
        assertNull(container1.getByName("Vasya"), "Test file already has data.");

        Employee employeeToSave = new Employee("Vasya", 45, 7000, "Driver");

        // Save 3 test employees. Our test employee must not be first or last.
        container1.save(new Employee("Innokentiy", 37, 140000, "Engineer"));
        container1.save(employeeToSave);
        container1.save(new Employee("Stanislav", 17, 0, "Student"));

        // Read data by other container to be sure that data was really written to file.
        EmployeeContainer container2 = new EmployeeContainer(TEST_FILENAME);

        Employee employeeFromContainer = container2.getByName("Vasya");

        assertNotNull(employeeFromContainer, "Cannot find employee by name.");
        assertEmployeesEqual(employeeToSave, employeeFromContainer);
    }

    @Test
    public void testDelete() {
        EmployeeContainer container1 = new EmployeeContainer(TEST_FILENAME);
        Employee employee = new Employee("Vasya", 23, 1, "test job");
        assertNull(container1.getByName(employee.getName()), "Test file already has data.");
        container1.save(employee);

        // Test that employee was saved.
        assertEquals(employee.getName(), new EmployeeContainer(TEST_FILENAME).getByName(employee.getName()).getName());

        // Try to delete and check that the employee was deleted.
        new EmployeeContainer(TEST_FILENAME).delete(employee);
        assertNull(
            new EmployeeContainer(TEST_FILENAME).getByName(employee.getName()),
            "Employee was not deleted"
        );
    }

    private void assertEmployeesEqual(Employee employee1, Employee employee2) {
        assertNotNull(employee1);
        assertNotNull(employee2);
        assertAll(
                () -> assertEquals(employee1.getName(), employee2.getName()),
                () -> assertEquals(employee1.getAge(), employee2.getAge()),
                () -> assertEquals(employee1.getSalary(), employee2.getSalary()),
                () -> assertEquals(employee1.getJob(), employee2.getJob())
        );
    }
}