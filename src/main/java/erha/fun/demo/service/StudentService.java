package erha.fun.demo.service;

import erha.fun.demo.Mapper.StudentMapper;
import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Evaluate;
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
    public Student queryStudentById(String id) {
        return studentMapper.queryStudentById(Long.getLong(id));
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

    /**
     * 查询班级的老师
     * @param cid 班级 id
     * @return Teacher
     */
    public List<Teacher> queryTeacherForClass(String cid) {
        return studentMapper.queryTeacherForClass(cid);
    }

    /**
     * 查询班级信息
     * @param cid 班级 cid
     * @return Classes
     */
    public Classes queryClasses(String cid) {
        return studentMapper.queryClass(cid);
    }

    /**
     * 查询学生所在班级的积分
     * @param sid 学生 sid
     * @param cid 班级 cid
     * @return 学生所在班级的积分
     */
    public Integer queryScoreForStudent(String sid, String cid) {
        return studentMapper.queryScoreForStudent(sid, cid);
    }

    /**
     * 查询历史评价
     * @param sid 学生 sid
     * @return 历史评价列表
     */
    public List<Evaluate> queryHistoryEvaluate(String sid, String pageNo, String pageSize) {
        int length = Integer.parseInt(pageSize);
        int index = (Integer.parseInt(pageNo) - 1);
        return studentMapper.queryEvaluate(sid, index, length);
    }

    /**
     * 查询评价的老师
     * @param tid 老师 tid
     * @return Teacher
     */
    public Teacher queryTeacherForEvaluate(String tid) {
        return studentMapper.queryTeacherForEvaluate(tid);
    }
}
