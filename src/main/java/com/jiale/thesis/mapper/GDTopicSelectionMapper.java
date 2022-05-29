package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopicSelection;
import com.jiale.thesis.entity.GD.vo.TeacherTopicSelectedVO;
import com.jiale.thesis.entity.vo.SelectionResultVo;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface GDTopicSelectionMapper extends BaseMapper<GDTopicSelection> {

    SelectionResultVo findSelectionResult(BigInteger studentId);

    Page<TeacherTopicSelectedVO> findTeacherTopicSelected(Page<TeacherTopicSelectedVO> page, BigInteger teacherId);
}
