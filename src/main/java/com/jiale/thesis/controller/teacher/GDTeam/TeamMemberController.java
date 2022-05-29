package com.jiale.thesis.controller.teacher.GDTeam;

import com.jiale.thesis.entity.GD.GDTeamMember;
import com.jiale.thesis.service.GDTeamMemberService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/teacher/gdTeam")
public class TeamMemberController {

    @Autowired
    GDTeamMemberService gdTeamMemberService;

    @RequestMapping("/postTeamMemberByTeacherId")
    @ResponseBody
    public Result<List<GDTeamMember>> postTeamMemberByTeacherId(Long gdTeamId){
        Result<List<GDTeamMember>> result = new Result<>();
        List<GDTeamMember> gdTeamMembers = gdTeamMemberService.findGDTeamMemberListTeamId(gdTeamId);
        if (gdTeamMembers.size()<=0){
            result.setMessage("没有找到更多信息");
            result.setResultCode(204);
        }else{
            result.setMessage("获取成功");
            result.setResultCode(200);
            result.setData(gdTeamMembers);
        }
        return result;
    }

}
