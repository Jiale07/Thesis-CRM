package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.Announcement.Announcement;
import com.jiale.thesis.mapper.AnnouncementMapper;
import com.jiale.thesis.service.AnnouncementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Resource
    AnnouncementMapper announcementMapper;


    @Override
    public Page<Announcement> findAnnouncementPage(Integer currentPage, Integer pageSize) {
        Page<Announcement> page = new Page<>(currentPage,pageSize);
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        queryWrapper.orderByDesc("create_time");
        return announcementMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Announcement> findAnnouncementByRoleArray(int[] roleArray, Integer number) {
        return announcementMapper.findAnnouncementByRoleArray(roleArray,number);
    }

    @Override
    public Announcement findOndAnnouncement(Long announcementId) {
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<Announcement>();
        queryWrapper.eq("id",announcementId);
        queryWrapper.eq("is_deleted",0);
        return announcementMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer addAnnouncement(Announcement announcement) {
        return announcementMapper.insert(announcement);
    }

    @Override
    public Integer updateAnnouncement(Announcement announcement) {
        return announcementMapper.updateById(announcement);
    }

    @Override
    public Integer deletedAnnouncementLogic(Long aId) {
        Announcement announcement = new Announcement();
        announcement.setIsDeleted(1);
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",aId);
        queryWrapper.eq("is_deleted",0);
        return announcementMapper.update(announcement,queryWrapper);
    }
}
