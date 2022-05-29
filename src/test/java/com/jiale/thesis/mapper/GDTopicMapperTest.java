package com.jiale.thesis.mapper;

import com.jiale.thesis.entity.GD.vo.GDTopicInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GDTopicMapperTest {

    @Resource
    GDTopicMapper gdTopicMapper;

    @Test
    void findTopicInfoByTopicId() {
        GDTopicInfoVo gdTopicInfoVo = gdTopicMapper.findTopicInfoByTopicId(BigInteger.valueOf(1484482321766715394L));
        System.out.println(gdTopicInfoVo.toString());
    }

    @Test
    void findTopicDetail() {
//        BigInteger teacherId = new BigInteger("1498298407292395521");
//        System.out.println(gdTopicMapper.findTopicDetail(teacherId).toString());
    }
}