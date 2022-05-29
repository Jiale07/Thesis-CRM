package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.GD.GDTeam;
import com.jiale.thesis.entity.GD.GDTeamMember;
import com.jiale.thesis.mapper.GDTeamMapper;
import com.jiale.thesis.mapper.GDTeamMemberMapper;
import com.jiale.thesis.service.GDTeamMemberService;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
public class GDTeamMemberServiceImpl implements GDTeamMemberService {
    @Resource
    GDTeamMemberMapper gdTeamMemberMapper;

    @Resource
    GDTeamMapper gdTeamMapper;

    @Override
    public GDTeamMember findGDTeamMemberById(BigInteger teamMemberId) {
        return null;
    }

    @Override
    public int addTeamMember(GDTeamMember gdTeamMember) {
        return gdTeamMemberMapper.insert(gdTeamMember);
    }

    @Override
    public int updateTeamMember(GDTeamMember gdTeamMember) {
        return gdTeamMemberMapper.updateById(gdTeamMember);
    }

    @Override
    public int deletedTeamMemberByIdLogic(Long teamMemberId) {
        QueryWrapper<GDTeamMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",teamMemberId);
        queryWrapper.eq("is_deleted",0);
        GDTeamMember gdTeamMember = new GDTeamMember();
        gdTeamMember.setId(teamMemberId);
        gdTeamMember.setIsDeleted(1);
        return gdTeamMemberMapper.update(gdTeamMember,queryWrapper);
    }

    @Override
    public List<GDTeamMember> findGDTeamMemberListTeamId(Long gdtTeamId) {
        return gdTeamMemberMapper.findTeamMemberListByTeamId(gdtTeamId);
    }

    @Override
    public GDTeamMember findTeamByStudentId(BigInteger studentId) {
        QueryWrapper<GDTeamMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("is_deleted",0);
        return gdTeamMemberMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean verifyStudentInTeacherTeam(BigInteger studentId,Long gdTeamId) {
        GDTeamMember gdTeamMember = gdTeamMemberMapper.findTeamMemberByTeamId(gdTeamId);
        return Objects.equals(gdTeamMember.getStudentId(), studentId);
    }

    @Override
    public int countGDTeamNumberByGDTeamId(Long gdTeamId) {
        QueryWrapper<GDTeamMember> queryWrapper = new QueryWrapper<GDTeamMember>();
        queryWrapper.eq("team_id",gdTeamId);
        queryWrapper.eq("is_deleted",0);
        return gdTeamMemberMapper.selectCount(queryWrapper);
    }
}
