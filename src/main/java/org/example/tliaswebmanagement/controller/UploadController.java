package org.example.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    //本地存储方式
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
//
//        log.info("文件上传：{},{},{}", username, age, image);
//
//        // 获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//
//        // 构造唯一的文件名（不能重复） - uuid（通用唯一标识码）
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extname;
//        log.info("新的文件名:{}", newFileName);
//
//        // 将文件存储在服务器的磁盘目录中
//        image.transferTo(new File(uploadPath + newFileName));
//
//        // 返回文件访问URL，前端可以直接通过这个URL访问图片
//        String fileUrl = "http://localhost:8080/images/" + newFileName;
//        log.info("文件访问URL:{}", fileUrl);
//
//        return Result.success(fileUrl);
//    }


    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名：{}",image.getOriginalFilename());
        //调用阿里云OSS工具进行工具类文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url:{}",url);
        return Result.success(url);
    }

}
