package com.jin10.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Airey
 * @date 2019/12/26 16:44
 * ----------------------------------------------
 * 事件缓存列表
 * ----------------------------------------------
 */
public class EventCache {


    /**
     * 缓存map
     */
    public static Map<String, Object> eventMap = new ConcurrentHashMap<>();


}
