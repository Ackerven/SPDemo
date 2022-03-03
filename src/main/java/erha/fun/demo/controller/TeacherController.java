package erha.fun.demo.controller;

import erha.fun.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
}
