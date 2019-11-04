package com._520.mybatis;

import com._520.mybatis.mapper.Student;
import com._520.mybatis.mapper.StudentMapper;
import com._520.mybatis.mapper.Teacher;
import com._520.mybatis.mapper.TeacherMapper;
import com._520.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class App {
    @Test
    public void save(){
        //
        Teacher teacher1 = new Teacher();
        teacher1.setName("老师1");
        Teacher teacher2 = new Teacher();
        teacher2.setName("老师2");
        //
        Student student1 = new Student();
        student1.setName("小法");
        student1.getTeachers().add(teacher1);
        student1.getTeachers().add(teacher2);
        Student student2 = new Student();
        student2.setName("露露");
        student2.getTeachers().add(teacher1);
        student2.getTeachers().add(teacher2);

        SqlSession session = MybatisUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        //保存
        studentMapper.save(student1);
        studentMapper.save(student2);
        teacherMapper.save(teacher1);
        teacherMapper.save(teacher2);
        //维护关系
        studentMapper.saveTeacherIdStudentId(student1.getId(),teacher1.getId());
        studentMapper.saveTeacherIdStudentId(student1.getId(),teacher2.getId());
        studentMapper.saveTeacherIdStudentId(student2.getId(),teacher1.getId());
        studentMapper.saveTeacherIdStudentId(student2.getId(),teacher2.getId());
        session.commit();
    }
    @Test
    public void get(){
        SqlSession session = MybatisUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student student = studentMapper.get(1L);
        System.out.println(student);
        System.out.println(student.getTeachers());
    }
    @Test
    public void delete(){
        SqlSession session = MybatisUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        studentMapper.deleteByRealtionTeacher(1L);
        studentMapper.delete(1L);
        session.commit();
    }
}
