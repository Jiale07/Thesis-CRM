package com.jiale.thesis.service.customForm;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.customForm.FormEntity;
import com.jiale.thesis.entity.customForm.vo.FormCompleteEntity;

import java.util.List;

public interface FormService {

    int createForm(FormEntity formEntity);

    int logicallyDelete(Long id);

    FormEntity selectForm(Long id);

    Page<FormEntity> selectForm(Page<FormEntity> page);

    int updateForm(FormEntity formEntity);

    int deleteForm(Long id);

    int countFormByFormTypeId(Long id);

    List<FormCompleteEntity> selectFormComplete(Long formId);
}
