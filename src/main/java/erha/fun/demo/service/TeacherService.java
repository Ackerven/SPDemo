package erha.fun.demo.service;

import erha.fun.demo.Mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/3/22 10:30 AM
 */
@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
}
