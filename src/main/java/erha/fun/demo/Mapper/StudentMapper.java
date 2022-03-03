package erha.fun.demo.Mapper;

import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Evaluate;
import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
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

    @Select("select * from teacher where tid in (select tid from tc_relation where cid = #{cid})")
    List<Teacher> queryTeacherForClass(String cid);

    @Select("select * from classes where cid = #{cid}")
    Classes queryClass(String cid);

    @Select("select SUM(score) from evaluate where sid = #{sid} and cid = #{cid}")
    Integer queryScoreForStudent(String sid, String cid);

    @Select("select * from evaluate where sid = #{sid} limit #{index}, #{pageSize}")
    List<Evaluate> queryEvaluate(String sid, int index, int pageSize);

    @Select("select * from teacher where tid = #{tid}")
    Teacher queryTeacherForEvaluate(String tid);
}
