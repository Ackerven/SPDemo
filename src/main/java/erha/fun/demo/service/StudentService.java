package erha.fun.demo.service;

import erha.fun.demo.Mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/2/22 2:11 PM
 */
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
}
