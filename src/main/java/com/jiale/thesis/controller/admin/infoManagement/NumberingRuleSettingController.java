package com.jiale.thesis.controller.admin.infoManagement;

import com.alibaba.fastjson.JSONObject;
import com.jiale.thesis.entity.numberingRuleSetting.NumberingRuleEntity;
import com.jiale.thesis.service.NumberingRuleSettingService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLOutput;
import java.util.List;

@Controller
@RequestMapping("/numberingRuleSetting")
public class NumberingRuleSettingController {

    @Autowired
    NumberingRuleSettingService numberingRuleSettingService;

    @RequestMapping("/postNumberingRule")
    @ResponseBody
    public Result<Object> createNumberingRule(@RequestBody String json) throws UnsupportedEncodingException {
        String str = URLDecoder.decode(json, "UTF-8");
        JSONObject obj = JSONObject.parseObject(str);

        Result<Object> result = new Result<>();
        NumberingRuleEntity numberingRuleEntity = new NumberingRuleEntity();
        int isSuccess;
        String resultMessage = "创建失败";
        int resultCode;

        if (obj.get("numberingRuleId") == null) {
            if ((Integer) obj.get("numberingRuleType") == null) {
                isSuccess = 0;
                resultMessage = "参数异常,请稍后重试！";
                resultCode = 500;
            } else {
                numberingRuleEntity.setType((Integer) obj.get("numberingRuleType"));
                numberingRuleEntity.setName((String) obj.get("numberingRuleName"));
                numberingRuleEntity.setContent((String) obj.get("content"));
                isSuccess = numberingRuleSettingService.createNumberingRule(numberingRuleEntity);
                if (isSuccess == 1) {
                    resultMessage = "保存成功";
                    resultCode = 200;
                } else {
                    resultMessage = "保存失败";
                    resultCode = 500;
                }
            }
        } else {
            numberingRuleEntity.setId(Long.parseLong((String) obj.get("numberingRuleId")));
            numberingRuleEntity.setName((String) obj.get("name"));
            numberingRuleEntity.setContent((String) obj.get("content"));
            isSuccess = numberingRuleSettingService.updateNumberingRule(numberingRuleEntity);
            if (isSuccess == 1) {
                resultMessage = "保存成功";
                resultCode = 200;
            } else {
                resultMessage = "保存失败";
                resultCode = 500;
            }
        }
        result.setResultCode(resultCode);
        result.setMessage(resultMessage);
        return result;
    }

    @RequestMapping("/getNumberingRule")
    @ResponseBody
    public Result<NumberingRuleEntity> getNumberingRule(Long id) {
        Result<NumberingRuleEntity> result = new Result<>();
        NumberingRuleEntity numberingRuleEntity = numberingRuleSettingService.selectNumberingRule(id);
        if (numberingRuleEntity != null) {
            result.setData(numberingRuleEntity);
            result.setMessage("查询成功");
            result.setResultCode(200);
        } else {
            result.setMessage("查询失败");
            result.setResultCode(500);
        }
        return result;
    }

    @RequestMapping("/getNumberingRulList")
    @ResponseBody
    public Result<List<NumberingRuleEntity>> getNumberingRuleList(Integer numberingRuleType) {
        Result<List<NumberingRuleEntity>> result = new Result<>();
        List<NumberingRuleEntity> list = numberingRuleSettingService.selectNumberingRuleList(numberingRuleType);
        result.setData(list);
        result.setResultCode(200);
        result.setMessage("查询成功");
        return result;
    }

    @RequestMapping("/deletedNumberingRuleById")
    @ResponseBody
    public Result<Object> deletedNumberingRuleById(Long id) {
        Result<Object> result = new Result<>();
        int isSuccess = numberingRuleSettingService.deletedNumberingRule(id);
        if (isSuccess == 1) {
            result.setMessage("删除成功");
            result.setResultCode(200);
        } else {
            result.setMessage("删除失败");
            result.setResultCode(500);
        }
        return result;
    }
}
