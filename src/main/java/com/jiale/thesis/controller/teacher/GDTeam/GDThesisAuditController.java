package com.jiale.thesis.controller.teacher.GDTeam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.vo.GDThesisSubmittedTableVO;
import com.jiale.thesis.service.GDThesisService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher/gdTeam/GDThesisAudit")
public class GDThesisAuditController {

    @Autowired
    GDThesisService gdThesisService;
    /*
    获取小组已经提交的毕业论文
     */
    @RequestMapping("/postGDThesisByTeamId")
    @ResponseBody
    public Result<Page<GDThesisSubmittedTableVO>> postGDThesisByTeamId(Integer currentPage, Integer pageSize, Long teamId){
        Result<Page<GDThesisSubmittedTableVO>> result = new Result<>();
        Page<GDThesisSubmittedTableVO> page =  gdThesisService.findGDThesisSubmittedTableVOPage(currentPage,pageSize,teamId);
        if(page.getTotal()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(page);
        }else{
            result.setResultCode(200);
            result.setMessage("获取成功");
        }
        return result;
    }
}
