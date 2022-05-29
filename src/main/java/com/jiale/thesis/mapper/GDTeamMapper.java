package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.GD.GDTeam;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface GDTeamMapper extends BaseMapper<GDTeam> {

    GDTeam findGDTeamByTeacherId(BigInteger teacherId);
}
