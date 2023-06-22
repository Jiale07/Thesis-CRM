package com.jiale.thesis.service.customForm;

import com.jiale.thesis.entity.customForm.FormComponentConfigEntity;

import java.util.List;

public interface FormComponentConfigService {

    int insertFormComponentConfig(List<FormComponentConfigEntity> formComponentConfigEntityList);

    FormComponentConfigEntity select(Long id);

    int updateFormComponentConfig(List<FormComponentConfigEntity> formComponentConfigEntityList);

    int logicallyDeleteByFormComponentId(Long formComponentId);
}
