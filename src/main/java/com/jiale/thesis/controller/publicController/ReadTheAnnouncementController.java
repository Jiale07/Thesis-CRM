package com.jiale.thesis.controller.publicController;

import com.jiale.thesis.entity.announcement.Announcement;
import com.jiale.thesis.entity.announcement.AnnouncementCategory;
import com.jiale.thesis.entity.vo.UserRoleVO;
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
@RequestMapping("/public")
public class ReadTheAnnouncementController {

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    AnnouncementCategoryService acService;
    @Autowired
    SysUserRoleService userRoleService;
    @Autowired
    AdminInfoService adminInfoService;
    @Autowired
    TeacherInfoService teacherInfoService;
    @Autowired
    StudentInfoService studentInfoService;

    @RequestMapping("/postAnnouncementList")
    @ResponseBody
    public Result<List<Announcement>> postAnnouncementList(Integer number, BigInteger userId){
        Result<List<Announcement>> result = new Result<>();
        // 1.查询通过用户id获取用户的所有有效的角色id，与公告的可查看角色id进行对比，
        // 若用户的角色id中包含可查看角色id，则将该公告的信息作为返回结果之一
        // 2.通过number限定获取的公告数量，
        List<UserRoleVO> sysUserRoles =  sysUserRoleService.findUserRoleByUserId(userId);
        int[] roleArray = new int[sysUserRoles.size()];
        for(int i = 0;i<sysUserRoles.size();i++){
            roleArray[i] =  sysUserRoles.get(i).getRoleId();
        }
        List<Announcement> announcementList =  announcementService.findAnnouncementByRoleArray(roleArray,number);
        if (announcementList.size()<=0){
            result.setMessage("没有更多公告");
            result.setResultCode(204);
        }else{
            result.setResultCode(200);
            result.setData(announcementList);
            result.setMessage("查询成功");
        }
        return result;
    }

    @RequestMapping("/postAnnouncementById")
    @ResponseBody
    public Result<Announcement> postAnnouncementById(Long announcementId){
        Result<Announcement> result = new Result<>();

        Announcement announcement = announcementService.findOndAnnouncement(announcementId);
        if (null==announcement) {
            result.setResultCode(204);
            result.setMessage("没有找到关于" + announcementId + "更多的信息");
        }else {
            result.setResultCode(200);
            result.setMessage("查询成功");
            result.setData(announcement);
        }
        return result;
    }

    @RequestMapping("/postACById")
    @ResponseBody
    public Result<AnnouncementCategory> postACById(Long acId){
        Result<AnnouncementCategory> result = new Result();

        System.out.println("ACId:"+acId);
        AnnouncementCategory announcementCategory =  acService.findOneACById(acId);
        if (null==announcementCategory) {
            result.setResultCode(204);
            result.setMessage("没有找到关于" + acId + "更多的信息");
        }else {
            result.setResultCode(200);
            result.setMessage("查询成功");
            result.setData(announcementCategory);
        }
        return result;
    }

    @RequestMapping("/postCreatorNameByCreatorId")
    @ResponseBody
    public Result<Map<String,String>> postCreatorNameByCreatorId(BigInteger creatorId){
        Result<Map<String,String>> result = new Result<>();
        Map<String,String> userMap = new HashMap<>();
        List<UserRoleVO> userRoleList = userRoleService.findUserRoleByUserId(creatorId);
        for (UserRoleVO userRoleVO : userRoleList) {
            if (userRoleVO.getRoleId() == 1001) {
                userMap.put("userName",adminInfoService.findAdminInfoByUserId(creatorId).getAdminName());
                result.setData(userMap);
            }
            if (userRoleVO.getRoleId() == 2001) {
                userMap.put("userName",teacherInfoService.findOneTeacherInfoById(creatorId).getTeacherName());
                result.setData(userMap);
            }
            if (userRoleVO.getRoleId() == 3001) {
                userMap.put("userName",studentInfoService.findStudentInfoById(creatorId).getStudentName());
                result.setData(userMap);
            }
        }
        result.setResultCode(200);
        result.setMessage("查询成功");
        return result;
    }
}
