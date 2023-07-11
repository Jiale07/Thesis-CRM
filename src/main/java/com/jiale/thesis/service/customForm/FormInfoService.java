package com.jiale.thesis.service.customForm;

import com.jiale.thesis.entity.customForm.FormInfoEntity;

public interface FormInfoService {

    int createFormInfo(FormInfoEntity formInfoEntity);

    int logicallyDelete(Long formInfoId);

    int updateFormInfo(FormInfoEntity formInfoEntity);

    FormInfoEntity selectFormInfo(Long formComponentId);

    int deleteFormInfo(Long formInfoId);
}
