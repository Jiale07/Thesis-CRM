package com.jiale.thesis.controller.student;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopicProperty;
import com.jiale.thesis.entity.GD.GDTopicSelection;
import com.jiale.thesis.entity.GD.vo.TopicAndTutorVO;
import com.jiale.thesis.entity.GD.vo.TopicDetailVO;
import com.jiale.thesis.entity.vo.SelectionResultVo;
import com.jiale.thesis.service.*;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/studentTwoWaySelection")
public class STwoWaySelectionController {
    /*
    student/twoWaySelection(WebView)
     */

    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    GDTopicService gdTopicService;
    @Autowired
    GDTopicSelectionService gdTopicSelectionService;
    @Autowired
    GDTopicPropertyService gdTopicPropertyService;
    @Autowired
    CollegeService collegeService;
    /*
    通过
    currentPage
    pageSize
    teacherName
    collegeId
    categoryId
    topicName
    获取学生对应学院的指导老师信息及题目信息
     */
    @RequestMapping("/findTopicPage")
    @ResponseBody
    public Result<Page<TopicAndTutorVO>> findTopicPage(Integer currentPage,Integer pageSize,String teacherName,Integer collegeId,BigInteger categoryId,String topicName){
        Result<Page<TopicAndTutorVO>> result = new Result<>();
        Page<TopicAndTutorVO> page = new Page<>(currentPage,pageSize);
        Page<TopicAndTutorVO> topicAndTutorPage = gdTopicService.findTopicAndTutorPage(page,teacherName,collegeId,categoryId,topicName);
        if (topicAndTutorPage.getTotal()<=0){
            result.setResultCode(500);
            result.setMessage("没有找到更多更多信息");
        }else{
            int length = topicAndTutorPage.getRecords().size();
            for(int i = 0;i<length;i++){
                TopicAndTutorVO topicAndTutorVO = topicAndTutorPage.getRecords().get(i);
                topicAndTutorPage.getRecords().get(i).setSelectionCount(gdTopicService.selectionCountByTopicId(topicAndTutorVO.getTopicId()));
            }
            result.setResultCode(200);
            result.setMessage("success");
            result.setData(topicAndTutorPage);
        }
        return result;
    }

//  选中毕业设计题目
    @RequestMapping("/selectedTopic")
    @ResponseBody
    public Result selectedTopic(BigInteger studentId,Long topicId){

        Result result = new Result();
        GDTopicSelection topicSelection = gdTopicService.findTopicSelectionByStudentId(studentId);
        if (null==topicSelection){
            //判断是否已经选中，没有选中则进行插入
            GDTopicProperty gdTopicProperty = gdTopicPropertyService.findGDTopicProperty(BigInteger.valueOf(topicId));
            int selectionCount = gdTopicService.selectionCountByTopicId(BigInteger.valueOf(topicId));
            // 判断当前题目被选中情况，实际被选中数量小于或等于设定可选中数量，则该学生可以选中题目，否则返回提示
            if (gdTopicProperty.getOptionalNumber()>selectionCount){
                int isSelected = gdTopicService.insertSelectionTopic(studentId,topicId);
                if (isSelected==1){
                    result.setResultCode(200);
                    result.setMessage("成功选中");
                }else{
                    result.setResultCode(500);
                    result.setMessage("选择失败，请稍后重新尝试");
                }
            }else{
                result.setResultCode(500);
                result.setMessage("该课题已经被选满，请选择其他题目");
            }
        }else{
            result.setMessage("您已经选有毕业设计课题，不可以多选");
            result.setResultCode(200);
        }
        return result;
    }
//  改选毕业设计题目
    @RequestMapping("/changeSelected")
    @ResponseBody
    public Result changeSelected(BigInteger studentId, Long newTopicId){
        Result result = new Result();
        GDTopicSelection topicSelection = gdTopicService.findTopicSelectionByStudentId(studentId);
        if (null!=topicSelection){
            // 判断当前用户是否已经选中，已有选中题目则将旧的选中记录中is_deleted改为1,若没有则返回提示
            GDTopicProperty gdTopicProperty = gdTopicPropertyService.findGDTopicProperty(BigInteger.valueOf(newTopicId));
            int selectionCount = gdTopicService.selectionCountByTopicId(BigInteger.valueOf(newTopicId));
            if (gdTopicProperty.getOptionalNumber()>selectionCount){
                //逻辑删除上一次选中的题目
                gdTopicService.deleteLastTopicSelectionLogic(studentId,topicSelection.getTopicId());
                // 插入新的学生选中题目记录
                int is_insert = gdTopicService.insertSelectionTopic(studentId,newTopicId);
                if (is_insert!=1){
                    result.setResultCode(500);
                    result.setMessage("更改失败，请稍后重新尝试");
                }else{
                    result.setResultCode(200);
                    result.setMessage("更改成功");
                }
            }else{
                result.setResultCode(500);
                result.setMessage("当前题目已经被选满");
            }
        }else{
            result.setMessage("尚未选中题目，无法改选");
            result.setResultCode(500);
        }
        return result;
    }

//  取消选中毕业设计题目
    @RequestMapping("/cancelSelected")
    @ResponseBody
    public Result cancelSelected(BigInteger studentId,Long topicId){
        Result result = new Result();
        GDTopicSelection topicSelection = gdTopicService.findTopicSelectionByStudentId(studentId);
        if(null!=topicSelection){
            int isUpdate = gdTopicService.deleteLastTopicSelectionLogic(studentId,topicId);
            if (isUpdate!=1){
                result.setResultCode(500);
                result.setMessage("服务出错");
            }else{
                result.setResultCode(200);
                result.setMessage("取消'选中'成功");
            }
        }else{
            result.setResultCode(500);
            result.setMessage("当前尚未选中题目");
        }

        return result;
    }

//    获取学生已选题目信息
    @RequestMapping("/findStudentSelectedByStudentId")
    @ResponseBody
    public Result<GDTopicSelection> findStudentSelectedByStudentId(BigInteger studentId){
        Result<GDTopicSelection> result = new Result<>();
        GDTopicSelection gdTopicSelection = gdTopicSelectionService.findTSSByStudent(studentId);
        result.setResultCode(200);
        result.setData(gdTopicSelection);
        return result;
    }

//    获取学生所属学院id
    @RequestMapping("/findCollegeIdByStudentId")
    @ResponseBody
    public Result<Integer> findCollegeIdByStudentId(BigInteger studentId){
        Result<Integer> result = new Result<>();
        Integer collegeId = studentInfoService.findStudentInfoById(studentId).getCollegeId();
        if (null!=collegeId){
            result.setResultCode(200);
            result.setMessage("查询成功");
            result.setData(collegeId);
        }else{
            result.setResultCode(500);
            result.setMessage("查询失败");
        }
        return result;
    }

//    通过题目id获取该题目已经被选中学生人数
    @RequestMapping("/postSelectionCountByTopicId")
    @ResponseBody
    public Result<Integer> postSelectionCountByTopicId(BigInteger topicId){
        Result<Integer> result = new Result<Integer>();
        Integer count =  gdTopicService.selectionCountByTopicId(topicId);
        result.setMessage("获取成功");
        result.setData(count);
        result.setResultCode(200);
        return result;
    }
    // 学生查看自己选中的毕业题目信息
    @RequestMapping("/postSelectionTopicResult")
    @ResponseBody
    public Result<SelectionResultVo> postSelectionTopicResult(BigInteger studentId){
        Result<SelectionResultVo> result = new Result<SelectionResultVo>();
        SelectionResultVo selectionResultVo =  gdTopicSelectionService.findSelectionResultVo(studentId);
        if(null!=selectionResultVo){
            result.setMessage("获取成功");
            result.setResultCode(200);
            result.setData(selectionResultVo);
        }else{
            result.setMessage("尚未选中毕业设计题目");
            result.setResultCode(204);
        }
        return result;
    }

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

}
