package com.jiale.thesis.controller.teacher;


import com.jiale.thesis.entity.College;
import com.jiale.thesis.entity.GD.GDTopic;
import com.jiale.thesis.entity.GD.GDTopicCategory;
import com.jiale.thesis.entity.GD.GDTopicProperty;
import com.jiale.thesis.entity.GD.vo.TopicDetailVO;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.service.*;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/teacher/topicDetails")
public class TopicDetailsController {
    @Autowired
    GDTopicCategoryService gdTopicCategoryService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    GDTopicService gdTopicService;

    @Autowired
    GDTopicPropertyService gdTopicPropertyService;

    /*
    获取题目详细信息
     */

    @RequestMapping("/postFindTopicDetail")
    @ResponseBody
    public Result<TopicDetailVO> postFindTopicDetail(BigInteger topicId){
        Result<TopicDetailVO> result  = new Result<TopicDetailVO>();
        TopicDetailVO topicDetail = gdTopicService.findTopicDetail(topicId);
        if (null==topicDetail){
            result.setResultCode(500);
            result.setMessage("没有更多信息");
        }
        result.setResultCode(200);
        result.setData(topicDetail);
        result.setMessage("success");
        return result;
    }

    @RequestMapping("/updateTopic")
    @ResponseBody
    public Result updateTopic(Long topicId,String description,Long topicPropertyId,Long categoryId,Integer optionalNumber){
        Result result = new Result();
        int isUpdateTopic=1, isUpdateTopicProperty=1;
        if (null != description){
            GDTopic gdTopic = new GDTopic();
            gdTopic.setId(topicId);
            gdTopic.setDescription(description);
            isUpdateTopic = gdTopicService.updateTopic(gdTopic);
        }
        if (null != categoryId){
            GDTopicProperty gdTopicProperty = new GDTopicProperty();
            gdTopicProperty.setId(topicPropertyId);
            gdTopicProperty.setCategoryId(categoryId);
            isUpdateTopicProperty = gdTopicPropertyService.updateTopicProperty(gdTopicProperty);
        }
        if (null != optionalNumber){
            GDTopicProperty gdTopicProperty = new GDTopicProperty();
            gdTopicProperty.setId(topicPropertyId);
            gdTopicProperty.setOptionalNumber(optionalNumber);
            isUpdateTopicProperty = gdTopicPropertyService.updateTopicProperty(gdTopicProperty);
        }
        if (isUpdateTopic!=1 && isUpdateTopicProperty!=1){
            result.setResultCode(500);
            result.setMessage("更新失败");
        }else{
            if (isUpdateTopicProperty!=1){
                result.setResultCode(500);
                result.setMessage("毕业设计课题类型更新失败");
                return result;
            }
            if (isUpdateTopic!=1 ){
                result.setResultCode(500);
                result.setMessage("毕业设计课题描述更新失败");
                return result;
            }
            result.setResultCode(200);
            result.setMessage("更新成功");
        }
        return result;
    }
}
