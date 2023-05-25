package com.Institute.management.system.InstituteManagementSystem.controller;

import com.Institute.management.system.InstituteManagementSystem.model.Course;
import com.Institute.management.system.InstituteManagementSystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping(path = "/{id}")
    public Optional<Course> getOneCourse(@PathVariable int id){
        return courseService.getOneCourse(id);
    }

    @PostMapping
    public Course registerCourse (@RequestBody Course course){
        return courseService.registerCourse(course);
    }

    @PutMapping (path = "/{id}")
    public Optional<Course> updateCourse (@PathVariable int id, @RequestBody Course course){
        return courseService.updateCourse(id, course);
    }
    @DeleteMapping("/{id}")
    public void dropCourse(@PathVariable int id){
        courseService.dropCourse(id);
    }
}
