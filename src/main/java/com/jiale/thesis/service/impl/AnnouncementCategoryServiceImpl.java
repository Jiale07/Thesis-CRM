package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.jiale.thesis.entity.Announcement.AnnouncementCategory;
import com.jiale.thesis.mapper.AnnouncementCategoryMapper;
import com.jiale.thesis.service.AnnouncementCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnnouncementCategoryServiceImpl implements AnnouncementCategoryService {

    @Resource
    AnnouncementCategoryMapper acMapper;

    @Override
    public Page<AnnouncementCategory> findAnnouncementCategoryPage(Integer currentPage, Integer pageSize) {
        Page<AnnouncementCategory> page = new Page<>(currentPage,pageSize);
        QueryWrapper<AnnouncementCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        return acMapper.selectPage(page,queryWrapper);
    }

    @Override
    public int addAnnouncementCategory(AnnouncementCategory announcementCategory) {
        return acMapper.insert(announcementCategory);
    }

    @Override
    public int updateAnnouncementCategory(AnnouncementCategory announcementCategory) {
        return acMapper.updateById(announcementCategory);
    }

    @Override
    public int deletedAnnouncementCategoryLogic(Long acId) {
        AnnouncementCategory announcementCategory = new AnnouncementCategory();
        announcementCategory.setIsDeleted(1);
        QueryWrapper<AnnouncementCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",acId);
        queryWrapper.eq("is_deleted",0);
        return acMapper.update(announcementCategory,queryWrapper);
    }

    @Override
    public List<AnnouncementCategory> findAllAC() {
        QueryWrapper<AnnouncementCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        return acMapper.selectList(queryWrapper);
    }

    @Override
    public AnnouncementCategory findOneACById(Long acId) {
        QueryWrapper<AnnouncementCategory> queryWrapper = new QueryWrapper<AnnouncementCategory>();
        queryWrapper.eq("id",acId);
        queryWrapper.eq("is_deleted",0);
        return acMapper.selectOne(queryWrapper);
    }

}
