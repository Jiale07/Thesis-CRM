package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.GD.GDThesisFinal;
import com.jiale.thesis.entity.GD.GDThesisInput;
import com.jiale.thesis.mapper.GDThesisInputMapper;
import com.jiale.thesis.service.GDThesisInputService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class GDThesisInputServiceImpl implements GDThesisInputService {

    @Resource
    GDThesisInputMapper gdThesisInputMapper;
    @Override
    public List<GDThesisInput> findGDThesisInputListByStudentId(BigInteger studentId) {
        return gdThesisInputMapper.findGDThesisInputList(studentId);
    }

    @Override
    public List<GDThesisInput> findGDThesisInputListByGDThesisId(Long gdThesisId) {


        return gdThesisInputMapper.findGDThesisInputListByGDThesisId(gdThesisId);
    }

    @Override
    public int addGDThesisInput(GDThesisInput gdThesisInput) {
        return gdThesisInputMapper.insert(gdThesisInput);
    }
}
