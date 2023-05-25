package com.Institute.management.system.InstituteManagementSystem.service;

import com.Institute.management.system.InstituteManagementSystem.model.Course;
import com.Institute.management.system.InstituteManagementSystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Optional<Course> getOneCourse(int id){

        return  courseRepository.findById(id);
    }

    public Course registerCourse(Course course){

        return courseRepository.save(course);
    }

    public void dropCourse ( int id){
        courseRepository.deleteById(id);
    }

    public Optional<Course> updateCourse(int id, Course updatedCourse) {
        Optional<Course> courseFound= getOneCourse(id);
        courseFound.ifPresent((course) -> {
            course.name= updatedCourse.name;
            courseRepository.save(course);
        });
        return courseFound; }
}
