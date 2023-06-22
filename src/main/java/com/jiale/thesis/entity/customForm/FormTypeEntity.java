package com.jiale.thesis.entity.customForm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiale.thesis.entity.BaseEntityClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("form_type")
@EqualsAndHashCode(callSuper = true)
public class FormTypeEntity extends BaseEntityClass {
    private String name;
    private Integer upperLimit;
}
