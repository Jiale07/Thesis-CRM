package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.StudentInfo;
import com.jiale.thesis.entity.vo.StudentBasicInformationVO;
import com.jiale.thesis.entity.vo.StudentInfoResVO;
import com.jiale.thesis.entity.vo.StudentInformationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;

@Mapper
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {


    StudentInformationVO findStudentInformationByStudentId(BigInteger studentId);

    Page<StudentInfoResVO> findStudentByPage(Page<StudentInfoResVO> page, Integer collegeId, Integer majorId, Integer classId);

    @Select("SELECT id FROM student_info ORDER BY id ${sorTord} LIMIT ${start},${end} ")
    BigInteger getOneStudentId(String sorTord,Integer start,Integer end);

    StudentBasicInformationVO findStudentBasicInformationVOById(BigInteger studentId);
}
