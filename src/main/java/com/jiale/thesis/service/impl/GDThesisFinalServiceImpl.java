package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.GD.GDThesisFinal;
import com.jiale.thesis.entity.GD.GDTopicFinal;
import com.jiale.thesis.entity.GD.vo.ThesisFinalSubmitTheRecordVO;
import com.jiale.thesis.entity.GD.vo.ThesisTableViewVO;
import com.jiale.thesis.mapper.GDThesisFinalMapper;
import com.jiale.thesis.mapper.GDTopicFinalMapper;
import com.jiale.thesis.service.GDThesisFinalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class GDThesisFinalServiceImpl implements GDThesisFinalService {

    @Resource
    GDThesisFinalMapper gdThesisFinalMapper;

    @Resource
    GDTopicFinalMapper gdTopicFinalMapper;


    @Override
    public List<ThesisFinalSubmitTheRecordVO> findThesisFinalSubmitTheRecordVOByStudentId(BigInteger studentId) {
        return gdThesisFinalMapper.findThesisFinalSubmitTheRecordVOByStudentId(studentId);
    }

    @Override
    public List<ThesisTableViewVO> findThesisTableViewVOByGDTeam(Long gdTeamId) {
        return gdThesisFinalMapper.findThesisTableViewVOByGDTeam(gdTeamId);
    }

    @Override
    public GDThesisFinal findGDThesisByStudentId(BigInteger studentId) {
        QueryWrapper<GDTopicFinal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("is_deleted",0);
        GDTopicFinal gdTopicFinal = gdTopicFinalMapper.selectOne(queryWrapper);
        QueryWrapper<GDThesisFinal> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("topic_final_id",gdTopicFinal.getId());
        queryWrapper1.eq("is_deleted",0);
        return gdThesisFinalMapper.selectOne(queryWrapper1);
    }


    @Override
    public int addThesisFinal(GDThesisFinal gdThesisFinal) {
        return gdThesisFinalMapper.insert(gdThesisFinal);
    }

    @Override
    public boolean VerifyHaveSubmittedByStudentId(BigInteger studentId) {
        return gdThesisFinalMapper.findThesisFinalSubmitTheRecordVOByStudentId(studentId).size() > 0;
    }
}
