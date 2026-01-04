package org.example.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzStudentMapper {

    List<Student> page(StudentQueryParam studentQueryParam);

    void save(Student student);

    void delete(List<Integer> ids);

    Student getInfo(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);


    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();


    //List<Map<String, Object>> countClassNumData();

    List<Map<String, Object>> countStudentCountData();


//PageResult<Student> page();
}
