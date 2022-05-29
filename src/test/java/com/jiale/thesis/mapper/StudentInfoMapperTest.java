package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.vo.StudentInfoResVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigInteger;

@SpringBootTest
class StudentInfoMapperTest {

    @Resource
    StudentInfoMapper studentInfoMapper;

    @Test
    void findStudentCandidateByTeacherId() {
    }

    @Test
    void testFindStudentCandidateByTeacherId() {
    }

    @Test
    void findStudentInformationByStudentId() {
        studentInfoMapper.findStudentInformationByStudentId(BigInteger.valueOf(202111010101L));
    }

    @Test
    void findStudentByPage() {
        Page<StudentInfoResVO> page = new Page<>(1,10);
        System.out.println(studentInfoMapper.findStudentByPage(page,null,null,null));
    }

    @Test
    void findStudentBasicInformationVOById() {
        System.out.println(studentInfoMapper.findStudentBasicInformationVOById(BigInteger.valueOf(Long.parseLong("202111010101"))));
    }
}