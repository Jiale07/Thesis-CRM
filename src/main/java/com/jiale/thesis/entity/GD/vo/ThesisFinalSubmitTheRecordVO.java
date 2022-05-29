package com.jiale.thesis.entity.GD.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class ThesisFinalSubmitTheRecordVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long thesisId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long fileId;
    private String fileName;
    private String fileType;
    private String auditStatusName;
    private Date submitTime;
}
