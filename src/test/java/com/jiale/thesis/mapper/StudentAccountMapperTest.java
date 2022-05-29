package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.vo.StudentInfoResVO;
import com.jiale.thesis.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StudentAccountMapperTest {

    @Resource
    StudentAccountMapper studentAccountMapper;

    @Autowired
    StudentService studentService;

    @Test
    void findAllStudentInfoList() {

        Page<StudentInfoResVO> page=new Page<>(1,10);
        Page<StudentInfoResVO> studentInfoResVOPage =  studentAccountMapper.findStudentByPage(page,null,null,null);
//        Page<StudentInfoResVO> studentPage = studentAccountMapper.findAllStudentInfoList(page,0);
        studentInfoResVOPage.getRecords().forEach(System.out::println);
        System.out.println(studentInfoResVOPage.getTotal());

    }

    @Test
    void findOneStudentId(){
    }


    @Test
    void getStudentInfo(){
        System.out.println(studentService.getStudentInfoById(BigInteger.valueOf(202111100101L)).toString());
    }

}