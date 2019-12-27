package com.jin10.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户操作历史记录
 * </p>
 *
 * @author Airey
 * @since 2019-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AnalysisHis implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 平台
     */
    private String platform;

    /**
     * 路径
     */
    private String referer;

    /**
     * 数据
     */
    private String data;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 事件名
     */
    @TableField(exist = false)
    private String event;


}
