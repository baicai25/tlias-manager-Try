package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClazzService {

    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    List<Clazz> findAll();

    void save( Clazz clazz);

    void delete(Integer id);

    Clazz getInfo(Integer id);


    void update(Clazz clazz);

}
