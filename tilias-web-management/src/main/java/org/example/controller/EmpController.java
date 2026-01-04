package org.example.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private  EmpService empService;


    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询: {}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);

    }

    @PostMapping
    public Result save(@RequestBody Emp emp){//Requestbody可以将前端传输过来的JSON格式的数据封装成一个对象,
        // 但JSON格式中的传递的过来的最少一个键名需要与属性名字相同
        log.info("新增员工; {}",emp);
        empService.save(emp);
        return Result.success();
    }

//    @DeleteMapping
//    public Result delete(Integer[] ids){//用数组,ids需要与前端传输的参数名保持一致
//        empService.delete(ids);
//        log.info("成功删除成员: +{}", Arrays.toString(ids));
//        return Result.success();
//    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("成功删除成员: +{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getinfo(@PathVariable Integer id){
        log.info("根据id查询回显: {}",id);
        Emp emp = empService.getinfo(id);
        return Result.success(emp);
    }


    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工 : {}" ,emp);
        empService.update(emp);
        return Result.success();

    }

    //文档2.6,接口2.2查询所有员工接口
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有员工接口------------00000000000000000000000000000");
        List<Emp> emp = empService.list();
        return Result.success(emp);
    }


//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        log.info("分页查询: {},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//
//    }

}
