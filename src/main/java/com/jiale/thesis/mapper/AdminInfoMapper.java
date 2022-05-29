package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.AdminInfo;
import com.jiale.thesis.entity.vo.AdminInformationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface AdminInfoMapper extends BaseMapper<AdminInfo> {

    AdminInformationVO findAdminInformation(BigInteger adminId);

//    @Select("SELECT * FROM student_account")
//    public List<StudentAccount> getStudentAccountList();
}
