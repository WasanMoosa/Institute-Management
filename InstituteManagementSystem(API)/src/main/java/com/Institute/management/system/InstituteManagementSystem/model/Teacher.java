package com.Institute.management.system.InstituteManagementSystem.model;

import jakarta.persistence.*;


@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column
    public String name;
    @Column
    public String email;
    @Column
    public double salary;

    @OneToOne(mappedBy = "mentor")
    public Course course;

}
