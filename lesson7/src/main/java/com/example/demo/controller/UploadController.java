package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: DELL
 * @Date: 2021-01-16 10:05
 * @Description:
 */
@RestController
public class UploadController {

    @Value("${fileRoot}")
    private String fileRoot;

    @PostMapping("upload")
    public String post(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = fileRoot;

        //如果目录不存在，则自动创建
        if (!new File(filePath).exists()) {
            new File(filePath).mkdirs();
        }

        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }


    @PostMapping("uploads")
    public String posts(@RequestParam("file") List<MultipartFile> files){//参数file以list数组接收
        if (files.isEmpty()) {
            return "上传失败，请选择文件";
        }

        //如果目录不存在，则自动创建
        String filePath = "d:\\files\\";
        if (!new File(filePath).exists()) {
            new File(filePath).mkdirs();
        }

        //遍历文件，一个个保存到服务器
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败！";
            }
        }
        return "上传成功";
    }
}
