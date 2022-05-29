package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTeam;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.mapper.GDTeamMapper;
import com.jiale.thesis.mapper.TeacherInfoMapper;
import com.jiale.thesis.service.GDTeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class GDTeamServiceImpl implements GDTeamService {
    @Resource
    GDTeamMapper gdTeamMapper;

    @Resource
    TeacherInfoMapper teacherInfoMapper;

    @Override
    public int createTeamByTeacherId(BigInteger teacherId) {
        TeacherInfo teacherInfo = teacherInfoMapper.selectById(teacherId);
        GDTeam gdTeam = new GDTeam();
        gdTeam.setTeacherId(teacherId);
        gdTeam.setCollegeId(teacherInfo.getCollegeId());
        gdTeam.setIsDeleted(0);
        return gdTeamMapper.insert(gdTeam);
    }

    @Override
    public List<GDTeam> findAllTeamList() {
        return gdTeamMapper.selectList(null);
    }

    @Override
    public GDTeam findTeamByTeacherId(BigInteger teacherId) {
//        QueryWrapper<GDTeam> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("teacher_id",teacherId);
//        queryWrapper.eq("is_deleted",0);
        return gdTeamMapper.findGDTeamByTeacherId(teacherId);
    }

    @Override
    public Page<GDTeam> findTeamPageByCollegeId(Page<GDTeam> page,BigInteger collegeId) {
        QueryWrapper<GDTeam> queryWrapper = new QueryWrapper<GDTeam>();
        queryWrapper.eq("college_id",collegeId);
        return gdTeamMapper.selectPage(page,queryWrapper);
    }

    @Override
    public int updateTeamById(GDTeam gdTeam) {
        return gdTeamMapper.updateById(gdTeam);
    }

    @Override
    public int deletedTeamByIdLogic(BigInteger teacherId) {
        QueryWrapper<GDTeam> queryWrapper = new QueryWrapper<GDTeam>();
        queryWrapper.eq("teacher_id",teacherId);
        queryWrapper.eq("is_deleted",0);
        GDTeam gdTeam = new GDTeam();
        gdTeam.setIsDeleted(1);
        return gdTeamMapper.update(gdTeam,queryWrapper);
    }

    @Override
    public BigInteger findTeacherIdByTeamId(Long teamId) {
        QueryWrapper<GDTeam> queryWrapper = new QueryWrapper<GDTeam>();
        queryWrapper.eq("id",teamId);
        queryWrapper.eq("is_deleted",0);
        return gdTeamMapper.selectOne(queryWrapper).getTeacherId();
    }


}
