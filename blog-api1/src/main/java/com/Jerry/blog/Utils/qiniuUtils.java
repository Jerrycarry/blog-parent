package com.Jerry.blog.Utils;

import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



/**
 * @author CaiBowen
 * @date 2023/7/21 15:49
 */


@Component
@PropertySource("classpath:application.properties")
public class qiniuUtils {

    public static  final String url = "http://s2kghs3e4.hn-bkt.clouddn.com/";

    @Value("${shop.qiniu.accessKey}")
    private  String accessKey;
    @Value("${shop.qiniu.accessSecretKey}")
    private  String accessSecretKey;
    @Value("${shop.qiniu.bucketName}")
    private String bucketName;   // 存储空间
    @Value("${shop.qiniu.path} ")
    private String path;       // 域名

    public  boolean upload(MultipartFile file,String fileName){

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String bucket = "123456c";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, accessSecretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
