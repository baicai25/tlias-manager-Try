package org.example.service.impl;

import org.example.mapper.ClazzStudentMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.JobOption;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private ClazzStudentMapper clazzStudentMapper;

    @Override
    public JobOption getEmpJobData() {
        //调用Mapper接口,获取统计数据
        List<Map<String,Object>> list = empMapper.countEmpJobData();

        //简单来说就是pos里面放的是职位,num放的是职位对应的人数
        //然后list集合里面存放了n个map,员工map集合存放两个key,value,
        // 第一个(key,value)对应(pos,职位名称),第二个(key,value)对应的(num,数字),
        // 两个map存放在一个Map集合中,这些Map集合存放在List集合里面
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        //组装结果并返回
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Integer>> getEmpGenderData() {

        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {

        return clazzStudentMapper.countStudentDegreeData();
    }

    @Override
    public Map<String, Object> getStudentCountData() {
        List<Map<String, Object>> rawData = clazzStudentMapper.countStudentCountData();
        List<String> clazzList = new ArrayList<>();
        List<Number> dataList = new ArrayList<>();
        for (Map<String, Object> item : rawData) {
            clazzList.add((String) item.get("name"));
            dataList.add((Number) item.get("value"));
        }
        Map<String, Object> result = new HashMap<>();
        result.put("clazzList", clazzList);
        result.put("dataList", dataList);
        return result;
    }

//    @Override
//    public JobOption getClassCountData() {
//        //调用Mapper接口,获取统计数据
//        List<Map<String,Object>> lists = clazzStudentMapper.countClassNumData();
//        //简单来说就是pos里面放的是职位,num放的是职位对应的人数
//        //然后list集合里面存放了n个map,员工map集合存放两个key,value,
//        // 第一个(key,value)对应(pos,职位名称),第二个(key,value)对应的(num,数字),
//        // 两个map存放在一个Map集合中,这些Map集合存放在List集合里面
//        List<Object>  clazzList = lists.stream().map(dataMap -> dataMap.get("po")).toList();
//        List<Object>  dataList = lists.stream().map(dataMap -> dataMap.get("nu")).toList();
//        //组装结果并返回
//        return new JobOption( clazzList, dataList);
//    }
}
