package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.announcement.AnnouncementVisible;
import com.jiale.thesis.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementVisibleMapper extends BaseMapper<AnnouncementVisible> {

    List<SysRole> findAVAndRoleInfo(Long announcementId);
}
