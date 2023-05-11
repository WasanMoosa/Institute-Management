package com.Institute.management.system.InstituteManagementSystem.controller;

import com.Institute.management.system.InstituteManagementSystem.configuration.SecurityConfig;
import com.Institute.management.system.InstituteManagementSystem.model.Student;
import com.Institute.management.system.InstituteManagementSystem.service.StudentService;
import jakarta.websocket.server.PathParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // To get all Student
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    // To get one student
    @GetMapping(path = "/{id}")
    public Optional<Student> getStudent(@PathVariable int id) {

        return studentService.getStudent(id);
    }

    // Add student to the list
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return student;
    }

    @PostMapping (value = "/withImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Student addStudentWithImage(@RequestParam String name,
                                       @RequestParam String email,
                                       @RequestParam(required = false) MultipartFile image ) throws IOException {
        Student student = new Student();
        student.name=name;
        student.email=email;
        Student savedStudent =studentService.addStudent(student);

        // TODO:  get the extention of original image
        // System.out.println(Arrays.toString(image.getOriginalFilename().split(".")));

        if (image != null){
            String imagePathToSave= ".\\src\\main\\resources\\static\\student_images\\"+Integer.toString(student.id)+"_"+student.name+".jpg";
            String imagePath="\\static\\student_images\\"+Integer.toString(student.id)+"_"+student.name+".jpg";
            FileUtils.writeByteArrayToFile(new File(imagePathToSave), image.getBytes());
            savedStudent.imagePath=imagePath;
            studentService.updateStudent(savedStudent.id, savedStudent);
        }


        return student;
    }

    // Update existing student using his id
    @PutMapping("/{id}")

    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return student;
    }

    // Delete existing student using his id
    @DeleteMapping("/{id}")
    public Optional<Student> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return studentService.deleteStudent(id);
    }
}
