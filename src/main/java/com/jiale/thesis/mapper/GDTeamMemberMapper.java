package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.GD.GDTeamMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GDTeamMemberMapper extends BaseMapper<GDTeamMember> {

    List<GDTeamMember> findTeamMemberListByTeamId(Long gdTeamId);

    GDTeamMember findTeamMemberByTeamId(Long gdTeamId);


}
