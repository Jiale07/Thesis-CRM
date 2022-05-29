package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposal;
import com.jiale.thesis.entity.vo.ThesisProposalApproveByInstructorVO;
import com.jiale.thesis.entity.vo.ThesisProposalAuditVO;
import com.jiale.thesis.entity.vo.ThesisProposalSubmittedTableVO;

import java.math.BigInteger;

public interface ThesisProposalService {

    ThesisProposal findThesisProposalById(Long id);

    ThesisProposal findThesisProposalByIdStudentId(BigInteger studentId);

    Page<ThesisProposalSubmittedTableVO> findThesisProposalSubmittedByTeamId(Integer currentPage, Integer pageSize, Long teamId);

    ThesisProposalAuditVO findThesisProposalAuditVO(Long thesisProposal);

    int addThesisProposal(ThesisProposal thesisProposal);

    int updateThesisProposalById(ThesisProposal thesisProposal);

    Page<ThesisProposalApproveByInstructorVO> findThesisProposalApproveByInstructorIdPage(Integer currentPage,Integer pageSize,BigInteger teacherId);

    int deletedThesisProposalLogic(Long thesisProposalId);

}
