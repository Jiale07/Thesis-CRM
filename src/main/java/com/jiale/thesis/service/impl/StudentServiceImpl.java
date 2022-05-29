package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.StudentInfo;
import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.UserAccount;
import com.jiale.thesis.mapper.StudentAccountMapper;
import com.jiale.thesis.mapper.StudentInfoMapper;
import com.jiale.thesis.mapper.SysUserRoleMapper;
import com.jiale.thesis.mapper.UserAccountMapper;
import com.jiale.thesis.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentInfoMapper studentInfoMapper;

    @Resource
    StudentAccountMapper studentAccountMapper;

    @Resource
    UserAccountMapper userAccountMapper;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public StudentInfo getStudentInfoById(BigInteger studentId){
        return  studentInfoMapper.selectById(studentId);
    }





    @Override
    public int deletedStudentById(BigInteger studentId){
        int isDeletedStudentOnInfo = studentInfoMapper.deleteById(studentId);
        int isDeletedUserAccountMapper =userAccountMapper.deleteById(studentId);
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",studentId);
        int isDeletedSysUserRoleMapper = sysUserRoleMapper.delete(queryWrapper);
        if (isDeletedStudentOnInfo==1 || isDeletedUserAccountMapper==1 ||isDeletedSysUserRoleMapper==1){
            return 1;
        }
        return 0;
    }

    @Override
    public int updateStudent(UserAccount userAccount,StudentInfo studentInfo){
        int is_update = 0;
        if(null != userAccount){
            studentInfoMapper.updateById(studentInfo);
            is_update = 1;
        }
        if(null != userAccount){
            studentAccountMapper.updateById(userAccount);
            is_update = 1;
        }
        return is_update;
    }

}
