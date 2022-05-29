package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDThesis;
import com.jiale.thesis.entity.GD.vo.GDThesisSubmittedTableVO;
import com.jiale.thesis.entity.GD.vo.ThesisBasicInformationVO;
import com.jiale.thesis.entity.GD.vo.ThesisSubmitTheRecordVO;
import com.jiale.thesis.entity.GD.vo.ThesisTableViewVO;
import com.jiale.thesis.mapper.GDThesisMapper;
import com.jiale.thesis.service.GDThesisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class GDThesisServiceImpl implements GDThesisService {

    @Resource
    GDThesisMapper gdThesisMapper;

    @Override
    public Page<GDThesisSubmittedTableVO> findGDThesisSubmittedTableVOPage(Integer currentPage,Integer pageSize, Long teamId) {
        Page<GDThesisSubmittedTableVO> page = new Page<>(currentPage,pageSize);
        return gdThesisMapper.findGDThesisSubmittedTableVOPage(teamId);
    }

    @Override
    public Page<ThesisSubmitTheRecordVO> findThesisSubmitTheRecordVOPage(Integer currentPage, Integer pageSize, BigInteger studentId) {
        Page<ThesisSubmitTheRecordVO> page = new Page<>(currentPage,pageSize);
        return gdThesisMapper.findThesisSubmitTheRecordVOPage(page,studentId);
    }

    @Override
    public Page<ThesisTableViewVO> findThesisTableViewVO(Integer currentPage, Integer pageSize, Long teamId) {
        Page<ThesisTableViewVO> page = new Page<>(currentPage,pageSize);
        return gdThesisMapper.findThesisTableViewVO(page,teamId);
    }

    @Override
    public ThesisBasicInformationVO findThesisBasicInformationVOByStudentId(BigInteger studentId) {
        return gdThesisMapper.findThesisBasicInformationVOByStudentId(studentId);
    }

    @Override
    public List<GDThesis> findGDThesisByStudentIdList(BigInteger studentId) {
        return gdThesisMapper.findGDThesisByStudentIdList(studentId);
    }

    @Override
    public GDThesis findGDThesisOneByStudentId(BigInteger studentId) {
        return gdThesisMapper.findGDThesisOneByStudentId(studentId);
    }

    @Override
    public GDThesis findGDTheisById(Long id) {
        return gdThesisMapper.selectById(id);
    }

    @Override
    public Integer verityIsCanSubmitThesis(BigInteger studentId) {
        return gdThesisMapper.verityIsCanSubmitThesis(studentId);
    }

    @Override
    public int addGDThesis(GDThesis gdThesis) {
        return gdThesisMapper.insert(gdThesis);
    }

    @Override
    public int CountThesisSubmitByStudentId(BigInteger studentId) {
        return gdThesisMapper.CountTheNumberOfThesisSubmittedByStudentId(studentId);
    }

    @Override
    public int verifyThesisAuditIsApproved(BigInteger studentId) {
        return gdThesisMapper.verifyThesisAuditIsApproved(studentId);
    }
}
