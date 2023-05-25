package com.Institute.management.system.InstituteManagementSystem.model;

import jakarta.persistence.*;


@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column
    public String name;
    @Column
    public String email;
    @Column
    public String imagePath;

}

