package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.TeacherResume;
import com.jiale.thesis.entity.vo.TeacherResumeVO;
import com.jiale.thesis.mapper.TeacherResumeMapper;
import com.jiale.thesis.service.TeacherResumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class TeacherResumeServiceImpl implements TeacherResumeService {

    @Resource
    TeacherResumeMapper teacherResumeMapper;

    @Override
    public TeacherResumeVO findTeacherResumeById(BigInteger teacherId) {
        return teacherResumeMapper.findTeacherResumeVO(teacherId);
    }

    @Override
    public int createTeacherResume(TeacherResume teacherResume) {
        return teacherResumeMapper.insert(teacherResume);
    }

    @Override
    public int deletedTeacherResume(BigInteger teacherID) {
        QueryWrapper<TeacherResume> queryWrapper = new QueryWrapper<TeacherResume>();
        queryWrapper.eq("teacher_id",teacherID);
        queryWrapper.eq("is_deleted",1);
        return teacherResumeMapper.delete(queryWrapper);
    }

    @Override
    public int updateTeacherResume(TeacherResume teacherResume) {
        return teacherResumeMapper.updateById(teacherResume);
    }
}
