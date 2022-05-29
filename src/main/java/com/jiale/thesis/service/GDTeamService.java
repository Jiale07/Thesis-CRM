package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTeam;

import java.math.BigInteger;
import java.util.List;

public interface GDTeamService {
//    C
    int createTeamByTeacherId(BigInteger teacherId);
//    R
    List<GDTeam> findAllTeamList();

    GDTeam findTeamByTeacherId(BigInteger teacherId);

    Page<GDTeam> findTeamPageByCollegeId(Page<GDTeam> page,BigInteger collegeId);
//    U
    int updateTeamById(GDTeam gdTeam);
//    D
    int deletedTeamByIdLogic(BigInteger teacherId);

    BigInteger findTeacherIdByTeamId(Long teamId);




}
