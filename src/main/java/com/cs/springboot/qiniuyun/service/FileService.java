package com.cs.springboot.qiniuyun.service;

import com.cs.springboot.qiniuyun.VO.FileVO;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description:
 * @author: chushi
 * @create: 2020-07-28 11:38
 **/
public interface FileService {

    /**
     * 单文件上传
     *
     * @auther: yore
     * @param file 文件对象
     * @return FileVO
     * @date: 2019/12/7 7:16 PM
     */
    FileVO imageload(MultipartFile file);

    /**
     * 多文件上传
     *
     * @auther: yore
     * @param multiValueMap 文件Map对象
     * @return List<FileVO>
     * @date: 2019/12/7 7:19 PM
     */
    List<FileVO> imagesload(MultiValueMap<String, MultipartFile> multiValueMap);



    /**
     * 保存图片实体对象信息
     *
     * @auther: yore
     * @param fileImageEntity
     * @date: 2019/12/7 1:56 PM
     *//*
    void saveFileInfo(FileImageEntity fileImageEntity);*/

}