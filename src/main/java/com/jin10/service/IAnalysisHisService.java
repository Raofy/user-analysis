package com.jin10.service;

import com.alibaba.fastjson.JSONObject;
import com.jin10.entity.AnalysisHis;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户操作历史记录 服务类
 * </p>
 *
 * @author Airey
 * @since 2019-12-26
 */
public interface IAnalysisHisService extends IService<AnalysisHis> {

    /**
     * 通过设备id推送消息
     * @param deviceId
     * @return
     */
    List<AnalysisHis> findByDeviceId(String deviceId);

}
