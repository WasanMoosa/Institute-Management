package com.Institute.management.system.InstituteManagementSystem.service;

import com.Institute.management.system.InstituteManagementSystem.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class StudentService {
    private List<Student> allStudents = new CopyOnWriteArrayList<>();

    // Get all student
    public List<Student> getStudents() {
        return allStudents;
    }

    // Get specific student
    public Optional<Student> getStudent(int id) {
        Optional<Student> foundStudent = allStudents.stream().filter(
                student -> {
                    return student.id == id;
                }).findFirst();
        return foundStudent;

    }

    // Add student
    int id = 1;

    public Student addStudent(Student student) {
        student.id = this.id++;
        allStudents.add(student);

        return student;
    }

    // update student
    public Optional<Student> updateStudent(int id, Student updatedStudent) {
        Optional<Student> student = getStudent(id);
        student.ifPresent((currStudent)-> {
            currStudent.name = updatedStudent.name;
            currStudent.email = updatedStudent.email;
                }
        );
        return student;
    }

    // Delete student
    public Optional<Student> deleteStudent(int id) {
        Optional<Student> student = getStudent(id);
        allStudents.remove(student);

        return student;
    }
}
