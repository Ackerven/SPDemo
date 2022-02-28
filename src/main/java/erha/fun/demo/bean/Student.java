package erha.fun.demo.bean;

import erha.fun.demo.utils.Tools;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 2/28/22 9:08 PM
 */


public class Student extends User {
    private String name;
    private String sid;
    private List<Classes> classList;

    public Student() {
    }

    public Student(String username, String password, String name) {
        super(username, password, User.STUDENT);
        this.name = name;
        this.classList = new ArrayList<>();
        setSid();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public List<Classes> getClassList() {
        return classList;
    }

    public void setClassList(List<Classes> classList) {
        this.classList = classList;
    }

    private void setSid() {
        this.sid = Tools.randomString(10,
                "123456789abcdefghijklmnopqrstuvwxyz");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sid='" + sid + '\'' +
                ", classList=" + classList +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", id=" + id +
                '}';
    }
}
