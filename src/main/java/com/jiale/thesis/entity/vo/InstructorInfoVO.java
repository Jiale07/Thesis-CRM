package com.jiale.thesis.entity.vo;

import com.jiale.thesis.entity.GD.GDTopic;
import com.jiale.thesis.entity.GD.vo.GDTopicInfoVo;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class InstructorInfoVO {
    private BigInteger teacherId;
    private String teacherName;
    private List<GDTopicInfoVo> gdTopicInfoVoList;
}
