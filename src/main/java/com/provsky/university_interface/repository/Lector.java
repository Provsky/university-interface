package com.provsky.university_interface.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lectors")
@Data
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private int salary;

    @ManyToOne(optional = false)
    @JoinColumn(name = "degree", referencedColumnName = "id")
    private Degrees degree;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "departments_lectors",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments;

}
