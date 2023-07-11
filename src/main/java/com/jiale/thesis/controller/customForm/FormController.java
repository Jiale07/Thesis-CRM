package com.jiale.thesis.controller.customForm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiale.thesis.entity.customForm.FormEntity;
import com.jiale.thesis.entity.customForm.FormInfoEntity;
import com.jiale.thesis.entity.customForm.vo.FormVO;
import com.jiale.thesis.service.customForm.FormInfoService;
import com.jiale.thesis.service.customForm.FormService;
import com.jiale.thesis.util.Result;
import com.jiale.thesis.util.jwt.Decrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/customForm")
public class FormController {
    Decrypt decrypt = new Decrypt();
    /*
    自定义表单的模板实例
     */
    @Autowired
    FormService formService;
    @Autowired
    FormInfoService formInfoService;

//    创建
    @RequestMapping("/createCustomForm")
    @ResponseBody
    public Result<Object> createCustomForm(@RequestHeader(value = "token", defaultValue = "")String token, @RequestBody String json) throws UnsupportedEncodingException {
        Result<Object> result = new Result<>();

        String deUrl = URLDecoder.decode(json, "UTF-8");
        JSONObject obj = JSONObject.parseObject(deUrl);

        String formName = String.valueOf(obj.get("formName"));
        Long formTemplateId = Long.valueOf(String.valueOf(obj.get("formTemplateId")));
        Long authorId = getUserId(token);

        FormEntity form = new FormEntity();
        form.setName(formName);
        form.setFormTemplateId(formTemplateId);
        form.setAuthorId(authorId);
        int isCreateFormSuccess = formService.createForm(form);

        Long formId = form.getId();
        if (formId == null && isCreateFormSuccess < 0) {
            result.setMessage("参数错误");
            result.setToken("500");
            return result;
        }
        JSONArray list = JSONArray.parseArray(String.valueOf(obj.get("componentList")));
        int formInfoCreateCount = 0;
        if (list.size() == 0) {
            result.setMessage("参数错误");
            result.setResultCode(500);
            return result;
        }
        List<Long> formInfoIdList = new ArrayList<>();
        try {
            for (int i = 0; i < list.size(); i++) {
                JSONObject componentItem = list.getJSONObject(i);
                if (componentItem.getString("formComponentId") == null) {
                    throw new Exception("参数错误");
                }
                if (componentItem.getString("value") == null) {
                    continue;
                }

                FormInfoEntity formInfoEntity = new FormInfoEntity();
                formInfoEntity.setFormId(formId);
                formInfoEntity.setFormComponentId(Long.valueOf(componentItem.getString("formComponentId")));
                formInfoEntity.setValue(String.valueOf(componentItem.getString("value")));


                formInfoCreateCount += formInfoService.createFormInfo(formInfoEntity);
                formInfoIdList.add(formInfoEntity.getId());
            }
        } catch (Exception e) {
            for (Long formInfoId : formInfoIdList) {
                formInfoService.deleteFormInfo(formInfoId);
            }
            formService.deleteForm(formId);
            result.setMessage(e.getMessage());
            result.setResultCode(500);
            return result;
        }

        result.setMessage("success");
        result.setResultCode(200);
        return result;
    }
//    更新
    @RequestMapping("/updateCustomForm")
    @ResponseBody
    public Result<Object> updateCustomForm(@RequestHeader(value = "token", defaultValue = "")String token, @RequestBody String json) throws UnsupportedEncodingException {
        Result<Object> result = new Result<>();

        String deUrl = URLDecoder.decode(json, "UTF-8");
        JSONObject obj = JSONObject.parseObject(deUrl);

        String formName = String.valueOf(obj.get("formName"));
        Long formId = null;
        if (String.valueOf(obj.get("formId")) == null) {
            result.setMessage("参数错误");
            result.setResultCode(500);
            return result;
        }
        formId = Long.valueOf(String.valueOf(obj.get("formId")));
//        Long formTemplateId = Long.valueOf(String.valueOf(obj.get("formTemplateId")));
        Long authorId = getUserId(token);

        JSONArray list = JSONArray.parseArray(String.valueOf(obj.get("componentList")));
        List<FormInfoEntity> newFormInfoEntityList = new ArrayList<>();
        List<FormInfoEntity> oldFormInfoEntityList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            JSONObject componentItem = list.getJSONObject(i);
            Long formComponentId = Long.valueOf(String.valueOf(componentItem.get("formComponentId")));
            FormInfoEntity oldFormInfo = formInfoService.selectFormInfo(formComponentId);
            String value = String.valueOf(componentItem.get("value"));
            if (oldFormInfo == null && value != null && !value.equals("null")) {
                FormInfoEntity newFormInfo = new FormInfoEntity();
                newFormInfo.setFormComponentId(formComponentId);
                newFormInfo.setFormId(formId);
                newFormInfo.setValue(value);
                newFormInfoEntityList.add(newFormInfo);
                continue;
            }
            if (value != null && !value.equals("null") && !oldFormInfo.getValue().equals(value)){
                FormInfoEntity newFormInfo = new FormInfoEntity();
                newFormInfo.setFormComponentId(oldFormInfo.getFormComponentId());
                newFormInfo.setFormId(oldFormInfo.getFormId());
                newFormInfo.setValue(value);
                newFormInfoEntityList.add(newFormInfo);

                oldFormInfo.setIsDeleted(1);
                oldFormInfoEntityList.add(oldFormInfo);
            }
        }
        int formInfoUpdateCount = 0;
        for (FormInfoEntity formInfoEntity: newFormInfoEntityList) {
            formInfoUpdateCount += formInfoService.createFormInfo(formInfoEntity);
        }
        for (FormInfoEntity formInfoEntity: oldFormInfoEntityList) {
            formInfoUpdateCount += formInfoService.updateFormInfo(formInfoEntity);
        }

