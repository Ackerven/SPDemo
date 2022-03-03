package erha.fun.demo.service;

import erha.fun.demo.Mapper.TeacherMapper;
import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/3/22 10:30 AM
 */
@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    public Teacher queryTeacherById(String id) {
        return teacherMapper.queryTeacherById(Long.getLong(id));
    }

    public Teacher queryTeacherByUsername(String username) {
        return teacherMapper.queryTeacherByUserName(username.toLowerCase(Locale.ROOT));
    }

    public Teacher queryTeacherByName(String name) {
        return teacherMapper.queryTeacherByName(name.toLowerCase(Locale.ROOT));
    }

    public List<Classes> queryClassOfTeacher(String tid) {
        return teacherMapper.queryClassOfTeacher(tid);
    }

    public List<Student> queryStudentForClass(String cid) {
        return teacherMapper.queryStudentForClass(cid);
    }
}
