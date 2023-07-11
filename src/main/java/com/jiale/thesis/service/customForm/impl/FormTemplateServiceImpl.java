package com.jiale.thesis.service.customForm.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.customForm.FormTemplateEntity;
import com.jiale.thesis.entity.customForm.vo.FormTemplateCompleteEntity;
import com.jiale.thesis.mapper.customFormMapper.FormTemplateMapper;
import com.jiale.thesis.service.customForm.FormTemplateService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FormTemplateServiceImpl implements FormTemplateService {

    @Resource
    FormTemplateMapper formTemplateMapper;

    @Override
    public int createFormTemplate(FormTemplateEntity formTemplateEntity) {
        if (formTemplateEntity.getName() == null || formTemplateEntity.getFormTypeId() == null) {
            return 0;
        } else {
            return formTemplateMapper.insert(formTemplateEntity);
        }
    }

    @Override
    public int logicallyDelete(Long id) {
        return 0;
    }

    @Override
    public FormTemplateEntity selectFormTemplate(Long id) {
        QueryWrapper<FormTemplateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.eq("id", id);
        return formTemplateMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<FormTemplateEntity> selectFormTemplate(Page<FormTemplateEntity> page) {
        QueryWrapper<FormTemplateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        return formTemplateMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<FormTemplateEntity> selectFormTemplate() {
        QueryWrapper<FormTemplateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        return formTemplateMapper.selectList(queryWrapper);
    }

    @Override
    public int updateForm(FormTemplateEntity formTemplateEntity) {
        return formTemplateMapper.updateById(formTemplateEntity);
    }

    @Override
    public int deleteFormTemplate(Long id) {
        return formTemplateMapper.deleteById(id);
    }

    @Override
    public int countFormTemplateByFormTypeId(Long id) {
        QueryWrapper<FormTemplateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("form_type_id", id);
        return formTemplateMapper.selectCount(queryWrapper);
    }

    @Override
    public List<FormTemplateCompleteEntity> selectFormTemplateComplete(Long formTemplateId) {
        return formTemplateMapper.selectFormTemplateComplete(formTemplateId);
    }
}
