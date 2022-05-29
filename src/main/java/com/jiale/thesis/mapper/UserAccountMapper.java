package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.UserAccount;
import com.jiale.thesis.entity.vo.LoginUserInfoResVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    LoginUserInfoResVO findLoginUserInfoResVo(BigInteger userId);

}
