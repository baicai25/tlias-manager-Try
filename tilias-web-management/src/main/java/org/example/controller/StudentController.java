package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @GetMapping
    private Result Page(StudentQueryParam studentQueryParam){
        log.info("分页查询学生列表数据-:----------------");
        PageResult<Student> pageresult = studentService.page(studentQueryParam);
        return Result.success(pageresult);
    }

    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加学员: {}",student);
        studentService.save(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids ){
        //PathVariable = 路径里的变量 //students/3 → id=3
        //RequestParam = URL 的 ? 参数//students?name=Tom
        //RequestBody = JSON 请求体 //{ "name": "Lucy" }

        log.info("根据ids批量删除员工信息");
        studentService.delete(ids);

        return Result.success();
    }


    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询回显");
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改员工基本信息");
        studentService.update(student);
        return Result.success();
    }

    //违纪
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("违纪处理999999999999999999999");
        studentService.violation(id,score);
        return Result.success();
    }
}
