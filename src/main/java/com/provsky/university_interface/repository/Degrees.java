package com.provsky.university_interface.repository;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "degrees")
@Data
public class Degrees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rank")
    private String rank;
}
