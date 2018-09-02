package stc12.javaio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
        Employee[] employees = saveTestEmployees();

        EmployeeContainer container = new EmployeeContainer(TEST_FILENAME);

        Employee employeeFromContainer = container.getByName("Vasya");

        assertNotNull(employeeFromContainer, "Cannot find employee by name.");
        assertEmployeesEqual(employees[1], employeeFromContainer);
    }

    @Test
    public void testDelete() {
        EmployeeContainer container1 = new EmployeeContainer(TEST_FILENAME);
        Employee employee = new Employee("Vasya", 23, 1, "test job");
        assertNull(container1.getByName(employee.getName()), "Test file already has data.");
        assertFalse(container1.delete(employee), "Deleting of not added employee returns true.");
        assertTrue(container1.save(employee));

        // Test that employee was saved.
        assertEquals(employee.getName(), new EmployeeContainer(TEST_FILENAME).getByName(employee.getName()).getName());

        // Try to delete and check that the employee was deleted.
        assertTrue(new EmployeeContainer(TEST_FILENAME).delete(employee));
        assertNull(
            new EmployeeContainer(TEST_FILENAME).getByName(employee.getName()),
            "Employee was not deleted"
        );
    }

    @Test
    public void testGetByJob() {
        Employee[] employees = saveTestEmployees();

        ArrayList<Employee> engineers = new EmployeeContainer(TEST_FILENAME).getByJob(  "Engineer");

        assertEquals(2, engineers.size(), "getByJob returned incorrect count of employees");
        assertTrue(
                (engineers.get(0).equals(employees[1]) && engineers.get(1).equals(employees[3])) ||
                        (engineers.get(0).equals(employees[3]) && engineers.get(1).equals(employees[1])),
                "Method getByJob returns incorrect employees"
        );
    }

    @Test
    public void testSaveOrUpdate() {
        Employee[] employees = saveTestEmployees();
        Employee testEmployee = new Employee("John", 34, 1, "Musician");
        assertTrue(new EmployeeContainer(TEST_FILENAME).saveOrUpdate(testEmployee));
        assertEmployeesEqual(testEmployee, new EmployeeContainer(TEST_FILENAME).getByName("John"));

        testEmployee = new Employee("John", 24, 2, "-");
        assertTrue(new EmployeeContainer(TEST_FILENAME).saveOrUpdate(testEmployee));
        assertEmployeesEqual(testEmployee, new EmployeeContainer(TEST_FILENAME).getByName("John"));
    }

    @Test
    public void testChangeAllWork() {
        saveTestEmployees();
        assertTrue(new EmployeeContainer(TEST_FILENAME).changeAllWork("Engineer", "Artist"));
        ArrayList<Employee> artists = new EmployeeContainer(TEST_FILENAME).getByJob("Artist");
        assertEquals(2, artists.size());
        assertTrue(
                (artists.get(0).getName().equals("Vasya") && artists.get(1).getName().equals("Stanislav")) ||
                        (artists.get(1).getName().equals("Vasya") && artists.get(0).getName().equals("Stanislav"))
        );
    }

    @Test
    public void testGetSalarySum() {
        assertEquals(0.0, new EmployeeContainer(TEST_FILENAME).getSalarySum());
        saveTestEmployees();
        assertEquals(221001.0, new EmployeeContainer(TEST_FILENAME).getSalarySum(), "Error salary sum counting");
    }

    private Employee[] saveTestEmployees() {
        EmployeeContainer container1 = new EmployeeContainer(TEST_FILENAME);
        assertNull(container1.getByName("Vasya"), "Test file already has data.");

        Employee[] employeesToSave = {
                new Employee("Name1", 3, 1, ""),
                new Employee("Vasya", 45, 7000, "Engineer"),
                new Employee("Innokentiy", 37, 14000, "Student"),
                new Employee("Stanislav", 17, 200000, "Engineer")
        };

        for (Employee em: employeesToSave) {
            assertTrue(container1.save(em));
        }

        return employeesToSave;
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