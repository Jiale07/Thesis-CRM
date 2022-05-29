package com.jiale.thesis.service;

import com.jiale.thesis.entity.UserAccount;
import com.jiale.thesis.entity.vo.LoginUserInfoResVO;

import java.math.BigInteger;

public interface UserAccountService {

    Boolean findUserAccountById(BigInteger userId);

    UserAccount findUserAccountPasswordById(UserAccount userAccount);

    int addUserAccount(UserAccount userAccount);

    int deletedUserAccountLogic(BigInteger userAccountId);

    int updateUserAccount(UserAccount userAccount);

    UserAccount findOneUserAccountById(BigInteger userAccountId);

    LoginUserInfoResVO findLoginUserInfoResVo(BigInteger userId);


}
