package com.jiale.thesis.service;

import com.jiale.thesis.entity.AdminInfo;
import com.jiale.thesis.entity.vo.AdminInformationVO;

import java.math.BigInteger;
import java.util.List;

public interface AdminInfoService {
    AdminInfo findAdminInfoByUserId(BigInteger id);

//    adminInfo+roleId
    AdminInformationVO findAdminInformationById(BigInteger adminId);



//    List<StudentAccount> getStudentAccountList();
//
//    AdminInfo findAdminAccount(BigInteger adminId);

}

