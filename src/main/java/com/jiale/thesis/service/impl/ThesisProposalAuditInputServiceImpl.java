package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalAuditInput;
import com.jiale.thesis.mapper.ThesisProposalAuditInputMapper;
import com.jiale.thesis.service.ThesisProposalAuditInputService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class ThesisProposalAuditInputServiceImpl implements ThesisProposalAuditInputService {
    @Resource
    ThesisProposalAuditInputMapper TPAIMapper;
    @Override
    public List<ThesisProposalAuditInput> findTPAIList(Long thesisProposalId) {
        return TPAIMapper.findTHAIList(thesisProposalId);
    }

    @Override
    public int countTPAIByThesisProposalId(Long thesisProposalId) {
        QueryWrapper<ThesisProposalAuditInput> queryWrapper = new QueryWrapper<ThesisProposalAuditInput>();
        queryWrapper.eq("is_deleted",0);
        queryWrapper.eq("tp_id",thesisProposalId);
        return TPAIMapper.selectCount(queryWrapper);
    }

    @Override
    public ThesisProposalAuditInput findTPAIObjectByTPIdAndRoleId(Long thesisProposalId, Integer roleId) {
        return TPAIMapper.findTPAIObjectByTPIdAndRoleId(thesisProposalId,roleId);
    }

    @Override
    public int addTPAI(ThesisProposalAuditInput TPAIObject) {
        return TPAIMapper.insert(TPAIObject);
    }

    @Override
    public int updateTPAI(ThesisProposalAuditInput TPAIObject) {
        return TPAIMapper.updateById(TPAIObject);
    }

    @Override
    public ThesisProposalAuditInput findTPAIByTPIdANDTeacherId(Long thesisProposalId,BigInteger teacherId) {
        return TPAIMapper.findTPAIByTPIdANDTeacherId(thesisProposalId,teacherId);
    }

    @Override
    public ThesisProposalAuditInput findTPAIListByTPIdAndTPAISId(Long thesisProposalId,Long TPAISId) {
        QueryWrapper<ThesisProposalAuditInput> queryWrapper = new QueryWrapper<ThesisProposalAuditInput>();
        queryWrapper.eq("tp_id",thesisProposalId);
        queryWrapper.eq("tpai_setting_id", TPAISId);
        return TPAIMapper.selectOne(queryWrapper);
    }

    @Override
    public int CountTPAINumberByThesisProposalId(Long thesisProposalId) {
        QueryWrapper<ThesisProposalAuditInput> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tp_id",thesisProposalId);
        queryWrapper.eq("is_deleted",0);
        return TPAIMapper.selectCount(queryWrapper);
    }
}
