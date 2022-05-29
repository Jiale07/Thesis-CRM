package com.jiale.thesis.service;

import com.jiale.thesis.entity.AuditStatusCategory;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalAuditInput;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalInput;

import java.math.BigInteger;
import java.util.List;

public interface ThesisProposalAuditInputService {

    List<ThesisProposalAuditInput> findTPAIList(Long thesisProposalId);

    int countTPAIByThesisProposalId(Long thesisProposalId);
    ThesisProposalAuditInput findTPAIObjectByTPIdAndRoleId(Long thesisProposalId,Integer roleId);

    int addTPAI(ThesisProposalAuditInput TPAIObject);

    int updateTPAI(ThesisProposalAuditInput TPAIObject);

    ThesisProposalAuditInput findTPAIByTPIdANDTeacherId(Long thesisProposalId,BigInteger teacherId);

    // 获取所有审核结果
    ThesisProposalAuditInput findTPAIListByTPIdAndTPAISId(Long thesisProposalId, Long TPAISId);

    int CountTPAINumberByThesisProposalId (Long thesisProposalId);
}
