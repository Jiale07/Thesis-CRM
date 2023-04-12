package com.jiale.thesis.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.numberingRuleSetting.NumberingRuleEntity;
import com.jiale.thesis.mapper.NumberingRuleSettingMapper;
import com.jiale.thesis.service.NumberingRuleSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static com.jiale.thesis.publicConstant.NumberingRuleSettingConstant.ParagraphType;

@Service
public class NumberingRuleSettingServiceImpl implements NumberingRuleSettingService {
    @Resource
    NumberingRuleSettingMapper numberingRuleSettingMapper;

    public int createNumberingRule(NumberingRuleEntity numberingRuleEntity) {
        int isSuccess = numberingRuleSettingMapper.insert(numberingRuleEntity);
        return isSuccess;
    }

    @Override
    public NumberingRuleEntity selectNumberingRule(Long id) {
        QueryWrapper<NumberingRuleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("is_deleted", 0);
        return (NumberingRuleEntity) numberingRuleSettingMapper.selectOne(queryWrapper);
    }

    @Override
    public List<NumberingRuleEntity> selectNumberingRuleList(Integer numberingRuleType) {
        QueryWrapper<NumberingRuleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", numberingRuleType);
        queryWrapper.eq("is_deleted", 0);
        return numberingRuleSettingMapper.selectList(queryWrapper);
    }

    @Override
    public int deletedNumberingRule(Long id) {
        NumberingRuleEntity numberingRuleEntity = new NumberingRuleEntity();
        numberingRuleEntity.setIsDeleted(1);
        QueryWrapper<NumberingRuleEntity> queryWrapper = new QueryWrapper<NumberingRuleEntity>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("is_deleted", 0);
        return numberingRuleSettingMapper.update(numberingRuleEntity, queryWrapper);
    }

    @Override
    public int updateNumberingRule(NumberingRuleEntity numberingRuleEntity) {
//        QueryWrapper<NumberingRuleEntity>  queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", numberingRuleEntity.getId());
//        queryWrapper.eq("is_deleted", 0);
        return numberingRuleSettingMapper.updateById(numberingRuleEntity);
    }

    @Override
    public String generatingNumberBy(Long id) {
        NumberingRuleEntity numberingRule = numberingRuleSettingMapper.selectById(id);
        if (numberingRule != null) {
            String content = numberingRule.getContent();
            JSONArray jsonArray = JSONArray.parseArray(content);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Integer paragraphType = object.getInteger("paragraphType");
                if (paragraphType != null) {
                    if (Objects.equals(ParagraphType.get("Data"), paragraphType)) {

                    } else if (Objects.equals(ParagraphType.get("College"), paragraphType)) {

                    }
                }
            }
        }
        return null;
    }
}
