package com.jiale.thesis.entity.GD.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;

@Data
public class TopicAndTutorVO {
    //对象序列化成JSON时，将Long转成String
    @JsonSerialize(using = ToStringSerializer.class)
    private BigInteger teacherId;
    private String teacherName;
    private String collegeName;
    //对象序列化成JSON时，将Long转成String
    @JsonSerialize(using = ToStringSerializer.class)
    private BigInteger topicId;
    private String topicName;
    private String topicDescription;
    private String categoryName;
    private Integer optionalNumber;
    private Integer selectionCount;
}
