package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.GD.GDThesisInputSetting;
import com.jiale.thesis.mapper.GDThesisInputSettingMapper;
import com.jiale.thesis.service.GDThesisInputSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GDThesisInputSettingServiceImpl implements GDThesisInputSettingService {

    @Resource
    GDThesisInputSettingMapper gdThesisInputSettingMapper;
    @Override
    public List<GDThesisInputSetting> findGDThesisInputSettingList() {
        QueryWrapper<GDThesisInputSetting> queryWrapper = new QueryWrapper<GDThesisInputSetting>();
        queryWrapper.eq("is_deleted",0);
        return gdThesisInputSettingMapper.selectList(queryWrapper);
    }

    @Override
    public int AddGDThesisInputSetting(GDThesisInputSetting gdThesisInputSetting) {
        gdThesisInputSetting.setIsDeleted(0);
        return gdThesisInputSettingMapper.insert(gdThesisInputSetting);
    }

    @Override
    public int updateGDThesisInputSetting(GDThesisInputSetting gdThesisInputSetting) {
        return gdThesisInputSettingMapper.updateById(gdThesisInputSetting);
    }

    @Override
    public int deletedGDThesisInputSettingById(Long gdThesisInputSettingId) {
        return gdThesisInputSettingMapper.deleteById(gdThesisInputSettingId);

    }
}
