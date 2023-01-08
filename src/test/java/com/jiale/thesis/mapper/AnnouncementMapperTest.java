package com.jiale.thesis.mapper;

import com.jiale.thesis.entity.announcement.Announcement;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
class AnnouncementMapperTest {

    @Resource
    AnnouncementMapper announcementMapper;

    @Test
    void insertAnnouncement() {
        Announcement announcement = new Announcement();
        announcement.setCreatorId(new BigInteger("10001"));
        announcement.setCategoryId(new Long("1001"));
        announcement.setAnnouncementName("jiale");
        announcement.setAnnouncementContent("jiale hello");
        announcement.setIsDeleted(0);
        System.out.println(announcementMapper.insertAnnouncement(announcement));
    }

    @Test
    void findAnnouncementByRoleList() {
        int[] roleArray = new int[2];
        roleArray[0] = 1001;
        roleArray[1] = 2001;
        List<Announcement> announcementList = announcementMapper.findAnnouncementByRoleArray(roleArray,2);
        System.out.println(announcementList.toString());
    }
}