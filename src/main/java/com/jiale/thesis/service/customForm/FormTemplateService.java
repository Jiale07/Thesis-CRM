package com.jiale.thesis.service.customForm;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.customForm.FormTemplateEntity;
import com.jiale.thesis.entity.customForm.vo.FormTemplateCompleteEntity;

import java.util.List;

public interface FormTemplateService {

    int createFormTemplate(FormTemplateEntity formTemplateEntity);

    int logicallyDelete(Long id);

    FormTemplateEntity selectFormTemplate(Long id);

    Page<FormTemplateEntity> selectFormTemplate(Page<FormTemplateEntity> page);

    List<FormTemplateEntity> selectFormTemplate();
    int updateForm(FormTemplateEntity formTemplateEntity);

    int deleteFormTemplate(Long id);

    int countFormTemplateByFormTypeId(Long id);

    List<FormTemplateCompleteEntity> selectFormTemplateComplete(Long formTemplateId);
}
