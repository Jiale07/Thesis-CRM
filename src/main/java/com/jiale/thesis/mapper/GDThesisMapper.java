package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDThesis;
import com.jiale.thesis.entity.GD.vo.GDThesisSubmittedTableVO;
import com.jiale.thesis.entity.GD.vo.ThesisBasicInformationVO;
import com.jiale.thesis.entity.GD.vo.ThesisSubmitTheRecordVO;
import com.jiale.thesis.entity.GD.vo.ThesisTableViewVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface GDThesisMapper extends BaseMapper<GDThesis> {
    Page<GDThesisSubmittedTableVO> findGDThesisSubmittedTableVOPage(Long teamId);
    Page<ThesisSubmitTheRecordVO> findThesisSubmitTheRecordVOPage(Page<ThesisSubmitTheRecordVO> page, BigInteger studentId);
    ThesisBasicInformationVO findThesisBasicInformationVOByStudentId(BigInteger studentId);
    List<GDThesis> findGDThesisByStudentIdList(BigInteger studentId);

    Page<ThesisTableViewVO> findThesisTableViewVO(Page<ThesisTableViewVO> page,Long teamId);

    GDThesis findGDThesisOneByStudentId(BigInteger studentId);

    Integer CountTheNumberOfThesisSubmittedByStudentId(BigInteger studentId);

    Integer verifyThesisAuditIsApproved(BigInteger studentId);

    Integer verityIsCanSubmitThesis(BigInteger studentId);
}
