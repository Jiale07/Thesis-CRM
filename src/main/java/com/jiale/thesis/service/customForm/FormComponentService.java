package com.jiale.thesis.service.customForm;

import com.jiale.thesis.entity.customForm.FormComponentEntity;

import java.util.List;

public interface FormComponentService {

    int insertFormComponent(List<FormComponentEntity> formComponentEntityList);

    List<FormComponentEntity> selectByFormId(Long formId);

    int updateFormComponentList(List<FormComponentEntity> list);

    int logicallyDelete(Long id);

}
