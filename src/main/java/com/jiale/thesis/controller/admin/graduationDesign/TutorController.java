package com.jiale.thesis.controller.admin.graduationDesign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.vo.TutorsInformationVO;
import com.jiale.thesis.entity.vo.UserRoleVO;
import com.jiale.thesis.service.GDTeamService;
import com.jiale.thesis.service.SysUserRoleService;
import com.jiale.thesis.service.TeacherInfoService;
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

@Controller
@RequestMapping("/admin/tutor")
public class TutorController {

    @Autowired
    TeacherInfoService teacherInfoService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    GDTeamService gdTeamService;

    /*
    获取毕业设计指导老师列表
    输入数据类型：currentPage,pageSize,roleId,collegeId
    返回数据类型Page<TutorsInformationVO>
     */

    @RequestMapping("/postTutorsPage")
    @ResponseBody
    public Result<Page<TutorsInformationVO>> postTutorsPage(Integer currentPage,Integer pageSize,Integer roleId, Integer collegeId){
        Result<Page<TutorsInformationVO>> result = new Result<>();
        Page<TutorsInformationVO> tutorsPage = teacherInfoService.findTutorsList(currentPage,pageSize,roleId,collegeId);

        if (tutorsPage.getTotal()<=0){
            result.setResultCode(500);
            result.setMessage("没有找到更多信息");
        }else{
            for (int i = 0;i<tutorsPage.getRecords().size();i++){
                List<UserRoleVO> userRoleVOList = sysUserRoleService.findUserRoleByUserId(tutorsPage.getRecords().get(i).getTeacherId());
                tutorsPage.getRecords().get(i).setRoleIdList(userRoleVOList);
            }
            result.setResultCode(200);
            result.setMessage("查询成功");
            result.setData(tutorsPage);
        }
        return result;
    }

    /*
    批量给教师添加毕业设计导师的角色。

     */
    @RequestMapping("/postBatchJoinTutor")
    @ResponseBody
    public Result postBatchJoinTutor(@RequestBody String JSON) throws UnsupportedEncodingException {
        Result result = new Result();
        String deurl = URLDecoder.decode(JSON,"UTF-8");
        JSONObject obj=JSONObject.parseObject(deurl);
        Integer roleId = (Integer) obj.get("roleId");
        String teacherIdArrayStr =String.valueOf(obj.get("teacherIdArray"));
        JSONArray teacherIdJSONArray = JSONArray.parseArray(teacherIdArrayStr);
        BigInteger[] teacherIdArray = new BigInteger[teacherIdJSONArray.size()];
        for(int i = 0;i<teacherIdJSONArray.size();i++){
            JSONObject teacherJSONObject = teacherIdJSONArray.getJSONObject(i);
            teacherIdArray[i] = teacherJSONObject.getBigInteger("id");
        }
        int isUpdate = teacherInfoService.batchAddSysUserRole(teacherIdArray,roleId);
        if (isUpdate!=1){
            result.setResultCode(500);
            result.setMessage("系统错误");
        }
        result.setResultCode(200);
        result.setMessage("success");
        return result;
    }

    /*
    获取非毕业设计导师角色
     */
    @RequestMapping("/postNotTutorPage")
    @ResponseBody
    public Result<Page<TutorsInformationVO>> postTeacherPageByCollegeId(Integer currentPage, Integer pageSize, Integer collegeId){
        Result<Page<TutorsInformationVO>> result = new Result<>();

        Page<TutorsInformationVO> page = new Page<>(currentPage,pageSize);

        Page<TutorsInformationVO> tutorsPage = teacherInfoService.findNotTutorPage(page,collegeId);

        if (tutorsPage.getTotal()<=0){
            result.setResultCode(500);
            result.setMessage("没有查到更多信息");
        }else{
            for (int i = 0;i<tutorsPage.getRecords().size();i++){
                List<UserRoleVO> userRoleVOList = sysUserRoleService.findUserRoleByUserId(tutorsPage.getRecords().get(i).getTeacherId());
                tutorsPage.getRecords().get(i).setRoleIdList(userRoleVOList);
            }
            result.setResultCode(200);
            result.setMessage("查询成功");
            result.setData(tutorsPage);
        }
        return result;
    }

    /*
    给教师添加毕业设计导师角色
     */
    @RequestMapping ("/postJoinTutor")
    @ResponseBody
    public Result postJoinTutor(BigInteger teacherId, Integer roleId){
        Result result = new Result();
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(teacherId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setIsDeleted(0);
        int isJoin = sysUserRoleService.addSysUserRole(sysUserRole);
        if (isJoin!=1){
            result.setResultCode(500);
            result.setMessage("添加失败");
        }else{
            gdTeamService.createTeamByTeacherId(teacherId);
            result.setResultCode(200);
            result.setMessage("添加成功");
        }
        return result;
    }

    /*
    移除毕业设计导师身份
     */
    @RequestMapping ("/postQuitTutor")
    @ResponseBody
    public Result quitTutor(BigInteger teacherId,Integer roleId){
        Result result = new Result();

        int isQuit = sysUserRoleService.deletedSysUserRoleLogic(teacherId,roleId);
        if (isQuit!=1){
            result.setResultCode(500);
            result.setMessage("移出失败");
        }else{
            gdTeamService.deletedTeamByIdLogic(teacherId);
            result.setResultCode(200);
            result.setMessage("移处成功");
        }

        return result;
    }

}
