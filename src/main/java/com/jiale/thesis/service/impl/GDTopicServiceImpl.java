package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopic;
import com.jiale.thesis.entity.GD.GDTopicProperty;
import com.jiale.thesis.entity.GD.GDTopicSelection;
import com.jiale.thesis.entity.GD.vo.GDTopicInfoVo;
import com.jiale.thesis.entity.GD.vo.TopicAndTutorVO;
import com.jiale.thesis.entity.GD.vo.TopicDetailVO;
import com.jiale.thesis.mapper.GDTopicMapper;
import com.jiale.thesis.mapper.GDTopicPropertyMapper;
import com.jiale.thesis.mapper.GDTopicSelectionMapper;
import com.jiale.thesis.service.GDTopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class GDTopicServiceImpl implements GDTopicService {

    @Resource
    GDTopicMapper topicMapper;

    @Resource
    GDTopicPropertyMapper topicPropertyMapper;

    @Resource
    GDTopicSelectionMapper gdTopicSelectionMapper;

    @Override
    public Page<GDTopic> findTopicPageByTeacherId(Page<GDTopic> page, BigInteger teacherId) {
        return topicMapper.findTopicPageByTeacherId(page,teacherId);
    }

    @Override
    public int addTopic(GDTopic topic , GDTopicProperty topicProperty) {
        int isAddTopic = topicMapper.insert(topic);
        if (isAddTopic!=1){
            return 0;
        }
        topicProperty.setTopicId(topic.getId());
        int isAddTopicProperty = topicPropertyMapper.insert(topicProperty);
        if (isAddTopicProperty!=1){
            topicMapper.deleteById(topic.getId());
            return 0;
        }
        return 1;
    }

    @Override
    public int updateTopic(GDTopic topic) {
        return topicMapper.updateById(topic);
    }

    @Override
    public int deletedTopic(BigInteger topicId) {
        QueryWrapper<GDTopicProperty> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topic_id",topicId);
        int isDeletedProperty = topicPropertyMapper.delete(queryWrapper);
        int isDeletedTopic = topicMapper.deleteById(topicId);
        if (isDeletedProperty!=1 || isDeletedTopic!=1){
            return 0;
        }
        return 1;
    }

    @Override
    public GDTopic findTopicById(BigInteger topicId) {
        return topicMapper.selectById(topicId);
    }

    @Override
    public GDTopicSelection findTopicSelectionByStudentId(BigInteger studentId) {
        QueryWrapper<GDTopicSelection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("is_deleted",0);
        return gdTopicSelectionMapper.selectOne(queryWrapper);
    }

    @Override
    public int insertSelectionTopic(BigInteger studentId, Long topicId) {
        GDTopicSelection gdTopicSelection = new GDTopicSelection();
        gdTopicSelection.setStudentId(studentId);
        gdTopicSelection.setTopicId(topicId);
        gdTopicSelection.setIsPassed(0);
        gdTopicSelection.setIsDeleted(0);
        return gdTopicSelectionMapper.insert(gdTopicSelection);
    }

    @Override
    public int deleteLastTopicSelectionLogic(BigInteger studentId,Long oldTopicId) {
        QueryWrapper<GDTopicSelection> queryWrapper = new QueryWrapper<GDTopicSelection>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("topic_id",oldTopicId);
        queryWrapper.eq("is_deleted",0);
        GDTopicSelection gdTopicSelection = new GDTopicSelection();
        gdTopicSelection.setIsDeleted(1);
        return gdTopicSelectionMapper.update(gdTopicSelection,queryWrapper);
    }


    @Override
    public Integer selectionCountByTopicId(BigInteger topicId) {
        QueryWrapper<GDTopicSelection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topic_id",topicId);
        queryWrapper.eq("is_deleted",0);
        return gdTopicSelectionMapper.selectCount(queryWrapper);
    }

    @Override
    public Page<TopicAndTutorVO> findTopicAndTutorPage(Page<TopicAndTutorVO> page, String teacherName, Integer collegeId, BigInteger categoryId, String topicName) {
        return topicMapper.findTopicAndTutorPage(page,teacherName,collegeId,categoryId,topicName);
    }

    @Override
    public TopicDetailVO findTopicDetail(BigInteger topicId) {
        return topicMapper.findTopicDetail(topicId);
    }


}
