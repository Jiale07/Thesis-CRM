package com.jiale.thesis.service.customForm;

import com.jiale.thesis.entity.customForm.FormTypeEntity;

import java.util.List;

public interface FormTypeService {

    List<FormTypeEntity> formType();

    FormTypeEntity formType(Long id);

    int createFormType(FormTypeEntity formTypeEntity);

    int updateFormType(FormTypeEntity formTypeEntity);

    int deleteFormType(Long id);

    int logicallyDeleteFormType(Long id);

}
