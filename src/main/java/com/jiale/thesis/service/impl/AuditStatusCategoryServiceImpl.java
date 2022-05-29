package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.AuditStatusCategory;
import com.jiale.thesis.mapper.AuditStatusCategoryMapper;
import com.jiale.thesis.service.AuditStatusCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuditStatusCategoryServiceImpl implements AuditStatusCategoryService {

    @Resource
    AuditStatusCategoryMapper auditStatusCategoryMapper;
    @Override
    public List<AuditStatusCategory> findAuditStatusCategoryList() {
        QueryWrapper<AuditStatusCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        return auditStatusCategoryMapper.selectList(queryWrapper);
    }

    @Override
    public AuditStatusCategory findAuditStatusCategory(Long id) {
        return auditStatusCategoryMapper.selectById(id);
    }

    @Override
    public int addAuditStatusCategory(AuditStatusCategory auditStatusCategory) {
        auditStatusCategory.setIsDeleted(0);
        return auditStatusCategoryMapper.insert(auditStatusCategory);
    }

    @Override
    public int updateAuditStatusCategory(AuditStatusCategory auditStatusCategory) {
        return auditStatusCategoryMapper.updateById(auditStatusCategory);
    }

    @Override
    public int deletedAuditStatusCategory(Long auditStatusCategoryId) {
        return auditStatusCategoryMapper.deleteById(auditStatusCategoryId);
    }
}
