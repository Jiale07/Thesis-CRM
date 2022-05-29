package com.jiale.thesis.controller.publicController;

import com.jiale.thesis.entity.TeacherResume;
import com.jiale.thesis.entity.vo.TeacherResumeVO;
import com.jiale.thesis.service.TeacherResumeService;
import com.jiale.thesis.util.Result;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigInteger;

@Controller
@RequestMapping("/public/teacherResume")
public class TeacherResumeController {
    @Autowired
    TeacherResumeService teacherResumeService;

    @RequestMapping("/postTeacherResume")
    @ResponseBody
    public Result<TeacherResumeVO> postTeacherResume(BigInteger teacherId){
        System.out.println(teacherId);
        Result<TeacherResumeVO> result = new Result<>();
        TeacherResumeVO teacherResumeVO = teacherResumeService.findTeacherResumeById(teacherId);
        if (null!=teacherResumeVO){
            result.setResultCode(200);
            result.setMessage("查询成功");
            result.setData(teacherResumeVO);
        }else{
            result.setResultCode(500);
            result.setMessage("查询失败");
        }
        return result;
    }

    /*
    教师更新个人简介
     */

    @RequestMapping("/postUpdateTeacherResume")
    @ResponseBody
    public Result postUpdateTeacherResume(TeacherResume teacherResume){
        Result result = new Result();
        System.out.println(teacherResume.toString());
        int isUpdate = teacherResumeService.updateTeacherResume(teacherResume);
        if (isUpdate!=1){
            result.setResultCode(500);
            result.setMessage("更新失败");
        }else{
            result.setResultCode(200);
            result.setMessage("更新成功");
        }

        return result;
    }
}
