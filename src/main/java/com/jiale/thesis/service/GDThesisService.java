package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDThesis;
import com.jiale.thesis.entity.GD.vo.GDThesisSubmittedTableVO;
import com.jiale.thesis.entity.GD.vo.ThesisBasicInformationVO;
import com.jiale.thesis.entity.GD.vo.ThesisSubmitTheRecordVO;
import com.jiale.thesis.entity.GD.vo.ThesisTableViewVO;
import com.jiale.thesis.util.Result;

import java.math.BigInteger;
import java.util.List;

public interface GDThesisService {

//    List<GDThesis> findGDThesisList();
    Page<GDThesisSubmittedTableVO> findGDThesisSubmittedTableVOPage(Integer currentPage,Integer pageSize,Long teamId);
    Page<ThesisSubmitTheRecordVO> findThesisSubmitTheRecordVOPage(Integer currentPage, Integer pageSize, BigInteger studentId);

    Page<ThesisTableViewVO> findThesisTableViewVO(Integer currentPage,Integer pageSize,Long teamId);
    ThesisBasicInformationVO findThesisBasicInformationVOByStudentId(BigInteger studentId);
    List<GDThesis> findGDThesisByStudentIdList(BigInteger studentId);

    GDThesis findGDThesisOneByStudentId(BigInteger studentId);

    GDThesis findGDTheisById(Long id);

    Integer verityIsCanSubmitThesis(BigInteger studentId);

    int addGDThesis(GDThesis gdThesis);

    int CountThesisSubmitByStudentId(BigInteger studentId);

    int verifyThesisAuditIsApproved(BigInteger studentId);

}
