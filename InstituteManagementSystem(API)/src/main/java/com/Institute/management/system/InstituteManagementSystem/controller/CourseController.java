package com.Institute.management.system.InstituteManagementSystem.controller;

import com.Institute.management.system.InstituteManagementSystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/{id}")
    public ArrayList<String> coursesOfTeacher(@PathVariable int id) {

        return courseService.getCourses(id);
    }
}
