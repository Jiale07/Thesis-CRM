package com.jiale.thesis.controller.teacher.GDTeam;


import com.jiale.thesis.entity.GD.GDTeam;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.service.GDTeamMemberService;
import com.jiale.thesis.service.GDTeamService;
import com.jiale.thesis.service.TeacherInfoService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/teacher/gdTeam")
public class myTeamController {

    @Autowired
    GDTeamService gdTeamService;
    @Autowired
    GDTeamMemberService gdTeamMemberService;
    @Autowired
    TeacherInfoService teacherInfoService;

    @RequestMapping("/postGDTeamByTeacherId")
    @ResponseBody
    public Result<GDTeam> postGDTeamByTeacherId(BigInteger teacherId){
        Result<GDTeam> result = new Result<>();
        GDTeam gdTeam = gdTeamService.findTeamByTeacherId(teacherId);
        if (null!=gdTeam){
            result.setResultCode(200);
            result.setMessage("查询成功");
            result.setData(gdTeam);
        }else{
            result.setResultCode(500);
            result.setMessage("查询失败");
        }
        return result;
    }

    @RequestMapping("/postTeacherInfo")
    @ResponseBody
    public Result<TeacherInfo> postTeacherInfo(BigInteger teacherId){
        Result<TeacherInfo> result = new Result<>();
        try{
            TeacherInfo teacherInfo = teacherInfoService.findOneTeacherInfoById(teacherId);
            if (null!=teacherInfo){
                result.setResultCode(200);
                result.setMessage("获取成功");
                result.setData(teacherInfo);
            }else{
                result.setResultCode(204);
                result.setMessage("找到更多信息");
            }
        }catch (Exception e){
            result.setResultCode(500);
            result.setMessage("服务错误");
        }
        return result;
    }

    @RequestMapping("/postGDTeamMemberNumber")
    @ResponseBody
    public Result<Integer> postGDTeamMemberNumber(Long gdTeamId){
        Result<Integer> result = new Result<>();
        int number = gdTeamMemberService.countGDTeamNumberByGDTeamId(gdTeamId);
        if (number>0) {
            result.setResultCode(200);
            result.setData(number);
        }else{
            result.setResultCode(204);
            result.setMessage("暂无");
        }
        return result;
    }
}
