package com.example.pms.error;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//自定义异常处理类
@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(value=Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {
        //此处返回json数据
        Map<String, Object> map = new HashMap<>();
        //捕捉到的异常如果是自定义异常类，那么就返回自定义异常类中的错误码和错误信息
        if(e instanceof LoginErrorException){
            map.put("code",50001);
            map.put("msg","用户名密码错误");
        }if(e instanceof NotUserException){
            map.put("code",50002);
            map.put("msg","用户未注册");
        }
        map.put("url", request.getRequestURL());
        return map;

    }
    @ExceptionHandler(value=NotPremissionException.class)
    public Object notPremissionException(Exception e, HttpServletRequest request) {
        //此处返回json数据
        Map<String, Object> map = new HashMap<>();
        //捕捉到的异常如果是自定义异常类，那么就返回自定义异常类中的错误码和错误信息
            map.put("code",50003);
            map.put("msg","没有登录");
        map.put("url", request.getRequestURL());
        return map;

    }
}
