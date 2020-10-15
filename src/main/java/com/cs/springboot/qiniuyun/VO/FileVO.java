package com.cs.springboot.qiniuyun.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: chushi
 * @create: 2020-07-28 11:35
 **/
@Data
public class FileVO implements Serializable {

    /** 响应状态 */
    private Integer code;
    /** 状态信息 */
    private String msg;

    /** 资源链接 */
    private String url ;
    /** 图片宽度 */
    private Integer width;
    /** 图片高度 */
    private Integer height;

}
