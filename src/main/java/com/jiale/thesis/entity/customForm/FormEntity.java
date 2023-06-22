package com.jiale.thesis.entity.customForm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jiale.thesis.entity.BaseEntityClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("form")
@EqualsAndHashCode(callSuper = true)
public class FormEntity extends BaseEntityClass {
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long formTypeId;
}
