package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.LoginInfo;
import org.example.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    //PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end);

    //分页查询接口
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    //新增员工接口
    void save(Emp emp);

    //根据id删除员工基本信息
    void delete(List<Integer> ids);

    //很具id查询回显
    Emp getinfo(Integer id);

    //根据id修改员工基本信息
    void update(Emp emp);

    List<Emp> list();

    LoginInfo login(Emp emp);
}
