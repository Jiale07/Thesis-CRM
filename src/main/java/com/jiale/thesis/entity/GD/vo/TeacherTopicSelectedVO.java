package com.jiale.thesis.entity.GD.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherTopicSelectedVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long gdtsId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicId;
    private String topicName;
    private Integer optionalNumber;
    private Date selectionCreateTime;
    private Date selectionUpdateTime;
    private Integer selectionCount;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long studentId;
    private String studentName;
    private String collegeName;
    private String majorName;
    private Integer classes;
}
