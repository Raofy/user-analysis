package com.jin10.mainThread;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jin10.cache.EventCache;
import com.jin10.entity.UserAnalysis;
import com.jin10.netty.service.NioWebSocketServer;
import com.jin10.service.IUserAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Airey
 * @date 2019/11/19 14:24
 * ----------------------------------------------
 * Netty服务线程启动
 * ----------------------------------------------
 */
@Component
@Slf4j
public class NettyServerThread implements CommandLineRunner {

    @Autowired
    private NioWebSocketServer nettyServer;

    @Autowired
    private IUserAnalysisService userAnalysisService;

//    @Value("${jin10.socket.port}")
//    private Integer port;


    @Override
    public void run(String... args) throws Exception {
        List<UserAnalysis> list = userAnalysisService.list();
        list.forEach(item -> {
            if (StringUtils.isNotBlank(item.getEvent()) && StringUtils.isNotBlank(item.getReferer())) {
                EventCache.eventMap.put(item.getReferer(), item.getEvent());
            }
        });
        log.info("初始化事件列表成功！ eventMap " + EventCache.eventMap);

        log.warn("websocket服务器准备启动！ 端口为 :  " + 4500);
        nettyServer.init(4500);

    }
}
