package com.jiale.thesis.controller.customForm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiale.thesis.entity.customForm.FormComponentConfigEntity;
import com.jiale.thesis.entity.customForm.FormComponentEntity;
import com.jiale.thesis.entity.customForm.FormEntity;
import com.jiale.thesis.entity.customForm.FormTypeEntity;
import com.jiale.thesis.entity.customForm.vo.FormCompleteEntity;
import com.jiale.thesis.service.customForm.FormComponentConfigService;
import com.jiale.thesis.service.customForm.FormComponentService;
import com.jiale.thesis.service.customForm.FormService;
import com.jiale.thesis.service.customForm.FormTypeService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customForm")
public class FormController {

    @Autowired
    FormTypeService formTypeService;
    @Autowired
    FormService formService;
    @Autowired
    FormComponentService formComponentService;
    @Autowired
    FormComponentConfigService formComponentConfigService;

    /*
        C
     */
    @RequestMapping("/postCreateForm")
    @ResponseBody
    public Result createForm(@RequestBody String json) throws UnsupportedEncodingException {
        Result result = new Result();
        String deUrl = URLDecoder.decode(json, "UTF-8");
        JSONObject obj = JSONObject.parseObject(deUrl);
        String formName = String.valueOf(obj.get("formName"));
        Long formTypeId = Long.parseLong(String.valueOf(obj.get("formTypeId")));
        FormTypeEntity upperLimit = formTypeService.formType(formTypeId);
        int currCount = formService.countFormByFormTypeId(formTypeId);
        if (upperLimit.getUpperLimit() <= currCount) {
            result.setResultCode(501);
            result.setMessage("已经达到可创建数量的上限");
            return result;
        }
        FormEntity formEntity = new FormEntity();
        formEntity.setName(formName);
        formEntity.setFormTypeId(formTypeId);
        // 创建表单
        int isSuccess = formService.createForm(formEntity);
        if (isSuccess != 1) {
            // 如果创建表达那失败则直接退出
            result.setResultCode(500);
            result.setMessage("参数错误");
            return result;
        }
        Long formId = formEntity.getId();
        String listJson = String.valueOf(obj.get("componentList"));
        JSONArray list = JSONArray.parseArray(listJson);
        List<FormComponentEntity> componentList = new ArrayList<>(list.size());

        List<JSONArray> configAllList = new ArrayList<>();
        try {
            for (int i = 0; i < list.size(); i++) {
                JSONObject component = list.getJSONObject(i);
                FormComponentEntity formComponentEntity = new FormComponentEntity();
                formComponentEntity.setFormId(formId);
                String componentKey = String.valueOf(component.getString("componentKey"));
                String componentLabel = String.valueOf(component.getString("componentLabel"));
                Integer sortNumber = Integer.parseInt(component.getString("serialNumber"));
                if (componentKey.isEmpty() || componentLabel.isEmpty()) {
                    // 如果组件的key和label有一个为空则直接退出
                    result.setResultCode(500);
                    result.setMessage("参数错误");
                    // 退出直接回退创建的表单
                    int isDeletedSuccess = formService.deleteForm(formId);
                    return result;
                }
                formComponentEntity.setComponentKey(componentKey);
                formComponentEntity.setComponentLabel(componentLabel);
                formComponentEntity.setSerialNumber(sortNumber);
                componentList.add(formComponentEntity);
                // 获取每个组件的配置属性对象
                String configListJson = String.valueOf(component.get("configList"));
                configAllList.add(JSONArray.parseArray(configListJson));
            }
            // 创建表单所属组件
            int createNumber = formComponentService.insertFormComponent(componentList);
        } catch (Exception e) {
            result.setResultCode(500);
            result.setMessage("error: 组件属性错误");
            return result;
        }

        try {
            List<FormComponentConfigEntity> fccList = new ArrayList<>();
            for (int i = 0; i < componentList.size(); i++) {
                JSONArray jsonArray = configAllList.get(i);
                for (int j = 0; j < jsonArray.size(); j++) {
                    FormComponentConfigEntity formComponentConfigEntity = new FormComponentConfigEntity();
                    JSONObject configObj = jsonArray.getJSONObject(j);
                    formComponentConfigEntity.setPropertyKey(configObj.getString("key"));
                    formComponentConfigEntity.setValue(configObj.getString("value"));
                    formComponentConfigEntity.setFormComponentId(componentList.get(i).getId());
                    fccList.add(formComponentConfigEntity);
                }
            }
            int insertCount = formComponentConfigService.insertFormComponentConfig(fccList);
        } catch (Exception e) {
            result.setResultCode(500);
            result.setMessage("error: 组件设置属性错误");
            return result;
        }

        result.setResultCode(200);
        result.setMessage("success");
        return result;
    }

