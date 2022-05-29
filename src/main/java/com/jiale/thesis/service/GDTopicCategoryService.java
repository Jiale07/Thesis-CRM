package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopicCategory;

import java.math.BigInteger;
import java.util.List;

public interface GDTopicCategoryService {

    Page<GDTopicCategory> findTopicCategoryByPage(Integer currentPage,Integer pageSize);

    List<GDTopicCategory> findAllTopicCategoryList();

    int addTopicCategory(GDTopicCategory topicCategory);

    int updateTopicCategory(GDTopicCategory topicCategory);

    int deletedTopiCategory(Long topicCategoryId);

    GDTopicCategory findTopicCategoryById(BigInteger topicCategory);
}
