package com.jiale.thesis.controller.student;

import com.jiale.thesis.entity.StudentInfo;
import com.jiale.thesis.service.StudentInfoService;
import com.jiale.thesis.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/student/home")
public class HomeController {

    @Autowired
    StudentInfoService studentInfoService ;

    @RequestMapping("/postStudentInfo")
    @ResponseBody
    public Result<StudentInfo> postStudentInfo(BigInteger studentId){
        Result<StudentInfo> result = new Result<>();
        StudentInfo studentInfo = studentInfoService.findStudentInfoById(studentId);
        result.setMessage("查询成功");
        result.setResultCode(200);
        result.setData(studentInfo);
        return result;
    }
}
