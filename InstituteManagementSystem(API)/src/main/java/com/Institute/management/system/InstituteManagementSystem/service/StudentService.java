package com.Institute.management.system.InstituteManagementSystem.service;

import com.Institute.management.system.InstituteManagementSystem.model.Student;
import com.Institute.management.system.InstituteManagementSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    // Get all student
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // Get specific student
    public Optional<Student> getStudent(int id) {

        return studentRepository.findById(id);

    }

    public Student addStudent(Student student) {

        return studentRepository.save(student);
    }

    // update student
    public Optional<Student> updateStudent(int id, Student updatedStudent) {
        Optional<Student> student = getStudent(id);
        student.ifPresent((currStudent)-> {
            currStudent.name = updatedStudent.name;
            currStudent.email = updatedStudent.email;

            studentRepository.save(currStudent);
                }
        );
        return student;
    }

    // Delete student
    public Optional<Student> deleteStudent(int id) {
        Optional<Student> student = getStudent(id);
       studentRepository.deleteById(id);

        return student;
    }
}
