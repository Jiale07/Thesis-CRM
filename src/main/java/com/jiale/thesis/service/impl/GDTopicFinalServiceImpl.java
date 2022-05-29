package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.GD.GDTopicFinal;
import com.jiale.thesis.mapper.GDTopicFinalMapper;
import com.jiale.thesis.service.GDTopicFinalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class GDTopicFinalServiceImpl implements GDTopicFinalService {
    @Resource
    GDTopicFinalMapper gdTopicFinalMapper;

    @Override
    public GDTopicFinal findGDTopicFinalByStudentId(BigInteger studentId) {
        QueryWrapper<GDTopicFinal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("is_deleted",0);
        return gdTopicFinalMapper.selectOne(queryWrapper);
    }

    @Override
    public int addGDTopicFinal(GDTopicFinal gdTopicFinal) {
        gdTopicFinal.setIsDeleted(0);
        return gdTopicFinalMapper.insert(gdTopicFinal);
    }
}