    @RequestMapping("/selectForm")
    @ResponseBody
    public Result<List<FormEntity>> selectForm(Long id) {
        Result<List<FormEntity>> result = new Result<>();
        if (id == null) {
            result.setData(formService.selectForm());
        } else {
            List<FormEntity> list = new ArrayList<>();
            list.add(formService.selectForm(id));
            result.setData(list);
        }
        result.setResultCode(200);
        return result;
    }

    @RequestMapping("/selectFormComplete")
    @ResponseBody
    public Result<List<FormCompleteEntity>> selectFormComplete(Long formId) {
        Result<List<FormCompleteEntity>> result = new Result<>();
        result.setData(formService.selectFormComplete(formId));
        result.setMessage("success");
        result.setResultCode(200);
        return result;
    }

    @RequestMapping("/postUpdateForm")
    @ResponseBody
    public Result updateForm(@RequestBody String json) throws UnsupportedEncodingException {
        Result result = new Result();
        String deUrl = URLDecoder.decode(json, "UTF-8");
        JSONObject obj = JSONObject.parseObject(deUrl);
        // 更新流程
        /*
            1.更新表单
                1.1 通过formId查询，判断执行的字段是否发生改变；例如：fromName是否发生改变，实则触发更新,
            2.更新表单的组件
                2.1 通过formId查询该form的所有有效（is_deleted = 1）的组件， 根据formComponentId进行对比数据是否发生改变，如果改变则将新的数据插入到需要更新的数组中。数组为空则不进行更新
                若有新增,则根据formComponentId创建,如果相对于查询结果有确实,则提取出来进行逻辑删除
            3.更新表单组件的属性
                3.1 通过formComponentId查询该组件的，判断是否发生更改，是则添加到需要跟新的数组中，如果数组为空，则不进行更新操作。
         */
        int isUpdateFormSuccess;
        boolean isUpdateFormComponentSuccess;
        Long formId = Long.parseLong(String.valueOf(obj.get("formId")));
        String formName = String.valueOf(obj.get("formName"));

        FormEntity form = formService.selectForm(formId);
        if (form == null) {
            result.setMessage("error");
            result.setResultCode(500);
            return result;
        }
        if (!Objects.equals(form.getName(), formName)) {
            form.setName(formName);
            isUpdateFormSuccess = formService.updateForm(form);
        } else {
            isUpdateFormSuccess = -1;
        }

        String listJson = String.valueOf(obj.get("componentList"));
        JSONArray list = JSONArray.parseArray(listJson);
        List<FormComponentEntity> componentList = new ArrayList<>(list.size());
        List<JSONArray> configAllList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            JSONObject component = list.getJSONObject(i);
            FormComponentEntity formComponentEntity = new FormComponentEntity();
            formComponentEntity.setFormId(formId);
            if (component.get("formComponentId") != null) {
                formComponentEntity.setId(Long.parseLong(String.valueOf(component.get("formComponentId"))));
            }
            formComponentEntity.setComponentKey(String.valueOf(component.getString("componentKey")));
            formComponentEntity.setComponentLabel(String.valueOf(component.getString("componentLabel")));
            formComponentEntity.setSerialNumber(Integer.parseInt(component.getString("serialNumber")));
            componentList.add(formComponentEntity);

            String configListJson = String.valueOf(component.get("configList"));
            configAllList.add(JSONArray.parseArray(configListJson));
        }
        List<FormComponentEntity> srcComponentList = formComponentService.selectByFormId(formId);
        List<FormComponentEntity> updateComponentList = new ArrayList<>();
        List<FormComponentEntity> createComponentList = new ArrayList<>();
        List<JSONArray> createComponentConfigList = new ArrayList<>();

