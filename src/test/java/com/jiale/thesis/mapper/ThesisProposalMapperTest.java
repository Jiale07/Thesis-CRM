package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.vo.ThesisProposalSubmittedTableVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ThesisProposalMapperTest {

    @Resource
    ThesisProposalMapper thesisProposalMapper;

    @Test
    void findThesisProposalSubmittedByTeamId() {
//        System.out.println(thesisProposalMapper.findThesisProposalSubmittedByTeamId(Long.valueOf("1506180189262110721")).toString());
    }

    @Test
    void testFindThesisProposalSubmittedByTeamId() {
        Page<ThesisProposalSubmittedTableVO> page = new Page<>(1,10);

        System.out.println(thesisProposalMapper.findThesisProposalSubmittedByTeamId(page,Long.valueOf("1506180189262110721")).getRecords().toString());
    }

    @Test
    void finThesisProposalAuditVO() {
        System.out.println(thesisProposalMapper.findThesisProposalAuditVO(Long.valueOf("1516328537291182081")).toString());;
    }
}