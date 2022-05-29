package com.jiale.thesis.entity.GD.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;

@Data
public class GDThesisSubmittedTableVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long gdThesisId;
    private BigInteger studentId;
    private String studentName;
    private String thesisName;
    private String thesisCategoryName;

}
