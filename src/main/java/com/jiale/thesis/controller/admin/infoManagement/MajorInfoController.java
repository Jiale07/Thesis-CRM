package com.jiale.thesis.controller.admin.infoManagement;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.Major;
import com.jiale.thesis.entity.vo.MajorInfoResVO;
import com.jiale.thesis.entity.vo.UpdateMajorVO;
import com.jiale.thesis.service.MajorService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/management")
public class MajorInfoController {

    @Autowired
    MajorService majorService;

    @RequestMapping("/addMajor")
    @ResponseBody
    public Result addMajor(Major major){
        Result result = new Result();

        if (majorService.addMajorInfo(major)!=1){
            result.setResultCode(500);
            result.setMessage("添加失败");
            return result;
        }
        result.setResultCode(200);
        result.setMessage("添加成功");

        return result;
    }

    @RequestMapping("/getMajorListByCollege")
    @ResponseBody
    public Result<List<Major>> getMajorListByCollege(Integer collegeId){
        Result<List<Major>> result = new Result<>();
        List<Major> majorList = null;
        if (null == collegeId || collegeId.equals("")){
            majorList = majorService.findAllMajorList();
        }else{
            majorList = majorService.findMajorListByCollegeId(collegeId);
        }

        if (majorList == null) {
            result.setResultCode(500);
            result.setMessage("没有找到更多");
            return result;
        }
        result.setData(majorList);
        result.setResultCode(200);
        result.setMessage("获取成功");
        return result;
    }

    @RequestMapping("/getMajorListByPage")
    @ResponseBody
    public Result<IPage<MajorInfoResVO>> getMajorListByPage(Integer currentPage, Integer pageSize, Integer collegeId){
        Result<IPage<MajorInfoResVO>> result = new Result<IPage<MajorInfoResVO>>();
        Page<MajorInfoResVO> majorInfoResVOPage =  majorService.findMajorInfoResVOPageByCollegeId(currentPage,pageSize,collegeId);
        if (majorInfoResVOPage.getTotal()<=0){
            result.setResultCode(500);
            result.setMessage("没有更多信息");
        }else{
            result.setMessage("success");
            result.setResultCode(200);
            result.setData(majorInfoResVOPage);
        }
        return result;
    }

    @RequestMapping("/updateMajor")
    @ResponseBody
    public Result updateMajorById(UpdateMajorVO updateMajorVO){
        Result result = new Result();

        if (majorService.updateMajorInfo(updateMajorVO)!=1){
            result.setResultCode(500);
            result.setMessage("更新失败");
        }else{
            result.setResultCode(200);
            result.setMessage("更新成功");
        }
        return result;
    }

    @RequestMapping("/deleteMajorById")
    @ResponseBody
    public Result deleteMajorById(Integer majorId){
        Result result = new Result();
        if (majorService.deleteMajorById(majorId)!=1){
            result.setResultCode(500);
            result.setMessage("删除失败");
            return result;
        }
        result.setResultCode(200);
        result.setMessage("删除成功");
        return result;
    }

    @RequestMapping("/addMajorByList")
    @ResponseBody
    public Result<List<Major>> addMajorByList(@RequestBody String params){
        Result<List<Major>> result = new Result<List<Major>>();
        JSONObject jo = JSONObject.parseObject(params);
        List<Major> majorList = JSONObject.parseArray(jo.getJSONArray("testMajorList").toJSONString(), Major.class);
        List<Major> errorMajorList = null;
        Major selectMajor = null;
        for (Major major:majorList){
            selectMajor = majorService.findMajorInfoById(major.getId());
            if (null!=selectMajor){
                errorMajorList.add(selectMajor);
            }
        }

        if (errorMajorList!=null){
            result.setData(errorMajorList);
            result.setResultCode(500);
            result.setMessage("存在错误的“专业”信息");
            return result;
        }
        majorService.addMajorByList(majorList);
        result.setMessage("success");
        result.setResultCode(200);
        return result;
    }
}
