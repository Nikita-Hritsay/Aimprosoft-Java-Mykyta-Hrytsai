package com.aimprosoft.aimlearning.models;

import com.aimprosoft.aimlearning.validations.department.IsUniqueName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;


@With
public class Department {
    private Integer idDepartment;

    @NotEmpty(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    @CheckWith(value = IsUniqueName.class, message = "department with such name exists")
    @Length(max = 100, message = "Name can not be greater than 100")
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

    public Department(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Department(Integer idDepartment, String name, String address) {
        this.idDepartment = idDepartment;
        this.name = name;
        this.address = address;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public String getName() {
        return name;
    }

    public Department() {
    }

    public Department(Integer idDepartment, String name) {
        this.idDepartment = idDepartment;
        this.name = name;
    }
}
