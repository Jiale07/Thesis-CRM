package com.jiale.thesis.controller.admin.announcement;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.announcement.Announcement;
import com.jiale.thesis.service.AnnouncementService;
import com.jiale.thesis.service.AnnouncementVisibleService;
import com.jiale.thesis.service.SysRoleService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;

@RequestMapping("/admin")
@Controller
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    AnnouncementVisibleService announcementVisibleService;

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping("/postAnnouncementPage")
    @ResponseBody
    public Result<Page<Announcement>> postAnnouncementPage(Integer currentPage,Integer pageSize){
        Result<Page<Announcement>> result = new Result<>();
        Page<Announcement> announcementPage = announcementService.findAnnouncementPage(currentPage,pageSize);
        if (announcementPage.getTotal()<=0){
            result.setResultCode(204);
            result.setMessage("没有找到跟多数据");
        }else{
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(announcementPage);
        }
        return result;
    }

    @RequestMapping("/postAddAnnouncement")
    @ResponseBody
    public Result postAddAnnouncement(@RequestBody String JSON) throws UnsupportedEncodingException {
        Result result = new Result();

        String deUrl = URLDecoder.decode(JSON,"UTF-8");
        JSONObject obj=JSONObject.parseObject(deUrl);

        String roleListJSONArrayStr = String.valueOf(obj.get("roleList"));
        JSONArray roleListJSONArray = JSONArray.parseArray(roleListJSONArrayStr);
        int[] roleListArray = new int[roleListJSONArray.size()];
        for(int i = 0;i<roleListJSONArray.size();i++){
            JSONObject object = roleListJSONArray.getJSONObject(i);
            roleListArray[i] = object.getInteger("roleId");
        }
        Announcement announcement = new Announcement();
        announcement.setCategoryId(Long.valueOf(String.valueOf(obj.get("categoryId"))));
        announcement.setCreatorId(new BigInteger(String.valueOf(obj.get("creatorId"))));
        announcement.setAnnouncementName((String) obj.get("announcementName"));
        announcement.setAnnouncementContent((String) obj.get("announcementContent"));
        announcement.setIsDeleted(0);
        int isAdd = announcementService.addAnnouncement(announcement);
        if (isAdd==1){
            announcementVisibleService.addAnnouncementVisibleByRoleList(announcement.getId(),roleListArray);
            result.setResultCode(200);
            result.setMessage("添加成功");
        }else{
            result.setResultCode(500);
            result.setMessage("请重新确认数据后重试");
        }
        return result;
    }

    @RequestMapping("/postUpdateAnnouncement")
    @ResponseBody
    public Result postUpdateAnnouncement(Announcement announcement){
        Result result = new Result();

        int isUpdate = announcementService.updateAnnouncement(announcement);
        if (isUpdate!=1){
            result.setResultCode(500);
            result.setMessage("请重新确认数据后，再提交");
        }else{
            result.setResultCode(200);
            result.setMessage("更新成功");
        }
        return result;
    }

    @RequestMapping("/postDeletedAnnouncementLogic")
    @ResponseBody
    public Result postDeletedAnnouncementLogic(Long announcementId){
        Result result = new Result();

        int isDeleted = announcementService.deletedAnnouncementLogic(announcementId);
        if (isDeleted!=1){
            result.setResultCode(500);
            result.setMessage("刷新页面后重新尝试");
        }else{
            result.setResultCode(200);
            result.setMessage("删除成功");
        }
        return result;
    }
}
