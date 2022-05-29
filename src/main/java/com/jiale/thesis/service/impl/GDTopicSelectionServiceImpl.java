package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopicSelection;
import com.jiale.thesis.entity.GD.vo.TeacherTopicSelectedVO;
import com.jiale.thesis.entity.vo.SelectionResultVo;
import com.jiale.thesis.mapper.GDTopicSelectionMapper;
import com.jiale.thesis.service.GDTopicSelectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class GDTopicSelectionServiceImpl implements GDTopicSelectionService {

    /*
    TSS:topic selected state
     */

    @Resource
    GDTopicSelectionMapper topicSelectionMapper;


    @Override
    public GDTopicSelection findTSSByStudent(BigInteger studentId) {
        QueryWrapper<GDTopicSelection> TSSQueryWrapper = new QueryWrapper<>();
        TSSQueryWrapper.eq("student_id",studentId).eq("is_deleted",0);
        return topicSelectionMapper.selectOne(TSSQueryWrapper);
    }

    @Override
    public SelectionResultVo findSelectionResultVo(BigInteger studentId) {
        return topicSelectionMapper.findSelectionResult(studentId);
    }

    @Override
    public Page<TeacherTopicSelectedVO> findTeacherTopicSelected(Page<TeacherTopicSelectedVO> page, BigInteger teacherId) {
        Page<TeacherTopicSelectedVO> teacherTopicSelectedVOS = topicSelectionMapper.findTeacherTopicSelected(page,teacherId);
        for (TeacherTopicSelectedVO teacherTopicSelectedVO : teacherTopicSelectedVOS.getRecords()) {
            Long topicId = teacherTopicSelectedVO.getTopicId();
            QueryWrapper<GDTopicSelection> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("topic_Id", topicId);
            queryWrapper.eq("is_deleted", 0);
            Integer selectionCount = topicSelectionMapper.selectCount(queryWrapper);
            teacherTopicSelectedVO.setSelectionCount(selectionCount);
        }
        return teacherTopicSelectedVOS;
    }

    @Override
    public int updateGDTopicSelection(GDTopicSelection gdTopicSelection,Long gdtopicSelectionId) {
        QueryWrapper<GDTopicSelection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        queryWrapper.eq("id",gdtopicSelectionId);
        return topicSelectionMapper.update(gdTopicSelection,queryWrapper);
    }
}
