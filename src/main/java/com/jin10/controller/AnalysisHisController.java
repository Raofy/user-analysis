package com.jin10.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jin10.entity.AnalysisHis;
import com.jin10.entity.BaseResponse;
import com.jin10.service.IAnalysisHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户操作历史记录 前端控制器
 * </p>
 *
 * @author Airey
 * @since 2019-12-26
 */
@RestController
@RequestMapping("/analysis-his")
public class AnalysisHisController {


    @Autowired
    private IAnalysisHisService analysisHisService;

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("list")
    public BaseResponse list() {

        List<AnalysisHis> list = analysisHisService.list();
        return BaseResponse.ok(list);
    }


    /**
     * 根据平台清空记录
     *
     * @param params
     * @return
     */
    @PostMapping("delete")
    public BaseResponse delete(@RequestBody Map<String, Object> params) {

        String platform = MapUtil.getStr(params, "platform");
        LambdaQueryWrapper<AnalysisHis> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AnalysisHis::getPlatform, platform);
        boolean remove = analysisHisService.remove(queryWrapper);

        return BaseResponse.ok(remove);

    }


}
