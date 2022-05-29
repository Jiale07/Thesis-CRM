package com.jiale.thesis.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class InterceptorResult {


    public void interceptorResult(HttpServletResponse response,String message,int resultCode) throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream resultWriter = response.getOutputStream();
        Result result = new Result();

        result.setMessage(message);
        result.setResultCode(resultCode);
        resultWriter.write(JsonUtil.objToJson(result).getBytes());
        resultWriter.flush();
        resultWriter.close();
    }
}
