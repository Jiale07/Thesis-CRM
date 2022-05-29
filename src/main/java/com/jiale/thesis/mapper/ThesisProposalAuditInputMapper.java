package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.AuditStatusCategory;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalAuditInput;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ThesisProposalAuditInputMapper extends BaseMapper<ThesisProposalAuditInput> {

    List<ThesisProposalAuditInput> findTHAIList(Long thesisProposalId);

    ThesisProposalAuditInput findTPAIByTPIdANDTeacherId(Long thesisProposalId, BigInteger teacherId);

    ThesisProposalAuditInput findTPAIObjectByTPIdAndRoleId(Long thesisProposalId, Integer roleId);

    List<AuditStatusCategory> findTPAIListByThesisProposalId(Long thesisProposalId);
}
