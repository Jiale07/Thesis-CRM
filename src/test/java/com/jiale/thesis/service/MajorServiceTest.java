package com.jiale.thesis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.Major;
import com.jiale.thesis.mapper.MajorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MajorServiceTest {

    @Resource
    MajorMapper majorMapper;

    @Test
    void selectMajorPage() {
        QueryWrapper<Major> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("is_deleted",1);
        Page<Major> page = new Page<>(1,5);
        majorMapper.selectPage(page,queryWrapper);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());

    }
}