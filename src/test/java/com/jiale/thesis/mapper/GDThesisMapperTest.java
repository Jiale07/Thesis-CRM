package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.vo.ThesisSubmitTheRecordVO;
import com.jiale.thesis.entity.GD.vo.ThesisTableViewVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GDThesisMapperTest {

    @Resource
    GDThesisMapper gdThesisMapper;
    @Test
    void findGDThesisSubmittedTableVOPage() {
//        Page<ThesisSubmitTheRecordVO> page = new Page<>(1,10);
//        System.out.println(gdThesisMapper.findThesisSubmitTheRecordVOPage(page, BigInteger.valueOf(Long.parseLong("202111010101"))).toString());;
    }

    @Test
    void findThesisTableViewVO() {
//        Page<ThesisTableViewVO> page = new Page<>(1,10);
//        System.out.println(gdThesisMapper.findThesisTableViewVO(page,Long.parseLong("1506180189262110721")).getRecords().toString());
    }

    @Test
    void countTheNumberOfThesisSubmittedByStudentId() {
        System.out.println(gdThesisMapper.CountTheNumberOfThesisSubmittedByStudentId(BigInteger.valueOf(Long.parseLong("202111010101"))));
    }

    @Test
    void verifyThesisAuditIsApproved() {
        System.out.println(gdThesisMapper.verifyThesisAuditIsApproved(BigInteger.valueOf(Long.parseLong("202111010101"))));
    }

    @Test
    void findGDThesisListOneByStudentId() {
//        System.out.println(gdThesisMapper.findGDThesisListOneByStudentId(BigInteger.valueOf(Long.parseLong("202111010101"))));
    }

    @Test
    void verityIsCanSubmitThesis() {
        System.out.println(gdThesisMapper.verityIsCanSubmitThesis(new BigInteger("202111010134")));
    }
}