package com.jiale.thesis.controller.teacher;

import com.jiale.thesis.entity.GD.GDTeam;
import com.jiale.thesis.service.GDTeamService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/teacherTeam")
public class TeamController {

    @Autowired
    GDTeamService gdTeamService;

    @RequestMapping("postGDTeamByTeacherId")
    @ResponseBody
    public Result<GDTeam> postGDTeamByTeacherId(BigInteger teacherId){
        Result<GDTeam> result = new Result<>();

        GDTeam gdTeam = gdTeamService.findTeamByTeacherId(teacherId);
        if (null==gdTeam){
            result.setResultCode(500);
            result.setMessage("没有更多信息");
        }else{
            result.setData(gdTeam);
            result.setResultCode(200);
            result.setMessage("success");
        }
        return result;
    }
}
