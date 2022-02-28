package erha.fun.demo.bean;

import erha.fun.demo.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 2/28/22 9:15 PM
 */

public class Classes {
    private Long id;
    private String grade;
    private String name;
    private String cid;
    private List<Student> students;
    private List<Teacher> teachers;

    public Classes() {
    }

    public Classes(String grade, String name) {
        this.grade = grade;
        this.name = name;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.setCid();
    }

    public Long getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    private void setCid() {
        this.cid = Tools.randomString(20);
    }

    @Override
    public String toString() {
        return "Classes{" + "id=" + id + ", grade='" + grade + '\'' + ", name='" + name + '\'' + ", cid='" + cid + '\'' + ", students=" + students + ", teachers=" + teachers + '}';
    }
}
