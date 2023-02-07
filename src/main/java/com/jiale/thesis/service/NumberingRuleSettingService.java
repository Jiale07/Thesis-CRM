package com.jiale.thesis.service;

import com.jiale.thesis.entity.numberingRuleSetting.NumberingRuleEntity;

import java.util.List;

public interface NumberingRuleSettingService {
    public int createNumberingRule(NumberingRuleEntity numberingRuleEntity);
    public NumberingRuleEntity selectNumberingRule(Long id);
    public List<NumberingRuleEntity> selectNumberingRuleList(Integer numberingRuleType);
    public int deletedNumberingRule(Long id);

    public int updateNumberingRule(NumberingRuleEntity numberingRuleEntity);
}
