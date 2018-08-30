package stc12.javaio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void testSaveAndGetByName()
    {
        EmployeeContainer container1 = null;
        EmployeeContainer container2 = null;

        try {
             container1 = new EmployeeContainer(TEST_FILENAME);
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertNull(container1.getByName("Vasya"), "Test file already has data.");

        Employee employeeToSave = new Employee("Vasya", 45, 7000, "Driver");

        // Save 3 test employees. Our test employee must not be first or last.
        container1.save(new Employee("Innokentiy", 37, 140000, "Engineer"));
        container1.save(employeeToSave);
        container1.save(new Employee("Stanislav", 17, 0, "Student"));

        // Read data by other container to be sure that data was really written to file.
        try {
            container2 = new EmployeeContainer(TEST_FILENAME);
        } catch (IOException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }

        Employee employeeFromContainer = container2.getByName("Vasya");

        assertEmployeesEqual(employeeToSave, employeeFromContainer);
    }

    private void assertEmployeesEqual(Employee employee1, Employee employee2) {
        assertAll(
                () -> assertEquals(employee1.getName(), employee2.getName()),
                () -> assertEquals(employee1.getAge(), employee2.getAge()),
                () -> assertEquals(employee1.getSalary(), employee2.getSalary()),
                () -> assertEquals(employee1.getJob(), employee2.getJob())
        );
    }
}