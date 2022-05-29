package com.jiale.thesis.controller.teacher.GDTeam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.vo.ThesisSubmitTheRecordVO;
import com.jiale.thesis.service.GDThesisService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/public/gdTeam/thesisRecordOfStudent")
public class ThesisRecordOfStudentController {


    @Autowired
    GDThesisService gdThesisService;

    @RequestMapping("/postThesisSubmittedRecordList")
    @ResponseBody
    public Result<Page<ThesisSubmitTheRecordVO>> postThesisSubmittedRecordList(Integer currentPage, Integer pageSize, BigInteger studentId){
        Result<Page<ThesisSubmitTheRecordVO>> result = new Result<Page<ThesisSubmitTheRecordVO>>();
        Page<ThesisSubmitTheRecordVO> page = gdThesisService.findThesisSubmitTheRecordVOPage(currentPage,pageSize,studentId);
        if (page.getTotal()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(page);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }
}
