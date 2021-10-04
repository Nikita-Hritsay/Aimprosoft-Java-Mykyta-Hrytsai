package com.aimprosoft.aimlearning.model;

public class Department {
    private int idDepartment;
    private String name;
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
