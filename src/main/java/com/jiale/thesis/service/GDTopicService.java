package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopic;
import com.jiale.thesis.entity.GD.GDTopicProperty;
import com.jiale.thesis.entity.GD.GDTopicSelection;
import com.jiale.thesis.entity.GD.vo.GDTopicInfoVo;
import com.jiale.thesis.entity.GD.vo.TopicAndTutorVO;
import com.jiale.thesis.entity.GD.vo.TopicDetailVO;

import java.math.BigInteger;
import java.util.List;

public interface GDTopicService {
//    通过分页方式获取题目信息
    Page<GDTopic> findTopicPageByTeacherId(Page<GDTopic> page,BigInteger teacherId);

    int addTopic(GDTopic topic, GDTopicProperty topicProperty);

    int updateTopic(GDTopic topic);

    int deletedTopic(BigInteger topicId);

    GDTopic findTopicById(BigInteger topicId);

    /*
    查询学生选中的土木
     */
    GDTopicSelection findTopicSelectionByStudentId(BigInteger studentId);

    /*
    插入选中记录
     */
    int insertSelectionTopic(BigInteger studentId,Long topicId);

    /*
    更改上一次的题目选中记录中is_deleted
     */
    int deleteLastTopicSelectionLogic(BigInteger studentId,Long oldTopicId);

    /*
    统计题目选中数量
     */
    Integer selectionCountByTopicId(BigInteger topicId);

    Page<TopicAndTutorVO> findTopicAndTutorPage(
            Page<TopicAndTutorVO> page,
            String teacherName,
            Integer collegeId,
            BigInteger categoryId,
            String topicName
    );

    /*
    题目详情
     */
    TopicDetailVO findTopicDetail(BigInteger topicId);

}
