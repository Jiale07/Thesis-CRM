package com.jiale.thesis.controller.teacher.account;

import com.jiale.thesis.entity.vo.TeacherBasicInformationVO;
import com.jiale.thesis.service.TeacherInfoService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/teacher/account/home")
public class AccountHomeOfTeacherController {
    @Autowired
    TeacherInfoService teacherInfoService;

    @RequestMapping("/posTeacherBasicInformation")
    @ResponseBody
    public Result<TeacherBasicInformationVO> posTeacherBasicInformation(BigInteger teacherId){
        Result<TeacherBasicInformationVO> result = new Result<>();
        TeacherBasicInformationVO vo = teacherInfoService.findTeacherBasicInformationVOById(teacherId);
        result.setResultCode(200);
        result.setMessage("获取成功");
        result.setData(vo);
        return result;
    }
}
