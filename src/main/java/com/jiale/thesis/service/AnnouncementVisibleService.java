package com.jiale.thesis.service;

import com.jiale.thesis.entity.announcement.AnnouncementVisible;
import com.jiale.thesis.entity.SysRole;

import java.util.List;

public interface AnnouncementVisibleService {

    Integer addAnnouncementVisibleByRoleList(Long announcementId, int[] roleList);

    List<SysRole> findSRListByAnnouncementId(Long announcementId);

    List<AnnouncementVisible> findAVListByAnnouncementId(Long announcementId);

    AnnouncementVisible findAVByAnnouncementIdANDRoleId(Long announcementId,Integer roleId);

    int updateAVById(AnnouncementVisible announcementVisible);

    int addAnnouncementVisible(Long announcementId,Integer roleId);

    int deletedAnnouncementVisibleLogic(Long announcementId,int[] roleArray);
}
