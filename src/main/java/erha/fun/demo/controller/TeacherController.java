package erha.fun.demo.controller;

import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
import erha.fun.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/3/22 10:30 AM
 */
@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacher/{value}")
    public Teacher space(@PathVariable("value") String value) {
        return teacherService.queryTeacherByUsername(value);
    }

    @GetMapping("/teacher/{value}/class")
    public List<Classes> classList(@PathVariable("value") String value) {
        Teacher teacher = teacherService.queryTeacherByUsername(value);
        List<Classes> list = teacherService.queryClassOfTeacher(teacher.getTid());
        for(Classes c: list) {
            List<Student> slist = teacherService.queryStudentForClass(c.getCid());
            for(Student s: slist) {
                s.setPassword("");
            }
            c.setStudents(slist);
        }
        return list;
    }

}
