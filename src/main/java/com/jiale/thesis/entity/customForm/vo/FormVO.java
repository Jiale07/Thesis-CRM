package com.jiale.thesis.entity.customForm.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jiale.thesis.entity.customForm.FormInfoEntity;
import lombok.Data;

import java.util.List;

@Data
public class FormVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long authorId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long formTemplateId;
    List<FormInfoEntity> formInfoList;
}
