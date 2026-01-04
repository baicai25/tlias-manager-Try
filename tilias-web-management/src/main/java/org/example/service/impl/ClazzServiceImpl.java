package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;


    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {

        //设置分页参数 每页条数和页数 用了PageHelper SQL语句后面不能有";",因为PageHelper要自己添加limit限制再加;
        //PageHelper只执行第一个分页查询语句
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());

        List<Clazz> clazzList = clazzMapper.page(clazzQueryParam);

        Page<Clazz> p = (Page<Clazz>) clazzList;    //多态的体现?

        return new PageResult<>(p.getTotal(),p.getResult());

    }

    //查询所有班级
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    @Override
    public void save( Clazz clazz) {
        //因为只传入了6个数据而clazz有8个属性,所有这里自行补充两个属性.否则新增的班级在数据库中这两个属性为null
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.save(clazz);
    }

    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }

    @Override
    public Clazz getInfo(Integer id) {

        return clazzMapper.getInfo(id);
    }

    @Override
    public void update(Clazz clazz) {
        //补全基本属性
        clazz.setUpdateTime(LocalDateTime.now());
        //更新班级
        clazzMapper.update(clazz);
    }
}
