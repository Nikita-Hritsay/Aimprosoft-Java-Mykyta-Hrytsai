package com.aimprosoft.aimlearning.models;

import com.aimprosoft.aimlearning.validations.department.IsUniqueName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

}
