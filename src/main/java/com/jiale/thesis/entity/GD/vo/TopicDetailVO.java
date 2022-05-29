package com.jiale.thesis.entity.GD.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class TopicDetailVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicId;
    private String topicName;
    private String topicDescription;
    private Date topicCreateTime;
    private Date topicUpdateTime;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;
    private String categoryName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicPropertyId;
    private Integer optionalNumber;
    private BigInteger teacherId;
    private String teacherName;

}
