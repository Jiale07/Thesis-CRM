package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Mapper
public interface CollegeMapper extends BaseMapper<College> {

//    逻辑删除
    @Update("UPDATE college SET is_deleted = 1 WHERE id = ${collegeId}")
    public int logicToDeleteCollege(Integer collegeId);

    List<College> xmlGetCollegeInfo();

    @Select("SELECT id FROM college ORDER BY id desc LIMIT 1")
    Integer findOneCollegeIdDesc();
}
