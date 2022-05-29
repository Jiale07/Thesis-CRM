package com.jiale.thesis.controller.publicController;

import com.jiale.thesis.entity.GD.GDTopicCategory;
import com.jiale.thesis.service.GDTopicCategoryService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/public")
public class GDTopicCategoryController {

    @Autowired
    GDTopicCategoryService gdTopicCategoryService;

    @RequestMapping("getTopicCategoryList")
    @ResponseBody
    public Result<List<GDTopicCategory>>  getTopicCategoryList(){
        Result<List<GDTopicCategory>> result = new Result<>();
        List<GDTopicCategory> gdTopicCategoryList =  gdTopicCategoryService.findAllTopicCategoryList();
        if(null!=gdTopicCategoryList){
            result.setData(gdTopicCategoryList);
            result.setMessage("查询成功");
            result.setResultCode(200);
        }else{
            result.setMessage("查询失败");
            result.setResultCode(500);
        }
        return result;
    }
}
