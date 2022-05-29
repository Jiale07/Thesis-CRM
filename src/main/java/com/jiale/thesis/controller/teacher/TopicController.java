package com.jiale.thesis.controller.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopic;
import com.jiale.thesis.entity.GD.GDTopicProperty;
import com.jiale.thesis.service.GDTopicService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/teacher/topic")
public class TopicController {

    @Autowired
    GDTopicService topicService;

    /*
    teacherWebView
    查询教师创建的题目列表，返回分页结果
     */

    @RequestMapping("/findAllTopicPage")
    @ResponseBody
    public Result<Page<GDTopic>> findAllTopicPage(Integer currentPage, Integer pageSize,BigInteger teacherId){
        Result<Page<GDTopic>> result = new Result<>();
        Page<GDTopic> page = new Page<>(currentPage,pageSize);
        Page<GDTopic> topicPage = topicService.findTopicPageByTeacherId(page,teacherId);
        if (topicPage.getTotal()<=0){
            result.setResultCode(500);
            result.setMessage("没有找到跟多信息");
        }
        result.setResultCode(200);
        result.setData(topicPage);
        result.setMessage("success");
        return result;
    }

    /*
    添加题目
     */

    @RequestMapping("/addTopic")
    @ResponseBody
    public Result addTopic(GDTopic topic, GDTopicProperty topicProperty){
        Result result = new Result();
        int isAdd = topicService.addTopic(topic,topicProperty);
        if (isAdd!=1){
            result.setResultCode(500);
            result.setMessage("添加失败");
        }
        result.setResultCode(200);
        result.setMessage("添加成功");
        return result;
    }

    /*
    更新题目
     */

    @RequestMapping("/updateTopic")
    @ResponseBody
    public Result updateTopic(GDTopic topic){
        Result result = new Result();
        int isUpdate = topicService.updateTopic(topic);
        if (isUpdate!=1){
            result.setResultCode(500);
            result.setMessage("更新失败");
        }
        result.setResultCode(200);
        result.setMessage("更新成功");
        return result;
    }

    /*
    删除题目
     */

    @RequestMapping("/deletedTopic")
    @ResponseBody
    public Result deletedTopic(BigInteger topicId){
        Result result = new Result();
        int isDeleted = topicService.deletedTopic(topicId);
        if (isDeleted!=1){
            result.setResultCode(500);
            result.setMessage("删除失败");
        }
        result.setResultCode(200);
        result.setMessage("删除成功");
        return result;
    }


}
