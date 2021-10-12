package com.aimprosoft.aimlearning.models;

import com.aimprosoft.aimlearning.validations.department.isUniqueName;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

public class Department {
    private int idDepartment;
    @NotEmpty(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    @Length(max = 100, message = "Name can not be greater than 100")
    @CheckWith(value = isUniqueName.class, message = "department with such name exists")
    private String name;
    @NotEmpty(message = "Address can not be empty")
    @NotNull(message = "Address can not be null")
    @Length(max = 200, message = "Address can not be greater than 200")
    private String address;

    @Override
    public String toString() {
        return "Department{" +
                "idDepartment=" + idDepartment +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Department(int idDepartment, String name, String address) {
        this.idDepartment = idDepartment;
        this.name = name;
        this.address = address;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department() {
    }

    public Department(int idDepartment, String name) {
        this.idDepartment = idDepartment;
        this.name = name;
    }
}
