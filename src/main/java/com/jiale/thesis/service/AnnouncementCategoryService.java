package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.Announcement.AnnouncementCategory;


import java.util.List;

public interface AnnouncementCategoryService {

    Page<AnnouncementCategory> findAnnouncementCategoryPage(Integer currentPage, Integer pageSize);

    int addAnnouncementCategory(AnnouncementCategory announcementCategory);

    int updateAnnouncementCategory(AnnouncementCategory announcementCategory);

    int deletedAnnouncementCategoryLogic(Long acId);

    List<AnnouncementCategory> findAllAC();

    AnnouncementCategory findOneACById(Long acId);

}
