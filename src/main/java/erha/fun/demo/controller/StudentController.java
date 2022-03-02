package erha.fun.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Student;
import erha.fun.demo.service.StudentService;
import erha.fun.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/2/22 2:11 PM
 */
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 获取学生基本信息
     * @param username
     * @return
     */
    @GetMapping("/student/{username}")
    public Student space(@PathVariable("username") String username) {
        Student student = studentService.queryStudentByUserName(username);
        student.setClassList(studentService.queryClassOfStudent(student.getSid()));
        return student;
    }
}
