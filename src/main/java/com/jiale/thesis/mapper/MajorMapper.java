package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.Major;
import com.jiale.thesis.entity.vo.MajorInfoResVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MajorMapper extends BaseMapper<Major> {
    IPage<Major> selectPageVo(Page<?> page,Integer state);

    Page<MajorInfoResVO> findMajorInfoResVOPageByCollegeId(Page<MajorInfoResVO> page,Integer collegeId);

//    更新Major的id，需要oldId和newId
    int updateMajorId(Integer oldId,Integer newId,String majorName,Integer collegeId);
}
