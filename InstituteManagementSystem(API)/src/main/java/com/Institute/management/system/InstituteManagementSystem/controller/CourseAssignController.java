package com.Institute.management.system.InstituteManagementSystem.controller;

import com.Institute.management.system.InstituteManagementSystem.model.Course;
import com.Institute.management.system.InstituteManagementSystem.model.CourseAssigner;
import com.Institute.management.system.InstituteManagementSystem.model.Teacher;
import com.Institute.management.system.InstituteManagementSystem.service.CourseService;
import com.Institute.management.system.InstituteManagementSystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/assigner")
public class CourseAssignController {

    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;
    @PostMapping

    public CourseAssigner assignMentorToCourse(@RequestBody CourseAssigner courseAssigner){

        Optional<Course> course= courseService.getOneCourse(courseAssigner.course_id);
        Optional<Teacher> teacher= teacherService.getTeacher(courseAssigner.teacher_id);

        course.ifPresent(currCourse-> {

            teacher.ifPresent(currTeacher -> {

                currCourse.mentor=currTeacher;
                courseService.registerCourse(currCourse);

            });
        });
   return courseAssigner; }
}
