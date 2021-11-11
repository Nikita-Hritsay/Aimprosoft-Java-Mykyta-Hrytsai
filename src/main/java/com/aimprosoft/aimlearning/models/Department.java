package com.aimprosoft.aimlearning.models;

import com.aimprosoft.aimlearning.validations.department.IsUniqueName;

import lombok.*;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@With
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "iddepartment")
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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "department")
    @Fetch(FetchMode.JOIN)
    @ToString.Exclude
    private List<Employee> employees;

}
