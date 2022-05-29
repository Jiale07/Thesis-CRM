package com.jiale.thesis.controller.teacher.GDTeam;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jiale.thesis.entity.AuditStatusCategory;
import com.jiale.thesis.entity.DepartmentHead;
import com.jiale.thesis.entity.FileInfo;
import com.jiale.thesis.entity.GD.GDTeam;
import com.jiale.thesis.entity.GD.GDTopicFinal;
import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.ThesisProposal.*;
import com.jiale.thesis.entity.vo.ThesisProposalAuditVO;
import com.jiale.thesis.service.*;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/teacher/gdTeam/ThesisProposalAuditController")
public class ThesisProposalAuditController {

    @Autowired
    ThesisProposalService thesisProposalService;
    @Autowired
    ThesisProposalInputSettingService tpInputSettingService;
    @Autowired
    FileInfoService fileInfoService;
    @Autowired
    ThesisProposalAuditInputSettingService TPAISettingService;
    @Autowired
    ThesisProposalAuditInputService TPAIService;
    @Autowired
    GDTeamService gdTeamService;
    @Autowired
    GDTeamMemberService gdTeamMemberService;
    @Autowired
    DepartmentHeadService departmentHeadService;
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    AuditStatusCategoryService auditStatusCategoryService;

    @Autowired
    GDTopicFinalService gdTopicFinalService;

    /*
    页面显示内容设置信息获取
     */
    @RequestMapping("/postThesisProposalAuditVO")
    @ResponseBody
    public Result<ThesisProposalAuditVO> postThesisProposalAuditVO(Long thesisProposalId){
        Result<ThesisProposalAuditVO> result = new Result<>();

        ThesisProposalAuditVO thesisProposalAuditVO = thesisProposalService.findThesisProposalAuditVO(thesisProposalId);
        if (null!=thesisProposalAuditVO){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(thesisProposalAuditVO);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到有效开题报告信息");
        }
        return result;
    }

    //获取开题报告主要内容获取
    @RequestMapping("/postThesisProposalInputSettingList")
    @ResponseBody
    public Result<List<ThesisProposalInputSetting>> postThesisProposalInputSettingList(){
        Result<List<ThesisProposalInputSetting>> result = new Result<>();

        List<ThesisProposalInputSetting> thesisProposalInputSettingList = tpInputSettingService.findThesisProposalInputSettingList();
        if (thesisProposalInputSettingList.size()>0){
            result.setMessage("获取成功");
            result.setResultCode(200);
            result.setData(thesisProposalInputSettingList);
        }else{
            result.setMessage("没有找到更过开题报告输入设置信息");
            result.setResultCode(204);
        }

        return result;
    }

     //获取开题报告附件的文件信息
    @RequestMapping("/postFileInfoByFileId")
    @ResponseBody
    public Result<FileInfo> postFileInfoByFileId(Long fileId){
        Result<FileInfo> result = new Result<>();

        FileInfo fileInfo = fileInfoService.findFileInfoByFileId(fileId);
        if (null!=fileInfo){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(fileInfo);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多文件信息");
        }

        return result;
    }

    @RequestMapping("/getTPAISettingList")
    @ResponseBody
    public Result<List<ThesisProposalAuditInputSetting>> getTPAISettingList(){
        Result<List<ThesisProposalAuditInputSetting>> result = new Result<>();
        List<ThesisProposalAuditInputSetting> list = TPAISettingService.findTPAISList();
        if (list.size()>0){
            result.setResultCode(200);
            result.setToken("获取成功");
            result.setData(list);
        }else{
            result.setResultCode(204);
            result.setToken("没有找到更多信息");
        }
        return result;
    }

    @RequestMapping("/postTPAIList")
    @ResponseBody
    public Result<List<ThesisProposalAuditInput>> postTPAIList(Long thesisProposalId){
        Result<List<ThesisProposalAuditInput>> result = new Result<>();
        List<ThesisProposalAuditInput> list = TPAIService.findTPAIList(thesisProposalId);
        if (list.size()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(list);
        }else{
            result.setResultCode(204);
            result.setToken("没有找到更多信息");
        }
        return result;
    }

