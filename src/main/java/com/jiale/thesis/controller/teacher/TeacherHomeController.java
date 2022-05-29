package com.jiale.thesis.controller.teacher;

import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.service.TeacherInfoService;
import com.jiale.thesis.service.TeacherService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/teacher/home")
public class TeacherHomeController {
    @Autowired
    TeacherInfoService teacherInfoService;

    @RequestMapping("/postTeacherInfo")
    @ResponseBody
    public Result<TeacherInfo> postTeacherInfo(BigInteger teacherId){
        Result<TeacherInfo> result = new Result<>();
        TeacherInfo teacherInfo = teacherInfoService.findOneTeacherInfoById(teacherId);
        result.setData(teacherInfo);
        result.setResultCode(200);
        result.setMessage("查询成功");
        return result;
    }
}
