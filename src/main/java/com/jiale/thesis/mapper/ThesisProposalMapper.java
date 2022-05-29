package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposal;
import com.jiale.thesis.entity.vo.ThesisProposalApproveByInstructorVO;
import com.jiale.thesis.entity.vo.ThesisProposalAuditVO;
import com.jiale.thesis.entity.vo.ThesisProposalSubmittedTableVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface ThesisProposalMapper extends BaseMapper<ThesisProposal> {

    Page<ThesisProposalSubmittedTableVO> findThesisProposalSubmittedByTeamId(Page<ThesisProposalSubmittedTableVO> page, Long teamId);

    ThesisProposalAuditVO findThesisProposalAuditVO(Long thesisProposalId);

    Page<ThesisProposalApproveByInstructorVO> findThesisProposalApproveByInstructorId(Page<ThesisProposalApproveByInstructorVO> page,BigInteger teacherId);
}
