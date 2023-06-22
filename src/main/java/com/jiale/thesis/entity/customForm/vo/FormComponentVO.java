package com.jiale.thesis.entity.customForm.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class FormComponentVO{
    @JsonSerialize(using = ToStringSerializer.class)
    private Long formComponentId;
    private String componentKey;
    private String componentLabel;
    private Integer serialNumber;
    private List<FormComponentConfigVO> formComponentConfigList;
}
