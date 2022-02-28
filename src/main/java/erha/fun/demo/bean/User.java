package erha.fun.demo.bean;

import erha.fun.demo.utils.Tools;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 2/28/22 9:00 PM
 */


@EqualsAndHashCode
@ToString
public class User {
    public static final Integer ADMIN = 1;
    public static final Integer STUDENT = 2;
    public static final Integer TEACHER = 3;
    protected String username;
    protected String password;
    protected Integer role;
    protected Long id;

    public User() {
    }

    public User(String username, String password, Integer role) {
        this.username = username;
        this.password = Tools.encryptToMD5(password);
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public String getRoleName() {
        switch (this.role) {
            case 1:
                return "Admin";
            case 2:
                return "Student";
            case 3:
                return "Teacher";
        }
        return null;
    }
}
