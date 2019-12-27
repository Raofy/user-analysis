package com.jin10.controller;


import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jin10.cache.EventCache;
import com.jin10.entity.AnalysisHis;
import com.jin10.entity.BaseResponse;
import com.jin10.netty.global.ChannelSupervise;
import com.jin10.service.IAnalysisHisService;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @author Airey
 * @date 2019/12/25 15:05
 * ----------------------------------------------
 * 接收前端发送的参数
 * ----------------------------------------------
 */
@RestController
@Slf4j
@RequestMapping("/receive")
public class ReceiverController {

    @Autowired
    private IAnalysisHisService hisService;

    @PostMapping("v1")
    public BaseResponse receive(@RequestBody Map<String, Object> params) {

        log.info("接收到前端的参数信息为 ： " + params);

        String referer = MapUtil.getStr(params, "referer");
        String deviceId = MapUtil.getStr(params, "deviceId");

        if (StringUtils.isNotBlank(referer)) {
            params.put("event", EventCache.eventMap.getOrDefault(referer, "无此事件！"));
        } else {
            params.put("event", "无此事件！");
        }

        Date date = new Date();
        params.put("createTime", date);



        if (StringUtils.isNotBlank(deviceId)) {
            if (ChannelSupervise.existObjectChannelGroup(deviceId)) {
                ChannelSupervise.send2AllAction(deviceId, params);
            }
        }

        AnalysisHis analysisHis = new AnalysisHis();
        analysisHis.setCreateTime(date);
        analysisHis.setReferer(referer);
        analysisHis.setData(MapUtil.getStr(params, "data"));
        analysisHis.setPlatform(MapUtil.getStr(params, "platform"));
        analysisHis.setDeviceId(deviceId);
        hisService.save(analysisHis);

        return BaseResponse.ok();
    }


}
