package com.jiale.thesis.service;

import com.jiale.thesis.entity.ThesisProposal.ThesisProposalAuditInputSetting;

import java.util.List;

public interface ThesisProposalAuditInputSettingService {

    //TPAIS:ThesisProposalAuditInputSetting

    List<ThesisProposalAuditInputSetting> findTPAISList();

    ThesisProposalAuditInputSetting findTPAISObjectById(Long TPAISId);

    int addTPAIS(ThesisProposalAuditInputSetting thesisProposalAuditInputSetting);

    int updateTPAIS(ThesisProposalAuditInputSetting thesisProposalAuditInputSetting);

    int deletedTPAIS(Long TPAISId);

    int CountTPAISNumber();

}
