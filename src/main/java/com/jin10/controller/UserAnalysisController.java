package com.jin10.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jin10.cache.EventCache;
import com.jin10.entity.BaseResponse;
import com.jin10.entity.UserAnalysis;
import com.jin10.service.IUserAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户行为分析测试表 前端控制器
 * </p>
 *
 * @author Airey
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/user-analysis")
public class UserAnalysisController {


    @Autowired
    private IUserAnalysisService userAnalysisService;

    @GetMapping("list")
    public BaseResponse list() {

        List<UserAnalysis> list = userAnalysisService.list();

        return BaseResponse.ok(list);
    }

    @PostMapping("add")
    public BaseResponse add(@RequestBody UserAnalysis userAnalysis) {

        boolean save = userAnalysisService.save(userAnalysis);
        if (save) {
            if (StringUtils.isNotBlank(userAnalysis.getEvent()) && StringUtils.isNotBlank(userAnalysis.getReferer())) {
                EventCache.eventMap.put(userAnalysis.getReferer(), userAnalysis.getEvent());
            }
        }
        return BaseResponse.ok(save);

    }

}
