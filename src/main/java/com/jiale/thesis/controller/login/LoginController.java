package com.jiale.thesis.controller.login;

import com.jiale.thesis.entity.*;
import com.jiale.thesis.entity.vo.*;
import com.jiale.thesis.service.*;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserAccountService userAccountService;

    /*
    1.检验userId和userPassword是否真确
    2.1 【1】成功，则通过userId检验role类被
    2.2 【1】失败，则返回“账号或密码错误，请重新登录”
    3.1 获取用户信息，包含xxxInfo、sysUserRole
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public Result<LoginUserInfoResVO> usersLogin(UserAccount user){
        System.out.println(user.getId());
        Result<LoginUserInfoResVO> result = new Result<>();
        UserAccount userAccount = userAccountService.findUserAccountPasswordById(user);
        if (null==userAccount){
            result.setResultCode(500);
            result.setMessage("账号或密码错误，请重新登录");
        }else{
            BigInteger userId = userAccount.getId();
            System.out.println("[userId]"+userId);
            String token = tokenService.getToken(userId);
            result.setData(userAccountService.findLoginUserInfoResVo(userId));
            result.setToken(token);
            result.setMessage("登录成功");
            result.setResultCode(200);
        }
        return result;
    }

//    @RequestMapping("/updateToken")
//    @ResponseBody
//    public Result updateToken(@RequestBody String token){
//        Result result = new Result();
//        System.out.println("[Token]:"+token);
//        try{
//            Decrypt decrypt = new Decrypt();
//            DecodedJWT jwt = null;
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("jiale")).withIssuer("auth0").build();
//            jwt = verifier.verify(token);
//            Date dateToken = new Date(jwt.getExpiresAt().toString());
//            Date dateNew = new Date();
//
//            // 1 dateToken>dateNew
//            // 0 dateToken=dateNew
//            // -1 dateToken<dateNew
//            BigInteger userId = BigInteger.valueOf(Long.parseLong(jwt.getClaim("userId").asString()));
//            String tokenNew = null;
//            Users users = new Users();
//            if(dateNew.compareTo(dateToken) <= 0){
//                switch (userId.toString().length()){
//                    case 12:{
//                        StudentAccount studentAccount = studentService.findStudentAccount(userId);
//                        users.setId(studentAccount.getId());
//                        users.setRoleId(studentAccount.getRoleId());
//                        break;
//                    }
//                    case 9:{
//                        TeacherAccount teacherAccount = teacherService.findTeacherAccount(userId);
//                        users.setId(teacherAccount.getId());
//                        users.setRoleId(teacherAccount.getRoleId());
//                        break;
//                    }
//                    case 5: {
//                        AdminInfo admin = adminInfoService.findAdminAccount(userId);
//                        users.setId(admin.getId());
//                        users.setRoleId(admin.getRoleId());
//                        break;
//                    }
//                    default: {
//                        result.setResultCode(500);
//                        result.setMessage("账号密码错误");
//                        return result;
//                    }
//                }
//                tokenNew = tokenService.getToken(users);
//                result.setToken(tokenNew);
//            }
//        }catch (TokenExpiredException exception){
//            exception.printStackTrace();
//            result.setMessage("登录超时，请重新登录");
//            result.setResultCode(401);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setMessage("登录信息有误，请重新登录");
//            result.setResultCode(500);
//        }
//        result.setMessage("newToken");
//        result.setResultCode(200);
//        return result;
//    }
}
