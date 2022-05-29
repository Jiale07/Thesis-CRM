package com.jiale.thesis.controller.student.account;


import com.jiale.thesis.entity.vo.StudentBasicInformationVO;
import com.jiale.thesis.service.StudentInfoService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/student/account/home")
public class AccountHomeController {

    @Autowired
    StudentInfoService studentInfoService;


    @RequestMapping("/postAccountBasicInformation")
    @ResponseBody
    public Result<StudentBasicInformationVO> postAccountBasicInformation(BigInteger studentId){
        Result<StudentBasicInformationVO> result = new Result<>();
        StudentBasicInformationVO vo = studentInfoService.findStudentBasicInformationVOById(studentId);
        result.setResultCode(200);
        result.setMessage("获取成功");
        result.setData(vo);
        return result;
    }

//    @RequestMapping("/postStudentPersonalInformation")
}
