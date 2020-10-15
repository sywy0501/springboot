package com.cs.springboot.qiniuyun.service.impl;

import cn.hutool.log.LogFactory;
import com.cs.springboot.qiniuyun.VO.FileVO;
import com.cs.springboot.qiniuyun.service.FileService;
import com.cs.springboot.qiniuyun.utils.MsgEnum;
import com.cs.springboot.qiniuyun.utils.QiNiuUploadUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: chushi
 * @create: 2020-07-28 11:37
 **/
@Service
public class FileServiceImpl implements FileService {

    private String imageNameSpace = "testsywy/space";

    @Override
    public FileVO imageload(MultipartFile file) {
        FileVO fileVO = new FileVO();
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            /*if(bufferedImage == null){
                // 文件非图片
                fileVO.setCode(MsgEnum.OK_2.getCode());
                fileVO.setMsg(MsgEnum.OK_2.getMessage() + ":" + file.getName());
                return fileVO;
            }
            fileVO.setWidth(bufferedImage.getWidth());
            fileVO.setHeight(bufferedImage.getHeight());*/
            bufferedImage = null;

            String url = QiNiuUploadUtils.updateFile(file, imageNameSpace, QiNiuUploadUtils.getNewFileName(file.getOriginalFilename()));

            fileVO.setUrl(url);
            fileVO.setCode(MsgEnum.SUCCESS_1.getCode());
            fileVO.setMsg(MsgEnum.SUCCESS_1.getMessage());

        } catch (IOException e) {
            LogFactory.get().error(e.getMessage());
            fileVO.setCode(MsgEnum.OK_3.getCode());
            fileVO.setMsg(MsgEnum.OK_3.getMessage() + ":" + file.getName());
        }
        return fileVO;
    }

    @Override
    public List<FileVO> imagesload(MultiValueMap<String, MultipartFile> multiValueMap) {
        List<FileVO> list = new ArrayList<>();
        multiValueMap.forEach((k, v) -> {
            v.forEach(f -> {
                list.add(imageload(f));
            });
        });
        return list;
    }
}
