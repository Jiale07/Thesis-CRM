package com.jiale.thesis.service.customForm.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.customForm.FormComponentConfigEntity;
import com.jiale.thesis.mapper.customFormMapper.FormComponentConfigMapper;
import com.jiale.thesis.service.customForm.FormComponentConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FormComponentConfigServiceImpl implements FormComponentConfigService {
    @Resource
    FormComponentConfigMapper formComponentConfigMapper;
    @Override
    public int insertFormComponentConfig(List<FormComponentConfigEntity> formComponentConfigEntityList) {
        int insertCount = 0;
        for (FormComponentConfigEntity formComponentConfigEntity : formComponentConfigEntityList) {
            insertCount += formComponentConfigMapper.insert(formComponentConfigEntity);
        }
        return insertCount;
    }

    @Override
    public FormComponentConfigEntity select(Long id) {
        return formComponentConfigMapper.selectById(id);
    }

    @Override
    public int updateFormComponentConfig(List<FormComponentConfigEntity> formComponentConfigEntityList) {
        int updateCount = 0;
        for (FormComponentConfigEntity formComponentConfigEntity : formComponentConfigEntityList) {
            updateCount += formComponentConfigMapper.updateById(formComponentConfigEntity);
        }
        return updateCount;
    }

    @Override
    public int logicallyDeleteByFormComponentId(Long formComponentId) {
        FormComponentConfigEntity formComponentConfigEntity = new FormComponentConfigEntity();
        formComponentConfigEntity.setIsDeleted(1);
        QueryWrapper<FormComponentConfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("form_component_id", formComponentId);
        return formComponentConfigMapper.update(formComponentConfigEntity, queryWrapper);
    }
}
