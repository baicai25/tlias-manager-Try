package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.example.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    //本地磁盘存储路径
//    @PostMapping("/upload")
//    public Result upload(String name,Integer age,MultipartFile file) throws IOException {
//        log.info("接收参数: {},{],{]",name,age,file);
//
//        //获取原文件名
//        String originalFilename =  file.getOriginalFilename();
//
//        //新的随机文件名
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newfileName = UUID.randomUUID().toString()+extension;
//
//        file.transferTo(new File("D:/java学习/临时/"+newfileName));
//
//        return  Result.success();
//
//    }

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传: {}", file.getOriginalFilename());
        //将文件交给OSS
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传到OSS的url地址 +{}",url);
        return Result.success(url);
    }


}
