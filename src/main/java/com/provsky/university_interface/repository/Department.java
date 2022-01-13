package com.provsky.university_interface.repository;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column
    private int head_id;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
    private List<Lector> lectors;

}