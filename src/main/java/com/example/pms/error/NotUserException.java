package com.example.pms.error;

import org.apache.shiro.authc.AuthenticationException;

public class NotUserException extends AuthenticationException {
    public  NotUserException(String 用户名未注册, String s) {

    }
}
