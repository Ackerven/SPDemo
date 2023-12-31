package erha.fun.demo.Mapper;

import erha.fun.demo.bean.Classes;
import erha.fun.demo.bean.Evaluate;
import erha.fun.demo.bean.Student;
import erha.fun.demo.bean.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select * from classes where cid in (select cid from tc_relation where tid = #{tid})")
    public List<Classes> queryClassOfTeacher(String tid);

    @Select("select * from student where sid in (select sid from sc_relation where cid = #{cid})")
    public List<Student> queryStudentForClass(String cid);

    @Select("select * from classes where cid = #{cid}")
    Classes queryClass(String cid);

    @Select("select * from evaluate where tid = #{tid} limit #{index}, #{pageSize}")
    List<Evaluate> queryEvaluate(String tid, int index, int pageSize);

    @Select("select * from student where sid = #{sid}")
    Student queryStudentForEvaluate(String sid);

    @Insert("insert into evaluate(eid, content, score, tid, sid, cid) values(#{eid}, #{content}, #{score}, #{tid}, #{sid}, #{cid})")
    void putEvaluate(String eid, String content, Integer score, String tid, String sid, String cid);
}
