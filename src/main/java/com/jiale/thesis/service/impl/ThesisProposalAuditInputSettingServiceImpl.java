package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalAuditInputSetting;
import com.jiale.thesis.mapper.ThesisProposalAuditInputSettingMapper;
import com.jiale.thesis.service.ThesisProposalAuditInputSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThesisProposalAuditInputSettingServiceImpl implements ThesisProposalAuditInputSettingService {

    @Resource
    ThesisProposalAuditInputSettingMapper TPAISMapper;


    @Override
    public List<ThesisProposalAuditInputSetting> findTPAISList() {
        QueryWrapper<ThesisProposalAuditInputSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        return TPAISMapper.selectList(queryWrapper);
    }

    @Override
    public ThesisProposalAuditInputSetting findTPAISObjectById(Long TPAISId) {
        QueryWrapper<ThesisProposalAuditInputSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        queryWrapper.eq("id",0);
        return TPAISMapper.selectOne(queryWrapper);
    }

    @Override
    public int addTPAIS(ThesisProposalAuditInputSetting thesisProposalAuditInputSetting) {
        return TPAISMapper.insert(thesisProposalAuditInputSetting);
    }

    @Override
    public int updateTPAIS(ThesisProposalAuditInputSetting thesisProposalAuditInputSetting) {
        return TPAISMapper.updateById(thesisProposalAuditInputSetting);
    }

    @Override
    public int deletedTPAIS(Long TPAISId) {
        return TPAISMapper.deleteById(TPAISId);
    }

    @Override
    public int CountTPAISNumber() {
        QueryWrapper<ThesisProposalAuditInputSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        return TPAISMapper.selectCount(queryWrapper);
    }
}
