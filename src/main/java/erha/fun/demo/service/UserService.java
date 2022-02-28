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
}
