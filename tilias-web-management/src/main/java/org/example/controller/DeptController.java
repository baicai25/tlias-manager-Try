package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.example.anno.Log;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController//restController里面封装了一个ResponseBody注解,会将数据库响应回去的对象进行封装转化为JSON数据格式,然后响应给前端
public class DeptController {


    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);日志,但是直接@SLF4J就行了


    @Autowired
    private DeptService deptService;

    //controller层就负责接收请求,响应数据.      用户发送请求,controller向service请求,service向mapper请求,mapper从mysql读取数据返还给service

    @GetMapping//下面的简化形式,会响应get请求,其他请求不响应例如post,delete,put...
    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    public Result list(){
//        System.out.println("查询全部部门数据!");
        log.info("查询全部部门数据!");
        List<Dept> deptList = deptService.findAll();//查询全部数据,调用service层方法发送请求,
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result delete( Integer id){//方法2 @RequestParam("id") Integer deptId),后面默认必须有参数,不然会报错
//        System.out.println("根据ID删除部门"+id );  //deptId);
        log.info("根据ID删除部门 + {}", id);
        deptService.deleteById(id);
        return Result.success();
    }


    @Log
    @PostMapping
    public Result add(@RequestBody  Dept dept){//Requestbody可以将前端传输过来的JSON格式的数据封装成一个对象,
        // 但JSON格式中的传递的过来的最少一个键名需要与属性名字相同
//        System.out.println("新增部门"+dept);
        log.info("新增部门 {}",dept);
        deptService.add(dept);
        return Result.success();
    }

//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("根据Id查询部门: "+deptId);
//        return Result.success();
//    }


    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
//        System.out.println("根据Id查询部门: "+id);
        log.info("根据Id查询部门: {}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){

//        System.out.println("修改部门 :"+dept);
        log.info("修改部门 : {}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
