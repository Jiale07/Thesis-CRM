package com.jiale.thesis.controller.student.processDocumentation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.AuditStatusCategory;
import com.jiale.thesis.entity.GD.GDThesis;
import com.jiale.thesis.entity.GD.GDThesisAuditRecord;
import com.jiale.thesis.entity.GD.GDTopicFinal;
import com.jiale.thesis.entity.GD.vo.ThesisSubmitTheRecordVO;
import com.jiale.thesis.service.*;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student/thesisSubmitTheRecord")
public class ThesisSubmitTheRecordController {

    @Autowired
    GDThesisService gdThesisService;
    @Autowired
    GDThesisAuditRecordService gdThesisAuditRecordService;
    @Autowired
    AuditStatusCategoryService auditStatusCategoryService;

    @Autowired
    GDTeamMemberService gdTeamMemberService;
    @Autowired
    ThesisProposalService thesisProposalService;

    @Autowired
    GDTopicFinalService gdTopicFinalService;

    /*
    获取学生论文提交记录和审核记录
     */
    @RequestMapping("/postThesisSubmitTheRecordVOPage")
    @ResponseBody
    public Result<Page<ThesisSubmitTheRecordVO>> postThesisSubmitTheRecordVOPage(Integer currentPage, Integer pageSize, BigInteger studentId){
        Result<Page<ThesisSubmitTheRecordVO>> result = new Result<>();
        List<GDThesis> list =  gdThesisService.findGDThesisByStudentIdList(studentId);
        if (list.size()>0){
            Page<ThesisSubmitTheRecordVO> page = gdThesisService.findThesisSubmitTheRecordVOPage(currentPage,pageSize,studentId);
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(page);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }

    /*
    总的审核结果
     */
    @RequestMapping("/postFinalAuditResults")
    @ResponseBody
    public Result<Object> postFinalAuditResults(BigInteger studentId){
        Result<Object> result = new Result<>();
        /*
        通过学生id查询该学生最近的一次的论文提交记录和审核和审核记录
        1.当没有找到提交记录，则返回true（即可以提交论文），message = 尚未提交
        2.当有提交记录，但没有对应的审核结果，则返回false（即不可以提交论文）message = 待审核
        3.当有提交记录，且有审核结果，audit_status_value = 2，则返回true，message = 审核不通过
        3.当有提交记录，且有审核结果，audit_status_value = 1，则返回false，message = 审核通过
         */
        Map<String,String> map = new HashMap<>();
        GDThesis gdThesis = gdThesisService.findGDThesisOneByStudentId(studentId);
        if (null!=gdThesis){
            GDThesisAuditRecord gdThesisAuditRecord =  gdThesisAuditRecordService.findGDThesisAuditRecordByGDThesisID(gdThesis.getId());
            System.out.println("[gdThesisAuditRecord]"+gdThesisAuditRecord);
            System.out.println("[gdThesis.getId()]"+gdThesis.getId());
            if (null!=gdThesisAuditRecord){
                AuditStatusCategory auditStatusCategory = auditStatusCategoryService.findAuditStatusCategory(gdThesisAuditRecord.getAuditStatusId());
                if (auditStatusCategory.getAuditStatusValue()==1){
                    map.put("isButtonShow","false");
                    map.put("message","审核通过");
                } else if (auditStatusCategory.getAuditStatusValue()==2) {
                    map.put("isButtonShow","true");
                    map.put("message","审核不通过");
                }
            }else{
                map.put("isButtonShow","false");
                map.put("message","待审核");
            }
        }else{
            map.put("isButtonShow","true");
            map.put("message","尚未提交");
        }
        result.setResultCode(200);
        result.setData(map);
        return result;
    }

    @RequestMapping("/postVerifyIsCanSubmitOfThesis")
    @ResponseBody
    public Result postVerifyIsCanSubmitOfThesis(BigInteger studentId){
        Result result = new Result();
        if (null==studentId){
            result.setResultCode(500);
            result.setMessage("服务错误");
            return result;
        }
        // 该学生是否加入毕业设计小组（拥有毕业设计导师）
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
        result.setResultCode(200);
        result.setMessage("验证完成");
        return result;
    }
}
