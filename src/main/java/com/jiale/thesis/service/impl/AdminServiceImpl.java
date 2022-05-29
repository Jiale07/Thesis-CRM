package com.jiale.thesis.service.impl;

import com.jiale.thesis.entity.AdminInfo;
import com.jiale.thesis.entity.vo.AdminInformationVO;
import com.jiale.thesis.mapper.AdminInfoMapper;
import com.jiale.thesis.mapper.StudentAccountMapper;
import com.jiale.thesis.service.AdminInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminInfoService {

    @Resource
    AdminInfoMapper adminInfoMapper;

    @Resource
    StudentAccountMapper studentAccountMapper;

    @Override
    public AdminInfo findAdminInfoByUserId(BigInteger adminId){
        return adminInfoMapper.selectById(adminId);

    }

    @Override
    public AdminInformationVO findAdminInformationById(BigInteger adminId) {
        return adminInfoMapper.findAdminInformation(adminId);
    }

//    @Override
//    public List<StudentAccount> getStudentAccountList(){
//        return adminMapper.getStudentAccountList();
//    }
//
//    @Override
//    public AdminInfo findAdminAccount(BigInteger adminId) {
//        return adminMapper.selectById(adminId);
//    }


}
