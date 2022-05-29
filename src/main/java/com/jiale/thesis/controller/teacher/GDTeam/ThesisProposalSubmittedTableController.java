package com.jiale.thesis.controller.teacher.GDTeam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.vo.ThesisProposalSubmittedTableVO;
import com.jiale.thesis.service.ThesisProposalService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher/gdTeam/ThesisProposalSubmittedTable")
public class ThesisProposalSubmittedTableController {

    @Autowired
    ThesisProposalService thesisProposalService;


    @RequestMapping("/postThesisProposalSubmitted")
    @ResponseBody
    public Result<Page<ThesisProposalSubmittedTableVO>> postThesisProposalByTeamId(Integer currentPage, Integer pageSize, Long teamId){
        Result<Page<ThesisProposalSubmittedTableVO>> result = new Result<>();
        System.out.println(currentPage);
        System.out.println(pageSize);
        Page<ThesisProposalSubmittedTableVO> thesisProposalAuditVOPage = thesisProposalService.findThesisProposalSubmittedByTeamId(currentPage,pageSize,teamId);
        System.out.println(thesisProposalAuditVOPage.getTotal());
        if (thesisProposalAuditVOPage.getTotal()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(thesisProposalAuditVOPage);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }
}
