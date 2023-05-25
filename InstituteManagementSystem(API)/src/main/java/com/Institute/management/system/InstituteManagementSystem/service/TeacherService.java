package com.Institute.management.system.InstituteManagementSystem.service;

import com.Institute.management.system.InstituteManagementSystem.model.Teacher;
import com.Institute.management.system.InstituteManagementSystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    // Get specific teacher
    public Optional<Teacher> getTeacher(int id) {
        return teacherRepository.findById(id);
    }

    public Teacher addTeacher(Teacher teacher) {

        return teacherRepository.save(teacher);
    }

    // update teacher
    public Optional<Teacher> updateTeacher(int id, Teacher updatedTeacher) {
        Optional<Teacher>  teacher = getTeacher(id);
        teacher.ifPresent((currTeacher)->{
            currTeacher.name = updatedTeacher.name;
            currTeacher.email = updatedTeacher.email;
            teacherRepository.save(currTeacher);
        });

        return teacher;
    }

    // Delete teacher
    public Optional<Teacher> deleteTeacher(int id) {
        Optional<Teacher> teacher = getTeacher(id);
        teacherRepository.deleteById(id);

        return teacher;
    }

}


