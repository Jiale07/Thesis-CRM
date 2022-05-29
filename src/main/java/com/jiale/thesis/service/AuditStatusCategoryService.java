package com.jiale.thesis.service;

import com.jiale.thesis.entity.AuditStatusCategory;

import java.util.List;

public interface AuditStatusCategoryService {

    List<AuditStatusCategory> findAuditStatusCategoryList();

    AuditStatusCategory  findAuditStatusCategory(Long id);

    int addAuditStatusCategory(AuditStatusCategory auditStatusCategory);

    int updateAuditStatusCategory(AuditStatusCategory auditStatusCategory);

    int deletedAuditStatusCategory(Long auditStatusCategoryId);
}
