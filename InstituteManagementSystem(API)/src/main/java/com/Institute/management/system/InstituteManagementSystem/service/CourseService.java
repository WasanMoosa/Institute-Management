package com.Institute.management.system.InstituteManagementSystem.service;

import com.Institute.management.system.InstituteManagementSystem.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    @Autowired
    TeacherService teacherService;

    // show courses of specific teacher
    public ArrayList<String> getCourses(int id) {
        Teacher foundTeacher = teacherService.getTeacher(id);


        return foundTeacher.courses;
    }
}
