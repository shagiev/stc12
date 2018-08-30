package stc12.javaio;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private int age;
    private float salary;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getSalary() {
        return salary;
    }

    public String getJob() {
        return job;
    }

    private String job;

    public Employee(String name, int age, float salary, String job) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.job = job;
    }

}
