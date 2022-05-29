package com.jiale.thesis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
//@AllArgsConstructor
@TableName("file_info")
public class FileInfo {
    @TableId(value = "id",type = IdType.NONE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String fileNameOld;
    private String fileNameNew;
    private String fileType;
    private Long fileSize;
    private Long uploaderId;
    private Integer downloads;
    private String filePath;
    private String url;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
