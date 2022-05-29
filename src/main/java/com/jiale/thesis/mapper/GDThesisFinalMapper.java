package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.GD.GDThesisFinal;
import com.jiale.thesis.entity.GD.vo.ThesisFinalSubmitTheRecordVO;
import com.jiale.thesis.entity.GD.vo.ThesisTableViewVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface GDThesisFinalMapper extends BaseMapper<GDThesisFinal> {

    List<ThesisFinalSubmitTheRecordVO> findThesisFinalSubmitTheRecordVOByStudentId(BigInteger studentId);

    List<ThesisTableViewVO> findThesisTableViewVOByGDTeam(Long GDTeamId);
}
