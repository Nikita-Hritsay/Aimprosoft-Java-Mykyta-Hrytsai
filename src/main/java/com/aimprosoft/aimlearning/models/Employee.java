package com.aimprosoft.aimlearning.models;


import com.aimprosoft.aimlearning.validations.employee.isUniqueEmail;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idemployee")
    private int id;
    @NotEmpty(message = "Firstname can not be empty")
    @NotNull(message = "Firstname can not be null")
    @Length(max = 30, message = "Firstname can not be greater than 30")
    private String firstName;
    @NotEmpty(message = "Lastname can not be empty")
    @NotNull(message = "Lastname can not be null")
    @Length(max = 30, message = "Lastname can not be greater than 30")
    private String lastName;
    @NotEmpty(message = "email can not be empty")
    @NotNull(message = "email can not be null")
    @Length(max = 75, message = "Firstname can not be greater than 30")
    @CheckWith(value = isUniqueEmail.class, message = "employee with such email exists")
    @Email(message = "wrong email format")
    private String email;
    @NotEmpty(message = "Salary can not be empty")
    @NotNull(message = "Salary can not be null")
    @Min(value = 1, message = "salary can not be smaller than 1")
    private int salary;
    @NotEmpty(message = "Hire date can not be empty")
    @NotNull(message = "Hire date can not be null")
    private Date hireDate;
    @NotEmpty(message = "Id department can not be empty")
    @NotNull(message = "Id department can not be null")
    @Min(value = 1, message = "Id department can not be smaller than 1")
    @Column(name = "department_idDepartment")
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
