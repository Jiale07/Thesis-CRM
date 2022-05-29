package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.Announcement.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {

    Long insertAnnouncement(Announcement announcement);

    List<Announcement> findAnnouncementByRoleArray(int[] roleArray,Integer number);
}
