package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.UserAccount;
import com.jiale.thesis.entity.vo.LoginUserInfoResVO;
import com.jiale.thesis.mapper.UserAccountMapper;
import com.jiale.thesis.service.UserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    UserAccountMapper userAccountMapper;


    @Override
    public Boolean findUserAccountById(BigInteger userId) {
        if (null!=userAccountMapper.selectById(userId)){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public UserAccount findUserAccountPasswordById(UserAccount userAccount) {
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<UserAccount>();
        queryWrapper.eq("id",userAccount.getId());
        queryWrapper.eq("password",userAccount.getPassword());
        queryWrapper.eq("is_deleted",0);
        return userAccountMapper.selectOne(queryWrapper);
    }

    @Override
    public int addUserAccount(UserAccount userAccount) {
        return userAccountMapper.insert(userAccount);
    }

    @Override
    public int deletedUserAccountLogic(BigInteger userAccountId) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userAccountId);
        userAccount.setIsDeleted(1);
        return userAccountMapper.updateById(userAccount);
    }

    @Override
    public int updateUserAccount(UserAccount userAccount) {
        return userAccountMapper.updateById(userAccount);
    }

    @Override
    public UserAccount findOneUserAccountById(BigInteger userAccountId) {
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<UserAccount>();
        queryWrapper.eq("id",userAccountId);
        queryWrapper.eq("is_deleted",0);
        return userAccountMapper.selectOne(queryWrapper);
    }

    @Override
    public LoginUserInfoResVO findLoginUserInfoResVo(BigInteger userId) {
        return userAccountMapper.findLoginUserInfoResVo(userId);
    }

}
