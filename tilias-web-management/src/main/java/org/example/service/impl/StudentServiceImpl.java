package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzStudentMapper;
import org.example.pojo.Emp;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ClazzStudentMapper clazzStudentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> students = clazzStudentMapper.page(studentQueryParam);
        Page<Student> page = (Page<Student>) students;
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void save(Student student) {
        //补全基本属性,因为值不能为空
        student.setViolationCount(0);
        student.setViolationScore(0);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        //添加学员
        clazzStudentMapper.save(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        clazzStudentMapper.delete(ids);
    }

    @Override
    public Student getInfo(Integer id) {
        return clazzStudentMapper.getInfo(id);
    }

    @Override
    public void update(Student student) {
        clazzStudentMapper.update(student);
    }

    @Override
    public void violation(Integer id, Integer score) {
        clazzStudentMapper.violation(id,score);
    }

}



////设置分页参数;        用了PageHelper SQL语句后面不能有;
////PageHelper只执行第一个分页查询语句
//        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
//
//List<Emp> empList = empMapper.list(empQueryParam);
////        List<Emp> empList = empMapper.list(); 第二句select操作不能执行,
//
//Page<Emp> p = (Page<Emp>) empList;//empList数据类型就是Page,多态的体现
//        return new PageResult<>(p.getTotal(), p.getResult());
////