package com.Institute.management.system.InstituteManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column
    public String name;

    @OneToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @JsonIgnore
    public Teacher mentor;

    @JsonProperty("teacher_id")
    public Integer getMentorId() {return mentor != null? mentor.id: null;}
}