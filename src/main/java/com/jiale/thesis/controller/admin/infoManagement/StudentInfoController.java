package com.jiale.thesis.controller.admin.infoManagement;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.StudentInfo;
import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.UserAccount;
import com.jiale.thesis.entity.vo.StudentInfoResVO;
import com.jiale.thesis.entity.vo.StudentInformationVO;
import com.jiale.thesis.service.StudentInfoService;
import com.jiale.thesis.service.StudentService;
import com.jiale.thesis.service.SysUserRoleService;
import com.jiale.thesis.service.UserAccountService;
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
public class StudentInfoController {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Autowired
    StudentInfoService studentInfoService;

    @Autowired
    StudentService studentService;

    @RequestMapping("/getStudentInfo")
    @ResponseBody
    public Result<Page<StudentInfoResVO>> getStudentInfo(Integer currentPage, Integer pageSize, Integer collegeId, Integer majorId,Integer classId){
        Result<Page<StudentInfoResVO>> result = new Result<Page<StudentInfoResVO>>();
        Page<StudentInfoResVO> page = new Page<>(currentPage,pageSize);
        Page<StudentInfoResVO> newPage = studentInfoService.findStudentInfoByPage(page,collegeId,majorId,classId);
        if (0 >= newPage.getTotal()){
            result.setResultCode(500);
            result.setMessage("没有更多数据了");
            return result;
        }
        result.setData(newPage);
        result.setResultCode(200);
        result.setMessage("查询成功");
        return result;
    }

    @RequestMapping("/addStudent")
    @ResponseBody
    public Result<Object> addStudent(StudentInformationVO studentInformationVO){
        Result<Object> result = new Result<>();
        BigInteger id = studentInformationVO.getId();
        StudentInfo studentInfo = studentInfoService.findOneStudentInfoById(id);
        UserAccount userAccount = userAccountService.findOneUserAccountById(id);
        SysUserRole sysUserRole = sysUserRoleService.findSysUserRoleByUserId(id);
        if (null==studentInfo && null==userAccount && null==sysUserRole){
            studentInfoService.addStudentInfo(setStudentInfoObject(studentInformationVO));
            userAccountService.addUserAccount(setUserAccountObject(studentInformationVO));
            SysUserRole newSysUserRole = new SysUserRole();
            newSysUserRole.setUserId(studentInformationVO.getId());
            newSysUserRole.setRoleId(studentInformationVO.getRoleId());
            newSysUserRole.setIsDeleted(0);
            sysUserRoleService.addSysUserRole(newSysUserRole);
            result.setMessage("添加成功");
            result.setResultCode(200);
        }else{
            result.setMessage("添加失败，请重新确认信息后重试");
            result.setResultCode(500);
        }
        return result;
    }

    @RequestMapping("/getNewStudentId")
    @ResponseBody
    public Result<Object> getNewStudentId(Integer amount){
        Result<Object> result = new Result<>();
        if (null == amount){
            result.setData(JSON.toJSONString(getOneNewStudent()));
        }else{
            result.setData(JSON.toJSONString(getListNewStudent(amount)));
        }
        result.setResultCode(200);
        result.setMessage("获取成功");

        return result;
    }
    // 获取一个根据数据库id获得的学号id
    private Map<String,BigInteger> getOneNewStudent(){
        BigInteger StudentId = studentInfoService.findOneStudentId(0,1);
        if (null == StudentId){
            StudentId = new BigInteger("202111100001");
        }
        Map<String,BigInteger> newStudentIdMap = new HashMap<>();
        newStudentIdMap.put("key1",StudentId.add(BigInteger.valueOf(1)));
        return newStudentIdMap;
    }
    // 获取指定数量、根据数据库id获得的学号id
    private Map<String,BigInteger> getListNewStudent(Integer amount){
        BigInteger StudentId = studentInfoService.findOneStudentId(0,1);
        if (null == StudentId){
            StudentId = new BigInteger("202111100001");
        }
        Map<String,BigInteger> newStudentIdMap = new HashMap<>();
        int i;
        for(i = 0; i<amount; i++) {
            String key = "key"+i;
            newStudentIdMap.put(key,StudentId.add(BigInteger.valueOf(i)));
        }
        return newStudentIdMap;
    }

    @RequestMapping("/deletedStudentById")
    @ResponseBody
    private Result deletedStudentById(BigInteger studentId){
        Result result = new Result();
        if(null != studentId){
            if (studentService.deletedStudentById(studentId)!=1){
                result.setMessage("删除失败");
                result.setResultCode(500);
            }else{
                result.setMessage("删除成功");
                result.setResultCode(200);
            }
        }else{
            result.setMessage("未能获取有效的删除对象信息");
            result.setResultCode(500);
        }
        return result;
    }

    // 更新Student数据
    @RequestMapping("/updateStudent")
    @ResponseBody
    public Result updateStudent(StudentInformationVO studentInformationVO){
        Result result = new Result();
        BigInteger id = studentInformationVO.getId();
        StudentInfo studentInfo = studentInfoService.findOneStudentInfoById(id);
        UserAccount userAccount = userAccountService.findOneUserAccountById(id);
        if (null!=studentInfo && null!=userAccount){
            studentInfoService.updateStudentInfo(setStudentInfoObject(studentInformationVO));
            userAccountService.updateUserAccount(setUserAccountObject(studentInformationVO));
            result.setMessage("更新成功");
            result.setResultCode(200);
        }else{
            result.setMessage("更新失败，请重新确认信息后重试");
            result.setResultCode(500);
        }
        return result;
    }

    public StudentInfo setStudentInfoObject(StudentInformationVO studentInformationVO){
        BigInteger studentId = studentInformationVO.getId();
        int isDeleted = 0;
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId(studentId);
        studentInfo.setStudentName(studentInformationVO.getStudentName());
        studentInfo.setCollegeId(studentInformationVO.getCollegeId());
        studentInfo.setMajorId(studentInformationVO.getMajorId());
        studentInfo.setClassId(studentInformationVO.getClassId());
        studentInfo.setIsDeleted(isDeleted);
        return studentInfo;
    }

    public UserAccount setUserAccountObject(StudentInformationVO studentInformationVO){
        UserAccount userAccount = new UserAccount();
        userAccount.setId(studentInformationVO.getId());
        userAccount.setPassword(studentInformationVO.getPassword());
        userAccount.setIsDeleted(0);
        return userAccount;
    }
}
