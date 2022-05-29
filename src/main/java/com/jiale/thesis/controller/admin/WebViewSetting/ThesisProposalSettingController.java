package com.jiale.thesis.controller.admin.WebViewSetting;

import com.jiale.thesis.entity.ThesisProposal.ThesisProposalAuditInputSetting;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalInputSetting;
import com.jiale.thesis.service.ThesisProposalAuditInputSettingService;
import com.jiale.thesis.service.ThesisProposalInputSettingService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/webViewSetting/thesisProposal")
public class ThesisProposalSettingController {

    @Autowired
    ThesisProposalInputSettingService TPISService;
    @Autowired
    ThesisProposalAuditInputSettingService TPAISService;

    @RequestMapping("/getThesisProposalInputSettingList")
    @ResponseBody
    public Result<List<ThesisProposalInputSetting>> getThesisProposalInputSettingList(){
        Result<List<ThesisProposalInputSetting>> result = new Result<>();
        List<ThesisProposalInputSetting> list = TPISService.findThesisProposalInputSettingList();
        if (list.size()<=0){
            result.setResultCode(500);
            result.setMessage("没有找到更多信息");
        }else{
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(list);
        }
        return result;
    }

    @RequestMapping("/postAddThesisProposalInputSetting")
    @ResponseBody
    public Result postAddThesisProposalInputSetting(ThesisProposalInputSetting thesisProposalInputSetting){
        Result result = new Result();
        int isAdd =  TPISService.addThesisProposalInputItem(thesisProposalInputSetting);
        if (isAdd==1){
            result.setResultCode(200);
            result.setMessage("添加成功");
        }else{
            result.setResultCode(500);
            result.setMessage("添加失败");
        }
        return result;
    }

    @RequestMapping("/postUpdateThesisProposalInputSetting")
    @ResponseBody
    public Result postUpdateThesisProposalInputSetting(ThesisProposalInputSetting thesisProposalInputSetting){
        Result result = new Result();
        int isUpdate = TPISService.updateThesisProposalInputSetting(thesisProposalInputSetting);
        if (isUpdate==1){
            result.setResultCode(200);
            result.setMessage("更新成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更新失败");
        }
        return result;
    }

    @RequestMapping("/postDeletedThesisProposalInputSetting")
    @ResponseBody
    public Result postDeletedThesisProposalInputSetting(Long thesisProposalInputSettingId){
        Result result = new Result();
        int isDeletedLogic = TPISService.deletedThesisProposalInputSettingLogic(thesisProposalInputSettingId);
        if (isDeletedLogic==1){
            result.setResultCode(200);
            result.setMessage("删除成功");
        }else{
            result.setResultCode(500);
            result.setMessage("删除失败");
        }
        return result;
    }

    /*
    TP:ThesisProposal
     */
    @RequestMapping("/postTPAuditInputSettingList")
    @ResponseBody
    public Result<List<ThesisProposalAuditInputSetting>> postTPAuditInputSettingList(){
        Result<List<ThesisProposalAuditInputSetting>> result = new Result<>();
        List<ThesisProposalAuditInputSetting> list = TPAISService.findTPAISList();
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

    @RequestMapping("postAddTPAuditInputSetting")
    @ResponseBody
    public Result postAddTPAuditInputSetting(ThesisProposalAuditInputSetting TPAISObject){
        Result result = new Result();

        int isAdd = TPAISService.addTPAIS(TPAISObject);
        if (isAdd==1){
            result.setResultCode(200);
            result.setMessage("添加成功");
        }else{
            result.setResultCode(500);
            result.setMessage("添加失败");
        }

        return result;
    }

    @RequestMapping("postUpdateTPAuditInputSetting")
    @ResponseBody
    public Result postUpdateTPAuditInputSetting(ThesisProposalAuditInputSetting TPAISObject){
        Result result = new Result();

        int isUpdate = TPAISService.updateTPAIS(TPAISObject);
        if (isUpdate==1){
            result.setResultCode(200);
            result.setMessage("更新成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更新失败");
        }

        return result;
    }

    @RequestMapping("postDeletedTPAuditInputSetting")
    @ResponseBody
    public Result postDeletedTPAuditInputSetting(Long TPAISId){
        Result result = new Result();

        int isDeleted = TPAISService.deletedTPAIS(TPAISId);
        if (isDeleted==1){
            result.setResultCode(200);
            result.setMessage("删除成功");
        }else{
            result.setResultCode(500);
            result.setMessage("删除失败");
        }

        return result;
    }

}
