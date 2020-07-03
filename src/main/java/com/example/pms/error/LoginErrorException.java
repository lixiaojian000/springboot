package com.example.pms.error;

import org.apache.shiro.authc.AuthenticationException;

public class LoginErrorException extends AuthenticationException {
    public LoginErrorException(String 用户名密码错误, String s) {
    }
}
