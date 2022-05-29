package com.jiale.thesis.service;

import com.jiale.thesis.entity.GD.GDThesisAuditRecord;
import com.jiale.thesis.entity.GD.vo.ThesisAuditResultVO;

public interface GDThesisAuditRecordService {

    GDThesisAuditRecord findGDThesisAuditRecordByGDThesisID(Long gdThesisId);

    ThesisAuditResultVO findThesisAuditResultVOByGDThesisId(Long gdThesisId);

    int addGDThesisAuditRecord(GDThesisAuditRecord gdThesisAuditRecord);

    int updateGDThesisAuditRecord(GDThesisAuditRecord gdThesisAuditRecord);
}
