package erha.fun.demo.controller;

import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
import erha.fun.demo.bean.User;
import erha.fun.demo.service.UserService;
import erha.fun.demo.utils.TokenUtils;
import erha.fun.demo.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/1/22 1:02 AM
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 登陆接口
     * @param map UserName Password
     * @param response
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> map, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        User user = null;
        Integer role = userService.getRoleByUserName(map.get("username"));
        if (role.equals(User.ADMIN)) {
            user = new User();
        } else if (role.equals(User.STUDENT)) {
            user = userService.queryStudentByUserName(map.get("username"));
        } else if (role.equals(User.TEACHER)) {
            user = userService.queryTeacherByUserName(map.get("username"));
        }

        if (user != null && user.getPassword().equals(Tools.encryptToMD5(map.get("password")))) {
            //Success
            String token = TokenUtils.sign(user.getUsername(), user.getRole());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
            cookie = new Cookie("username", user.getUsername());
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
            cookie = new Cookie("role", user.getRole().toString());
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
            result.put("code", 200);
            result.put("msg", "success");
            result.put("token", token);
            if (user.getRole().equals(User.ADMIN)) {
                User u;
            } else if (user.getRole().equals(User.STUDENT)) {
                userService.addLoginRecord(((Student) user).getSid());
            } else if (user.getRole().equals(User.TEACHER)) {
                userService.addLoginRecord(((Teacher) user).getTid());
            }
        } else {
            result.put("code", 403);
            result.put("msg", "用户名和密码错误");
        }
        return result;
    }
}
