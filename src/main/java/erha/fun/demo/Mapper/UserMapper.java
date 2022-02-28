package erha.fun.demo.Mapper;

import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/1/22 2:12 AM
 */
@Mapper
public interface UserMapper {
    @Select("select * from student where username = #{username}")
    Student queryStudentByUserName(String username);

    @Select("select * from teacher where username = #{username}")
    Teacher queryTeacherByUserName(String username);

    @Select("select role from Student where username = #{username}")
    Integer getRoleByUserName(String username);
}
