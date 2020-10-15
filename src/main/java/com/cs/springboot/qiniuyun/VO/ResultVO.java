package com.cs.springboot.qiniuyun.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: '
 * @author: chushi
 * @create: 2020-07-28 11:36
 **/
@Data
public class ResultVO<T> implements Serializable {

    // 响应状态
    private Integer code;

    // 状态信息
    private String msg;

    // 响应时间戳
    private Long t;

    // 数据体
    private T data;

    public ResultVO() {
        this.t = System.currentTimeMillis();
    }

}
