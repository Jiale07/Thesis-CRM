package com.jiale.thesis.controller.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTeam;
import com.jiale.thesis.entity.GD.GDTeamMember;
import com.jiale.thesis.entity.GD.GDTopicSelection;
import com.jiale.thesis.entity.GD.vo.TeacherTopicSelectedVO;
import com.jiale.thesis.service.GDTeamMemberService;
import com.jiale.thesis.service.GDTeamService;
import com.jiale.thesis.service.GDTopicSelectionService;
import com.jiale.thesis.service.StudentService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/teacher/TowWaySelection")
public class TTwoWaySelectionController {

    @Autowired
    StudentService studentService;

    @Autowired
    GDTopicSelectionService gdTopicSelectionService;

    @Autowired
    GDTeamService gdTeamService;

    @Autowired
    GDTeamMemberService gdTeamMemberService;

    /*
        查看当前该老师可反选的列表
        接受到的数据包含：教师id、
     */
    @RequestMapping("/postTeacherTopicSelected")
    @ResponseBody
    public Result<Page<TeacherTopicSelectedVO>> postTeacherTopicSelected(Integer currentPage, Integer pageSize, BigInteger teacherId){
        Result<Page<TeacherTopicSelectedVO>> result = new Result<>();
        Page<TeacherTopicSelectedVO> page = new Page<>(currentPage,pageSize);
        Page<TeacherTopicSelectedVO> teacherTopicSelectedVOS = gdTopicSelectionService.findTeacherTopicSelected(page,teacherId);
        if (null==teacherTopicSelectedVOS){
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }else{
            result.setData(teacherTopicSelectedVOS);
            result.setResultCode(200);
            result.setMessage("success");
        }
        return result;
    }

    /*
    将学生添添加到毕业设计小组中
    student_id
    teacher_id
     */

    @RequestMapping("/joinGDTTeam")
    @ResponseBody
    public Result JoinGDTTeam(BigInteger studentId,BigInteger teacherId,Long gdtsId){
        Result result = new Result();
        System.out.println("teacherId:"+teacherId);
        GDTeam gdTeam = gdTeamService.findTeamByTeacherId(teacherId);
        if (null!=gdTeam){
            GDTeamMember gdTeamMember = new GDTeamMember();
            gdTeamMember.setStudentId(studentId);
            gdTeamMember.setTeamId(gdTeam.getId());
            gdTeamMember.setIsDeleted(0);
            int isAdd = gdTeamMemberService.addTeamMember(gdTeamMember);
            if(isAdd==1){
                GDTopicSelection gdTopicSelection = new GDTopicSelection();
                gdTopicSelection.setIsPassed(1);
                gdTopicSelectionService.updateGDTopicSelection(gdTopicSelection,gdtsId);
                result.setMessage("操作成功");
                result.setResultCode(200);
            }else{
                result.setMessage("操作失败");
                result.setResultCode(500);
            }
        }else{
            result.setResultCode(500);
            result.setMessage("服务错误");
        }
        return result;
    }

    /*
    驳回学生申请
    gdts:graduation_design_topic_selection
     */

    @RequestMapping("/dismissal")
    @ResponseBody
    public Result dismissal(Long gdtsId){
        Result result = new Result();
        GDTopicSelection gdTopicSelection = new GDTopicSelection();
        gdTopicSelection.setIsPassed(2);//驳回
        gdTopicSelection.setIsDeleted(1);// 逻辑删除
        int isDeletedLogic =  gdTopicSelectionService.updateGDTopicSelection(gdTopicSelection,gdtsId);
        if(isDeletedLogic == 1){
            result.setResultCode(200);
            result.setMessage("驳回成功");
        }else{
            result.setResultCode(500);
            result.setMessage("驳回失败");
        }

        return result;
    }
}