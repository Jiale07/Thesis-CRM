package com.jiale.thesis.entity.numberingRuleSetting;

import com.baomidou.mybatisplus.annotation.*;
import com.jiale.thesis.entity.BaseEntityClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_numbering_rule_setting")
@EqualsAndHashCode(callSuper = true)
public class NumberingRuleEntity extends BaseEntityClass {
    private Integer type;
    private String name;
    private String content;
}
