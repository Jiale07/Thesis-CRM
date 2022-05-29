package com.jiale.thesis.controller.student.processDocumentation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiale.thesis.entity.AuditStatusCategory;
import com.jiale.thesis.entity.GD.*;
import com.jiale.thesis.entity.GD.vo.ThesisFinalSubmitTheRecordVO;
import com.jiale.thesis.service.*;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/student/thesisFinal")
public class ThesisFinalSubmitController {


    @Autowired
    GDThesisFinalService gdThesisFinalService;
    @Autowired
    GDTopicFinalService gdTopicFinalService;

    @Autowired
    GDThesisInputService gdThesisInputService;
    @Autowired
    GDThesisService gdThesisService;
    @Autowired
    GDTeamMemberService gdTeamMemberService;
    @Autowired
    ThesisProposalService thesisProposalService;
    @Autowired
    GDThesisAuditRecordService gdThesisAuditRecordService;
    @Autowired
    AuditStatusCategoryService auditStatusCategoryService;
    // 查看已有提交论文最终版记录
    @RequestMapping("/postThesisFinalByStudentId")
    @ResponseBody
    public Result<List<ThesisFinalSubmitTheRecordVO>> postThesisFinalByStudentId(BigInteger studentId){
        Result<List<ThesisFinalSubmitTheRecordVO>> result = new Result<>();

        List<ThesisFinalSubmitTheRecordVO> list= gdThesisFinalService.findThesisFinalSubmitTheRecordVOByStudentId(studentId);
        if (list.size()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(list);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }

    @RequestMapping("/postVerifyHaveSubmitted")
    @ResponseBody
    public Result postVerifyHaveSubmitted(BigInteger studentId){
        Result result = new Result();
        boolean isHaveSubmit = gdThesisFinalService.VerifyHaveSubmittedByStudentId(studentId);
        if (isHaveSubmit){
            result.setResultCode(200);
            result.setMessage("毕业设计论文（最终版）已经提交");
        }else{
            result.setResultCode(500);
            result.setMessage("毕业设计论文（最终版）尚未提交");
        }
        return result;
    }


    // 提交

    @RequestMapping("/postThesisFinalSubmit")
    @ResponseBody
    public Result postThesisFinalSubmit(@RequestBody String json)throws UnsupportedEncodingException {
        Result result = new Result();

        String deURL = URLDecoder.decode(json,"UTF-8");
        JSONObject obj = JSONObject.parseObject(deURL);

        BigInteger studentId = BigInteger.valueOf(Long.parseLong((String) obj.get("studentId")));
        GDTopicFinal gdTopicFinal = gdTopicFinalService.findGDTopicFinalByStudentId(studentId);

        GDThesisFinal gdThesisFinal = new GDThesisFinal();
        gdThesisFinal.setTopicFinalId(gdTopicFinal.getId());
        if (!Objects.equals(obj.get("fileInfoId").toString(), "")){
            gdThesisFinal.setAccessoryFileId(Long.parseLong((String) obj.get("fileInfoId")));
        }
        gdThesisFinal.setIsDeleted(0);

        if (gdThesisFinalService.addThesisFinal(gdThesisFinal)==1){
            String inputListJSONArrayStr = String.valueOf(obj.get("inputList"));
            JSONArray inputListJSONArray = JSONArray.parseArray(inputListJSONArrayStr);
            for (int i=0;i<inputListJSONArray.size();i++){
                JSONObject object = inputListJSONArray.getJSONObject(i);
                GDThesisInput gdThesisInput = new GDThesisInput();
                gdThesisInput.setGdthesisId(gdThesisFinal.getId());
                gdThesisInput.setGdthesisInputSettingId(Long.parseLong((String) object.get("gdthesisInputSetting")));
                gdThesisInput.setInputContent((String) object.get("inputContent"));
                gdThesisInput.setIsDeleted(0);
                gdThesisInputService.addGDThesisInput(gdThesisInput);
            }
            result.setResultCode(200);
            result.setMessage("提交成功");
        }else{
            result.setResultCode(500);
            result.setMessage("提交失败");
        }
        return result;
    }

    @RequestMapping("/postVerifyIsCanSubmit")
    @ResponseBody
    public Result postVerifyIsCanSubmit(BigInteger studentId){
        Result result = new Result();

        // 验证用户是否已经提交开论文 且已经审核通过
        if (null==gdTeamMemberService.findTeamByStudentId(studentId)){
            result.setResultCode(204);
            result.setMessage("尚未拥有毕业设计导师");
            return result;
        }

        // 该学生是提价了开题报告
        if (null==thesisProposalService.findThesisProposalByIdStudentId(studentId)){
            result.setResultCode(204);
            result.setMessage("尚未提交开题报告");
            return result;
        }
        // 该学生的开题报告审核是否通过
        if (null==gdTopicFinalService.findGDTopicFinalByStudentId(studentId)){
            result.setResultCode(204);
            result.setMessage("开题报告审核尚未完成");
            return result;
        }

        // 该学生是否提交了毕业设计论文
        GDThesis gdThesis = gdThesisService.findGDThesisOneByStudentId(studentId);
        if (null==gdThesis){
            result.setResultCode(204);
            result.setMessage("毕业论文尚未提交");
            return result;
        }

        // 该学生提交论是否被审核，若被审核，审核结果是什么。
        GDThesisAuditRecord gdThesisAuditRecord = gdThesisAuditRecordService.findGDThesisAuditRecordByGDThesisID(gdThesis.getId());
        if (null!=gdThesisAuditRecord){
            AuditStatusCategory auditStatusCategory =  auditStatusCategoryService.findAuditStatusCategory(gdThesisAuditRecord.getAuditStatusId());
            switch (auditStatusCategory.getAuditStatusValue()){
                case 1:{
                    result.setResultCode(200);
                    result.setMessage("验证通过");
                    break;
                }
                case 2:{
                    result.setResultCode(204);
                    result.setMessage("论文审核结果："+auditStatusCategory.getAuditStatusName()+",请重新提交审核");
                    break;
                }
            }
        }else{
            result.setResultCode(204);
            result.setMessage("论文尚未审核");
        }
        return result;
    }
}
