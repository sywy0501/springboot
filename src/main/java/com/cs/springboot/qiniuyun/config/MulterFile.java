package com.cs.springboot.qiniuyun.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

/**
 * @description:
 * @author: chushi
 * @create: 2020-07-28 11:33
 **/
@Configuration
public class MulterFile {
    /**
     * 文件上传配置
     * @return MultipartConfigElement
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        DataSize dataSize = DataSize.of(1, DataUnit.GIGABYTES);
        factory.setMaxFileSize(dataSize); //KB,MB
        // 设置总上传数据总大小
        factory.setMaxRequestSize(dataSize);
        return factory.createMultipartConfig();
    }
}
