package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.Major;
import com.jiale.thesis.entity.vo.MajorInfoResVO;
import com.jiale.thesis.entity.vo.UpdateMajorVO;
import com.jiale.thesis.mapper.MajorMapper;
import com.jiale.thesis.service.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Resource
    MajorMapper majorMapper;


    @Override
    public int addMajorInfo(Major major){
        return majorMapper.insert(major);
    }

    @Override
    public int deleteMajorById(Integer collegeId){
        return majorMapper.deleteById(collegeId);
    }

    @Override
    public int updateMajorInfo(UpdateMajorVO updateMajorVO){
        return majorMapper.updateMajorId(
                updateMajorVO.getOldId(),
                updateMajorVO.getId(),
                updateMajorVO.getMajorName(),
                updateMajorVO.getCollegeId()
        );
    }

    @Override
    public Major findMajorInfoById(Integer majorId){
        return majorMapper.selectById(majorId);
    }

    @Override
    public List<Major> findMajorListByCollegeId(Integer collegeId){
        QueryWrapper<Major> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("college_id",collegeId);
        return majorMapper.selectList(queryWrapper);
    }

    @Override
    public List<Major> findAllMajorList(){
        return majorMapper.selectList(null);
    }

    @Override
    public int addMajorByList(List<Major> majorList){
        int returnNumber = 0;
        for(Major major : majorList){
            returnNumber = majorMapper.insert(major);
            if (returnNumber == 0){
                return returnNumber;
            }
        }
        return returnNumber;
    }

    @Override
    public Page<MajorInfoResVO> findMajorInfoResVOPageByCollegeId(Integer currentPage,Integer pageSize,Integer collegeId) {
        Page<MajorInfoResVO> page = new Page<>(currentPage,pageSize);
        return majorMapper.findMajorInfoResVOPageByCollegeId(page,collegeId);
    }


}
