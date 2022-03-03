package erha.fun.demo.Mapper;

import erha.fun.demo.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/3/22 10:30 AM
 */
@Mapper
public interface TeacherMapper {
    @Select("select * from teacher where id = #{id}")
    public Teacher queryTeacherById(Long id);

    @Select("select * from teacher where username = #{username}")
    public Teacher queryTeacherByUserName(String username);

    @Select("select * from teacher where name = #{name}}")
    public Teacher queryTeacherByName(String name);
}
