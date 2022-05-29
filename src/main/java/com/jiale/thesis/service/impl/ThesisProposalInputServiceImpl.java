package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalInput;
import com.jiale.thesis.mapper.ThesisProposalInputMapper;
import com.jiale.thesis.service.ThesisProposalInputService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThesisProposalInputServiceImpl implements ThesisProposalInputService {
    @Resource
    ThesisProposalInputMapper thesisProposalInputMapper;

    @Override
    public List<ThesisProposalInput> findThesisProposalInputList(Long thesisProposalId) {
        QueryWrapper<ThesisProposalInput> queryWrapper = new QueryWrapper<ThesisProposalInput>();
        queryWrapper.eq("thesis_proposal_id",thesisProposalId);
        queryWrapper.eq("is_deleted",0);
        return thesisProposalInputMapper.selectList(queryWrapper);
    }

    @Override
    public int addThesisProposalInputService(ThesisProposalInput thesisProposalInput) {
        thesisProposalInput.setIsDeleted(0);
        return thesisProposalInputMapper.insert(thesisProposalInput);
    }

    @Override
    public int updateThesisProposalInputService(ThesisProposalInput thesisProposalInput) {
        return thesisProposalInputMapper.updateById(thesisProposalInput);
    }
}
