package com.jin10.netty.global;

import cn.hutool.core.collection.CollUtil;
import com.jin10.utils.JsonUtils2;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChannelSupervise {
    private static ChannelGroup GlobalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static ConcurrentMap<String, ChannelId> ChannelMap = new ConcurrentHashMap();

    private static ConcurrentHashMap<Object, ChannelGroup> actionGlobalGroupMap = new ConcurrentHashMap<>();


    public static void addChannel(Channel channel) {
        GlobalGroup.add(channel);
        ChannelMap.put(channel.id().asShortText(), channel.id());
    }

    public static void removeChannel(Channel channel) {
        GlobalGroup.remove(channel);
        ChannelMap.remove(channel.id().asShortText());
        removeChannelByActionCode(channel);
    }

    public static Channel findChannel(String id) {
        return GlobalGroup.find(ChannelMap.get(id));
    }

    public static void send2All(TextWebSocketFrame tws) {
        GlobalGroup.writeAndFlush(tws);
    }


    public static void addChannelByActionCode(Channel channel, int actionCode) {
        ChannelGroup channels = actionGlobalGroupMap.get(actionCode);
        if (channels == null){
            channels =  new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        }
        channels.add(channel);
        actionGlobalGroupMap.put(actionCode, channels);
       // addChannelByObject(channel, actionCode);
    }


    public static void addChannelByObject(Channel channel, Object object) {
        ChannelGroup channels = actionGlobalGroupMap.get(object);
        if (channels == null){
            channels =  new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        }
        channels.add(channel);
        actionGlobalGroupMap.put(object, channels);
    }



    public static void removeChannelByActionCode(Channel channel){
        for (Object actionCode : actionGlobalGroupMap.keySet()){
            ChannelGroup channels = actionGlobalGroupMap.get(actionCode);
            removeChannel(channels, channel);
        }
    }

    private static void removeChannel(ChannelGroup channels , Channel channel){
        if (channels != null && channel != null){
            channels.remove(channel);
            channels.remove(channel.id().asShortText());
        }
    }


    public static void removeByObjectChannel(Channel channel, Object object){
        ChannelGroup channels = actionGlobalGroupMap.get(object);
        removeChannel(channels, channel);
    }


    public static void send2AllAction(Object action, Object entity) {
        ChannelGroup channels = actionGlobalGroupMap.get(action);
        if (channels != null){
            channels.writeAndFlush( new TextWebSocketFrame(JsonUtils2.writeValue(entity)));
        }
    }


    public static boolean existActionChannelGroup(int action){
        return CollUtil.isNotEmpty(actionGlobalGroupMap.get(action));
    }


    public static boolean existObjectChannelGroup(Object object){
        return CollUtil.isNotEmpty(actionGlobalGroupMap.get(object));
    }


}
