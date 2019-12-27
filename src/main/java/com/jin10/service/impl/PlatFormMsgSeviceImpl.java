package com.jin10.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jin10.annotation.ActionCode;
import com.jin10.annotation.SocketResponseBody;
import com.jin10.constants.ActionCodeConstants;
import com.jin10.entity.AnalysisHis;
import com.jin10.netty.global.ChannelSupervise;
import com.jin10.netty.interf.IActionSocketService;
import com.jin10.service.IAnalysisHisService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Airey
 * @date 2019/12/27 15:27
 * ----------------------------------------------
 * TODO
 * ----------------------------------------------
 */
@Service
@Slf4j
@ActionCode(ActionCodeConstants.PLATFORM_MSG)
public class PlatFormMsgSeviceImpl implements IActionSocketService {


    @Autowired
    private IAnalysisHisService analysisHisService;


    @SocketResponseBody
    @Override
    public Object doAction(ChannelHandlerContext context, String message) {

        log.info("收到终端请求 === " + message);
        JSONObject parseJson = JSONObject.parseObject(message);
        String deviceId = parseJson.getString("deviceId");
        ChannelSupervise.addChannelByObject(context.channel(), deviceId);
        List<AnalysisHis> analysisHisList = analysisHisService.findByDeviceId(deviceId);
        return analysisHisList;
    }
}
