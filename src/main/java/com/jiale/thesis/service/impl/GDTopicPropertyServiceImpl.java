package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.GD.GDTopicProperty;
import com.jiale.thesis.mapper.GDTopicPropertyMapper;
import com.jiale.thesis.service.GDTopicPropertyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class GDTopicPropertyServiceImpl implements GDTopicPropertyService {

    @Resource
    GDTopicPropertyMapper gdTopicPropertyMapper;


    @Override
    public int updateTopicProperty(GDTopicProperty gdTopicProperty) {
        return gdTopicPropertyMapper.updateById(gdTopicProperty);
    }

    @Override
    public GDTopicProperty findGDTopicProperty(BigInteger topicId) {
        QueryWrapper<GDTopicProperty> queryWrapper = new QueryWrapper<GDTopicProperty>();
        queryWrapper.eq("topic_id",topicId);
        queryWrapper.eq("is_deleted",0);
        return gdTopicPropertyMapper.selectOne(queryWrapper);
    }
}
