package com.jiale.thesis.service.customForm.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.customForm.FormInfoEntity;
import com.jiale.thesis.mapper.customFormMapper.FormInfoMapper;
import com.jiale.thesis.service.customForm.FormInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FormInfoServiceImpl implements FormInfoService {

    @Resource
    FormInfoMapper formInfoMapper;

    @Override
    public int createFormInfo(FormInfoEntity formInfoEntity) {
        formInfoEntity.setIsDeleted(0);
        return formInfoMapper.insert(formInfoEntity);
    }

    @Override
    public int logicallyDelete(Long formInfoId) {
        FormInfoEntity formInfoEntity = new FormInfoEntity();
        formInfoEntity.setId(formInfoId);
        formInfoEntity.setIsDeleted(1);
        return formInfoMapper.updateById(formInfoEntity);
    }

    @Override
    public int updateFormInfo(FormInfoEntity formInfoEntity) {
        return formInfoMapper.updateById(formInfoEntity);
    }

    @Override
    public FormInfoEntity selectFormInfo(Long formComponentId) {
        QueryWrapper<FormInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("form_component_id", formComponentId);
        queryWrapper.eq("is_deleted", 0);
        return formInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public int deleteFormInfo(Long formInfoId) {
        QueryWrapper<FormInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", formInfoId);
        return formInfoMapper.delete(queryWrapper);
    }
}
