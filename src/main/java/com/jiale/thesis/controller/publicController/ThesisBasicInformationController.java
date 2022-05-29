package com.jiale.thesis.controller.publicController;


import com.jiale.thesis.entity.GD.vo.ThesisBasicInformationVO;
import com.jiale.thesis.service.GDThesisService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/public")
public class ThesisBasicInformationController {

    @Autowired
    GDThesisService gdThesisService;

    @RequestMapping("/postThesisBasicInformationVO")
    @ResponseBody
    public Result<ThesisBasicInformationVO> postThesisBasicInformationVO(BigInteger studentId){
        Result<ThesisBasicInformationVO> result = new Result<>();
        try{
            ThesisBasicInformationVO thesisBasicInformationVO = gdThesisService.findThesisBasicInformationVOByStudentId(studentId);
            result.setData(thesisBasicInformationVO);
            result.setResultCode(200);
            result.setMessage("获取成功");
        }catch (Exception e){
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }
}
