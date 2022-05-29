package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.AuditStatusCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuditStatusCategoryMapper extends BaseMapper<AuditStatusCategory> {

    int  findAuditStatusCategory(Long id);
}
