package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> page(ClazzQueryParam clazzQueryParam);


    List<Clazz> findAll();

    void save(Clazz clazz);

    void delete(Integer id);


    Clazz getInfo(Integer id);

    void update( Clazz clazz);

}
