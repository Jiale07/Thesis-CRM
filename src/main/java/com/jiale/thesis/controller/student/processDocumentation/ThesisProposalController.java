package com.jiale.thesis.controller.student.processDocumentation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiale.thesis.entity.AuditStatusCategory;
import com.jiale.thesis.entity.FileInfo;
import com.jiale.thesis.entity.GD.GDTeamMember;
import com.jiale.thesis.entity.StudentInfo;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.entity.ThesisProposal.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student/ThesisProposal")
public class ThesisProposalController {

    @Autowired
    ThesisProposalService thesisProposalService;
    @Autowired
    ThesisProposalInputSettingService TPISService;
    @Autowired
    ThesisProposalInputService thesisProposalInputService;
    @Autowired
    ThesisProposalAuditInputService TPAIService;
    @Autowired
    ThesisProposalAuditInputSettingService TPAISService;
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    TeacherInfoService teacherInfoService;
    @Autowired
    GDTeamMemberService gdTeamMemberService;
    @Autowired
    GDTeamService gdTeamService;
    @Autowired
    FileInfoService fileInfoService;
    @Autowired
    AuditStatusCategoryService auditStatusCategoryService;

    @RequestMapping("/getThesisProposalInputSettingList")
    @ResponseBody
    public Result<List<ThesisProposalInputSetting>> getThesisProposalInputSetting(){
        Result<List<ThesisProposalInputSetting>> result = new Result<>();
        List<ThesisProposalInputSetting> list =  TPISService.findThesisProposalInputSettingList();
        if (list.size()<=0){
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }else{
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(list);
        }
        return result;
    }
    /*
    通过学生id获取学生信息
     */
    @RequestMapping("/postStudentInfoById")
    @ResponseBody
    public Result<StudentInfo> postStudentInfoById(BigInteger studentId){
        Result<StudentInfo> result = new Result<>();
        StudentInfo studentInfo =  studentInfoService.findStudentInfoById(studentId);
        result.setResultCode(200);
        result.setData(studentInfo);
        result.setMessage("获取成功");
        return result;
    }
    /*
    通过学生id查询毕业设计导师的信息
     */
    @RequestMapping("/postTeacherInfoOfInstructorById")
    @ResponseBody
    public Result<TeacherInfo> postTeacherInfoOfInstructorById(BigInteger studentId){
        Result<TeacherInfo> result = new Result<>();
        GDTeamMember gdTeamMember = gdTeamMemberService.findTeamByStudentId(studentId);
        if (null!=gdTeamMember){
            Long teamId = gdTeamMember.getTeamId();
            BigInteger teacherId = gdTeamService.findTeacherIdByTeamId(teamId);
            TeacherInfo teacherInfo = teacherInfoService.findOneTeacherInfoById(teacherId);
            result.setResultCode(200);
            result.setData(teacherInfo);
            result.setMessage("获取成功");
        }else{
            result.setResultCode(500);
            result.setMessage("该学生尚未加入毕设队伍");
        }

        return result;
    }

    /*
    通过文件id获取文件相关信息
     */
    @RequestMapping("/postFindFileInfoById")
    @ResponseBody
    public Result<FileInfo> postFindFileInfoById(Long fileId){
        Result<FileInfo> result = new Result<FileInfo>();
        System.out.println("[fileId]"+fileId);
        FileInfo fileInfo = fileInfoService.findFileInfoByFileId(fileId);
        if (null!=fileInfo){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(fileInfo);
        }else{
            result.setResultCode(204);
            result.setMessage("文件不存在");
        }
        return result;
    }

