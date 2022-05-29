package com.jiale.thesis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.College;
import com.jiale.thesis.entity.Major;
import com.jiale.thesis.entity.vo.MajorInfoResVO;
import com.jiale.thesis.entity.vo.UpdateMajorVO;

import java.util.List;

public interface MajorService {

    int addMajorInfo(Major major);

    int deleteMajorById(Integer majorId);

    int updateMajorInfo(UpdateMajorVO updateMajorVO);

    Major findMajorInfoById(Integer majorId);

    List<Major> findMajorListByCollegeId(Integer CollegeId);

    List<Major> findAllMajorList();

    int addMajorByList(List<Major> majorList);

    Page<MajorInfoResVO> findMajorInfoResVOPageByCollegeId(Integer currentPage,Integer pageSize,Integer collegeId);

}
