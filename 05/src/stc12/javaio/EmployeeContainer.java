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
     * @return success.
     */
    public boolean save(Employee employee) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename))) {
            employeeList.add(employee);
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Delete employee from file, if found.
     *
     * @param employeeToDelete object.
     * @return true if employee deleted. False if nothing to delete.
     */
    public boolean delete(Employee employeeToDelete) {
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
            return true;
        }
        return false;
    }

    /**
     * Get list of employees, working on concrete job.
     *
     * @param job Job name.
     * @return List of employees.
     */
    public ArrayList<Employee> getByJob(String job) {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee em: employeeList) {
            if (em.getJob().equals(job)) {
                result.add(em);
            }
        }
        return result;
    }

    /**
     * Add employee to list or update if already exists.
     *
     * @param employee object to add or update.
     * @return success.
     */
    public boolean saveOrUpdate(Employee employee) {
        Employee foundEmployee = getByName(employee.getName());
        if (foundEmployee != null) {
            employeeList.remove(foundEmployee);
        }
        employeeList.add(employee);
        saveToFile();
        return true;
    }

    /**
     * Change job for all employees had old job to new job.
     *
     * @param oldJob old job name.
     * @param newJob new job name.
     * @return success.
     */
    public boolean changeAllWork(String oldJob, String newJob) {
        for (Employee em: getByJob(oldJob)) {
            Employee newEmployee = new Employee(em.getName(), em.getAge(), em.getSalary(), newJob);
            employeeList.remove(em);
            employeeList.add(newEmployee);
        }
        saveToFile();
        return true;
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
