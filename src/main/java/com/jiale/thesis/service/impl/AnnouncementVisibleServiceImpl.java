package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.announcement.AnnouncementVisible;
import com.jiale.thesis.entity.SysRole;
import com.jiale.thesis.mapper.AnnouncementVisibleMapper;
import com.jiale.thesis.service.AnnouncementVisibleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnnouncementVisibleServiceImpl implements AnnouncementVisibleService {

    @Resource
    AnnouncementVisibleMapper announcementVisibleMapper;

    @Override
    public Integer addAnnouncementVisibleByRoleList(Long announcementId, int[] roleList) {

        for (Integer integer : roleList) {
            AnnouncementVisible announcement = new AnnouncementVisible();
            announcement.setAnnouncementId(announcementId);
            announcement.setRoleId(integer);
            announcementVisibleMapper.insert(announcement);
        }
        return 1;
    }

    @Override
    public List<SysRole> findSRListByAnnouncementId(Long announcementId) {
        return announcementVisibleMapper.findAVAndRoleInfo(announcementId);
    }

    @Override
    public List<AnnouncementVisible> findAVListByAnnouncementId(Long announcementId) {
        QueryWrapper<AnnouncementVisible> queryWrapper = new QueryWrapper();
        queryWrapper.eq("announcement_id",announcementId);
        queryWrapper.eq("is_deleted",0);
        return announcementVisibleMapper.selectList(queryWrapper);
    }

    @Override
    public AnnouncementVisible findAVByAnnouncementIdANDRoleId(Long announcementId, Integer roleId) {
        QueryWrapper<AnnouncementVisible> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("announcement_id",announcementId);
        queryWrapper.eq("role_id",roleId);
        queryWrapper.eq("is_deleted",0);
        return announcementVisibleMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateAVById(AnnouncementVisible announcementVisible) {
        return announcementVisibleMapper.updateById(announcementVisible);
    }

    @Override
    public int addAnnouncementVisible(Long announcementId, Integer roleId) {
        AnnouncementVisible announcementVisible = new AnnouncementVisible();
        announcementVisible.setAnnouncementId(announcementId);
        announcementVisible.setRoleId(roleId);
        announcementVisible.setIsDeleted(0);
        return announcementVisibleMapper.insert(announcementVisible);
    }

    @Override
    public int deletedAnnouncementVisibleLogic(Long announcementId, int[] roleArray) {
        for(int roleId : roleArray){
            AnnouncementVisible announcementVisible = new AnnouncementVisible();
            announcementVisible.setIsDeleted(1);
            QueryWrapper<AnnouncementVisible> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("announcement_id",announcementId);
            queryWrapper.eq("role_id",roleId);
            announcementVisibleMapper.update(announcementVisible,queryWrapper);
        }
        return 1;
    }
}
