package com.cs.springboot.qiniuyun.controller;

import com.cs.springboot.qiniuyun.VO.FileVO;
import com.cs.springboot.qiniuyun.VO.ResultVO;
import com.cs.springboot.qiniuyun.service.FileService;
import com.cs.springboot.qiniuyun.utils.MsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: chushi
 * @create: 2020-07-28 11:45
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    public ResultVO<List<FileVO>> upload(HttpServletRequest request) {
        //List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        //Map<String, MultipartFile> fileMap =  ((MultipartHttpServletRequest) request).getFileMap();
        MultiValueMap<String, MultipartFile> multiValueMap =  ((MultipartHttpServletRequest) request).getMultiFileMap();
        System.out.println(multiValueMap);

        ResultVO<List<FileVO>> resultVO = new ResultVO<>();
        List<FileVO> listFileVO = fileService.imagesload(multiValueMap);
        //Long uid = Long.parseLong(request.getHeader("uid"));
        /*listFileVO.forEach(fileVO -> {
            FileImageEntity fileImageEntity = new FileImageEntity();
            BeanUtils.copyProperties(fileVO, fileImageEntity);
            fileImageEntity.setUid(uid);
            fileService.saveFileInfo(fileImageEntity);
        });*/

        resultVO.setCode(MsgEnum.SUCCESS_1.getCode());
        resultVO.setMsg(MsgEnum.SUCCESS_1.getMessage());
        resultVO.setData(listFileVO);

        return resultVO;
    }
}
