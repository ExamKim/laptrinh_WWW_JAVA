package com.example.midterm3.model;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class Employee {
    private int id;
    private String name;
    private double salary;
    private int departmentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Employee() {
    }

    public Employee(int id, String name, double salary, int departmentId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.departmentId = departmentId;
    }
}
