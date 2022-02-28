package erha.fun.demo.bean;

import erha.fun.demo.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 2/28/22 9:12 PM
 */


public class Teacher extends User {
    private String name;
    private String tid;
    private List<Classes> classList;

    public Teacher() {
    }

    public Teacher(String username, String password, String name) {
        super(username, password, User.TEACHER);
        this.name = name;
        this.classList = new ArrayList<>();
        this.setTid();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getTid() {
        return tid;
    }

    public List<Classes> getClassList() {
        return classList;
    }

    public void setClassList(List<Classes> classList) {
        this.classList = classList;
    }

    private void setTid() {
        this.tid = Tools.randomString(10,
                "123456789abcdefghijklmnopqrstuvwxyz");
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", tid='" + tid + '\'' +
                ", classList=" + classList +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", id=" + id +
                '}';
    }
}
