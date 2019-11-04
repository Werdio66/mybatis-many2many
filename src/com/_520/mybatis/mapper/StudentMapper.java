package com._520.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    void save(Student student);
    void saveTeacherIdStudentId(
            @Param("studentId") Long studentId,
            @Param("teacherId") Long teacherId
    );

    Student get(Long id);

    void delete(Long id);
    void deleteByRealtionTeacher(Long id);
}
