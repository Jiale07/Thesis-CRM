package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.College;
import com.jiale.thesis.mapper.CollegeMapper;
import com.jiale.thesis.service.CollegeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Resource
    CollegeMapper collegeMapper;

    @Override
    public int addCollegeInfo(College college){
        return collegeMapper.insert(college);
    }

    @Override
    public int deleteCollegeById(Integer collegeId){
        return collegeMapper.deleteById(collegeId);
    }

    @Override
    public int logicToDeleteDeleteCollege(Integer collegeId){
        return collegeMapper.logicToDeleteCollege(collegeId);
    }

    @Override
    public int updateCollegeName(College college){
        return collegeMapper.updateById(college);
    }

    @Override
    public int updateCollegeId(int oldId, int  newId){
        College oldCollege = collegeMapper.selectById(oldId);
        if (null != oldCollege) {
            College college = new College();
            college.setId(newId);
            college.setCollegeName(oldCollege.getCollegeName());
            college.setCreateTime(oldCollege.getCreateTime());
            collegeMapper.deleteById(oldId);
            return collegeMapper.insert(college);
        }
        return 0;
    }

    @Override
    public List<College> findCollegeList(){
        return collegeMapper.selectList(null);
    }

    @Override
    public Integer findOneCollegeIdDesc() {
        return collegeMapper.findOneCollegeIdDesc();
    }

    @Override
    public College findCollegeById(Integer collegeId) {
        return collegeMapper.selectById(collegeId);
    }

    @Override
    public Page<College> findCollegePage(Integer currentPage, Integer pageSize) {
        Page<College> page = new Page<>(currentPage,pageSize);
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        return collegeMapper.selectPage(page,queryWrapper);
    }
}
