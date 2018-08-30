package stc12.javaio;

import java.io.*;
import java.util.ArrayList;

public class EmployeeContainer {
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private String filename;

    public EmployeeContainer(String filename) throws IOException {
        this.filename = filename;
        if ((new File(filename)).exists()) {
            loadFromFile();
        }

    }

    private void loadFromFile() throws IOException {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
            Employee employee;
            do {
                employee = (Employee) stream.readObject();
                employeeList.add(employee);
            } while (employee != null);
            stream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Employee getByName(String name) {
        for (Employee employee: employeeList) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }

    public void save(Employee employee) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename))) {
            stream.writeObject(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
