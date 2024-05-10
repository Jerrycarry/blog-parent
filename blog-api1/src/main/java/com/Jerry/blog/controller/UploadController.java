package com.Jerry.blog.controller;


import com.Jerry.blog.Utils.qiniuUtils;
import com.Jerry.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.UUID;

/**
 * @author CaiBowen
 * @date 2023/7/21 15:44
 */

@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private qiniuUtils qiniuUtils;

    @PostMapping
    public Result upload(@RequestParam("img")MultipartFile file){
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        // 唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        // 上传文件到七牛云 云服务器
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload){
            return Result.success(qiniuUtils.url+fileName);
        }
        return Result.fail(20001,"上传失败");
    }
}
