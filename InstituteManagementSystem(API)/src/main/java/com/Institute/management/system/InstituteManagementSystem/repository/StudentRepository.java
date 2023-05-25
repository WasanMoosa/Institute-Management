package com.Institute.management.system.InstituteManagementSystem.repository;

import com.Institute.management.system.InstituteManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