    /*
    保存开题报告
    JSON{
        studentId
        inputList{
            id(inputSettingId)
            content
        },
        thesisName,
        thesisCategory,
        fileInfoId
    }
     */
//    @RequestMapping("/postSaveThesisProposal")
//    @ResponseBody
//    private Result postSaveThesisProposal(@RequestBody String json) throws UnsupportedEncodingException{
//        Result result = new Result();
//        String deURL = URLDecoder.decode(json,"UTF-8");
//        JSONObject obj = JSONObject.parseObject(deURL);
//        //获取studentId
//        BigInteger studentId = BigInteger.valueOf(Long.parseLong((String) obj.get("studentId")));
//        //查询该学生是否已有有效的开题报告
//        ThesisProposal thesisProposal1 = thesisProposalService.findThesisProposalByIdStudentId(studentId);
//        if(null!=thesisProposal1){
//            //已有保存的开题报告
//            ThesisProposal thesisProposal = JSONToThesisProposal(obj);
//            thesisProposal.setId(thesisProposal1.getId());
//            thesisProposalService.updateThesisProposalById(thesisProposal);
//            String inputListJOSNArrayStr = String.valueOf(obj.get("inputList"));
//            JSONArray inputListJSONArray = JSONArray.parseArray(inputListJOSNArrayStr);
//            for(int i=0;i<inputListJSONArray.size();i++){
//                JSONObject object = inputListJSONArray.getJSONObject(i);
//                ThesisProposalInput thesisProposalInput = new ThesisProposalInput();
//                thesisProposalInput.setId(Long.valueOf((String) object.get("id")));
//                thesisProposalInput.setInputContent(String.valueOf(object.get("content")));
//                thesisProposalInputService.updateThesisProposalInputService(thesisProposalInput);
//            }
//            result.setResultCode(200);
//            result.setMessage("保存成功");
//        }else{
//            //没有保存的开题报告
//            ThesisProposal thesisProposal = JSONToThesisProposal(obj);
//            thesisProposal.setIsSubmit(0);
//            int isAdd = thesisProposalService.addThesisProposal(thesisProposal);
//            if (isAdd==1){
//                String inputListJOSNArrayStr = String.valueOf(obj.get("inputList"));
//                JSONArray inputListJSONArray = JSONArray.parseArray(inputListJOSNArrayStr);
//                for(int i=0;i<inputListJSONArray.size();i++){
//                    JSONObject object = inputListJSONArray.getJSONObject(i);
//                    ThesisProposalInput thesisProposalInput = new ThesisProposalInput();
//                    thesisProposalInput.setThesisProposalId(thesisProposal.getId());
//                    thesisProposalInput.setInputContent(String.valueOf(object.get("content")));
//                    thesisProposalInput.setTpInputSettingId(Long.valueOf(String.valueOf(object.get("inputSettingId"))));
//                    thesisProposalInputService.addThesisProposalInputService(thesisProposalInput);
//                }
//                result.setResultCode(200);
//                result.setMessage("保存成功");
//            }else{
//                result.setResultCode(500);
//                result.setMessage("保存失败");
//            }
//        }
//        return result;
//    }

    @RequestMapping("/postSubmitThesisProposal")
    @ResponseBody
    private Result postSubmitThesisProposal(@RequestBody String json) throws UnsupportedEncodingException{
        Result result = new Result();
        String deURL = URLDecoder.decode(json,"UTF-8");
        JSONObject obj = JSONObject.parseObject(deURL);
        BigInteger studentId = BigInteger.valueOf(Long.parseLong((String) obj.get("studentId")));
        ThesisProposal thesisProposal1 = thesisProposalService.findThesisProposalByIdStudentId(studentId);
        if(null!=thesisProposal1){
            //已有保存的开题报告
            ThesisProposal thesisProposal = JSONToThesisProposal(obj);
            thesisProposal.setId(thesisProposal1.getId());
            thesisProposalService.updateThesisProposalById(thesisProposal);
            String inputListJOSNArrayStr = String.valueOf(obj.get("inputList"));
            JSONArray inputListJSONArray = JSONArray.parseArray(inputListJOSNArrayStr);
            for(int i=0;i<inputListJSONArray.size();i++){
                JSONObject object = inputListJSONArray.getJSONObject(i);
                ThesisProposalInput thesisProposalInput = new ThesisProposalInput();
                thesisProposalInput.setId(Long.valueOf((String) object.get("id")));
                thesisProposalInput.setInputContent(String.valueOf(object.get("content")));
                thesisProposalInputService.updateThesisProposalInputService(thesisProposalInput);
            }
            result.setResultCode(200);
            result.setMessage("提交成功");
        }else{
            ThesisProposal thesisProposal = JSONToThesisProposal(obj);
            int isAdd = thesisProposalService.addThesisProposal(thesisProposal);
            if (isAdd==1){
                String inputListJOSNArrayStr = String.valueOf(obj.get("inputList"));
                JSONArray inputListJSONArray = JSONArray.parseArray(inputListJOSNArrayStr);
                for(int i=0;i<inputListJSONArray.size();i++){
                    JSONObject object = inputListJSONArray.getJSONObject(i);
                    ThesisProposalInput thesisProposalInput = new ThesisProposalInput();
                    thesisProposalInput.setThesisProposalId(thesisProposal.getId());
                    thesisProposalInput.setInputContent(String.valueOf(object.get("content")));
                    thesisProposalInput.setTpInputSettingId(Long.valueOf(String.valueOf(object.get("inputSettingId"))));
                    thesisProposalInputService.addThesisProposalInputService(thesisProposalInput);
                }
                result.setResultCode(200);
                result.setMessage("提交成功");
            }else{
                result.setResultCode(500);
                result.setMessage("提交失败");
            }
        }

        return result;
    }

    private ThesisProposal JSONToThesisProposal(JSONObject obj){
        ThesisProposal thesisProposal = new ThesisProposal();
        thesisProposal.setThesisName(String.valueOf(obj.get("thesisName")));
        thesisProposal.setThesisCategoryId(Long.valueOf((String) obj.get("thesisCategory")));
        thesisProposal.setStudentId(BigInteger.valueOf(Long.parseLong(String.valueOf(obj.get("studentId")))));
        if (!"null".equals(String.valueOf(obj.get("fileInfoId")))) {
            Long fileId = Long.valueOf(String.valueOf(obj.get("fileInfoId")));
            thesisProposal.setAccessoryFileId(fileId);
        }
        thesisProposal.setIsDeleted(0);
        return thesisProposal;
    }