        for (int i = 0; i< componentList.size(); i++) {
            FormComponentEntity componentEntity = componentList.get(i);
            FormComponentEntity oldComponent = new FormComponentEntity();
            for (FormComponentEntity srcComponentEntity : srcComponentList) {
                if (Objects.equals(componentEntity.getId(), srcComponentEntity.getId())) {
                    oldComponent = srcComponentEntity;
                }
            }
            if (Objects.equals(oldComponent, new FormComponentEntity())) {
                // 新增
                createComponentList.add(componentEntity);
                createComponentConfigList.add(configAllList.get(i));
            } else {
                // 匹配后对比

                if (!Objects.equals(componentEntity.getComponentKey(), oldComponent.getComponentKey())
                        || !Objects.equals(componentEntity.getComponentLabel(), oldComponent.getComponentLabel())
                        || !Objects.equals(componentEntity.getSerialNumber(), oldComponent.getSerialNumber())
                ) {
                    updateComponentList.add(componentEntity);
                }
            }
        }
        // 新创建
        if (createComponentConfigList.size() > 0) {
            configAllList.removeAll(createComponentConfigList);

            int newComponentCreateCount = formComponentService.insertFormComponent(createComponentList);
            List<FormComponentConfigEntity> configList = new ArrayList<>();
            for (int i = 0; i < createComponentList.size(); i++) {
                Long currFormComponentId = createComponentList.get(i).getId();
                JSONArray jsonArray = createComponentConfigList.get(i);
                for (int j = 0; j < jsonArray.size(); j++) {
                    FormComponentConfigEntity formComponentConfigEntity = new FormComponentConfigEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(j);

                    formComponentConfigEntity.setPropertyKey(jsonObject.getString("key"));
                    formComponentConfigEntity.setValue(jsonObject.getString("value"));
                    formComponentConfigEntity.setFormComponentId(currFormComponentId);
                    configList.add(formComponentConfigEntity);
                }
            }
            int newComponentConfigCreateCount = formComponentConfigService.insertFormComponentConfig(configList);
        }

        // 缺失,进行逻辑删除
        List<Long> componentIdList = new ArrayList<>();
        for (FormComponentEntity fc : componentList) {
            if (fc.getId() != null) {
                componentIdList.add(fc.getId());
            }
        }
        List<FormComponentEntity> diffSrcComponentList = srcComponentList.stream()
                .filter(component -> !componentIdList.contains(component.getId()))
                .collect(Collectors.toList());
        if (diffSrcComponentList.size() > 0) {
            for (FormComponentEntity fc : diffSrcComponentList) {
                int isLogicallyDeleteCount = formComponentService.logicallyDelete(fc.getId());
            }
        }

//         更新和逻辑删除的统一使用update,逻辑删除是指isDeleted字段值为1
        if (updateComponentList.size() > 0) {
            int updateCount = formComponentService.updateFormComponentList(updateComponentList);
            isUpdateFormComponentSuccess = updateCount == updateComponentList.size();
        }

        List<FormComponentConfigEntity> updateConfigList = new ArrayList<>();
        for (JSONArray configArr : configAllList) {
            if (configArr.size() > 0) {
                for (int j = 0; j < configArr.size(); j++) {
                    JSONObject configObj = configArr.getJSONObject(j);
                    FormComponentConfigEntity fcce = formComponentConfigService.select(Long.parseLong(configObj.get("formComponentConfigId").toString()));
                    if (!Objects.equals(fcce.getPropertyKey(), String.valueOf(configObj.get("key")))
                            || !Objects.equals(fcce.getValue(), String.valueOf(configObj.get("value")))
                    ) {
                        FormComponentConfigEntity formComponentConfigEntity = new FormComponentConfigEntity();
                        formComponentConfigEntity.setId(Long.parseLong(configObj.get("formComponentConfigId").toString()));
                        formComponentConfigEntity.setPropertyKey(String.valueOf(configObj.get("key")));
                        formComponentConfigEntity.setValue(String.valueOf(configObj.get("value")));
                        updateConfigList.add(formComponentConfigEntity);
                    }
                }
            }
        }
        if (updateConfigList.size() > 0) {
            int updateComponentCount = formComponentConfigService.updateFormComponentConfig(updateConfigList);
        }

        result.setMessage("success");
        result.setResultCode(200);
        return result;
    }

//    private List<FormComponentEntity> formComponentJSONToList(String fcJSON) {
//        JSONArray list = JSONArray.parseArray(fcJSON);
//        List<FormComponentEntity> componentList = new ArrayList<>(list.size());
//        for (int i = 0; i < list.size(); i++) {
//            JSONObject component = list.getJSONObject(i);
//            FormComponentEntity formComponentEntity = new FormComponentEntity();
//            formComponentEntity.setComponentKey(String.valueOf(component.getString("componentKey")));
//            formComponentEntity.setComponentLabel(String.valueOf(component.getString("componentLabel")));
//            formComponentEntity.setSerialNumber(Integer.parseInt(component.getString("serialNumber")));
//            componentList.add(formComponentEntity);
//        }
//        return componentList;
//    }
}
