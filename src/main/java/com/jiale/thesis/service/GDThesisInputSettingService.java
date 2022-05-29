package com.jiale.thesis.service;

import com.jiale.thesis.entity.GD.GDThesisInputSetting;

import java.util.List;

public interface GDThesisInputSettingService {

    List<GDThesisInputSetting> findGDThesisInputSettingList();

    int AddGDThesisInputSetting(GDThesisInputSetting gdThesisInputSetting);

    int updateGDThesisInputSetting(GDThesisInputSetting gdThesisInputSetting);

    int deletedGDThesisInputSettingById(Long gdThesisInputSettingId);

}
