package com.cs.springboot.qiniuyun.utils;

import com.alibaba.fastjson.JSON;
import com.cs.springboot.qiniuyun.constant.QiNiuConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @description:
 * @author: chushi
 * @create: 2020-07-28 11:26
 **/
public class QiNiuUploadUtils {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    //密钥配置
    private static Auth auth = Auth.create(QiNiuConstant.ACCESS_KEY, QiNiuConstant.SECRET_KEY);
    // 配置对象，设置为华南存储区域
    private static Configuration cfg = new Configuration(Zone.huanan());
    private static UploadManager uploadManager = new UploadManager(cfg);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken(){
        return auth.uploadToken(QiNiuConstant.BUCKET);
    }

    /**
     * 将文件上传到 七牛云
     *
     * @auther: yore
     * @param file Multipart对象
     * @param nameSpace 上传的文件的命名空间
     * @param fileName 新的文件名（为了不重复、去除掉文件中的中文）
     * @return String 上传的文件路径
     * @date: 2019/12/8 4:23 PM
     */
    public static String updateFile(MultipartFile file, String nameSpace, String fileName) throws IOException {
        InputStream inputStream=file.getInputStream();
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[600]; //buff用于存放循环读取的临时数据
        int rc = 0;
        while ((rc = inputStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }

        // 给图片命名空间加上时间
        nameSpace = nameSpace.endsWith("/")? nameSpace: nameSpace+"/";
        nameSpace = nameSpace + sdf.format(new Date()) + "/";

        byte[] uploadBytes  = swapStream.toByteArray();
        try {
            Response response = uploadManager.put(uploadBytes, nameSpace + fileName, getUpToken());
            //解析上传成功的结果
            DefaultPutRet putRet;
            putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return QiNiuConstant.DOMAIN + "/"+ putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
            }
        }
        return null;
    }

    /**
     * 获取一个随机的新文件名
     *
     * @auther: yore
     * @param fileName 旧文件名
     * @return  String 新文件名
     * @date: 2019/12/8 4:27 PM
     */
    public static String getNewFileName(String fileName){
        String newName = UUID.randomUUID().toString();
        // uuid + 文件后缀
        newName = newName + fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return newName;
    }
}
