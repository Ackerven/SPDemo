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
    private Teacher teacher;

    public Classes() {
    }

    public Classes(String grade, String name) {
        this.grade = grade;
        this.name = name;
        this.students = new ArrayList<>();
        this.setCid();
    }

    public Classes(String grade, String name, Teacher teacher) {
        this.grade = grade;
        this.name = name;
        this.students = new ArrayList<>();
        this.teacher = teacher;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    private void setCid() {
        this.cid = Tools.randomString(20);
    }

    @Override
    public String toString() {
        return "Classes{" + "id=" + id + ", grade='" + grade + '\'' + ", name='" + name + '\'' + ", cid='" + cid + '\'' + ", students=" + students + ", teacher=" + teacher + '}';
    }
}
