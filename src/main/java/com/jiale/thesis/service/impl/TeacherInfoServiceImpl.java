package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.entity.vo.*;
import com.jiale.thesis.mapper.SysUserRoleMapper;
import com.jiale.thesis.mapper.TeacherInfoMapper;
import com.jiale.thesis.service.GDTeamService;
import com.jiale.thesis.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Resource
    TeacherInfoMapper teacherInfoMapper;

    @Autowired
    GDTeamService gdTeamService;


    @Override
    public TeacherInformationVO findTeacherInformationById(BigInteger teacherId) {
        return teacherInfoMapper.findTeacherInformationByTeacherId(teacherId);
    }

    @Override
    public TeacherBasicInformationVO findTeacherBasicInformationVOById(BigInteger teacherId) {
        return teacherInfoMapper.findTeacherBasicInformationVOById(teacherId);
    }

    @Override
    public int addTeacherInfo(TeacherInfo teacherInfo) {
        return teacherInfoMapper.insert(teacherInfo);
    }

    @Override
    public TeacherInfo findOneTeacherInfoById(BigInteger teacherId) {
        return teacherInfoMapper.selectById(teacherId);
    }

    @Override
    public int updateTeacherInfoByTIObject(TeacherInfo teacherInfo) {
        return teacherInfoMapper.updateById(teacherInfo);
    }


    @Override
    public int deletedTeacherInfoLogicById(BigInteger teacherId) {
        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setId(teacherId);
        teacherInfo.setIsDeleted(0);
        return teacherInfoMapper.updateById(teacherInfo);
    }

    @Override
    public BigInteger findOneTeacherId(Integer start,Integer end){
        return teacherInfoMapper.getOneTeacherId("DESC",start,end);
    }

    @Override
    public Page<TeacherInfoResVO> findTeacherByPage(Page<TeacherInfoResVO> page, Integer collegeId){
        return teacherInfoMapper.findTeacherByPage(page,collegeId);
    }

    @Override
    public Page<TutorsInformationVO> findNotTutorPage(Page<TutorsInformationVO> page, Integer collegeId) {
        return teacherInfoMapper.findNotTheTutor(page,collegeId);
    }

    @Override
    public Page<TutorsInformationVO> findTutorsList(Integer currentPage,Integer pageSize,Integer roleId, Integer collegeId) {
        Page<TutorsInformationVO> page = new Page<>(currentPage,pageSize);
        return teacherInfoMapper.findTutors(page,roleId,collegeId);
    }


    @Override
    public int batchAddSysUserRole(BigInteger[] teacherIdArray,Integer roleId) {
        for (BigInteger teacherId : teacherIdArray) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(teacherId);
            sysUserRole.setRoleId(roleId);
            sysUserRole.setIsDeleted(0);
            // 创建毕业设计小组
            gdTeamService.createTeamByTeacherId(teacherId);
            // 添加角色
            sysUserRoleMapper.insert(sysUserRole);
        }
        return 1;
    }

}
