package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.GD.GDThesisInput;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface GDThesisInputMapper extends BaseMapper<GDThesisInput> {

    List<GDThesisInput> findGDThesisInputList(BigInteger studentId);

    List<GDThesisInput> findGDThesisInputListByGDThesisId(Long gdThesisId);


}
