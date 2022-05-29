package com.jiale.thesis.entity.GD;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
@TableName("graduation_design_thesis_input_setting")
public class GDThesisInputSetting {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.NONE)
    private Long id;
    private Integer serialNumber;
    private String titleName;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
