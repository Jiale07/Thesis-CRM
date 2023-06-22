package com.jiale.thesis.mapper.customFormMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.customForm.FormEntity;
import com.jiale.thesis.entity.customForm.vo.FormCompleteEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FormMapper extends BaseMapper<FormEntity> {

    List<FormCompleteEntity> selectFormComplete(Long formId);

//    List<FormEntity> selectFormXML();
}
