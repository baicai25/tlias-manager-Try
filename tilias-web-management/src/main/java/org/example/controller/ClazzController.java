package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController//内含有ResponseBody注解,会将数据库响应回去的对象进行封装转化为JSON数据格式,然后响应给前端
@RequestMapping("/clazzs")
public class ClazzController {



    //RequestParam = URL 的 ? 参数//students?name=Tom


    @Autowired
    private ClazzService clazzService;



    @PostMapping    //RequestBody = JSON 请求体 //{ "name": "Lucy" }
    public Result save(@RequestBody Clazz clazz) {
        log.info("增加班级+{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }


    @DeleteMapping("/{id}")    //PathVariable = 路径里的变量 //students/3 → id=3
    public Result delete (@PathVariable Integer id) {
        log.info("删除班级: {}",id);
        clazzService.delete(id);
        return Result.success();
    }


    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级信息");
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询班级(查询回显): {}",id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }


    //这个接口的目的并不是为了班级管理的正常显示,而是为了员工管理的编辑页面里班级的显示选择
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有班级列表-----------------------------------------");
        List<Clazz> clazzlist = clazzService.findAll();
        return Result.success(clazzlist);
    }

    @GetMapping  //为什么这个没有RequestParam也能正常读取到url /clazzs/?...后的数据
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("分页查询班级管理:{}--------------------------------------", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

}
