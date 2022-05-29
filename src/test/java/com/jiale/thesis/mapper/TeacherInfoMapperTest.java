package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.vo.TutorsInformationVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TeacherInfoMapperTest {

    @Resource
    TeacherInfoMapper teacherInfoMapper;

    @Test
    void findTutors() {
        Page<TutorsInformationVO> page = new Page<>(1,10);
//        System.out.println(teacherInfoMapper.findTutors(page,2002,null).toString());
    }

    @Test
    void findSelectionResult() {
        BigInteger studentId = new BigInteger("202111010101");
//        System.out.println(teacherInfoMapper.findSelectionResult(studentId).toString());
    }
}