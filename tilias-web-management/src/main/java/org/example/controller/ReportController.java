package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.JobOption;
import org.example.pojo.Result;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {


    @Autowired
    private ReportService reportService;

    @GetMapping("empJobData")
    public Result GetEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }


    @GetMapping("/empGenderData")
    public Result GetEmpGenderData() {
        log.info("统计员工性别人数");
        List<Map<String, Integer>> genderList =  reportService.getEmpGenderData() ;
        return Result.success(genderList);
    }

    @GetMapping("/studentDegreeData")
    public Result GetStudentDegreeData() {
        log.info("统计学员学历信息");
        List<Map<String,Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }

    @GetMapping("/studentCountData")
    public Result GetStudentCountData() {
        log.info("班级人数信息统计");
        Map<String, Object> countData = reportService.getStudentCountData();
        return Result.success(countData);
//        JobOption joboption = reportService.getClassCountData();
//        return Result.success(joboption);
    }
}
