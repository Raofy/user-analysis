package com.jin10.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jin10.cache.EventCache;
import com.jin10.entity.AnalysisHis;

import java.util.List;

/**
 * @author Airey
 * @date 2019/12/27 15:54
 * ----------------------------------------------
 * TODO
 * ----------------------------------------------
 */
public class AnalysisUtils {


    /**
     * 加入事件
     *
     * @param list
     */
    public static void putEvent(List<AnalysisHis> list) {

        list.forEach(item -> {
            if (StringUtils.isNotBlank(item.getReferer())) {
                item.setEvent((String) EventCache.eventMap.getOrDefault(item.getReferer(), "无此事件！"));
            }
        });

    }



}
