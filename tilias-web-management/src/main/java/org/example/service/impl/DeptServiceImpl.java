package org.example.service.impl;

import org.example.mapper.DeptMapper;
import org.example.pojo.Dept;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service//service层就负责逻辑处理,接收controller的请求,向mapper索要数据
public class DeptServiceImpl implements DeptService {

    @Autowired//读取mapper返还的数据//返还给controller层
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);//其实有返回值,返回的是修改的记录数,单数我们现在不需要就不管了
    }

    @Override
    public void add(Dept dept) {
        //因为传递过来的只有一个name属性,需要自己补全当前时间,id会自增在暂时不管
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //同上补全
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }


}
