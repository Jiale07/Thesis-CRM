package com.jiale.thesis.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jiale.thesis.util.InterceptorResult;
import com.jiale.thesis.util.JsonUtil;
import com.jiale.thesis.util.jwt.Decrypt;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {


    public static final String SECRET = "JKKLJOoasdlfj";

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    JsonUtil jsonUtil = new JsonUtil();
    InterceptorResult interceptorResult = new InterceptorResult();

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1,Object arg2, Exception arg3) throws Exception{

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
//        System.out.println(arg1.getHeader("token"));
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,Object arg2) throws Exception{
        String headerToken = arg0.getHeader("token");
        if (null == headerToken || headerToken.trim().equals("")){
            interceptorResult.interceptorResult(arg1,"无token，请重新登录",500);
            return false;
        }
        try{
            Decrypt decrypt = new Decrypt();
//            DecodedJWT jwt = decrypt.deToken(headerToken);
            DecodedJWT jwt = null;
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("jiale")).withIssuer("auth0").build();
            jwt = verifier.verify(headerToken);

        }catch (TokenExpiredException exception){
            exception.printStackTrace();
            interceptorResult.interceptorResult(arg1,"登录超时，请重新登录",401);
            return false;
        }catch (Exception e){
            e.printStackTrace();
            interceptorResult.interceptorResult(arg1,"登录错误，请重新登录",500);
            return false;
        }
        return true;
    }

}
