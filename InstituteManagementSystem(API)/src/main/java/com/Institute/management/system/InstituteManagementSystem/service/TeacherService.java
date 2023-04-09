package com.Institute.management.system.InstituteManagementSystem.service;

import com.Institute.management.system.InstituteManagementSystem.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TeacherService {
    private List<Teacher> allTeachers = new CopyOnWriteArrayList<>();

    // Create default teacher
    public Teacher getDefultTeacher() {
        Teacher defultTeacher = new Teacher();
        defultTeacher.name = "Unknown";
        defultTeacher.id = 0000;
        defultTeacher.courses.add("Unknown");
        defultTeacher.email = "Unknown";
        defultTeacher.salary = 0000;
        return defultTeacher;

    }

    public List<Teacher> getTeachers() {
        return allTeachers;
    }

    // Get specific teacher
    public Teacher getTeacher(int id) {
        Optional<Teacher> foundTeacher = allTeachers.stream().filter(
                teacher -> {
                    return teacher.id == id;
                }).findFirst();
        if (foundTeacher.isPresent()){
            return foundTeacher.get();
        }
        else {
            return getDefultTeacher();
        }
    }

    // Add teacher
    int id = 1;

    public Teacher addTeacher(Teacher teacher) {
        teacher.id = this.id++;
        allTeachers.add(teacher);

        return teacher;
    }

    // update student
    public Teacher updateTeacher(int id, Teacher updatedTeacher) {
        Teacher teacher = getTeacher(id);
        teacher.name = updatedTeacher.name;
        teacher.email = updatedTeacher.email;

        return updatedTeacher;
    }

    // Delete teacher
    public Teacher deleteTeacher(int id) {
        Teacher teacher = getTeacher(id);
        allTeachers.remove(teacher);

        return teacher;
    }

}


