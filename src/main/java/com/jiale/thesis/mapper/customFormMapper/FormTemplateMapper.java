package com.jiale.thesis.mapper.customFormMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.customForm.FormTemplateEntity;
import com.jiale.thesis.entity.customForm.vo.FormTemplateCompleteEntity;
import com.jiale.thesis.entity.customForm.vo.FormVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FormTemplateMapper extends BaseMapper<FormTemplateEntity> {

    List<FormTemplateCompleteEntity> selectFormTemplateComplete(Long formTemplateId);

}
