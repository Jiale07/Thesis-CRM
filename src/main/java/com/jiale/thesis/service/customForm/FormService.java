package com.jiale.thesis.service.customForm;

import com.jiale.thesis.entity.customForm.FormEntity;
import com.jiale.thesis.entity.customForm.vo.FormVO;

public interface FormService {
    int createForm(FormEntity form);
    int deleteForm(Long formId);
    int updateForm(FormEntity form);

    FormEntity selectForm(Long formId, Long authorId);
    FormVO selectFormVO(Long formId);
    FormVO selectFormVO(Long formTemplateId, Long authorId);

    int formCount(Long formTemplateId, Long authorId);
}
