package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopicSelection;
import com.jiale.thesis.entity.GD.vo.TeacherTopicSelectedVO;
import com.jiale.thesis.entity.vo.SelectionResultVo;

import java.math.BigInteger;
import java.util.List;

public interface GDTopicSelectionService {

    /*
    TSS:topic selected state
    studentId
    is_deleted
     */
    GDTopicSelection findTSSByStudent(BigInteger studentId);

    SelectionResultVo findSelectionResultVo(BigInteger studentId);

    Page<TeacherTopicSelectedVO> findTeacherTopicSelected(Page<TeacherTopicSelectedVO> page, BigInteger teacherId);

    int updateGDTopicSelection(GDTopicSelection gdTopicSelection ,Long gdtopicSelectionId);
}
