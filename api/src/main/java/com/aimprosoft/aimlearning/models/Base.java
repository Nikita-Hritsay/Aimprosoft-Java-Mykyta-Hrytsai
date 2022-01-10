package com.aimprosoft.aimlearning.models;

import lombok.Getter;
import lombok.Setter;
import lombok.With;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public class Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_department")
    private Integer id;

}
