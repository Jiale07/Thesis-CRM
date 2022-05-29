package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.entity.vo.InstructorInfoVO;
import com.jiale.thesis.mapper.GDTopicSelectionMapper;
import com.jiale.thesis.mapper.SysUserRoleMapper;
import com.jiale.thesis.mapper.TeacherInfoMapper;
import com.jiale.thesis.mapper.TeacherResumeMapper;
import com.jiale.thesis.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    TeacherInfoMapper teacherInfoMapper;


    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Resource
    TeacherResumeMapper teacherResumeMapper;

    @Resource
    GDTopicSelectionMapper gdTopicSelectionMapper;


    @Override
    public TeacherInfo findTeacherInfoById(BigInteger id){
        return teacherInfoMapper.selectById(id);
    }




    @Override
    public Page<InstructorInfoVO> findAllInstructor(Page<InstructorInfoVO> page,Integer roleId, Integer collegeId,BigInteger topicCategoryId) {
        Page<InstructorInfoVO> instructorInfoVOPage = teacherInfoMapper.findAllInstructorInfo(page,roleId,collegeId,topicCategoryId);
        //查询结合的total小于零，则直接跳过以下步骤
        if (instructorInfoVOPage.getTotal()>0){
            int instructorCount = instructorInfoVOPage.getRecords().size();
            for (int i = 0;i<instructorCount;i++){
                int topicCount  = instructorInfoVOPage.getRecords().get(i).getGdTopicInfoVoList().size();
                for (int j=0;j<topicCount;j++){
                    Long topicId = instructorInfoVOPage.getRecords().get(i).getGdTopicInfoVoList().get(j).getId();
                    QueryWrapper<com.jiale.thesis.entity.GD.GDTopicSelection> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("topic_id",BigInteger.valueOf(topicId));
                    queryWrapper.eq("is_deleted",0);
                    instructorInfoVOPage.getRecords().get(i).getGdTopicInfoVoList().get(j).setSelectedCount((gdTopicSelectionMapper.selectCount(queryWrapper)));
                }
            }
        }
        return instructorInfoVOPage;
    }

}
