package erha.fun.demo.Mapper;

import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/2/22 2:12 PM
 */
@Mapper
public interface StudentMapper {
    @Select("select * from student where id = #{id}")
    Student queryStudentById(Long id);

    @Select("select * from student where name = #{name}")
    Student queryStudentByName(String name);

    @Select("select * from student where username = #{username}")
    Student queryStudentByUserName(String username);

    @Select("select * from classes where cid in (select cid from sc_relation where sid = #{sid})")
    List<Classes> queryClassOfStudent(String sid);
}
