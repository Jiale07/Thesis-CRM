package com.jiale.thesis.util;

import com.jiale.thesis.publicConstant.NumberingRuleSettingConstant;

import java.util.Objects;

import static com.jiale.thesis.publicConstant.NumberingRuleSettingConstant.NumberingRuleType;

public class NumberingRuleParse {
    NumberingRuleSettingConstant numberingRuleSettingContent;
    public String parseNumberingRule(Integer numberingRuleType, String content) {
        String numbering = null;

        if (Objects.equals(NumberingRuleType.get("StudentNumberingRule"), numberingRuleType)){

        } else {

        }
        return numbering;
    };

    public String parseStudentNumberingRule (String content) {
        String resultNumbering = null;

        return resultNumbering;
    }
}
