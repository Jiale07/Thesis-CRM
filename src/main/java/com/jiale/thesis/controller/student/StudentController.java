package com.jiale.thesis.controller.student;

import com.jiale.thesis.entity.StudentInfo;
import com.jiale.thesis.service.StudentService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

//    学生住页面信息
    @RequestMapping("/getStudentInfo")
    @ResponseBody
    public Result<StudentInfo> getStudentInfo(BigInteger studentId){
        Result<StudentInfo> result = new Result();

        if (null==studentId){
            result.setResultCode(500);
            result.setMessage("数据异常，请重新登录");
        }
        StudentInfo studentInfo = studentService.getStudentInfoById(studentId);
        if (null == studentInfo){
            result.setResultCode(500);
            result.setMessage("没有找到对应的用户信息");
        }
        result.setResultCode(200);
        result.setData(studentInfo);
        result.setMessage("success");

        return result;
    }
}
