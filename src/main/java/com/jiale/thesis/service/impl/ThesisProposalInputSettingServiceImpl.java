package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalInputSetting;
import com.jiale.thesis.mapper.ThesisProposalInputSettingMapper;
import com.jiale.thesis.service.ThesisProposalInputSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThesisProposalInputSettingServiceImpl implements ThesisProposalInputSettingService {
    @Resource
    ThesisProposalInputSettingMapper settingMapper;

    @Override
    public List<ThesisProposalInputSetting> findThesisProposalInputSettingList() {
        QueryWrapper<ThesisProposalInputSetting> queryWrapper = new QueryWrapper<ThesisProposalInputSetting>();
        queryWrapper.eq("is_deleted",0);
        return settingMapper.selectList(queryWrapper);
    }

    @Override
    public int addThesisProposalInputItem(ThesisProposalInputSetting thesisProposalInputSetting) {
        return settingMapper.insert(thesisProposalInputSetting);
    }

    @Override
    public int updateThesisProposalInputSetting(ThesisProposalInputSetting thesisProposalInputSetting) {
        return settingMapper.updateById(thesisProposalInputSetting);
    }

    @Override
    public int deletedThesisProposalInputSettingLogic(Long thesisProposalInputSettingId) {
        ThesisProposalInputSetting thesisProposalInputSetting = new ThesisProposalInputSetting();
        thesisProposalInputSetting.setId(thesisProposalInputSettingId);
        thesisProposalInputSetting.setIsDeleted(1);
        return settingMapper.updateById(thesisProposalInputSetting);
    }
}
