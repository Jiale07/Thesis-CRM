package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.entity.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface TeacherInfoMapper extends BaseMapper<TeacherInfo> {

    TeacherInformationVO findTeacherInformationByTeacherId(BigInteger teacherId);

    TeacherBasicInformationVO findTeacherBasicInformationVOById(BigInteger teacherId);

    Page<TeacherInfoResVO> findTeacherByPage(Page<TeacherInfoResVO> page, Integer collegeId);


    @Select("SELECT id FROM teacher_info ti WHERE ti.is_deleted = 0 ORDER BY id ${sorTord} LIMIT ${start},${end}")
    BigInteger getOneTeacherId(String sorTord,Integer start,Integer end);

    Page<TutorsInformationVO> findTutors(Page<TutorsInformationVO> page,Integer roleId, Integer collegeId);

    Page<TutorsInformationVO> findNotTheTutor(Page<TutorsInformationVO> page, Integer collegeId);

    Page<InstructorInfoVO> findAllInstructorInfo(Page<InstructorInfoVO> page,Integer roleId,Integer collegeId,BigInteger topicCategoryId);


}
