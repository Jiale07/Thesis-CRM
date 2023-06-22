package com.jiale.thesis.service.customForm.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.customForm.FormTypeEntity;
import com.jiale.thesis.mapper.customFormMapper.FormTypeMapper;
import com.jiale.thesis.service.customForm.FormTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FormTypeServiceImpl implements FormTypeService {
    @Resource
    FormTypeMapper formTypeMapper;

    @Override
    public List<FormTypeEntity> formType() {
        QueryWrapper<FormTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        return formTypeMapper.selectList(queryWrapper);
    }

    @Override
    public FormTypeEntity formType(Long id) {
        return formTypeMapper.selectById(id);
    }

    /*
        C
     */
    public int createFormType(FormTypeEntity formTypeEntity) {
        return formTypeMapper.insert(formTypeEntity);
    }

    public int updateFormType(FormTypeEntity formTypeEntity) {
        QueryWrapper<FormTypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", formTypeEntity.getId());
        queryWrapper.eq("is_deleted", 0);
        return formTypeMapper.update(formTypeEntity, queryWrapper);
    }

    public int deleteFormType(Long id) {
        return formTypeMapper.deleteById(id);
    }

    @Override
    public int logicallyDeleteFormType(Long id) {
        FormTypeEntity formTypeEntity = new FormTypeEntity();
        formTypeEntity.setId(id);
        formTypeEntity.setIsDeleted(1);
        return formTypeMapper.updateById(formTypeEntity);
    }
}
