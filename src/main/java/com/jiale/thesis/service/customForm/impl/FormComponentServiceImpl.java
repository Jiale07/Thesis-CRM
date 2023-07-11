package com.jiale.thesis.service.customForm.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.customForm.FormComponentEntity;
import com.jiale.thesis.mapper.customFormMapper.FormComponentMapper;
import com.jiale.thesis.service.customForm.FormComponentConfigService;
import com.jiale.thesis.service.customForm.FormComponentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FormComponentServiceImpl implements FormComponentService {
    @Resource
    FormComponentMapper formComponentMapper;
    @Resource
    FormComponentConfigService formComponentConfigService;
    @Override
    public int insertFormComponent(List<FormComponentEntity> formComponentEntityList) {
        int insertCount = 0;
        for (FormComponentEntity formComponentEntity : formComponentEntityList) {
            insertCount += formComponentMapper.insert(formComponentEntity);
        }
        return insertCount;
    }

    @Override
    public List<FormComponentEntity> selectByFormId(Long formTemplateId) {
        QueryWrapper<FormComponentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("form_template_id", formTemplateId);
        queryWrapper.eq("is_deleted", 0);
        return formComponentMapper.selectList(queryWrapper);
    }

    @Override
    public int updateFormComponentList(List<FormComponentEntity> list) {
        int updateCount = 0;
        for (FormComponentEntity formComponentEntity : list) {
            updateCount += formComponentMapper.updateById(formComponentEntity);
        }
        return updateCount;
    }

    @Override
    public int logicallyDelete(Long id) {
//        逻辑删除对应组件的所有配置属性
        int isSuccess = formComponentConfigService.logicallyDeleteByFormComponentId(id);

        FormComponentEntity formComponentEntity = new FormComponentEntity();
        formComponentEntity.setId(id);
        formComponentEntity.setIsDeleted(1);
        return formComponentMapper.updateById(formComponentEntity);
    }
}
