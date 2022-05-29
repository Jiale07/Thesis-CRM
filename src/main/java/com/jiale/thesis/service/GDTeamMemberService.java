package com.jiale.thesis.service;

import com.jiale.thesis.entity.GD.GDTeam;
import com.jiale.thesis.entity.GD.GDTeamMember;

import java.math.BigInteger;
import java.util.List;

public interface GDTeamMemberService {

    GDTeamMember findGDTeamMemberById(BigInteger teamMemberId);

    int addTeamMember(GDTeamMember gdTeamMember);

    int updateTeamMember(GDTeamMember gdTeamMember);

    int deletedTeamMemberByIdLogic(Long teamMemberId);

    List<GDTeamMember> findGDTeamMemberListTeamId(Long gdtTeamId);

    GDTeamMember findTeamByStudentId(BigInteger studentId);

    boolean verifyStudentInTeacherTeam(BigInteger studentId,Long gdTeamId);

    int countGDTeamNumberByGDTeamId(Long gdTeamId);
}
