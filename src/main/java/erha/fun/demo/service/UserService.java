package erha.fun.demo.service;

import erha.fun.demo.Mapper.UserMapper;
import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/1/22 2:16 AM
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 通过用户名查询学生
     * @param username
     * @return
     */
    public Student queryStudentByUserName(String username) {
        return userMapper.queryStudentByUserName(username);
    }

    /**
     * 通过用户名查询老师
     * @param username
     * @return
     */
    public Teacher queryTeacherByUserName(String username) {
        return userMapper.queryTeacherByUserName(username);
    }

    /**
     * 通过用户名查询用户角色
     * @param username
     * @return
     */
    public Integer getRoleByUserName(String username) {
        return userMapper.getRoleByUserName(username);
    }

    /**
     * 记录登陆记录
     * @param uid
     */
    public void addLoginRecord(String uid) {
        userMapper.loginRecord(uid);
    }

    /**
     * 学生注册
     * @param student
     */
    public void addStudent(Student student) {
//        userMapper.insertStudent(student.getSid(),
//                student.getUsername(),
//                student.getPassword(),
//                student.getRole(),
//                student.getName());
        userMapper.insertStudent(student);
    }

    /**
     * 教师注册
     * @param teacher
     */
    public void addTeacher(Teacher teacher) {
//        userMapper.insertTeacher(teacher.getTid(),
//                teacher.getUsername(),
//                teacher.getPassword(),
//                teacher.getRole(),
//                teacher.getName());
        userMapper.insertTeacher(teacher);
    }
}
