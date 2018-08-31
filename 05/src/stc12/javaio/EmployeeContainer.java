package stc12.javaio;

import java.io.*;
import java.util.ArrayList;

public class EmployeeContainer {
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private String filename;

    public EmployeeContainer(String filename) {
        this.filename = filename;
        if ((new File(filename)).exists()) {
            loadFromFile();
        }

    }

    /**
     * Get employee by his name. Return null, if not found.
     *
     * @param name Employee name.
     * @return object or null.
     */
    public Employee getByName(String name) {
        for (Employee employee: employeeList) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Save new employee to file.
     *
     * @param employee object to save.
     */
    public void save(Employee employee) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename))) {
            employeeList.add(employee);
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete employee from file, if found.
     *
     * @param employeeToDelete object.
     */
    public void delete(Employee employeeToDelete) {
        Employee foundEmployee = null;
        for (Employee employee: employeeList) {
            if (employee.equals(employeeToDelete)) {
                foundEmployee = employee;
                break;
            }
        }
        if (foundEmployee != null) {
            employeeList.remove(foundEmployee);
            saveToFile();
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
            employeeList = (ArrayList<Employee>) stream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename))) {
            stream.writeObject(employeeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
