package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.UserAccount;
import com.jiale.thesis.entity.vo.StudentInfoResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;

@Mapper
public interface StudentAccountMapper extends BaseMapper<UserAccount> {
//    admin
    public Page<StudentInfoResVO> findAllStudentInfoList(Page<StudentInfoResVO> page, Integer isNumber);

    Page<StudentInfoResVO> findStudentByPage(Page<StudentInfoResVO> page,Integer collegeId,Integer majorId,Integer classId);



}
