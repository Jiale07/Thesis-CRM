package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.GD.GDThesisAuditRecord;
import com.jiale.thesis.entity.GD.vo.ThesisAuditResultVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GDThesisAuditRecordMapper extends BaseMapper<GDThesisAuditRecord> {

    ThesisAuditResultVO findThesisAuditResultVOByGDThesisId(Long gdThesisId);
}
