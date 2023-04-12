package com.jiale.thesis.publicConstant;

import java.util.HashMap;

public class NumberingRuleSettingConstant {
    public final static HashMap<String, Number> NumberingRuleType = new HashMap<String, Number>() {
        {
            put("StudentNumberingRule", 1);
        }
    };

    public final static HashMap<String, Integer> ParagraphType = new HashMap<String, Integer>() {
        {
            put("Data", 1);
            put("College", 1);
            put("Major", 1);
            put("SerialNumber", 1);
            put("Text", 1);
        }
    };

    public final static HashMap<String, Integer> ComponentType = new HashMap<String, Integer>() {
        {
            put("DatePicker", 1);
            put("Select", 1);
            put("Input", 1);
        }
    };

    public final static HashMap<String, Integer> OptionType = new HashMap<String, Integer>() {
        {
            put("College", 1);
            put("Major", 2);
        }
    };

    public final static HashMap<String, Integer> InputType = new HashMap<String, Integer>() {
        {
            put("SerialNumber", 1);
            put("Character", 2);
        }
    };

}
