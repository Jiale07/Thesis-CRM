package com.jiale.thesis.entity.GD.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class ThesisSubmitTheRecordVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long thesisId;
    private String fileName;
    private String auditStatusName;
    private Date submitTime;
}
