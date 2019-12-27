package com.jin10.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jin10.entity.AnalysisHis;
import com.jin10.mapper.AnalysisHisMapper;
import com.jin10.service.IAnalysisHisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.utils.AnalysisUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户操作历史记录 服务实现类
 * </p>
 *
 * @author Airey
 * @since 2019-12-26
 */
@Service
public class AnalysisHisServiceImpl extends ServiceImpl<AnalysisHisMapper, AnalysisHis> implements IAnalysisHisService {

    /**
     * 通过设备id推送消息
     *
     * @param deviceId
     * @return
     */
    @Override
    public List<AnalysisHis> findByDeviceId(String deviceId) {

        LambdaQueryWrapper<AnalysisHis> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AnalysisHis::getDeviceId,deviceId);
        List<AnalysisHis> analysisHisList = baseMapper.selectList(queryWrapper);
        AnalysisUtils.putEvent(analysisHisList);
        return analysisHisList;
    }
}
