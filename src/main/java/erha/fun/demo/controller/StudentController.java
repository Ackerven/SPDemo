package erha.fun.demo.controller;

import erha.fun.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/2/22 2:11 PM
 */
@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
}
