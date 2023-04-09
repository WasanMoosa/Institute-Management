package com.Institute.management.system.InstituteManagementSystem.controller;

import com.Institute.management.system.InstituteManagementSystem.model.Student;
import com.Institute.management.system.InstituteManagementSystem.model.Teacher;
import com.Institute.management.system.InstituteManagementSystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // To get all teachers
    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    // To get one teacher
    @GetMapping(path = "/{id}")
    public Teacher getTeacher(@PathVariable int id) {

        return teacherService.getTeacher(id);
    }

    // Add student to the list
    @PostMapping
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return teacher;
    }

    // Update existing student using his id
    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        teacherService.updateTeacher(id, teacher);
        return teacher;
    }

    // Delete existing student using his id
    @DeleteMapping("/{id}")
    public Teacher deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
        return teacherService.deleteTeacher(id);
    }
}
    
