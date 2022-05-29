package com.jiale.thesis.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.TeacherResume;
import com.jiale.thesis.entity.vo.TeacherResumeVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface TeacherResumeMapper extends BaseMapper<TeacherResume> {

    TeacherResumeVO findTeacherResumeVO(BigInteger teacherId);

}
