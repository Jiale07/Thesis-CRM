package com.jiale.thesis.service.customForm.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.customForm.FormEntity;
import com.jiale.thesis.entity.customForm.vo.FormCompleteEntity;
import com.jiale.thesis.mapper.customFormMapper.FormMapper;
import com.jiale.thesis.service.customForm.FormService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    @Resource
    FormMapper formMapper;

    @Override
    public int createForm(FormEntity formEntity) {
        if (formEntity.getName() == null || formEntity.getFormTypeId() == null) {
            return 0;
        } else {
            return formMapper.insert(formEntity);
        }
    }

    @Override
    public int logicallyDelete(Long id) {
        return 0;
    }

    @Override
    public FormEntity selectForm(Long id) {
        QueryWrapper<FormEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.eq("id", id);
        return formMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<FormEntity> selectForm(Page<FormEntity> page) {
        QueryWrapper<FormEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        return formMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int updateForm(FormEntity formEntity) {
        return formMapper.updateById(formEntity);
    }

    @Override
    public int deleteForm(Long id) {
        return formMapper.deleteById(id);
    }

    @Override
    public int countFormByFormTypeId(Long id) {
        QueryWrapper<FormEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("form_type_id", id);
        return formMapper.selectCount(queryWrapper);
    }

    @Override
    public List<FormCompleteEntity> selectFormComplete(Long formId) {
        return formMapper.selectFormComplete(formId);
    }
}
