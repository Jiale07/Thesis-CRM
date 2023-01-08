package com.jiale.thesis.controller.admin.announcement;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.jiale.thesis.entity.announcement.AnnouncementCategory;
import com.jiale.thesis.service.AnnouncementCategoryService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AnnouncementCategoryController {

    @Autowired
    AnnouncementCategoryService acService;


    /*
    分页查询公告种类信息
    AC:AnnouncementCategory
     */
    @RequestMapping("/postFindACPage")
    @ResponseBody
    public Result<Page<AnnouncementCategory>> postFindACPage(Integer currentPage, Integer pageSize){
        Result<Page<AnnouncementCategory>> result = new Result<>();
        Page<AnnouncementCategory> acPage = acService.findAnnouncementCategoryPage(currentPage,pageSize);
        if (acPage.getTotal()<=0){
            result.setResultCode(204);
            result.setMessage("没有找到更多数据");
        }else{
            result.setResultCode(200);
            result.setMessage("查询成功");
            result.setData(acPage);
        }
        return result;
    }

    /*
    添加公告分类
     */
    @RequestMapping("/postAddAC")
    @ResponseBody
    public Result postAddAC(AnnouncementCategory announcementCategory){
        System.out.println(announcementCategory.toString());
        Result result = new Result();
        announcementCategory.setIsDeleted(0);
        int isAdd = acService.addAnnouncementCategory(announcementCategory);
        if (isAdd!=1){
            result.setResultCode(500);
            result.setMessage("添加失败，重新确认数据后重试");
        }else{
            result.setResultCode(200);
            result.setMessage("添加成功");
        }
        return result;
    }

    /*
    更新公告分类
     */
    @RequestMapping("/postUpdateAC")
    @ResponseBody
    public Result postUpdateAC (AnnouncementCategory announcementCategory){
        Result result = new Result();
        announcementCategory.setIsDeleted(0);
        int isUpdate = acService.updateAnnouncementCategory(announcementCategory);
        if (isUpdate!=1){
            result.setResultCode(500);
            result.setMessage("更新失败，重新确认数据后重试");
        }else{
            result.setResultCode(200);
            result.setMessage("更新成功");
        }
        return result;
    }

    /*
    逻辑删除公告分类
     */
    @RequestMapping("/postDeletedACLogic")
    @ResponseBody
    public Result postDeletedACLogic (Long acId){
        Result result = new Result();

        int isDeletedLogic = acService.deletedAnnouncementCategoryLogic(acId);
        if (isDeletedLogic!=1){
            result.setResultCode(500);
            result.setMessage("删除失败，刷新页面重试");
        }else{
            result.setResultCode(200);
            result.setMessage("删除成功");
        }
        return result;
    }

    @RequestMapping("/getACList")
    @ResponseBody
    public Result<List<AnnouncementCategory>> getACList(){
        Result<List<AnnouncementCategory>> result = new Result<>();
        List<AnnouncementCategory> announcementCategory = acService.findAllAC();
        result.setResultCode(200);
        result.setData(announcementCategory);
        return result;
    }
}
