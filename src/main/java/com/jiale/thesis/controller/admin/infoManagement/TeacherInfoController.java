package com.jiale.thesis.controller.admin.infoManagement;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.entity.TeacherResume;
import com.jiale.thesis.entity.UserAccount;
import com.jiale.thesis.entity.vo.TeacherInfoResVO;
import com.jiale.thesis.entity.vo.TeacherInformationVO;
import com.jiale.thesis.service.*;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/admin/management")
public class TeacherInfoController {
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Autowired
    TeacherInfoService teacherInfoService;
    @Autowired
    TeacherResumeService teacherResumeService;


    @Autowired
    TeacherService teacherService;

    @RequestMapping("/getTeacherPage")
    @ResponseBody
    public Result<Page<TeacherInfoResVO>> getTeacherPage(Integer currentPage, Integer pageSize, Integer collegeId){
        Result<Page<TeacherInfoResVO>> result = new Result<>();
        Page<TeacherInfoResVO> page = new Page<>(currentPage,pageSize);
        Page<TeacherInfoResVO> newPage = teacherInfoService.findTeacherByPage(page,collegeId);
        if (0 >= newPage.getTotal()){
            result.setResultCode(500);
            result.setMessage("没有找到更多信息了");
            return result;
        }
        result.setData(newPage);
        result.setResultCode(200);
        result.setMessage("查询成功");
        return result;
    }

    @RequestMapping("/addTeacher")
    @ResponseBody
    public Result addTeacher(TeacherInformationVO teacherInformationVO){
        Result result = new Result();
        System.out.println(teacherInformationVO.toString());
        BigInteger teacherId = teacherInformationVO.getId();
        TeacherInfo teacherInfo = teacherInfoService.findOneTeacherInfoById(teacherId);
        UserAccount userAccount = userAccountService.findOneUserAccountById(teacherId);
        SysUserRole sysUserRole = sysUserRoleService.findSysUserRoleByUserId(teacherId);
        sysUserRoleService.findSysUserRoleByUserId(teacherId);
        if (!(null!=teacherInfo && null!=userAccount && null!=sysUserRole)){
            int isDeleted = 0;
            teacherInfoService.addTeacherInfo(setTeacherInfoObject(teacherInformationVO));
            userAccountService.addUserAccount(setUserAccountObject(teacherInformationVO));
            SysUserRole newSysUserRole = new SysUserRole();
            newSysUserRole.setUserId(teacherId);
            newSysUserRole.setRoleId(teacherInformationVO.getRoleId());
            newSysUserRole.setIsDeleted(isDeleted);
            sysUserRoleService.addSysUserRole(newSysUserRole);
            TeacherResume teacherResume = new TeacherResume();
            teacherResume.setTeacherId(teacherId);
            teacherResume.setIsDeleted(isDeleted);
            teacherResumeService.createTeacherResume(teacherResume);
            result.setResultCode(200);
            result.setMessage("添加成功");
        }else{
            result.setResultCode(500);
            result.setMessage("添加失败，请确认后重试");
        }
        return result;
    }


    @RequestMapping("/updateTeacher")
    @ResponseBody
    public Result updateTeacher(TeacherInformationVO teacherInformationVO){
        Result result = new Result();

        BigInteger teacherId = teacherInformationVO.getId();
        TeacherInfo teacherInfo = teacherInfoService.findOneTeacherInfoById(teacherId);
        UserAccount userAccount = userAccountService.findOneUserAccountById(teacherId);
        if (null!=teacherInfo && null!=userAccount){
            teacherInfoService.updateTeacherInfoByTIObject(setTeacherInfoObject(teacherInformationVO));
            userAccountService.updateUserAccount(setUserAccountObject(teacherInformationVO));
            SysUserRole newSysUserRole = new SysUserRole();
            newSysUserRole.setUserId(teacherId);
            result.setResultCode(200);
            result.setMessage("更新成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更新失败，请重新确认信息后重试");
        }

        return result;
    }

    @RequestMapping("/deletedTeacherById")
    @ResponseBody
    public Result deletedTeacherById(BigInteger teacherId){
        Result result = new Result();

        TeacherInfo teacherInfo = teacherInfoService.findOneTeacherInfoById(teacherId);
        UserAccount userAccount = userAccountService.findOneUserAccountById(teacherId);
        if (null != teacherInfo && null!= userAccount){
            teacherInfoService.deletedTeacherInfoLogicById(teacherId);
            userAccountService.deletedUserAccountLogic(teacherId);
            sysUserRoleService.deletedSysUserRoleLogic(teacherId);
            teacherResumeService.deletedTeacherResume(teacherId);
            result.setResultCode(200);
            result.setMessage("删除成功");
        }else{
            result.setResultCode(500);
            result.setMessage("删除失败");
        }
        return result;
    }

//    获取新的id
    @RequestMapping("/getNewTeacherId")
    @ResponseBody
    public Result<String> getNewTeacherId(Integer amount){
        Result<String> result = new Result<String>();
        if (null == amount){
            result.setData(JSON.toJSONString(getOneNewTeacherId()));
        }else{
            result.setData(JSON.toJSONString(getListNewTeacherId(amount)));
        }
        result.setResultCode(200);
        result.setMessage("获取成功");
        return result;
    }

    private Map<String,BigInteger> getOneNewTeacherId(){
        BigInteger teacherId = teacherInfoService.findOneTeacherId(0,1);
        if(null == teacherId){
            teacherId = new BigInteger("110101001");
        }
        Map<String,BigInteger> newTeacherIdMap = new HashMap<>();
        newTeacherIdMap.put("key1",teacherId.add(BigInteger.valueOf(1)));
        return newTeacherIdMap;
    }

    private Map<String,BigInteger> getListNewTeacherId(Integer amount){
        BigInteger teacherId = teacherInfoService.findOneTeacherId(0,1);
        if(null == teacherId){
            teacherId = new BigInteger("110101001");
        }
        Map<String,BigInteger> newTeacherIdMap = new HashMap<>();
        int i;
        for (i=0;i<amount;i++){
            String key = "key"+i;
            newTeacherIdMap.put(key,teacherId.add(BigInteger.valueOf(i)));
        }
        newTeacherIdMap.put("key1",teacherId.add(BigInteger.valueOf(1)));
        return newTeacherIdMap;
    }



    private TeacherInfo setTeacherInfoObject(TeacherInformationVO teacherInformationVO){
        BigInteger studentId = teacherInformationVO.getId();
        int isDeleted = 0;
        TeacherInfo teacherInfo  = new TeacherInfo();
        teacherInfo.setId(studentId);
        teacherInfo.setTeacherName(teacherInformationVO.getTeacherName());
        teacherInfo.setCollegeId(teacherInformationVO.getCollegeId());
        teacherInfo.setIsDeleted(isDeleted);
        return teacherInfo;
    }

    private UserAccount setUserAccountObject(TeacherInformationVO teacherInformationVO){
        UserAccount userAccount = new UserAccount();
        userAccount.setId(teacherInformationVO.getId());
        userAccount.setPassword(teacherInformationVO.getPassword());
        userAccount.setIsDeleted(0);
        return userAccount;
    }



}