    @RequestMapping("/postThesisProposalByStudentId")
    @ResponseBody
    public Result<ThesisProposal> postThesisProposalByStudentId(BigInteger studentId){
        Result<ThesisProposal> result = new Result<>();
        ThesisProposal thesisProposal = thesisProposalService.findThesisProposalByIdStudentId(studentId);
        if (null!=thesisProposal){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(thesisProposal);
        }else{
            result.setResultCode(204);
            result.setMessage("尚未提交开题报告");
        }
        return result;
    }

    @RequestMapping("/postTPInputListByTPId")
    @ResponseBody
    public Result<List<ThesisProposalInput>> postTPInputListByTPId(Long TPId){
        Result<List<ThesisProposalInput>> result = new Result<List<ThesisProposalInput>>();
        List<ThesisProposalInput> inputList = thesisProposalInputService.findThesisProposalInputList(TPId);
        if (inputList.size()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(inputList);
        }else{
            result.setResultCode(204);
            result.setMessage("尚未提交开题报告");
        }
        return result;
    }

    /*
    验证学生是否已有毕业设计指导老师
     */
    @RequestMapping("/postVerifyJoinGDTeam")
    @ResponseBody
    public Result postVerifyJoinGDTeam(BigInteger studentId){
        Result result = new Result();
        GDTeamMember gdTeamMember = gdTeamMemberService.findTeamByStudentId(studentId);
        if (null!=gdTeamMember){
            result.setResultCode(200);
            result.setMessage("该学生已经在毕业设计小组");
        }else{
            result.setResultCode(204);
            result.setMessage("尚未加入毕业设计小组");
        }
        return result;
    }

    /*
    验证开题报告是否已经被审核
     */
    @RequestMapping("/postVerifyTheThesisProposalIsAudit")
    @ResponseBody
    public Result<Map<String, Boolean>> postVerifyTheThesisProposalIsAudit(Long thesisProposalId){
        Result<Map<String, Boolean>> result = new Result<>();
        int countNumber =   TPAIService.countTPAIByThesisProposalId(thesisProposalId);
        if (countNumber > 0) {
            List<ThesisProposalAuditInput> list  = TPAIService.findTPAIList(thesisProposalId);
            for (ThesisProposalAuditInput thesisProposalAuditInput : list) {
                AuditStatusCategory auditStatusCategory = auditStatusCategoryService.findAuditStatusCategory(thesisProposalAuditInput.getAuditStatusId());
                if (auditStatusCategory.getAuditStatusValue() == 2) {
                    Map<String, Boolean> map = new HashMap<>();
                    map.put("isApproved", false);
                    result.setData(map);
                    result.setResultCode(200);
                    result.setMessage("已审核");
                    return result;
                }
            }
            Map<String, Boolean> map = new HashMap<>();
            map.put("isApproved",true);
            result.setData(map);
            result.setResultCode(200);
            result.setMessage("已审核");
        }else{
            result.setResultCode(204);
            result.setMessage("未审核");
        }
        return result;
    }

    @RequestMapping("/postThesisProposalAuditInputSettingList")
    @ResponseBody
    public Result<List<ThesisProposalAuditInputSetting>> postThesisProposalAuditInputSettingList(){
        Result<List<ThesisProposalAuditInputSetting>> result = new Result<>();

        List<ThesisProposalAuditInputSetting> list = TPAISService.findTPAISList();
        if (list.size() > 0) {
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(list);
        }else{
            result.setResultCode(204);
            result.setMessage("获取失败");
        }
        return result;
    }


    @RequestMapping("/postThesisProposalAuditInputList")
    @ResponseBody
    public Result<List<ThesisProposalAuditInput>> postThesisProposalAuditInputList(Long thesisProposalId){
        Result<List<ThesisProposalAuditInput>> result = new Result<>();

        List<ThesisProposalAuditInput> list = TPAIService.findTPAIList(thesisProposalId);
        if (list.size() > 0) {
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(list);
        }else{
            result.setResultCode(204);
            result.setMessage("获取失败");
        }
        return result;
    }

    @RequestMapping("/deletedThesisProposalLogic")
    @ResponseBody
    public Result deletedThesisProposalLogic(Long thesisProposalId){
        Result result = new Result();
        int isDeleted =  thesisProposalService.deletedThesisProposalLogic(thesisProposalId);
        if (isDeleted==1) {
            result.setResultCode(200);
            result.setMessage("删除成功");
        }else{
            result.setResultCode(204);
            result.setMessage("删除失败");
        }
        return result;
    }
}
