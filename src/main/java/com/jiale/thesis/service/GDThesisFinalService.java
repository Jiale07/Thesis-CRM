package com.jiale.thesis.service;

import com.jiale.thesis.entity.GD.GDThesis;
import com.jiale.thesis.entity.GD.GDThesisFinal;
import com.jiale.thesis.entity.GD.vo.ThesisFinalSubmitTheRecordVO;
import com.jiale.thesis.entity.GD.vo.ThesisTableViewVO;

import java.math.BigInteger;
import java.util.List;

public interface GDThesisFinalService {

    List<ThesisFinalSubmitTheRecordVO> findThesisFinalSubmitTheRecordVOByStudentId(BigInteger studentId);

    List<ThesisTableViewVO> findThesisTableViewVOByGDTeam(Long gdTeamId);

    GDThesisFinal findGDThesisByStudentId(BigInteger studentId);

    int addThesisFinal(GDThesisFinal gdThesisFinal);

    boolean VerifyHaveSubmittedByStudentId(BigInteger studentId);

}
