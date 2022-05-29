package com.jiale.thesis.controller.teacher.GDTeam;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.vo.ThesisTableViewVO;
import com.jiale.thesis.service.GDThesisService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;


@Controller
@RequestMapping("/teacher/gdTeam/ThesisSubmittedTable")
public class ThesisSubmittedTableController {

    @Autowired
    GDThesisService gdThesisService;


    /*
    学生信息
    题目信息
     */
    @RequestMapping("/postThesisTableViewVOPage")
    @ResponseBody
    public Result<Page<ThesisTableViewVO>> postThesisSubmittedTableVOPage(Integer currentPage, Integer pageSize, Long teamId){
        Result<Page<ThesisTableViewVO>> result = new Result<>();
        Page<ThesisTableViewVO> page =  gdThesisService.findThesisTableViewVO(currentPage,pageSize,teamId);
        if (page.getTotal()>0){
            int pageLength =  page.getRecords().size();
            for (int i=0;i<pageLength;i++){
                BigInteger studentId = page.getRecords().get(i).getStudentId();
                int submitTheNumber =  gdThesisService.CountThesisSubmitByStudentId(studentId);
                page.getRecords().get(i).setSubmitTheNumber(submitTheNumber);
                if (submitTheNumber>0){
                    page.getRecords().get(i).setSubmitStatus(true);
                }
//                if (gdThesisService.verifyThesisAuditIsApproved(studentId)>0){
//                    page.getRecords().get(i).setAuditStatus("审核通过");
//                }
            }
            result.setResultCode(200);
            result.setData(page);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多数据");
        }
        return result;
    }

}
