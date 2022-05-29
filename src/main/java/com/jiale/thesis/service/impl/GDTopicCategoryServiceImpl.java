package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopicCategory;
import com.jiale.thesis.mapper.GDTopicCategoryMapper;
import com.jiale.thesis.service.GDTopicCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class GDTopicCategoryServiceImpl implements GDTopicCategoryService {

    @Resource
    GDTopicCategoryMapper gdTopicCategoryMapper;
    @Override
    public Page<GDTopicCategory> findTopicCategoryByPage(Integer currentPage, Integer pageSize) {
        Page<GDTopicCategory> gdTopicCategoryPage = new Page<>(currentPage,pageSize);
        return gdTopicCategoryMapper.selectPage(gdTopicCategoryPage,null);
    }

    @Override
    public List<GDTopicCategory> findAllTopicCategoryList() {
        return gdTopicCategoryMapper.selectList(null);
    }

    @Override
    public int addTopicCategory(GDTopicCategory topicCategory) {
        return gdTopicCategoryMapper.insert(topicCategory);
    }

    @Override
    public int updateTopicCategory(GDTopicCategory topicCategory) {
        return gdTopicCategoryMapper.updateById(topicCategory);
    }

    @Override
    public int deletedTopiCategory(Long topicCategoryId) {
        return gdTopicCategoryMapper.deleteById(topicCategoryId);
    }

    @Override
    public GDTopicCategory findTopicCategoryById(BigInteger topicCategory) {
        return gdTopicCategoryMapper.selectById(topicCategory);
    }
}
