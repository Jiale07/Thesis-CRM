package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopic;
import com.jiale.thesis.entity.GD.vo.GDTopicInfoVo;
import com.jiale.thesis.entity.GD.vo.TopicAndTutorVO;
import com.jiale.thesis.entity.GD.vo.TopicDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface GDTopicMapper extends BaseMapper<GDTopic> {

    GDTopicInfoVo findTopicInfoByTopicId(BigInteger topicId);

    List<GDTopic> findTopicByCreatorId(BigInteger teacherId);

    Page<TopicAndTutorVO> findTopicAndTutorPage(Page<TopicAndTutorVO>page, String teacherName,Integer collegeId,BigInteger categoryId,String topicName);

    Page<GDTopic> findTopicPageByTeacherId(Page<GDTopic> page, BigInteger teacherId);

    TopicDetailVO findTopicDetail(BigInteger topicId);
}
