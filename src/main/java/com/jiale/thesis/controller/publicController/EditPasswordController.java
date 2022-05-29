package com.jiale.thesis.controller.publicController;

import com.jiale.thesis.entity.UserAccount;
import com.jiale.thesis.service.UserAccountService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.Objects;

@Controller
@RequestMapping("/editPassword")
public class EditPasswordController {

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping("/postCheckOldPassword")
    @ResponseBody
    public Result postCheckOldPassword(BigInteger userId,String password){
        Result result = new Result();
        UserAccount userAccount1 = userAccountService.findOneUserAccountById(userId);
        if (Objects.equals(userAccount1.getPassword(), password)){
            result.setResultCode(200);
            result.setMessage("验证成功");
        }else{
            result.setResultCode(500);
            result.setMessage("密码错误");
        }
        return result;
    }

    @RequestMapping("/postUpdatePassword")
    @ResponseBody
    public Result postCheckOldPassword(UserAccount userAccount){
        Result result = new Result();
        int isUpdate = userAccountService.updateUserAccount(userAccount);
        if (isUpdate==1){
            result.setResultCode(200);
            result.setMessage("更改密码成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更改密码失败");
        }
        return result;
    }
}
