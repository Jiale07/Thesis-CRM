package com.jiale.thesis.mapper;

import com.jiale.thesis.entity.College;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class CollegeMapperTest {
    @Resource
    CollegeMapper collegeMapper;

    @Test
    void getCollegeInfo(){
        System.out.println(collegeMapper.xmlGetCollegeInfo());
    }

    @Test
    void updateCollegeId(){
//        Date date = new Date();
//        SimpleDateFormat demo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String nowTime = demo.format(date);
//        System.out.println(collegeMapper.updateCollegeByOldId(1002,1012,date));
//        College college = new College();
//        college.setId(1013);
//        college.setName("医学院");
//        college.setIsDeleted(0);
//        System.out.println(college.getCreateTime());
//        collegeMapper.insert(college);
    }
}
