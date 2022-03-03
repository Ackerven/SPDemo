package erha.fun.demo.controller;

import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Evaluate;
import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
import erha.fun.demo.service.TeacherService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

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
        Teacher t = teacherService.queryTeacherByUsername(value);
        t.setPassword("");
        t.setClassList(this.classList(value));
        return t;
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

    @GetMapping("/teacher/{value}/evaluate")
    public List<Map<String, Object>> historyEvaluate(@PathVariable("value") String value,
                                                     @RequestParam Map<String, String> param) {
        List<Map<String, Object>> result = new ArrayList<>();
        Teacher teacher = teacherService.queryTeacherByUsername(value);
        List<Evaluate> list = teacherService.queryHistoryEvaluate(teacher.getTid(), param.get("pageNo"), param.get("pageSize"));
        for(Evaluate e: list) {
            Map<String, Object> map = new HashMap<>();
            Student s = teacherService.queryStudentForEvaluate(e.getSid());
            s.setPassword("");
            Classes c = teacherService.queryClass(e.getCid());
            map.put("student", s);
            map.put("class", c);
            map.put("evaluate", e);
            result.add(map);
        }
        return result;
    }

    @PostMapping("/teacher/{value}/evaluate")
    public Map<String ,Object> submitEvaluate(@PathVariable("value") String value,
                                              @RequestParam Map<String, String> params) {
        Map<String, Object> map = new HashMap<>();
        JSONArray evaluate = JSONArray.fromObject(params.get("evaluate"));
        List<String> sids = Arrays.asList(((String) params.get("sid")).split(","));
        List<Evaluate> evaluates = new ArrayList<>();
        for(int i = 0; i < evaluate.size(); i++) {
            JSONObject j = evaluate.getJSONObject(i);
            String content = j.getString("content");
            Integer score = j.getInt("score");
            Evaluate e = new Evaluate(content, score);
            e.setCid(params.get("cid"));
            e.setTid(params.get("tid"));
            evaluates.add(e);
        }
        for(Evaluate e: evaluates) {
            for(String sid: sids) {
                e.setSid(sid);
                e.setEid();
                teacherService.addEvaluateToDB(e);
            }
        }
        map.put("evaluate", evaluates);
        return map;
    }

    private String trims(String str) {
        StringBuffer s = new StringBuffer();
        byte[] b = str.getBytes(StandardCharsets.UTF_8);
        for(byte ch: b) {
            if(ch == '/' || ch == '"') {
                continue;
            }
            s.append(ch);
        }
        return s.toString();
    }

}
