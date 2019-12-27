package com.jin10.entity;

import lombok.Data;

/**
 * @author Airey
 * @date 2019/12/27 15:05
 * ----------------------------------------------
 * 上报参数
 * ----------------------------------------------
 */
@Data
public class ReportForm {

    /**
     * 数据
     */
    private String data;
    /**
     * 路径
     */
    private String referer;
    /**
     * 平台
     */
    private String platform;
    /**
     * 设备id
     */
    private String deviceId;


}
