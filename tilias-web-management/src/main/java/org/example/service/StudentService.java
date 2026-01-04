package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    //List<Student> findAll();

    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void save(Student student);

    void delete(List<Integer> ids);

    Student getInfo(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);
}
