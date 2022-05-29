package com.jiale.thesis.controller.admin.infoManagement;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.College;
import com.jiale.thesis.service.CollegeService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/management")
public class CollegeInfoController {

    @Autowired
    CollegeService collegeService;

    @RequestMapping("/addCollegeInfo")
    @ResponseBody
    public Result addCollegeInfo(College college){
        Result result = new Result();
        college.setCreateTime(new Date());
        if(collegeService.addCollegeInfo(college)==1){
            result.setResultCode(200);
            result.setMessage("添加成功");
        }else{
            result.setResultCode(500);
            result.setMessage("添加失败");
        }
        return result;
    }

    @RequestMapping("/updateCollegeName")
    @ResponseBody
    public Result updateCollegeName(College college){
        Result result = new Result();
        college.setUpdateTime(new Date());
        if(collegeService.updateCollegeName(college)==1){
            result.setResultCode(200);
            result.setMessage("更新成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更新失败");
        }
        return result;
    }

    @RequestMapping("/updateCollegeId")
    @ResponseBody
    public Result updateCollegeId(int oldId,int newId){
        Result result = new Result();
        if(collegeService.updateCollegeId(oldId,newId)==1){
            result.setResultCode(200);
            result.setMessage("更新成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更新失败");
        }
        return result;
    }

    @RequestMapping("/deleteCollegeInfoById")
    @ResponseBody
    public Result deleteCollegeInfoById(Integer collegeId){
        Result result = new Result();
        if(collegeService.deleteCollegeById(collegeId)==1){
            result.setResultCode(200);
            result.setMessage("删除成功");
        }else{
            result.setResultCode(500);
            result.setMessage("删除失败");
        }
        return result;
    }

    @RequestMapping("/logicToDeleteCollege")
    @ResponseBody
    public Result logicToDeleteCollege(Integer collegeId){
        Result result = new Result();
        if(collegeService.logicToDeleteDeleteCollege(collegeId)==1){
            result.setResultCode(200);
            result.setMessage("逻辑删除成功");
        }else{
            result.setResultCode(500);
            result.setMessage("逻辑删除失败");
        }
        return result;
    }

    @RequestMapping("/postCollegeInfoPage")
    @ResponseBody
    public Result<Page<College>> getCollegeInfoPage(Integer currentPage,Integer pageSize){
        Result<Page<College>> result = new Result<>();
        System.out.println(currentPage);
        System.out.println(pageSize);
        Page<College> page = collegeService.findCollegePage(currentPage,pageSize);
        if(page.getTotal()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(page);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }

    @RequestMapping("/getCollegeInfoList")
    @ResponseBody
    public Result<List<College>> getCollegeInfoList(){
        Result<List<College>> result = new Result<>();
        List<College> collegeList = collegeService.findCollegeList();
        if(collegeList!=null){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(collegeList);
        }else{
            result.setResultCode(500);
            result.setMessage("获取失败");
        }
        return result;
    }

    @RequestMapping("/getCollegeNewId")
    @ResponseBody
    public Result<Integer> getCollegeNewId(){
        Result<Integer> result = new Result<Integer>();

        Integer collegeIdNew = collegeService.findOneCollegeIdDesc();
        if (collegeIdNew==null){
            collegeIdNew = 101;
            result.setData(collegeIdNew);
            result.setMessage("默认起始值");
        }
        result.setData(collegeIdNew+1);
        result.setResultCode(200);
        result.setMessage("新的学院编号");
        return result;
    }
}
