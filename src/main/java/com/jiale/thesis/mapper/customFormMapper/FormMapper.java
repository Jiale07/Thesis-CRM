package com.jiale.thesis.mapper.customFormMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.customForm.FormEntity;
import com.jiale.thesis.entity.customForm.vo.FormVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FormMapper extends BaseMapper<FormEntity> {
    FormVO selectFormVO(Long formId);
    FormVO selectFormVOByTemplateId(Long formTemplateId, Long authorId);
}
