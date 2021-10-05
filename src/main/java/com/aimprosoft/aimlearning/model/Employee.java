package com.aimprosoft.aimlearning.model;

import java.util.Date;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int salary;
    private Date hireDate;
    private int idDepartment;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", hireDate='" + hireDate + '\'' +
                ", idDepartment=" + idDepartment +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee(String firstName, String lastName, String email, int salary, Date hireDate, int idDepartment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.hireDate = hireDate;
        this.idDepartment = idDepartment;
    }

    public Employee(int id, String firstName, String lastName, String email, int salary, Date hireDate, int idDepartment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.hireDate = hireDate;
        this.idDepartment = idDepartment;
    }

    public Employee(String firstName, String lastName, int salary, Date hireDate, int idDepartment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.hireDate = hireDate;
        this.idDepartment = idDepartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, int salary, Date hireDate, int idDepartment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.hireDate = hireDate;
        this.idDepartment = idDepartment;
    }


}
