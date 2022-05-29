package com.jiale.thesis.controller.admin.graduationDesign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.GD.GDTopicCategory;
import com.jiale.thesis.service.GDTopicCategoryService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/topicCategory")
public class TopicCategoryController {

    @Autowired
    GDTopicCategoryService gdTopicCategoryService;
    /*
        分页查询
     */


    @RequestMapping("/postTopicCategoryPage")
    @ResponseBody
    public Result<Page<GDTopicCategory>> postTopicCategoryPage(Integer currentPage,Integer pageSize){
        Result<Page<GDTopicCategory>> result = new Result<>();
        Page<GDTopicCategory> gdTopicCategoryPage = gdTopicCategoryService.findTopicCategoryByPage(currentPage,pageSize);
        if (gdTopicCategoryPage.getTotal()<=0){
            result.setResultCode(500);
            result.setMessage("没有找到跟多信息");
        }
        result.setResultCode(200);
        result.setMessage("success");
        result.setData(gdTopicCategoryPage);
        return result;
    }

    /*
        查询topic category所有数据
     */
    @RequestMapping("/getAllTopicCategoryList")
    @ResponseBody
    public Result<List<GDTopicCategory>> getAllTopicCategoryList(){
        Result<List<GDTopicCategory>> result = new Result<>();
        List<GDTopicCategory> gdTopicCategoryList = gdTopicCategoryService.findAllTopicCategoryList();
        if (null==gdTopicCategoryList){
            result.setMessage("没有找到更多信息");
            result.setResultCode(500);
        }
        result.setResultCode(200);
        result.setMessage("success");
        result.setData(gdTopicCategoryList);
        return result;
    }

    @RequestMapping("/addTopicCategory")
    @ResponseBody
    public Result addTopicCategory(GDTopicCategory topicCategory){
        Result result = new Result();
        int isAdd = gdTopicCategoryService.addTopicCategory(topicCategory);
        if (1!=isAdd){
            result.setMessage("添加失败");
            result.setResultCode(500);
        }
        result.setResultCode(200);
        result.setMessage("添加成功");
        return result;
    }

    @RequestMapping("/updateTopicCategory")
    @ResponseBody
    public Result updateTopicCategory(GDTopicCategory topicCategory){
        Result result = new Result();
        int isUpdate = gdTopicCategoryService.updateTopicCategory(topicCategory);
        if (1!=isUpdate){
            result.setMessage("更新失败");
            result.setResultCode(500);
        }
        result.setResultCode(200);
        result.setMessage("更新成功");
        return result;
    }

    @RequestMapping("/deletedTopicCategory")
    @ResponseBody
    public Result deletedTopicCategory(Long topicCategoryId){
        Result result = new Result();
        int isDelete = gdTopicCategoryService.deletedTopiCategory(topicCategoryId);
        if (1!=isDelete){
            result.setMessage("删除失败");
            result.setResultCode(500);
        }
        result.setResultCode(200);
        result.setMessage("删除成功");
        return result;
    }
}
