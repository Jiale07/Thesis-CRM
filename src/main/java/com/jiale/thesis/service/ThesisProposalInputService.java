package com.jiale.thesis.service;

import com.jiale.thesis.entity.ThesisProposal.ThesisProposalInput;

import java.util.List;

public interface ThesisProposalInputService {

    List<ThesisProposalInput> findThesisProposalInputList(Long thesisProposalId);

    int addThesisProposalInputService(ThesisProposalInput thesisProposalInput);

    int updateThesisProposalInputService(ThesisProposalInput thesisProposalInput);
}
