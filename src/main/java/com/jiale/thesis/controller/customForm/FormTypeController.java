package com.jiale.thesis.controller.customForm;

import com.jiale.thesis.entity.customForm.FormTypeEntity;
import com.jiale.thesis.service.customForm.FormTypeService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customForm")
public class FormTypeController {
    @Autowired
    FormTypeService formTypeService;

    @RequestMapping("/formType")
    @ResponseBody
    public Result<List<FormTypeEntity>> formType(Long id) {
        Result<List<FormTypeEntity>> result = new Result<>();
        if (id != null) {
            FormTypeEntity obj = formTypeService.formType(id);
            if (obj != null) {
                List<FormTypeEntity> list = new ArrayList<>();
                list.add(obj);
                result.setData(list);
                result.setResultCode(200);
            } else {
                result.setMessage("没有找到该数据");
                result.setResultCode(404);
            }
        } else {
            List<FormTypeEntity> list = formTypeService.formType();
            result.setData(list);
            result.setResultCode(200);
        }
        return result;
    }

    @RequestMapping("/addFormType")
    @ResponseBody
    public Result createFormType(FormTypeEntity formTypeEntity) {
        Result result = new Result();
        formTypeEntity.setIsDeleted(0);
        if(formTypeEntity.getName() == null || formTypeEntity.getUpperLimit() == null) {
            result.setMessage("error");
            result.setResultCode(500);
            return result;
        }
        int isSuccess = formTypeService.createFormType(formTypeEntity);

        if (isSuccess == 1) {
            result.setMessage("success");
            result.setResultCode(200);
        } else {
            result.setMessage("error");
            result.setResultCode(500);
        }
        return result;
    }

    @RequestMapping("/updateFormType")
    @ResponseBody
    public Result updateFormType(FormTypeEntity formTypeEntity) {
        Result result = new Result();
        if (formTypeEntity.getId() == null) {
            result.setMessage("参数错误");
            result.setResultCode(500);
            return result;
        }
        if (formTypeEntity.getUpperLimit() == null && formTypeEntity.getName() == null) {
            result.setMessage("无更新内容");
            result.setResultCode(500);
            return result;
        }
        int isSuccess = formTypeService.updateFormType(formTypeEntity);
        if (isSuccess == 1) {
            result.setMessage("success");
            result.setResultCode(200);
        } else {
            result.setMessage("error");
            result.setResultCode(404);
        }
        return result;
    }

    @RequestMapping("/logicallyDeleteFormType")
    @ResponseBody
    public Result logicallyDeleteFormType(Long id) {
        Result result = new Result();
        if (id == null) {
            result.setMessage("参数错误");
            result.setResultCode(500);
            return result;
        }

        int isSuccess = formTypeService.logicallyDeleteFormType(id);
        if (isSuccess == 1) {
            result.setMessage("success");
            result.setResultCode(200);
        } else {
            result.setMessage("error");
            result.setResultCode(404);
        }
        return result;
    }
}
