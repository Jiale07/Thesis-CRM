package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.Announcement.Announcement;

import java.util.List;

public interface AnnouncementService {

    Page<Announcement> findAnnouncementPage(Integer currentPage,Integer pageSize);

    List<Announcement> findAnnouncementByRoleArray(int[] roleArray,Integer number);

    Announcement findOndAnnouncement(Long announcementId);

    Integer addAnnouncement(Announcement announcement);

    Integer updateAnnouncement(Announcement announcement);

    Integer deletedAnnouncementLogic(Long aId);
}
