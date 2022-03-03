package erha.fun.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Evaluate;
import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
import erha.fun.demo.service.StudentService;
import erha.fun.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
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
     * @param value
     * @return
     */
    @GetMapping("/student/{value}")
    public Student space(@PathVariable("value") String value) {
        Student student = studentService.queryStudentByUserName(value);
        student.setClassList(this.classList(value));
        student.setPassword("");
        return student;
    }

    @GetMapping("/student/{username}/class")
    public List<Classes> classList(@PathVariable("username") String username) {
        Student student = studentService.queryStudentByUserName(username);
        List<Classes> list = studentService.queryClassOfStudent(student.getSid());
        for(Classes c: list) {
            List<Teacher> tlist = studentService.queryTeacherForClass(c.getCid());
            for(Teacher t: tlist) {
                t.setPassword("");
            }
            c.setTeacher(tlist);
        }
        return list;
    }

    @GetMapping("/student/{username}/class/{cid}")
    public Map<String, Object> classes(@PathVariable("username") String username,
                           @PathVariable("cid") String cid) {
        Map<String, Object> map = new HashMap<>();
        Classes classes = studentService.queryClasses(cid);
        List<Teacher> list = studentService.queryTeacherForClass(classes.getCid());
        for(Teacher t: list) {
            t.setPassword("");
        }
        classes.setTeacher(list);
        Student student = studentService.queryStudentByUserName(username);
        map.put("score", studentService.queryScoreForStudent(student.getSid(), classes.getCid()));
        map.put("class", classes);
        return map;
    }

    @GetMapping("/student/{username}/evaluate")
    public List<Map<String, Object>> historyEvaluate(@PathVariable("username") String username,
                                                     @RequestParam Map<String, String> param) {
        List<Map<String, Object>> result = new ArrayList<>();
        Student student = studentService.queryStudentByUserName(username);
        List<Evaluate> list = studentService.queryHistoryEvaluate(student.getSid(),param.get("pageNo"), param.get("pageSize"));
        for(Evaluate e: list) {
            Map<String, Object> map = new HashMap<>();
            Teacher t = studentService.queryTeacherForEvaluate(e.getTid());
            t.setPassword("");
            Classes c = studentService.queryClasses(e.getCid());
            map.put("teacher", t);
            map.put("evaluate", e);
            map.put("class", c);
            result.add(map);
        }
        return result;
    }
}
