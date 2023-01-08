package com.jiale.thesis.controller.admin.announcement;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiale.thesis.entity.announcement.Announcement;
import com.jiale.thesis.entity.announcement.AnnouncementVisible;
import com.jiale.thesis.entity.SysRole;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AnnouncementDetailController {

    @Autowired
    AnnouncementService announcementService;
    @Autowired
    AnnouncementCategoryService acService;
    @Autowired
    AnnouncementVisibleService avService;

    @Autowired
    SysUserRoleService userRoleService;
    @Autowired
    AdminInfoService adminInfoService;
    @Autowired
    TeacherInfoService teacherInfoService;
    @Autowired
    StudentInfoService studentInfoService;

    /*
    AV:AnnouncementVisible
     */
    @RequestMapping("/postAVListByAnnouncementId")
    @ResponseBody
    public Result<List<SysRole>> postAVListByAnnouncementId(Long announcementId){
        Result<List<SysRole>> result = new Result<>();


        List<SysRole> ac =  avService.findSRListByAnnouncementId(announcementId);
        if (null==ac) {
            result.setResultCode(204);
            result.setMessage("没有找到关于" + announcementId + "更多的信息");
        }else {
            result.setResultCode(200);
            result.setMessage("查询成功");
            result.setData(ac);
        }
        return result;
    }

// to public ReadTheAnnouncement
//
//
//    @RequestMapping("/postAnnouncementById")
//    @ResponseBody
//    public Result<Announcement> postAnnouncementById(Long announcementId){
//        Result<Announcement> result = new Result<>();
//
//        Announcement announcement = announcementService.findOndAnnouncement(announcementId);
//        if (null==announcement) {
//            result.setResultCode(204);
//            result.setMessage("没有找到关于" + announcementId + "更多的信息");
//        }else {
//            result.setResultCode(200);
//            result.setMessage("查询成功");
//            result.setData(announcement);
//        }
//        return result;
//    }
//
//    @RequestMapping("/postACById")
//    @ResponseBody
//    public Result<AnnouncementCategory> postACById(Long acId){
//        Result<AnnouncementCategory> result = new Result();
//
//        System.out.println("ACId:"+acId);
//        AnnouncementCategory announcementCategory =  acService.findOneACById(acId);
//        if (null==announcementCategory) {
//            result.setResultCode(204);
//            result.setMessage("没有找到关于" + acId + "更多的信息");
//        }else {
//            result.setResultCode(200);
//            result.setMessage("查询成功");
//            result.setData(announcementCategory);
//        }
//        return result;
//    }
//
//    @RequestMapping("/postCreatorNameByCreatorId")
//    @ResponseBody
//    public Result<Map<String,String>> postCreatorNameByCreatorId(BigInteger creatorId){
//        Result<Map<String,String>> result = new Result<>();
//        Map<String,String> userMap = new HashMap<>();
//        List<UserRoleVO> userRoleList = userRoleService.findUserRoleByUserId(creatorId);
//        for (UserRoleVO userRoleVO : userRoleList) {
//            if (userRoleVO.getRoleId() == 1001) {
//                userMap.put("userName",adminInfoService.findAdminInfoByUserId(creatorId).getAdminName());
//                result.setData(userMap);
//            }
//            if (userRoleVO.getRoleId() == 2001) {
//                userMap.put("userName",teacherInfoService.findOneTeacherInfoById(creatorId).getTeacherName());
//                result.setData(userMap);
//            }
//            if (userRoleVO.getRoleId() == 3001) {
//                userMap.put("userName",studentInfoService.findStudentInfoById(creatorId).getStudentName());
//                result.setData(userMap);
//            }
//        }
//        result.setResultCode(200);
//        result.setMessage("查询成功");
//        return result;
//    }


    @RequestMapping("/postUpdateAnnouncementInfo")
    @ResponseBody
    public Result postUpdateAnnouncement(@RequestBody String JSON) throws UnsupportedEncodingException {
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
        announcement.setId(new Long((String) obj.get("id")));
        announcement.setCategoryId(new Long((String) obj.get("categoryId")));
        announcement.setCreatorId(new BigInteger(String.valueOf(obj.get("creatorId"))));
        announcement.setAnnouncementName((String) obj.get("announcementName"));
        announcement.setAnnouncementContent((String) obj.get("announcementContent"));
        announcement.setIsDeleted(0);

        int isAdd = announcementService.updateAnnouncement(announcement);
        if (isAdd == 1) {
            List<AnnouncementVisible> avList =  avService.findAVListByAnnouncementId(announcement.getId());
            int[] avArray = new int[avList.size()];
            for(int i = 0;i<avList.size();i++){
                avArray[i] = avList.get(i).getRoleId();
            }
            // 添加新角色数组中，新出先的role_id
            int[] newArray = leftRemoveDuplicates(roleListArray,avArray);
            if (newArray.length>0){
                avService.addAnnouncementVisibleByRoleList(announcement.getId(),newArray);
            }
            // 逻辑删除在旧的角色列表中独有的role_id
            int[] oldArray = leftRemoveDuplicates(avArray,roleListArray);
            if (newArray.length>0){
                avService.deletedAnnouncementVisibleLogic(announcement.getId(),oldArray);
            }
            result.setResultCode(200);
            result.setMessage("更新成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更新失败");
        }

        return result;
    }

    private int[] leftRemoveDuplicates(int[] list1,int[] list2){
        List<Integer> newList = new ArrayList<>();
        for (int i = 0;i<list1.length;i++) {
            boolean notRepeat = true;
            for (int k : list2) {
                if (list1[i] == k) {
                    notRepeat = false;
                    break;
                }
            }
            if (notRepeat) {
                newList.add(list1[i]);
            }
            notRepeat = true;
        }
        int[] newArray = new int[newList.size()];
        for(int i = 0;i<newList.size();i++){
            newArray[i] = newList.get(i);
        }
        return newArray;
    }
}
