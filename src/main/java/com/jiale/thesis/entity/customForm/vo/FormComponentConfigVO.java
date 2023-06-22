package com.jiale.thesis.entity.customForm.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class FormComponentConfigVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long formComponentConfigId;
    private String propertyKey;
    private String value;
}