    @RequestMapping("/postSubmitTPAI")
    @ResponseBody
    public Result postSubmitTPAI(ThesisProposalAuditInput TPAIObject){
        Result result = new Result();
        BigInteger teacherId = TPAIObject.getTeacherId();
        Long thesisProposalId = TPAIObject.getTpId();
        ThesisProposalAuditInput thesisProposalAuditInput = TPAIService.findTPAIByTPIdANDTeacherId(thesisProposalId,teacherId);
        if (null!=thesisProposalAuditInput){
            TPAIObject.setId(thesisProposalAuditInput.getId());
            int isUpdate = TPAIService.updateTPAI(TPAIObject);
            result = ReturnResult(isUpdate);
        }else{
            int isAdd = TPAIService.addTPAI(TPAIObject);
            result = ReturnResult(isAdd);
        }
        // 当所有设置的审核输入项都已经完成审核，则产生毕业设计题目最终版。
        int TPAISNumber = TPAISettingService.CountTPAISNumber();
        int TPAINumber = TPAIService.CountTPAINumberByThesisProposalId(thesisProposalId);
        if (TPAINumber>=TPAISNumber){
            int approveNumber = 0;
            List<ThesisProposalAuditInputSetting> TPAISList = TPAISettingService.findTPAISList();
            for (ThesisProposalAuditInputSetting TPAISObject:TPAISList){
                ThesisProposalAuditInput auditInput = TPAIService.findTPAIListByTPIdAndTPAISId(thesisProposalId,TPAISObject.getId());
                if(null!=auditInput){
                    int  auditStatusValue = auditStatusCategoryService.findAuditStatusCategory(auditInput.getAuditStatusId()).getAuditStatusValue();
                    if (auditStatusValue==1){
                        approveNumber +=1;
                    }
                }
            }
            if (approveNumber>=TPAISNumber){
                ThesisProposal thesisProposal =  thesisProposalService.findThesisProposalById(thesisProposalId);
                GDTopicFinal gdTopicFinal = new GDTopicFinal();
                gdTopicFinal.setThesisTopicName(thesisProposal.getThesisName());
                gdTopicFinal.setTopicCategoryId(thesisProposal.getThesisCategoryId());
                gdTopicFinal.setStudentId(thesisProposal.getStudentId());
                gdTopicFinalService.addGDTopicFinal(gdTopicFinal);
            }
        }
        return result;
    }

    private Result ReturnResult(int is){
        Result result = new Result();
        if (is!=1){
            result.setResultCode(500);
            result.setMessage("提交审核失败");
        }else{
            result.setResultCode(200);
            result.setMessage("提交审核成功");

        }
        return result;
    }

//
//    @RequestMapping("/postVerifyStudentInTeacherTeam")
//    @ResponseBody
//    public Result postVerifyStudentInTeacherTeam(BigInteger studentId,BigInteger teacherId){
//        Result result = new Result();
//        GDTeam gdTeam = gdTeamService.findTeamByTeacherId(teacherId);
//        if (gdTeamMemberService.verifyStudentInTeacherTeam(studentId,gdTeam.getId())){
//            result.setResultCode(200);
//            result.setMessage("验证成功");
//        }else{
//            result.setResultCode(500);
//            result.setMessage("验证失败，该学生不是你的小组成员");
//        }
//        return result;
//    }
//
//    /*
//    Verify that the student is in the major administered by the teacher
//     */
//    @RequestMapping("/postVerifyStudentInTheMajorByTheTeacher")
//    @ResponseBody
//    public Result postVerifyStudentInTheMajorByTheTeacher(BigInteger studentId,BigInteger teacherId){
//        Result result = new Result();
//
//        DepartmentHead departmentHead = departmentHeadService.findDepartmentHeadByTeacherId(teacherId);
//        Integer studentOfMajorId = studentInfoService.findStudentInfoById(studentId).getMajorId();
//        if (Objects.equals(departmentHead.getMajorId(), studentOfMajorId)){
//            result.setResultCode(200);
//            result.setMessage("该学生属于该系主任管理的专业");
//        }else{
//            result.setResultCode(500);
//            result.setMessage("该学生不属于该系主任管理的专业");
//        }
//        return result;
//    }


    /*
    验证系主任是否对指定开题报告是否进行了审批
     */
    @RequestMapping("/postVerifyIsAuditByDepartmentHead")
    @ResponseBody
    public Result postVerifyIsAuditByDepartmentHead(Long thesisProposalId){
        Result result = new Result();
        Integer roleIdOfDepartmentHead = 2004;
        try{
            ThesisProposalAuditInput thesisProposalAuditInput = TPAIService.findTPAIObjectByTPIdAndRoleId(thesisProposalId,roleIdOfDepartmentHead);
            List<AuditStatusCategory> list = auditStatusCategoryService.findAuditStatusCategoryList();
            for (AuditStatusCategory auditStatusCategory : list) {
                if (Objects.equals(auditStatusCategory.getId(), thesisProposalAuditInput.getAuditStatusId())) {
                    result.setResultCode(200);
                    result.setMessage("匹配成功");
                    return result;
                }
            }
            result.setResultCode(500);
            result.setMessage("匹配失败");
            return result;
        }catch (Exception e){
            result.setResultCode(500);
            result.setMessage("匹配失败");
            return result;
        }
    }

}
