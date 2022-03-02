package erha.fun.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
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
import java.util.List;
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
        student.setClassList(this.classList(username));
        student.setPassword("");
        return student;
    }

    @GetMapping("/student/{username}/class")
    public List<Classes> classList(@PathVariable("username") String username) {
        Student student = studentService.queryStudentByUserName(username);
        List<Classes> list = studentService.queryClassOfStudent(student.getSid());
        for(Classes c: list) {
            Teacher t = studentService.queryTeacherForClass(c.getCid());
            t.setPassword("");
            c.setTeacher(t);
        }
        return list;
    }

    @GetMapping("/student/{username}/class/{cid}")
    public Map<String, Object> classes(@PathVariable("username") String username,
                           @PathVariable("cid") String cid) {
        Map<String, Object> map = new HashMap<>();
        Classes classes = studentService.queryClasses(cid);
        Teacher t = studentService.queryTeacherForClass(classes.getCid());
        t.setPassword("");
        classes.setTeacher(t);
        Student student = studentService.queryStudentByUserName(username);
        map.put("score", studentService.queryScoreForStudent(student.getSid(), classes.getCid()));
        map.put("class", classes);
        return map;
    }
}
