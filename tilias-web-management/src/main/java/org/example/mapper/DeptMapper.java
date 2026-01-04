package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Dept;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper//mapper层ordao层就负责数据访问,包括增删改查,从数据库查找然后返还
public interface DeptMapper {

    @Select("select id,name,create_time,update_time from dept order by update_time desc")//向数据库查找数据,然后存放在IOC容器里面
    List<Dept> findAll();


    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);



}


