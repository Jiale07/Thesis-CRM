package com.jiale.thesis.service;

import com.jiale.thesis.entity.ThesisProposal.ThesisProposalAuditInputSetting;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalInputSetting;

import java.util.List;

public interface ThesisProposalInputSettingService {

    List<ThesisProposalInputSetting> findThesisProposalInputSettingList();

    int addThesisProposalInputItem(ThesisProposalInputSetting thesisProposalInputSetting);

    int updateThesisProposalInputSetting(ThesisProposalInputSetting thesisProposalInputSetting);

    int deletedThesisProposalInputSettingLogic(Long thesisProposalInputSettingId);
}
