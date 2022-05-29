package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.Classes;
import com.jiale.thesis.entity.Major;
import com.jiale.thesis.mapper.ClassesMapper;
import com.jiale.thesis.service.ClassesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Resource
    ClassesMapper classesMapper;


    @Override
    public int addClassesInfo(Classes classes){
        return classesMapper.insert(classes);
    }

    @Override
    public int deleteClassesById(Integer classId){
        return classesMapper.deleteById(classId);
    }

    @Override
    public int updateClassesInfo(Classes classes){
        return classesMapper.updateById(classes);
    }

    @Override
    public IPage<Classes> findClassesPage(Page<Classes> page){
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        return classesMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Classes> findClassesPage(Page<Classes> page,Integer collegeId){
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("college_id",collegeId);
        return classesMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Classes> findClassesPage(Page<Classes> page,Integer collegeId,Integer majorId){
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("college_id",collegeId);
        queryWrapper.eq("major_id",majorId);
        return classesMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Classes> findClassesPage(Page<Classes> page,Integer collegeId,Integer majorId,Integer classesId){
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("college_id",collegeId);
        queryWrapper.eq("major_id",majorId);
        queryWrapper.eq("id",classesId);
        return classesMapper.selectPage(page,queryWrapper);
    }
}
