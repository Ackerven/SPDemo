package erha.fun.demo.service;

import erha.fun.demo.Mapper.StudentMapper;
import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/2/22 2:11 PM
 */
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    /**
     * 获取学生基本信息
     * @param id 学生 ID
     * @return Student
     */
    public Student queryStudentById(Long id) {
        return studentMapper.queryStudentById(id);
    }

    /**
     * 获取学生基本信息
     * @param name 学生名字
     * @return Student
     */
    public Student queryStudentByName(String name) {
        return studentMapper.queryStudentByName(name);
    }

    /**
     * 获取学生基本信息
     * @param username 学生用户名
     * @return Student
     */
    public Student queryStudentByUserName(String username) {
        return studentMapper.queryStudentByUserName(username);
    }

    /**
     * 获取学生班级信息
     * @param sid 学生 sid
     * @return 班级列表
     */
    public List<Classes> queryClassOfStudent(String sid) {
        return studentMapper.queryClassOfStudent(sid);
    }

    public Teacher queryTeacherForClass(String cid) {
        return studentMapper.queryTeacherForClass(cid);
    }


}