        FormEntity formObj = formService.selectForm(formId, authorId);
        if (!Objects.equals(formObj.getName(), formName)) {
            FormEntity updateForm = new FormEntity();
            updateForm.setId(formId);
            updateForm.setName(formName);
            formService.updateForm(updateForm);
        } else if (formInfoUpdateCount > 0) {
            FormEntity updateForm = new FormEntity();
            updateForm.setId(formId);
            updateForm.setUpdateTime(new Date());
            formService.updateForm(updateForm);
        }

        result.setResultCode(200);
        result.setMessage("success");
        return result;
    }
    //    删除（逻辑删除）


    private Long getUserId(String token) {
        return Long.valueOf(decrypt.deToken(token).getClaim("userId").asString());
    }

    // 检查当前用户是否已经创建了指定的自定义表单。返回类型为boolean

    @RequestMapping("/getFormVO")
    @ResponseBody
    public Result<FormVO> getForm(Long formId) {
        Result<FormVO> result = new Result<>();
        FormVO formVO = formService.selectFormVO(formId);
        if (formVO == null) {
            result.setMessage("参数错误");
            result.setResultCode(500);
        } else {
            result.setData(formVO);
            result.setResultCode(200);
        }
        return result;
    }
    @RequestMapping("/getFormVOByFormTemplateId")
    @ResponseBody
    public Result<FormVO> getFormVOByFormTemplateId(@RequestHeader(value = "token", defaultValue = "")String token, Long formTemplateId) {
        Result<FormVO> result = new Result<>();
        FormVO formVO = formService.selectFormVO(formTemplateId, getUserId(token));
        if (formVO == null) {
            result.setMessage("参数错误");
            result.setResultCode(500);
        } else {
            result.setData(formVO);
            result.setResultCode(200);
        }
        return result;
    }

    @RequestMapping("/verifyIsCanCreateForm")
    @ResponseBody
    public Result<Boolean> verifyIsCanCreateForm(@RequestHeader(value = "token", defaultValue = "")String token, Long formTemplateId) {
        Result<Boolean> result = new Result<>();

        Long authorId = getUserId(token);
        if (formTemplateId == null) {
            result.setData(false);
            result.setMessage("参数错误");
            result.setResultCode(200);
        }
        int count = formService.formCount(formTemplateId, authorId);
        // 现阶段一种表单模板只能创建一个表达实例
        if (count > 0) {
            result.setData(false);
            result.setMessage("已经完成填写");
        } else {
            result.setData(true);
        }
        result.setResultCode(200);
        return result;
    }
}
