package com.jiale.thesis.entity.customForm.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class FormCompleteEntity {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long formId;
    private String formName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long formTypeId;
    private String formTypeName;
    private List<FormComponentVO> formComponentList;
}
