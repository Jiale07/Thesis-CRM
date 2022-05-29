package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposal;
import com.jiale.thesis.entity.vo.ThesisProposalApproveByInstructorVO;
import com.jiale.thesis.entity.vo.ThesisProposalAuditVO;
import com.jiale.thesis.entity.vo.ThesisProposalSubmittedTableVO;
import com.jiale.thesis.mapper.ThesisProposalMapper;
import com.jiale.thesis.service.ThesisProposalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class ThesisProposalServiceImpl implements ThesisProposalService {

    @Resource
    ThesisProposalMapper thesisProposalMapper;

    @Override
    public ThesisProposal findThesisProposalById(Long id) {
        QueryWrapper<ThesisProposal> queryWrapper = new QueryWrapper<ThesisProposal>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("is_deleted",0);
        return thesisProposalMapper.selectOne(queryWrapper);
    }

    @Override
    public ThesisProposal findThesisProposalByIdStudentId(BigInteger studentId) {
        QueryWrapper<ThesisProposal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("is_deleted",0);
        return thesisProposalMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<ThesisProposalSubmittedTableVO> findThesisProposalSubmittedByTeamId(Integer currentPage, Integer pageSize, Long teamId) {
        Page<ThesisProposalSubmittedTableVO> page = new Page<>(currentPage,pageSize);
        return thesisProposalMapper.findThesisProposalSubmittedByTeamId(page,teamId);
    }

    @Override
    public ThesisProposalAuditVO findThesisProposalAuditVO(Long thesisProposal) {
        return thesisProposalMapper.findThesisProposalAuditVO(thesisProposal);
    }

    @Override
    public int addThesisProposal(ThesisProposal thesisProposal) {
        thesisProposal.setIsDeleted(0);
        return thesisProposalMapper.insert(thesisProposal);
    }

    @Override
    public int updateThesisProposalById(ThesisProposal thesisProposal) {
        return thesisProposalMapper.updateById(thesisProposal);
    }

    @Override
    public Page<ThesisProposalApproveByInstructorVO> findThesisProposalApproveByInstructorIdPage(Integer currentPage,Integer pageSize, BigInteger teacherId) {
        Page<ThesisProposalApproveByInstructorVO> page = new Page<>(currentPage,pageSize);
        return thesisProposalMapper.findThesisProposalApproveByInstructorId(page,teacherId);
    }

    @Override
    public int deletedThesisProposalLogic(Long thesisProposalId) {
        ThesisProposal thesisProposal = new ThesisProposal();
        thesisProposal.setId(thesisProposalId);
        thesisProposal.setIsDeleted(1);
        return thesisProposalMapper.updateById(thesisProposal);
    }
}
