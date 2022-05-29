package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.GD.GDThesisAuditRecord;
import com.jiale.thesis.entity.GD.vo.ThesisAuditResultVO;
import com.jiale.thesis.mapper.GDThesisAuditRecordMapper;
import com.jiale.thesis.service.GDThesisAuditRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GDThesisAuditRecordServiceImpl implements GDThesisAuditRecordService {

    @Resource
    GDThesisAuditRecordMapper gdThesisAuditRecordMapper;
    @Override
    public GDThesisAuditRecord findGDThesisAuditRecordByGDThesisID(Long gdThesisId) {
        QueryWrapper<GDThesisAuditRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gdthesis_id",gdThesisId);
        queryWrapper.eq("is_deleted",0);
        return gdThesisAuditRecordMapper.selectOne(queryWrapper);
    }

    @Override
    public ThesisAuditResultVO findThesisAuditResultVOByGDThesisId(Long gdThesisId) {
        return gdThesisAuditRecordMapper.findThesisAuditResultVOByGDThesisId(gdThesisId);
    }

    @Override
    public int addGDThesisAuditRecord(GDThesisAuditRecord gdThesisAuditRecord) {
        return gdThesisAuditRecordMapper.insert(gdThesisAuditRecord);
    }

    @Override
    public int updateGDThesisAuditRecord(GDThesisAuditRecord gdThesisAuditRecord) {
        return gdThesisAuditRecordMapper.updateById(gdThesisAuditRecord);
    }
}
